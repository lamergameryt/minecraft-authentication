/*
 * Copyright (c) 2020 Harsh Patil
 * All rights reserved.
 */

package me.lamergameryt.ServerAuth;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ServerAuthMain extends JavaPlugin {
    private File configfile;
    private FileConfiguration config;

    private static ServerAuthMain instance;
    public static ServerAuthMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        setupMainConfig();
        getServer().getPluginManager().registerEvents(new Events(), this);
        getCommand("serverauth").setExecutor(new ServerAuthCommand());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Disabling Server Authentication v" + getDescription().getVersion());
    }

    /**
     * Returns a String which contains the hash value of the input bytes array.
     *
     * @param inputbytes Bytes of the string to hash.
     * @param algo The algorithm used to hash the string
     * @param p The player associated with the hash
     * @return The hashed value of the input.
     */
    public static String getHash(byte[] inputbytes, String algo, Player p) {
        String hashValue = "";

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algo);
            messageDigest.update(inputbytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes);
        } catch (NoSuchAlgorithmException e) {
            p.sendMessage(ChatColor.RED + "Encryption failed, try again!");
        }
        return hashValue;
    }

    private void setupMainConfig() {
        configfile = new File(getDataFolder(), "config.yml");
        if (!configfile.exists()) {
            try {
                configfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(configfile);
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configfile);
    }

    @Override
    public FileConfiguration getConfig() {
        return config;
    }

    @Override
    public void saveConfig() {
        try {
            config.save(configfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
