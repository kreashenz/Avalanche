package com.gmail.xendroidzx.avalanche.listeners;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.gmail.xendroidzx.avalanche.game.Game;
import com.gmail.xendroidzx.avalanche.game.GameUtils;

public class evt_EntityDamageByEntity extends Listener<EntityDamageByEntityEvent> {

    // check instance of player, instance of last damager, make player wrapper
    // to allow setting of last damager
    // profit.

    @Override
    @EventHandler
    public void onEvent(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (GameUtils.isPlaying(p)) {
                if (e.getDamager() instanceof Snowball) {
                    Game g = GameUtils.getGame(p);
                    e.setDamage(0D);
                    g.gotOut(p);
                }
                else if (e.getDamager() instanceof Player) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
