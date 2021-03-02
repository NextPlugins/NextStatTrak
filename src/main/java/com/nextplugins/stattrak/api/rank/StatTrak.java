package com.nextplugins.stattrak.api.rank;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@Data
@Builder
public class StatTrak {

    private final int minKills;
    private final String coloredName;
    private final List<String> prizes;

}
