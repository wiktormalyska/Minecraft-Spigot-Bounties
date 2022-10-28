package bettermobs.bounties.gui;

import bettermobs.bounties.Bounties;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class gui implements Listener {
    public final Inventory gui;

    public gui(){
        gui = Bukkit.createInventory(null, 54, "Bounties");

        initializeItems();
    }

    public static String[] requested_items(){
        Bounties main = new Bounties();
        String [] req_items = main.getConfig().getStringList("bounties.items.requested").toArray(new String[0]);
        for (String req_item : req_items) {
            System.out.println(req_item);
        }
        return  req_items;
    }

    public static String[] rewards_items(){
        Bounties main = new Bounties();
        String [] rew_items = main.getConfig().getStringList("bounties.items.rewards").toArray(new String[0]);
        for (String rew_item : rew_items) {
            System.out.println(rew_item);
        }
        return  rew_items;


    }


    public void initializeItems() {
        String [] req_items = requested_items();
        String [] rew_items = rewards_items();
        for (String req_item : req_items) {
            gui.addItem(createGuiItem(Material.PAPER, req_item, rew_items[(int) (Math.random() * (rew_items.length) + 0)]));
        }
    }


    protected ItemStack createGuiItem(final Material material, String req_item, String rew_item) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        req_item.replace("_", " ");
        req_item.toLowerCase();
        req_item = req_item.substring(0, 1).toUpperCase() + req_item.substring(1);
        // Set the name of the item
        meta.setDisplayName("Requested item: "+req_item);

        rew_item.replace("_", " ");
        rew_item.toLowerCase();
        rew_item = req_item.substring(0, 1).toUpperCase() + req_item.substring(1);
        // Set the lore of the item
        meta.setLore(Arrays.asList("Reward: "+rew_item));

        item.setItemMeta(meta);

        return item;
    }
}
