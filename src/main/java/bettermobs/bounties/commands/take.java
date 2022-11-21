package bettermobs.bounties.commands;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class take implements CommandExecutor {
    Plugin plugin;
    String prefix;
    public take(Plugin plug, String pref) {
        plugin = plug;
        prefix = pref;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("bounties.take")){
            Map<String, List<ItemStack>> get_user_and_item = get_uandi_from_data(plugin, sender, prefix);
            if (get_user_and_item.get(sender.getName())!=null){
                for (int i = 0; i < get_user_and_item.size(); i++) {
                    sender.getServer().getPlayer(sender.getName()).getInventory().addItem(get_user_and_item.get(sender.getName()).get(i));
                }
                plugin.getConfig().set("bounties.data."+sender.getName(), null);
            }
        }
        return false;
    }
    public Map<String, List<ItemStack>> get_uandi_from_data(Plugin plugin, CommandSender sender, String prefix){
        Map<String,List<ItemStack>> data = new HashMap<>();
        List<String> users_to_get_items = (List<String>) plugin.getConfig().getConfigurationSection("bounties.data.").getKeys(false);
        if (users_to_get_items.contains(sender.getName())) {
            for(String user : users_to_get_items){
                List<String> items_id = (List<String>) plugin.getConfig().getConfigurationSection("bounties.data."+user).getKeys(false);
                List<ItemStack> items = new ArrayList<>();
                for(String item : items_id){
                    String item_name = plugin.getConfig().getString("bounties.data."+user+"."+item+".item");
                    int item_amount = plugin.getConfig().getInt("bounties.data."+user+"."+item+".amount");
                    ItemStack item_stack = new ItemStack(Material.valueOf(item_name), item_amount);
                    items.add(item_stack);
                }
                data.put(user, items);
            }
        } else {
            sender.sendMessage("§4§l" + prefix + "§r§7You don't have any bounties to take!");
        }
        return data;
    }
}
