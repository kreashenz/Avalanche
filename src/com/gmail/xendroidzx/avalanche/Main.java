package com.gmail.xendroidzx.avalanche;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.xendroidzx.avalanche.commands.CmdHandler;

public class Main extends JavaPlugin {

    private static Main ins;

    @Override
    public void onEnable() {
        ins = this;

        getCommand("avalance").setExecutor(new CmdHandler());
    }

    public static Main getInstance() {
        return ins;
    }

}
