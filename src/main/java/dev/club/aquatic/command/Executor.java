package dev.club.aquatic.command;

import dev.club.aquatic.AuthCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author Vasty
 * @date 18/12/2024 @ 05:16
 * @url https://github.com/vastydev
 */
public class Executor implements CommandExecutor {

    private final AuthCore plugin;

    public Executor(AuthCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("authcore.admin")) {
            sender.sendMessage("§c[AuthCore] You do not have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§b[AuthCore] Use: /authcore <settings|events|reload>");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            AuthCore.SetConfig().reload();
            sender.sendMessage("§a[AuthCore] Configuration has been reloaded.");
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage("§3[AuthCore] Use: /authcore <settings|events> <key> <true|false>");
            return true;
        }

        String category = args[0].toLowerCase();
        String key = args[1].toLowerCase();
        String value = args[2].toLowerCase();

        if (!value.equals("true") && !value.equals("false")) {
            sender.sendMessage("§c[AuthCore] The value must be 'true' or 'false'.");
            return true;
        }

        boolean booleanValue = Boolean.parseBoolean(value);
        FileConfiguration config = AuthCore.SetConfig();

        if (category.equals("settings") || category.equals("events")) {
            String path = category + "." + key;
            if (!config.contains(path)) {
                sender.sendMessage("§c[AuthCore] The key '" + key + "' does not exist in " + category + ".");
                return true;
            }

            config.set(path, booleanValue);
            plugin.saveConfig();
            plugin.reloadConfig();
            sender.sendMessage("§a[AuthCore] The key '" + key + "' in " + category + " has been updated to " + value + ".");
        } else {
            sender.sendMessage("§c[AuthCore] Unknown category. Use 'settings' or 'events'.");
        }

        return true;
    }
}