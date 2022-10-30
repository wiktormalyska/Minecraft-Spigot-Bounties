package bettermobs.bounties.commands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class open implements CommandExecutor {
    String [] req_items;
    String menu_title;
    Plugin plugin;
    public open(String[] requested_items, String title, Plugin plug) {
        req_items = requested_items;
        menu_title = ""+ ChatColor.DARK_RED + ChatColor.BOLD +title;
        plugin = plug;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && sender.hasPermission("bounties.open")){
            sender.sendMessage("§4&l[Bounties] Opening menu...");
            Inventory inventory = Bukkit.createInventory((Player) sender, 54, menu_title);
            List<ItemStack> requested_items_list = new ArrayList<ItemStack>();
            List<Integer> slots = new ArrayList<Integer>();

            // Add items to lists
            for(int i = 0;i<req_items.length;i++) {
                String material = plugin.getConfig().getString("bounties.items.requested." + req_items[i]+".material");
                int amount = plugin.getConfig().getInt("bounties.items.requested." + req_items[i]+".amount");
                String reward_material = plugin.getConfig().getString("bounties.items.requested."+req_items[i]+".reward.material");
                int reward_amount = plugin.getConfig().getInt("bounties.items.requested." + req_items[i]+".reward.amount");
                int slot = plugin.getConfig().getInt("bounties.items.requested." + req_items[i]+".slot");
                ItemStack item_requested = new ItemStack(Material.valueOf(material),amount);
                ItemMeta item_requestes_meta = item_requested.getItemMeta();
                ArrayList<String> item_requested_lore = new ArrayList<>();
                item_requested_lore.add("§2§lPrize: §a"+reward_amount+"x "+reward_material);
                item_requested_lore.add("§7§o(Click to redeem)");
                item_requestes_meta.setLore(item_requested_lore);
                item_requestes_meta.setDisplayName("§3§lRequested: §a"+material);
                item_requested.setItemMeta(item_requestes_meta);
                requested_items_list.add(item_requested);
                slots.add(slot);
                System.out.println(material+amount+"\t"+reward_material+reward_amount+"\t"+slot);
            }
            // Add lists to inventory
            for (int i = 0;i<requested_items_list.toArray().length;i++){
                inventory.setItem(slots.get(i),requested_items_list.get(i));
            }
            // Show inventory
            ((Player) sender).openInventory(inventory);
        } else{
            sender.sendMessage("§4&l[Bounties] You do not have permission: bounties.open");
        }
        return true;
    }
}
