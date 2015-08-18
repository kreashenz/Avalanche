package com.gmail.xendroidzx.avalanche.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class GameUtils {

    private static List<Game> games = new ArrayList<Game>();

    public static void addGame(Game game) {
        games.add(game);
    }

    public static void removeGame(Game game) {
        games.remove(game);
    }

    public static Game getGame(String name) {
        for (Game g : games) {
            if (g.getName().equalsIgnoreCase(name)) {
                return g;
            }
        }

        return null;
    }

    public static Game getGame(Player p) {
        for (Game g : games) {
            if (g.getPlayers().contains(p.getName())) {
                return g;
            }
        }

        return null;
    }

    public static boolean isPlaying(Player p) {
        return getGame(p) != null;
    }

}
