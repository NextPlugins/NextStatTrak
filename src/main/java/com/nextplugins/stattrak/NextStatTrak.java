package com.nextplugins.stattrak;

import com.nextplugins.stattrak.configuration.ConfigurationManager;
import com.nextplugins.stattrak.manager.RanksManager;
import lombok.Getter;
import me.bristermitten.pdm.PluginDependencyManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextStatTrak extends JavaPlugin {

    @Getter private final RanksManager ranksManager = new RanksManager();
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

}
