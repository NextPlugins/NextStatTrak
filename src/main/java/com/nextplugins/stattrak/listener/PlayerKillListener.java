package com.nextplugins.stattrak.listener;

import com.nextplugins.stattrak.manager.StatTrakManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public final class PlayerKillListener implements Listener {

    private final StatTrakManager statTrakManager;

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Player whoDeath = event.getEntity();
        Player killer = whoDeath.getKiller();

        if (killer == null) return;

        final ItemStack itemInHand = killer.getItemInHand();

        if (statTrakManager.getPermittedItems().contains(itemInHand.getType())) {
            statTrakManager.incrementKill(itemInHand);
        }
    }

}
