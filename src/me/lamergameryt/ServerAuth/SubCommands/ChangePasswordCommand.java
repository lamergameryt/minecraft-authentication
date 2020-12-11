/*
 * Copyright (c) 2020 Harsh Patil
 * All rights reserved.
 */

package me.lamergameryt.ServerAuth.SubCommands;

import me.lamergameryt.ServerAuth.ServerAuthMain;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class ChangePasswordCommand implements SubCommand {
    private ServerAuthMain plugin = ServerAuthMain.getInstance();

    @Override
    public void onCommand(Player p, String[] args) {
        if (!plugin.getConfig().getBoolean("players." + p.getUniqueId() + ".registered")) {
            p.sendMessage(ChatColor.RED + "You need to register before logging in, use the following: " + ChatColor.GOLD + new RegisterCommand().getName());
            return;
        }

        if (!plugin.getConfig().getBoolean("players." + p.getUniqueId() + ".loggedin")) {
            p.sendMessage(ChatColor.RED + "Oops, you can't change your password, first log in: " + new LoginCommand().getName());
            return;
        }

        if (args.length != 3) {
            p.sendMessage(ChatColor.RED + "Invalid command, correct format: " + getName());
            return;
        }

        String password = args[1];
        String hashedRealPass = ServerAuthMain.getHash(password.getBytes(), "SHA-512", p);

        if (!plugin.getConfig().getString("players." + p.getUniqueId() + ".id").equals(hashedRealPass)) {
            p.sendMessage(ChatColor.RED + "Passwords do not match, failed to change password!");
            return;
        }

        String changeTo = args[2];
        String hashedChangePass = ServerAuthMain.getHash(changeTo.getBytes(), "SHA-512", p);

        plugin.getConfig().set("players." + p.getUniqueId() + ".id", hashedChangePass);
        plugin.saveConfig();
        p.sendMessage(ChatColor.GREEN + "Successfully changed passwords!");
    }

    @Override
    public String getName() {
        return "/serverauth changepassword <original-password> <confim-password>";
    }

    @Override
    public String getDescription() {
        return "Command to change your password!";
    }
}
