/*
 * Copyright (c) 2020 Harsh Patil
 * All rights reserved.
 */

package me.lamergameryt.ServerAuth.SubCommands;

import me.lamergameryt.ServerAuth.ServerAuthMain;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class RegisterCommand implements SubCommand {
    private ServerAuthMain plugin = ServerAuthMain.getInstance();

    @Override
    public void onCommand(Player p, String[] args) {
        if (plugin.getConfig().getBoolean("players." + p.getUniqueId() + ".registered")) {
            p.sendMessage(ChatColor.RED  + "You are already registered, to change your password do: " + ChatColor.GOLD + new ChangePasswordCommand().getName());
            return;
        }

        if (args.length < 3) {
            p.sendMessage(ChatColor.RED + "Invalid usage, correct way to use: " + ChatColor.GOLD + getName());
            return;
        }

        String pass = args[1];
        String conpass = args[2];

        if (!pass.equals(conpass)) {
            p.sendMessage(ChatColor.RED + "Passwords don't match!!");
            return;
        }

        String encodedpass = ServerAuthMain.getHash(pass.getBytes(), "SHA-512", p);

        plugin.getConfig().set("players." + p.getUniqueId() + ".id", encodedpass);
        plugin.getConfig().set("players." + p.getUniqueId() + ".registered", true);
        plugin.getConfig().set("players." + p.getUniqueId() + ".loggedin", true);
        plugin.saveConfig();

        p.sendMessage(ChatColor.GREEN + "Successfully registered!");
    }

    @Override
    public String getName() {
        return "/serverauth register <password> <confirm-password>";
    }

    @Override
    public String getDescription() {
        return "Command to register you to the server!";
    }
}
