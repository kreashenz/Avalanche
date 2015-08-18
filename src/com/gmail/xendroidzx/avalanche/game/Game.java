package com.gmail.xendroidzx.avalanche.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.gmail.xendroidzx.avalanche.Main;
import com.gmail.xendroidzx.avalanche.utils.Config;
import com.gmail.xendroidzx.avalanche.utils.Utils;

public class Game {

    private Main plugin = Main.getInstance();

    private String name, winner;
    private List<String> players, out, blocks;

    private Location lobby, bound1, bound2;

    private File file;
    private Config config;

    public Game(String name) {
        this.name = name;

        players = new ArrayList<String>();
        out = new ArrayList<String>();

        load();
    }

    private void load() {
        file = new File(plugin.getDataFolder() + File.separator + "gamedata", name.toLowerCase() + ".yml");
        config = new Config(file);

        if (config.contains("boundary-1")) {
            bound1 = Utils.stringToLoc(config.getString("boundary-1"), false);
        }
        if (config.contains("boundary-2")) {
            bound2 = Utils.stringToLoc(config.getString("boundary-2"), false);
        }
        if (config.contains("lobby")) {
            lobby = Utils.stringToLoc(config.getString("lobby"), true);
        }

        if (bound1 != null && bound2 != null) {
            for (int x = Math.min(bound1.getBlockX(), bound2.getBlockX()); x < Math.max(bound1.getBlockX(), bound2.getBlockX()); x++) {
                for (int y = Math.min(bound1.getBlockY(), bound2.getBlockY()); y < Math.max(bound1.getBlockY(), bound2.getBlockY()); y++) {
                    for (int z = Math.min(bound1.getBlockZ(), bound2.getBlockZ()); z < Math.max(bound1.getBlockZ(), bound2.getBlockZ()); z++) {
                        Block b = bound1.getWorld().getBlockAt(x, y, z);
                        if (b.getType() != Material.AIR || b != null) {
                            blocks.add(Utils.locToString(b.getLocation(), false));
                        }
                    }
                }
            }
        }
    }

    public void start() {
        for (String s : players) {
            Player p = Bukkit.getPlayer(s);

            p.teleport(Utils.stringToLoc(blocks.get(new Random().nextInt(blocks.size())), false));
        }
        broadcast("§7New §6avalanche §7game starting §6now§7!");
    }

    public void end() {
        for (String s : players) {
            Player p = Bukkit.getPlayer(s);
            p.teleport(lobby);
        }
        broadcast("§6Avalanche §7game has §6finished§7! The winner was: §6" + winner + "§7!");
    }

    public void addPlayer(Player p) {
        if (players.contains(p.getName())) {
            return;
        }

        players.add(p.getName());
    }

    public void removePlayer(Player p) {
        if (!players.contains(p.getName())) {
            return;
        }
        if (out.contains(p.getName())) {
            out.remove(p.getName());
        }

        players.remove(p.getName());
    }

    public void setWinner(Player p) {
        winner = p.getName();
    }

    public void gotOut(Player p) {
        out.add(p.getName());
        p.teleport(lobby);
        Utils.tell(p, "§7You were hit!");
        broadcast("§6" + p.getName() + "§7 was hit by the avalanche!");
    }

    public void broadcast(String msg) {
        for (String s : players) {
            Utils.tell(Bukkit.getPlayer(s), msg);
        }
    }

    public String getName() {
        return name;
    }

    public String getWinner() {
        return winner;
    }

    public List<String> getOutList() {
        return out;
    }

    public List<String> getBlocks() {
        return blocks;
    }

    public List<String> getPlayers() {
        return players;
    }

}
