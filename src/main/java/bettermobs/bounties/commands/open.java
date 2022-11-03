package bettermobs.bounties.commands;
import org.bukkit.Bukkit;
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
        menu_title = "§4§l"+title;
        plugin = plug;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && sender.hasPermission("bounties.open")){
            sender.sendMessage("§4§l[Bounties] §7Opening menu...");
            Inventory inventory = Bukkit.createInventory((Player) sender, 54, menu_title);
            List<ItemStack> requested_items_list = new ArrayList<ItemStack>();
            List<Integer> slots = new ArrayList<>();

            // Add items to lists
            for (String req_item : req_items) {
                String material = plugin.getConfig().getString("bounties.items.requested." + req_item + ".material");
                int amount = plugin.getConfig().getInt("bounties.items.requested." + req_item + ".amount");
                String reward_material = plugin.getConfig().getString("bounties.items.requested." + req_item + ".reward.material");
                int reward_amount = plugin.getConfig().getInt("bounties.items.requested." + req_item + ".reward.amount");
                int slot = plugin.getConfig().getInt("bounties.items.requested." + req_item + ".slot");
                ItemStack item_requested = new ItemStack(Material.valueOf(material), amount);
                ItemMeta item_requested_meta = item_requested.getItemMeta();
                ArrayList<String> item_requested_lore = new ArrayList<>();
                item_requested_lore.add("§2§lPrize: §a" + reward_amount + "x " + reward_material);
                if (plugin.getConfig().getBoolean("bounties.items.requested." + req_item + ".removable")) {
                    item_requested_lore.add("§4§lOne time offer!");
                }
                item_requested_lore.add("§7§o(Click to redeem)");
                item_requested_meta.setLore(item_requested_lore);
                item_requested_meta.setDisplayName("§3§lRequested: §a" + material);
                item_requested.setItemMeta(item_requested_meta);
                requested_items_list.add(item_requested);
                slots.add(slot);
                System.out.println(material + amount + "\t" + reward_material + reward_amount + "\t" + slot);
            }
            // Add lists to inventory
            for (int i = 0;i<requested_items_list.toArray().length;i++){
                inventory.setItem(slots.get(i),requested_items_list.get(i));
            }
            // Show inventory
            ((Player) sender).openInventory(inventory);
        } else{
            sender.sendMessage("§4§l[Bounties] You do not have permission: §3§lbounties.open");
        }
        return true;
    }
}
