package dev.club.aquatic.command.tab;

import dev.club.aquatic.AuthCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Vasty
 * @date 18/12/2024 @ 05:56
 * @url https://github.com/vastydev
 */
public class TabCompleterImpl implements TabCompleter {

    private final AuthCore plugin;

    public TabCompleterImpl(AuthCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("authcore.admin")) {
            return null;
        }

        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            suggestions.add("settings");
            suggestions.add("events");
            suggestions.add("reload");
        } else if (args.length == 2) {
            String category = args[0].toLowerCase();
            if (category.equals("settings")) {
                suggestions.addAll(getKeysFromCategory("settings"));
            } else if (category.equals("events")) {
                suggestions.addAll(getKeysFromCategory("events"));
            }
        } else if (args.length == 3 && (args[0].equalsIgnoreCase("settings") || args[0].equalsIgnoreCase("events"))) {
            suggestions.add("true");
            suggestions.add("false");
        }

        String currentArg = args[args.length - 1].toLowerCase();
        suggestions.removeIf(suggestion -> !suggestion.toLowerCase().startsWith(currentArg));

        return suggestions;
    }

    private List<String> getKeysFromCategory(String category) {
        List<String> keys = new ArrayList<>();
        Map<String, Object> values = plugin.getConfig().getConfigurationSection(category).getValues(false);

        for (String key : values.keySet()) {
            keys.add(key);
        }

        return keys;
    }
}
