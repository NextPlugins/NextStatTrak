package com.nextplugins.stattrak;

import me.bristermitten.pdm.PluginDependencyManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextStatTrak extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginDependencyManager.of(this).loadAllDependencies().thenRun(() -> {
            try {

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
