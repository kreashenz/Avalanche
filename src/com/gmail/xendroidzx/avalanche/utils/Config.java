package com.gmail.xendroidzx.avalanche.utils;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

    private File f;
    private YamlConfiguration conf;

    public Config(File f) {
        this.f = f;
        if (!f.exists()) {
            try {
                f.createNewFile();
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
        conf = YamlConfiguration.loadConfiguration(f);
    }

    public void set(String path, Object ob) {
        if (ob == null) {
            return;
        }

        if (ob instanceof Location) {
            Location l = (Location) ob;
            conf.set(path, Utils.locToString(l, true));
        }

        else if (ob instanceof Iterable) {
            for (Object o : ((Iterable<?>) ob)) {
                if (o instanceof Location) {
                    set(path, o);
                }
                else {
                    conf.set(path, o);
                }
            }
        }
        else {
            conf.set(path, ob);
        }

        save();
    }

    public void save() {
        try {
            conf.save(f);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public String getString(String path, String def) {
        return conf.getString(path, def);
    }

    public String getString(String path) {
        return conf.getString(path);
    }

    public int getInt(String path, int def) {
        return conf.getInt(path, def);
    }

    public int getInt(String path) {
        return conf.getInt(path);
    }

    public double getDouble(String path, double def) {
        return conf.getDouble(path, def);
    }

    public double getDouble(String path) {
        return conf.getDouble(path);
    }

    public float getFloat(String path, float def) {
        return (float) conf.getDouble(path, def);
    }

    public float getFloat(String path) {
        return (float) conf.getDouble(path);
    }

    public boolean getBoolean(String path) {
        return conf.getBoolean(path);
    }

    public boolean contains(String path) {
        return conf.contains(path);
    }

    public Location getLocation(String path, boolean yawAndPitch) {
        return Utils.stringToLoc(getString(path), yawAndPitch);
    }

    public File getFile() {
        return f;
    }

    public YamlConfiguration getConfig() {
        return conf;
    }

}
