package com.nextplugins.stattrak.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public final class PlayerKillListener implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Player whoDeath = event.getEntity();
        Player killer = whoDeath.getKiller();

        if (killer == null) return;

        final ItemStack itemInHand = killer.getItemInHand();

        // todo
    }

}
