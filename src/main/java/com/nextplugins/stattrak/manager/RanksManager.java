package com.nextplugins.stattrak.manager;

import com.google.common.collect.Maps;
import com.nextplugins.stattrak.api.rank.StatTrak;
import com.nextplugins.stattrak.parser.StatTrakParser;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Map;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@Getter
public class RanksManager {

    protected static final StatTrakParser parser = new StatTrakParser();

    private final Map<Integer, StatTrak> ranks = Maps.newHashMap();

    public void loadRanks(FileConfiguration file) {
        parser.parseListSection(file.getConfigurationSection("ranks")).forEach(this::register);
    }

    private void register(StatTrak rank) {
        ranks.put(rank.getMinKills(), rank);
    }

}
