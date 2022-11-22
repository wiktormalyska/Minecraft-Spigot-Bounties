package bettermobs.bounties.data;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class data {
    public static Map<String, List<ItemStack>> get_uandi_from_data(Plugin plugin, CommandSender sender, String prefix){
        Map<String,List<ItemStack>> data = new HashMap<>();
        List<String> users_to_get_items = new ArrayList<>();
        users_to_get_items.addAll(plugin.getConfig().getConfigurationSection("bounties.data.").getKeys(false));
        for(String user : users_to_get_items){
            List<String> items_id = new ArrayList<>();
            items_id.addAll(plugin.getConfig().getConfigurationSection("bounties.data."+user).getKeys(false));
            List<ItemStack> items = new ArrayList<>();
            for(String item : items_id){
                String item_name = plugin.getConfig().getString("bounties.data."+user+"."+item+".item");
                int item_amount = plugin.getConfig().getInt("bounties.data."+user+"."+item+".amount");
                ItemStack item_stack = new ItemStack(Material.valueOf(item_name), item_amount);
                items.add(item_stack);
            }
            data.put(user, items);
        }
        return data;
    }
}
