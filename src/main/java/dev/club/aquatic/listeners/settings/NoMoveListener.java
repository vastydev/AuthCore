package dev.club.aquatic.listeners.settings;

import dev.club.aquatic.AuthCore;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author Vasty
 * @date 18/12/2024 @ 05:21
 * @url https://github.com/vastydev
 */

public class NoMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        boolean noMoveEnabled = AuthCore.SetConfig().getBoolean("settings.no-move");

        if (noMoveEnabled) {
            Player player = event.getPlayer();
            Location targetLocation = new Location(player.getWorld(), 0.5, 10.0, 0.5, 0.0960753f, -0.6451049f);

            Location from = event.getFrom();
            Location to = event.getTo();

            if (to != null && (from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ())) {
                player.teleport(targetLocation);
            }
        }
    }
}
