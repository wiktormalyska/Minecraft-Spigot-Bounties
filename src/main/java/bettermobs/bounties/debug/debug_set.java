package bettermobs.bounties.debug;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class debug_set {
    public static void show_string_array(String [] array, Plugin plugin){
        for (String s : array) {
            plugin.getLogger().log(Level.WARNING, s);;
        }
    }

    public static void show_map_key(Map<String, List<ItemStack>> map, Plugin plug){
        for (String s : map.keySet()) {
            plug.getLogger().log(Level.WARNING, s);;
        }
    }
    public static void show(String text){
        System.out.println(text);
    }
}
