package bettermobs.bounties.commands.menu.click;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MenuClick implements Listener {
    String item_name, prize, title;
    public MenuClick(String menu_title) {
        title = menu_title;
    }
    String commands_prefix =""+ChatColor.DARK_RED+ChatColor.BOLD+"[Bounties] ";
    @EventHandler
    public void onMenuClick(InventoryClickEvent event){
        if (event.getView().getTitle().equalsIgnoreCase("§4§l"+title)){
            if(event.getCurrentItem()==null){
                return;
            }
            item_name = event.getCurrentItem().getItemMeta().getDisplayName().replace("§2§lRequested item: ","");
            List<String> lore = event.getCurrentItem().getItemMeta().getLore();
            prize = lore.get(0).replace("§3§lReward: ","");
            ItemStack reward = new ItemStack(Material.valueOf(prize.replace("§3§l", "")));
            ItemMeta reward_meta = reward.getItemMeta();
            reward.setItemMeta(reward_meta);
            ItemStack item_stack = new ItemStack(Material.valueOf(item_name.replace("§2§l", "")));

            if(event.getWhoClicked().getInventory().containsAtLeast(item_stack, 1)){
                event.getWhoClicked().getInventory().addItem(reward);
                event.getWhoClicked().getInventory().removeItem(item_stack);
                event.getWhoClicked().sendMessage(commands_prefix+ChatColor.DARK_GREEN+ChatColor.BOLD+"You got an reward: "+ChatColor.GREEN+prize+"!");
                event.getWhoClicked().closeInventory();
            } else {
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().sendMessage(commands_prefix+ChatColor.RED+ChatColor.BOLD+"You do not have required item: "+ChatColor.GREEN+item_name);
            }
            event.setCancelled(true);
        }
    }
}
