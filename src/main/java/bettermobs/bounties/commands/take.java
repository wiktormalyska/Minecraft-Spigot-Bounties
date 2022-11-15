package bettermobs.bounties.commands;
import bettermobs.bounties.Bounties;
import org.bukkit.plugin.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class take implements CommandExecutor {
    public take(Plugin plug) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        return false;
    }
    public static Map<String, ItemStack> get_uandi_from_data(Plugin plugin, CommandSender sender){
        Map<String,ItemStack> data = new HashMap<>();
        String data_path = "bounties.data";
        String[] items = plugin.getConfig().getConfigurationSection(data_path).getKeys(false).toArray(new String[0]);
        for(int i=0;i<items.length;i++){

        }
        return data;
    }
}
