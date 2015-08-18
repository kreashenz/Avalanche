package com.gmail.xendroidzx.avalanche.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class Utils {

    public static void tell(CommandSender s, String msg) {
        msg = "§7[§6Avalanche§7] " + msg;
        s.sendMessage(s instanceof Player ? format(msg) : unformat(msg));
    }

    public static Location stringToLoc(String loc, boolean yawAndPitch) {
        String[] sp = loc.split(",");
        World w = Bukkit.getWorld(sp[0]);
        double x = Double.valueOf(sp[1]), y = Double.valueOf(sp[2]), z = Double.valueOf(sp[3]);
        if (sp.length == 6) {
            float yaw = Float.valueOf(sp[4]), pitch = Float.valueOf(sp[5]);
            return new Location(w, x, y, z, yaw, pitch);
        }

        return new Location(w, x, y, z);
    }

    public static String locToString(Location loc, boolean yawAndPitch) {
        String s = loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ();
        if (yawAndPitch) {
            s = s + "," + loc.getYaw() + "," + loc.getPitch();
        }

        return s;
    }

    public static String format(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static String unformat(String input) {
        return ChatColor.stripColor(input);
    }

}
