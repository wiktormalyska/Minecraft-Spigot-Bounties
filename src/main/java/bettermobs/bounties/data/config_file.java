package bettermobs.bounties.data;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.TreeMap;

public class config_file {
    public static String [] get_requested_items(Plugin plugin){
        return  plugin.getConfig().getConfigurationSection("bounties.items.requested").getKeys(false).toArray(new String[0]);
    }
    public static String get_title(@NotNull Plugin plugin){
        return plugin.getConfig().getString("bounties.menu.title");
    }
    public static @NotNull Map<String, ItemStack> get_users_and_items(Plugin plugin){
        Map<String, ItemStack> users = new TreeMap<>();
        String [] req_items = get_requested_items(plugin);
        for (int i=0;i<req_items.length;){
            if(!plugin.getConfig().getString("bounties.items.requested."+req_items[i]+".user").isEmpty()){
                String user = plugin.getConfig().getString("bounties.items.requested."+req_items[i]+".user");
                ItemStack item = new ItemStack(Material.valueOf(plugin.getConfig().getString("bounties.items.requested."+req_items[i]+".reward.material")),plugin.getConfig().getInt("bounties.items.requested."+req_items[i]+".reward.amount"));
                users.put(user, item);
            }
        }
        return users;
    }
}
