package com.nextplugins.stattrak;

import com.nextplugins.stattrak.configuration.ConfigurationManager;
import com.nextplugins.stattrak.listener.PlayerKillListener;
import com.nextplugins.stattrak.manager.RanksManager;
import com.nextplugins.stattrak.manager.StatTrakManager;
import lombok.Getter;
import me.bristermitten.pdm.PluginDependencyManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextStatTrak extends JavaPlugin {

    /**
     * Metrics plugin id (used for statistics)
     */
    private static final int PLUGIN_ID = 10536;

    @Getter private final RanksManager ranksManager = new RanksManager();
    @Getter private final StatTrakManager statTrakManager = new StatTrakManager();

    private FileConfiguration ranksConfig;

    @Override
    public void onLoad() {

        saveDefaultConfig();
        ranksConfig = ConfigurationManager.of("ranks.yml").saveDefault().load();

    }

    @Override
    public void onEnable() {
        PluginDependencyManager.of(this).loadAllDependencies().thenRun(() -> {
            try {

                ranksManager.loadRanks(this.ranksConfig);
                statTrakManager.init();

                configureBStats();

                Bukkit.getPluginManager().registerEvents(new PlayerKillListener(statTrakManager), this);

                getLogger().info("Plugin inicializado com sucesso!");

            } catch (Throwable t) {
                t.printStackTrace();
                getLogger().severe("Ocorreu um erro durante a inicialização do plugin!");
                Bukkit.getPluginManager().disablePlugin(this);
            }
        });
    }

    public static NextStatTrak getInstance() {
        return getPlugin(NextStatTrak.class);
    }

    private void configureBStats() {

        Metrics metrics = new Metrics(this, PLUGIN_ID);
        metrics.addCustomChart(new Metrics.SingleLineChart("total_ranks_registered",
                () -> this.ranksManager.getRanks().size())
        );

        this.getLogger().info("Enabled bStats successfully, statistics enabled");

    }

}
