package com.nextplugins.stattrak.manager;

import com.nextplugins.stattrak.configuration.value.ConfigValue;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class StatTrakManager {

    @Getter private List<String> permittedItems = new ArrayList<>();

    public void init() {
        permittedItems = ConfigValue.get(ConfigValue::permittedItems);
    }

    public int getKills(ItemStack itemStack) {
        NBTItem nbtItem = new NBTItem(itemStack);

        if (nbtItem.getInteger("next.stattrak.kills") == null) return 0;

        return nbtItem.getInteger("next.stattrak.kills");
    }

    public void incrementKill(ItemStack itemStack) {
        NBTItem nbtItem = new NBTItem(itemStack);

        if (nbtItem.getInteger("next.stattrak.kills") == null) {
            nbtItem.setInteger("next.stattrak.kills", 0);
        }

        nbtItem.setInteger("next.stattrak.kills", getKills(itemStack) + 1);

        List<String> lore = itemStack.getItemMeta().getLore() == null
                ? new ArrayList<>()
                : itemStack.getItemMeta().getLore();

        List<String> newLore = new ArrayList<>();

        ConfigurationSection configurationSection = ConfigValue.get(ConfigValue::itemSection);

        if (configurationSection == null) return;

        for (String line : lore) {
            if (line.startsWith(configurationSection.getString("lore"))) {
                line = configurationSection.getString("lore").replace(
                        "$victims", String.valueOf(getKills(itemStack) + 1)
                );
            } else {
                lore.add(configurationSection.getString("lore").replace(
                        "$victims", String.valueOf(getKills(itemStack))
                ));
            }
            newLore.add(line);
        }

        itemStack.getItemMeta().setLore(newLore);
    }

}
