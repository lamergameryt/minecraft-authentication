/*
 * Copyright (c) 2020 Harsh Patil
 * All rights reserved.
 */

package me.lamergameryt.ServerAuth;

import me.lamergameryt.ServerAuth.SubCommands.LoginCommand;
import me.lamergameryt.ServerAuth.SubCommands.RegisterCommand;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
    private ServerAuthMain plugin = ServerAuthMain.getInstance();

    /**
     * Require the user to authenticate before allowing him into the server.
     *
     * @param e The PlayerJoinEvent associated with the player who invoked the event.
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        // If the user isn't present in the configuration file then create a new entry.
        if (!plugin.getConfig().contains("players." + e.getPlayer().getUniqueId())) {
            plugin.getConfig().set("players." + e.getPlayer().getUniqueId() + ".id", "");
            plugin.getConfig().set("players." + e.getPlayer().getUniqueId() + ".registered", false);
            plugin.getConfig().set("players." + e.getPlayer().getUniqueId() + ".loggedin", false);
        }

        // If the user is not previously registered, then ask him to register.
        if (!plugin.getConfig().getBoolean("players." + e.getPlayer().getUniqueId() + ".registered")) {
            e.getPlayer().sendMessage(ChatColor.GREEN + "Welcome, " + ChatColor.GOLD + e.getPlayer().getName() + ChatColor.GREEN + " before playing, you have to register to the server! Use the following command: " + ChatColor.YELLOW + new RegisterCommand().getName());
        } else {
            e.getPlayer().sendMessage(ChatColor.GREEN + "Welcome, " + ChatColor.GOLD + e.getPlayer().getName() + ChatColor.GREEN + " before playing, you have to log in to the server! Use the following command: " + ChatColor.YELLOW + new LoginCommand().getName());
        }
    }

    /**
     * Log out the user after the user leaves the server.
     *
     * @param e The PlayerQuitEvent associated with the player who invoked the event.
     */
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        plugin.getConfig().set("players." + e.getPlayer().getUniqueId() + ".loggedin", false);
        plugin.saveConfig();
    }

    /**
     * Teleport the user back to his spawn point if the he isn't registered / logged in.
     *
     * @param move The PlayerMoveEvent associated with the player who invoked the event.
     */
    @EventHandler
    public void onMove(PlayerMoveEvent move) {
        if (!plugin.getConfig().getBoolean("players." + move.getPlayer().getUniqueId() + ".registered")) {
            teleportBack(move);
        }

        if (!plugin.getConfig().getBoolean("players." + move.getPlayer().getUniqueId() + ".loggedin")) {
            teleportBack(move);
        }

    }

    /**
     * Teleport the user back to his original position if he tries to move.
     *
     * @param move The PlayerMoveEvent associated with the player who invoked the event.
     */
    private void teleportBack(PlayerMoveEvent move) {
        Location from = move.getFrom();
        Location to = move.getTo();
        double x = Math.floor(from.getX());
        double z = Math.floor(from.getZ());
        if (Math.floor(to.getX()) != x || Math.floor(to.getZ()) != z) {
            x += .5;
            z += .5;
            move.getPlayer().teleport(new Location(from.getWorld(), x, from.getY(), z, from.getYaw(), from.getPitch()));
        }
    }
}

