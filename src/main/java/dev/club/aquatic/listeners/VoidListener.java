package dev.club.aquatic.listeners;

import dev.club.aquatic.AuthCore;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author Vasty
 * @date 18/12/2024 @ 06:30
 * @url https://github.com/vastydev
 */

public class VoidListener implements Listener {

    @EventHandler
    public void onPlayerFallIntoVoid(PlayerMoveEvent event) {
        boolean voidSpawnEnabled = AuthCore.SetConfig().getBoolean("events.void-spawn");

        if (voidSpawnEnabled) {
            Player player = event.getPlayer();
            Location to = event.getTo();

            if (to != null && to.getY() <= 0) {
                Location targetLocation = new Location(player.getWorld(), 0.5, 10.0, 0.5, 0.0960753f, -0.6451049f);
                player.teleport(targetLocation);
            }
        }
    }
}
