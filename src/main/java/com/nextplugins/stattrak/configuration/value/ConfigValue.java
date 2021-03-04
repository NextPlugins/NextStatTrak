package com.nextplugins.stattrak.configuration.value;

import com.nextplugins.stattrak.NextStatTrak;
import com.nextplugins.stattrak.utils.ColorUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigValue {

    private static final ConfigValue instance = new ConfigValue();

    private final Configuration configuration = NextStatTrak.getInstance().getConfig();

    // configurations

    private final ConfigurationSection itemSection = configuration.getConfigurationSection("plugin.configuration.item");
    private final List<String> permittedItems = itemSection.getStringList("permitted-items");

    // methods

    public static <T> T get(Function<ConfigValue, T> supplier) {
        return supplier.apply(ConfigValue.instance);
    }

    private String colored(String message) {
        return ColorUtils.colored(message);
    }

    private String message(String key) {
        return colored(configuration.getString(key));
    }

    private List<String> messageList(String key) {
        return configuration.getStringList(key)
                .stream()
                .map(this::colored)
                .collect(Collectors.toList());
    }

}
