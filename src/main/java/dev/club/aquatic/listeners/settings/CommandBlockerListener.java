package dev.club.aquatic.listeners.settings;

import dev.club.aquatic.AuthCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.List;

/**
 * @author Vasty
 * @date 18/12/2024 @ 06:45
 * @url https://github.com/vastydev
 */

public class CommandBlockerListener implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        boolean blockCommands = AuthCore.SetConfig().getBoolean("settings.block-commands");

        if (blockCommands) {
            List<String> allowedCommands = AuthCore.SetConfig().getStringList("commands");
            String command = event.getMessage().split(" ")[0].toLowerCase();

            if (!allowedCommands.stream().anyMatch(command::equalsIgnoreCase)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onCommandSend(PlayerCommandSendEvent event) {
        boolean blockCommands = AuthCore.SetConfig().getBoolean("settings.block-commands");

        if (blockCommands) {
            List<String> allowedCommands = AuthCore.SetConfig().getStringList("commands");
            event.getCommands().removeIf(command ->
                    allowedCommands.stream().noneMatch(allowed -> allowed.equalsIgnoreCase("/" + command)));
        }
    }
}
