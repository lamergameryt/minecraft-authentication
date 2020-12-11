/*
 * Copyright (c) 2020 Harsh Patil
 * All rights reserved.
 */

package me.lamergameryt.ServerAuth.SubCommands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ResetCommand implements SubCommand {
    @Override
    public void onCommand(Player p, String[] args) {
        if (!p.hasPermission("serverauth.resetcommand")) {
            return;
        }

        if (args.length != 2) {
            p.sendMessage(ChatColor.RED + "Invalid usage, use the following command: " + ChatColor.GOLD + getName());
            return;
        }

        URL url;
        try {
            url = new URL("https://api.mojang.com/users/profiles/minecraft/" + args[1]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }

        Scanner s;
        try {
            s = new Scanner(url.openStream());
        } catch (IOException e) {
            p.sendMessage(ChatColor.RED + "Failed to connect with Mojang Servers, run server in online mode!");
            return;
        }

        StringBuilder response = new StringBuilder();
        while (s.hasNext()) {
            response.append(s.next());
        }

        if (response.toString().equals("")) {
            p.sendMessage(ChatColor.RED + "The player name you have entered is invalid!");
            return;
        }

        String tuuids = response.substring(7, 39);
        System.out.println(tuuids);
    }

    @Override
    public String getName() {
        return "/serverauth reset <player>";
    }

    @Override
    public String getDescription() {
        return "Command to reset a player's password";
    }
}
