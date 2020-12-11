/*
 * Copyright (c) 2020 Harsh Patil
 * All rights reserved.
 */

package me.lamergameryt.ServerAuth.SubCommands;

import me.lamergameryt.ServerAuth.ServerAuthMain;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class ReloadConfigCommand implements SubCommand {
    private ServerAuthMain plugin = ServerAuthMain.getInstance();

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("serverauth.reloadcommand")) {
            plugin.reloadConfig();
            p.sendMessage(ChatColor.GREEN + "Successfully reloaded the config!");
        } else {
            p.sendMessage(ChatColor.RED + "You cannot execute this command.");
        }
    }

    @Override
    public String getName() {
        return "/serverauth reloadconfig | /serverauth rc";
    }

    @Override
    public String getDescription() {
        return "Reloads the config";
    }
}
