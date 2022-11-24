package bettermobs.bounties.data;

import bettermobs.bounties.commands.take;
import bettermobs.bounties.debug.debug_set;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class data {
    public static Map<String, List<ItemStack>> get_uandi_from_data(Plugin plugin, CommandSender sender, String prefix){
        Map<String,List<ItemStack>> data = new HashMap<>();
        debug_set debuger = new debug_set();
        Set<String> items = plugin.getConfig().getConfigurationSection("bounties.data").getKeys(false);
        for(String item_id : items){
            List<ItemStack> itemStacks = new ArrayList<>();
            String user = plugin.getConfig().getString("bounties.data." + item_id + ".user");
            int amount = plugin.getConfig().getInt("bounties.data." + item_id + ".amount");
            String material = plugin.getConfig().getString("bounties.data." + item_id + ".material");
            itemStacks.add(new ItemStack(Material.getMaterial(material), amount));
            if(data.containsKey(user)){
                List<ItemStack> oldItemStacks = data.get(user);
                for(ItemStack itemStack : oldItemStacks){
                    itemStacks.add(itemStack);
                }
            }
            data.put(user, itemStacks);
        }
        return data;
    }

    public static Set<String> get_where_user_is(Plugin plugin, String user_name){
        Set<String> ids= new HashSet<>();
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("bounties.data");
        Set<String> all_ids = section.getKeys(false);
        for(String id : all_ids){
            if(Objects.equals(plugin.getConfig().getString("bounties.data." + id + ".user"), user_name)){
                ids.add(id);
            }
        }
        return ids;
    }
}
