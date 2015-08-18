package com.gmail.xendroidzx.avalanche.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String usage, String[] args) {
        if (s instanceof Player) {
            Player p = (Player) s;
            if (args.length == 0) {
                sendHelp(p);
            }
        }
        return true;
    }

    private void sendHelp(Player p) {
        p.sendMessage("");
    }

}
