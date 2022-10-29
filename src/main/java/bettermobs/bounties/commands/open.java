package bettermobs.bounties.commands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

public class open implements CommandExecutor {
    String [] req_items;
    String [] rew_items;
    String menu_title;
    public open(String[] requested_items, String[] rewards, String title) {
        req_items = requested_items;
        rew_items = rewards;
        menu_title = ""+ ChatColor.RED + ChatColor.BOLD + title;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && sender.hasPermission("bounties.open")){
            Inventory inventory = Bukkit.createInventory((Player) sender, 54, menu_title);
            for(int i = 0; i< req_items.length;i++) {
                int slot = i;
                if (i>35){
                slot = i;
                continue;
                }
                inventory.setItem(slot,createGuiItem(Material.valueOf(req_items[i]), "Requested item: "+req_items[i],"Reward: "+rew_items[(int)(Math.random() * rew_items.length)]));
                ((Player) sender).openInventory(inventory);
            }

        }
        return true;
        }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }
    }
