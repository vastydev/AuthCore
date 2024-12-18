package dev.club.aquatic.listeners;

import dev.club.aquatic.AuthCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * @author Vasty
 * @date 18/12/2024 @ 06:15
 * @url https://github.com/vastydev
 */

public class BlockListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        boolean blockWeatherEnabled = AuthCore.SetConfig().getBoolean("events.block-weather");

        if (blockWeatherEnabled && event.toWeatherState()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        boolean blockBreakEnabled = AuthCore.SetConfig().getBoolean("events.block-break");

        if (blockBreakEnabled) {
            event.setCancelled(true);
        }
    }
}
