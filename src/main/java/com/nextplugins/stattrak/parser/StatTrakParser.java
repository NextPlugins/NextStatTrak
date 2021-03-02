package com.nextplugins.stattrak.parser;

import com.nextplugins.stattrak.api.rank.StatTrak;
import com.nextplugins.stattrak.utils.ColorUtils;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class StatTrakParser {

    public List<StatTrak> parseListSection(ConfigurationSection section) {

        List<StatTrak> list = new ArrayList<>();
        for (String key : section.getKeys(false)) {
            list.add(parseSection(section.getConfigurationSection(key)));
        }

        return list;

    }

    public StatTrak parseSection(ConfigurationSection section) {

        return StatTrak.builder()
                .minKills(section.getInt("deathsRequired"))
                .coloredName(ColorUtils.colored(section.getString("name")))
                .prizes(section.getStringList("prizes"))
                .build();

    }

}
