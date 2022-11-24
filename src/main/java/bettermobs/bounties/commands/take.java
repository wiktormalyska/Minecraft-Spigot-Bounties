package bettermobs.bounties.commands;

import bettermobs.bounties.data.data;
import bettermobs.bounties.debug.debug_set;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class take implements CommandExecutor {
    Plugin plugin;
    String prefix;

    public take(Plugin plugin, String prefix) {
        this.plugin = plugin;
        this.prefix = prefix;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("bounties.take")) {
            Map<String, List<ItemStack>> get_user_and_item = data.get_uandi_from_data(plugin, commandSender, prefix);
            try {
                if (get_user_and_item.containsKey(commandSender.getName())) {
                    for (int i = 0; i < get_user_and_item.get(commandSender.getName()).size(); i++) {
                        plugin.getServer().getPlayer(commandSender.getName()).getInventory().addItem(get_user_and_item.get(commandSender.getName()).get(i));
                    }
                    for(String id : data.get_where_user_is(plugin, commandSender.getName())) {
                        plugin.getConfig().set("bounties.data." + id, null);
                    }
                } else {
                    commandSender.sendMessage("§4§l" + prefix + "§r§7You don't have any bounties to take.");
                }
            } catch (Exception e) {
                commandSender.sendMessage("§4§l" + prefix + "§r§7You don't have any bounties to take.");
            }
            } else{
                commandSender.sendMessage("§4§l" + prefix + "§r§7You don't have permission to do this!");
            }
        plugin.saveConfig();
        return true;
    }
}
