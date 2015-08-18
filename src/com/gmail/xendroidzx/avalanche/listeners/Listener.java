package com.gmail.xendroidzx.avalanche.listeners;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;

import com.gmail.xendroidzx.avalanche.Main;
import com.gmail.xendroidzx.avalanche.game.Game;
import com.gmail.xendroidzx.avalanche.game.GameUtils;

public abstract class Listener<T extends Event> implements org.bukkit.event.Listener {

    @EventHandler
    public abstract void onEvent(T e);

    protected Main getMain() {
        return Main.getInstance();
    }

    protected Game getGame(String name) {
        return GameUtils.getGame(name);
    }

}
