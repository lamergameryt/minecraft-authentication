/*
 * Copyright (c) 2020 Harsh Patil
 * All rights reserved.
 */

package me.lamergameryt.ServerAuth.SubCommands;

import org.bukkit.entity.Player;

public interface SubCommand {
    void onCommand(Player p, String[] args);
    String getName();
    String getDescription();
}
