package com.nextplugins.stattrak.configuration;

import com.nextplugins.stattrak.NextStatTrak;
import lombok.Data;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@Data(staticConstructor = "of")
public final class ConfigurationManager {

    private final String config;

    /**
     * Create config
     * @return instance of class
     */
    public ConfigurationManager saveDefault() {

        NextStatTrak instance = NextStatTrak.getInstance();
        instance.saveResource(this.config, false);

        return this;

    }

    /**
     * Get {@link File} by config file name
     *
     * @return {@link File} found in folder by name
     */
    public File getFile() {

        NextStatTrak instance = NextStatTrak.getInstance();
        return new File(instance.getDataFolder(), this.config);

    }

    /**
     * Bukkit configuration of {@link File}
     *
     * @return {@link FileConfiguration} by {@link File}
     */
    public FileConfiguration load() {
        return YamlConfiguration.loadConfiguration(getFile());
    }


}
