package dev.club.aquatic.listeners.settings;

import dev.club.aquatic.AuthCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author Vasty
 * @date 18/12/2024 @ 05:26
 * @url https://github.com/vastydev
 */

public class ChatDisableListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        boolean chatDisableEnabled = AuthCore.SetConfig().getBoolean("settings.chat-disable");

        if (chatDisableEnabled) {
            event.setCancelled(true);
        }
    }
}