package dev.club.aquatic.listeners;

import dev.club.aquatic.AuthCore;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.TimeSkipEvent;

/**
 * @author Vasty
 * @date 18/12/2024 @ 06:00
 * @url https://github.com/vastydev
 */

public class AlwaysDayListener implements Listener {

    @EventHandler
    public void onTimeSkip(TimeSkipEvent event) {
        boolean alwaysDayEnabled = AuthCore.SetConfig().getBoolean("events.always-day");

        if (alwaysDayEnabled && event.getSkipReason() == TimeSkipEvent.SkipReason.NIGHT_SKIP) {
            event.setCancelled(true);
        }
    }

    public void enforceDay() {
        boolean alwaysDayEnabled = AuthCore.SetConfig().getBoolean("events.always-day");

        if (alwaysDayEnabled) {
            for (World world : Bukkit.getWorlds()) {
                world.setTime(1000);
            }
        }
    }
}
