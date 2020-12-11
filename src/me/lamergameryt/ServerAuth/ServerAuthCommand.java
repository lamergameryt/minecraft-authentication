/*
 * Copyright (c) 2020 Harsh Patil
 * All rights reserved.
 */

package me.lamergameryt.ServerAuth;

import me.lamergameryt.ServerAuth.SubCommands.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerAuthCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED +"Only player's are allowed to use this command.");
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(ChatColor.LIGHT_PURPLE + "Here are the following commands you can use: ");
            p.sendMessage(ChatColor.AQUA + "1.)" + ChatColor.GOLD + new RegisterCommand().getName() + ChatColor.WHITE + ": " + ChatColor.YELLOW + new RegisterCommand().getDescription());
            p.sendMessage(ChatColor.AQUA + "2.)" + ChatColor.GOLD + new LoginCommand().getName() + ChatColor.WHITE + ": " + ChatColor.YELLOW + new LoginCommand().getDescription());
            p.sendMessage(ChatColor.AQUA + "3.)" + ChatColor.GOLD + new ReloadConfigCommand().getName() + ChatColor.WHITE + ": " + ChatColor.YELLOW + new ReloadConfigCommand().getDescription());
            p.sendMessage(ChatColor.AQUA + "4.)" + ChatColor.GOLD + new ChangePasswordCommand().getName() + ChatColor.WHITE + ": " + ChatColor.YELLOW + new ChangePasswordCommand().getDescription());
            return true;
        }

        if (args[0].equalsIgnoreCase("register")) {
            new RegisterCommand().onCommand(p, args);
            return true;
        }

        if (args[0].equalsIgnoreCase("login")) {
            new LoginCommand().onCommand(p, args);
            return true;
        }

        if (args[0].equalsIgnoreCase("reloadconfig") || args[0].equalsIgnoreCase("rc")) {
            new ReloadConfigCommand().onCommand(p, args);
            return true;
        }

        if (args[0].equalsIgnoreCase("changepassword") || args[0].equalsIgnoreCase("cp")) {
            new ChangePasswordCommand().onCommand(p, args);
            return true;
        }

        if (args[0].equalsIgnoreCase("reset")) {
            new ResetCommand().onCommand(p, args);
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            helpMessages(p);
            return true;
        }

        helpMessages(p);
        return true;
    }

    /**
     * Provide the user with a help message if he executes the help subcommand or if the entered subcommand is not found.
     *
     * @param p The player who invoked the command.
     */
    private void helpMessages(Player p) {
        p.sendMessage(ChatColor.RED + "Please use the following subcommands: ");
        p.sendMessage(ChatColor.AQUA + "1.)" + ChatColor.GOLD + "/serverauth help" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Show help for all the commands!");
        p.sendMessage(ChatColor.AQUA + "2.)" + ChatColor.GOLD + new RegisterCommand().getName() + ChatColor.WHITE + ": " + ChatColor.YELLOW + new RegisterCommand().getDescription());
        p.sendMessage(ChatColor.AQUA + "3.)" + ChatColor.GOLD + new LoginCommand().getName() + ChatColor.WHITE + ": " + ChatColor.YELLOW + new LoginCommand().getDescription());
        p.sendMessage(ChatColor.AQUA + "4.)" + ChatColor.GOLD + new ReloadConfigCommand().getName() + ChatColor.WHITE + ": " + ChatColor.YELLOW + new ReloadConfigCommand().getDescription());
        p.sendMessage(ChatColor.AQUA + "5.)" + ChatColor.GOLD + new ChangePasswordCommand().getName() + ChatColor.WHITE + ": " + ChatColor.YELLOW + new ChangePasswordCommand().getDescription());
    }
}
