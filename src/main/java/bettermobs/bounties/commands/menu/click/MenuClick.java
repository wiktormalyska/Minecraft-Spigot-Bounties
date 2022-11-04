package bettermobs.bounties.commands.menu.click;

import bettermobs.bounties.commands.open;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Objects;

public class MenuClick implements Listener {
    String item_name, prize, title;
    Plugin plugin;
    public MenuClick(String menu_title, Plugin plu) {
        title = menu_title;
        plugin = plu;
    }
    String commands_prefix ="§4§l[Bounties] ";
    @EventHandler
    public void onMenuClick(InventoryClickEvent event){
        if (event.getView().getTitle().equalsIgnoreCase("§4§l"+title)){
            if(event.getCurrentItem()==null){
                return;
            }
            String [] requested_items = plugin.getConfig().getConfigurationSection("bounties.items.requested").getKeys(false).toArray(new String[0]);
            item_name = event.getCurrentItem().getItemMeta().getDisplayName().replace("§3§lRequested: §a","");
            int item_amount = event.getCurrentItem().getAmount();
            List<String> lore = event.getCurrentItem().getItemMeta().getLore();
            char prize_amount_char = lore.get(0).replace("§2§lPrize: §a","").charAt(0);
            int prize_amount = prize_amount_char-48;
            prize = lore.get(0).replace("§2§lPrize: §a"+prize_amount+"x ","");
            ItemStack reward = new ItemStack(Material.valueOf(prize),prize_amount);
            ItemMeta reward_meta = reward.getItemMeta();
            reward.setItemMeta(reward_meta);
            ItemStack item_stack = new ItemStack(Material.valueOf(item_name.replace("§2§l", "")),item_amount);

            if(event.getWhoClicked().getInventory().containsAtLeast(item_stack, item_amount)){
                int current_slot = event.getSlot();
                String name_of_equal_section_to_slot = open.name_of_section_with_the_same_slot(current_slot, plugin);
                List<String> lore2 = event.getCurrentItem().getItemMeta().getLore();
                if(plugin.getConfig().getBoolean("bounties.items.requested."+name_of_equal_section_to_slot+".removable")){
                    System.out.println("Item disabled!" + name_of_equal_section_to_slot);
                    plugin.getConfig().set("bounties.items.requested."+name_of_equal_section_to_slot, null);
                    //save config
                    plugin.saveConfig();
                }
                event.getWhoClicked().getInventory().addItem(reward);
                event.getWhoClicked().getInventory().removeItem(item_stack);
                event.getWhoClicked().sendMessage(commands_prefix+"§2§lYou got an reward: §a"+prize_amount+"x "+prize+"!");

                event.getWhoClicked().closeInventory();
            } else {
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().sendMessage(commands_prefix+"§c§lNot enough item: §a"+item_amount+"x "+item_name);
            }
            event.setCancelled(true);
        }
    }
}
