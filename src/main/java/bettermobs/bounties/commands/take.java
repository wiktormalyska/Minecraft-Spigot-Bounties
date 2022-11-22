package bettermobs.bounties.commands;

import bettermobs.bounties.data.data;
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
        if (commandSender.hasPermission("bounties.take")){
            Map<String, List<ItemStack>> get_user_and_item = data.get_uandi_from_data(plugin, commandSender, prefix);
            if (get_user_and_item.get(commandSender.getName()).contains(commandSender.getName())){
                for (int i = 0; i < get_user_and_item.size(); i++) {
                    plugin.getServer().getPlayer(commandSender.getName()).getInventory().addItem(get_user_and_item.get(commandSender.getName()).get(i));
                }
                plugin.getConfig().set("bounties.data."+commandSender.getName(), null);
            }else {
                commandSender.sendMessage("§4§l" + prefix + "§r§7You don't have any bounties to take!");
            }
        } else{
            commandSender.sendMessage("§4§l" + prefix + "§r§7You don't have permission to do this!");
        }
        return false;
    }
}
