/*
 * Copyright (c) 2020 Harsh Patil
 * All rights reserved.
 */

package me.lamergameryt.ServerAuth.SubCommands;

import me.lamergameryt.ServerAuth.ServerAuthMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LoginCommand implements SubCommand {
    private ServerAuthMain plugin = ServerAuthMain.getInstance();

    @Override
    public void onCommand(Player p, String[] args) {
        if (!plugin.getConfig().getBoolean("players." + p.getUniqueId() + ".registered")) {
            p.sendMessage(ChatColor.RED + "You have to register before logging in");
            return;
        }

        if (args.length < 2) {
            p.sendMessage(ChatColor.RED + "Invalid command usage, proper usage: " + ChatColor.RED + getName());
            return;
        }

        String pass = ServerAuthMain.getHash(args[1].getBytes(), "SHA-512", p);

        if (!plugin.getConfig().getString("players." + p.getUniqueId() + ".id").equals(pass)) {
            p.sendMessage(ChatColor.RED + "Passwords do not match, try again!");
            return;
        }

        p.sendMessage(ChatColor.GREEN + "Successfully logged in!");
        plugin.getConfig().set("players." + p.getUniqueId() + ".loggedin", true);
        plugin.saveConfig();
    }

    @Override
    public String getName() {
        return "/serverauth login <password>";
    }

    @Override
    public String getDescription() {
        return "Command to log you in to the server!";
    }
}
