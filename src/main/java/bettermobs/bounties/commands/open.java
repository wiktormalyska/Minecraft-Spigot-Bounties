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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class open implements CommandExecutor {
    String menu_title;
    Plugin plugin;

    public open(String title, Plugin plug) {
        menu_title = "§4§l"+title;
        plugin = plug;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        String [] requested_items = plugin.getConfig().getConfigurationSection("bounties.items.requested").getKeys(false).toArray(new String[0]);
        if (sender instanceof Player && sender.hasPermission("bounties.open")) {
            sender.sendMessage(menu_title+"§7Opening menu...");
            Inventory site_1 = Bukkit.createInventory((Player) sender, 54, menu_title+" §7Page 1");
            Inventory site_2 = Bukkit.createInventory((Player) sender, 54, menu_title+" §7Page 2");
            Inventory site_3 = Bukkit.createInventory((Player) sender, 54, menu_title+" §7Page 3");
            Inventory site_4 = Bukkit.createInventory((Player) sender, 54, menu_title+" §7Page 4");
            Inventory site_5 = Bukkit.createInventory((Player) sender, 54, menu_title+" §7Page 5");
            Inventory site_6 = Bukkit.createInventory((Player) sender, 54, menu_title+" §7Page 6");
            Inventory site_7 = Bukkit.createInventory((Player) sender, 54, menu_title+" §7Page 7");
            Inventory site_8 = Bukkit.createInventory((Player) sender, 54, menu_title+" §7Page 8");
            Inventory site_9 = Bukkit.createInventory((Player) sender, 54, menu_title+" §7Page 9");
            List<ItemStack> requested_items_list = new ArrayList<>();
            // Add items to lists
            for (String req_item : requested_items) {
                if (plugin.getConfig().getBoolean("bounties.items.requested." + req_item + ".enabled")){
                    String material = plugin.getConfig().getString("bounties.items.requested." + req_item + ".material");
                    int amount = plugin.getConfig().getInt("bounties.items.requested." + req_item + ".amount");
                    String reward_material = plugin.getConfig().getString("bounties.items.requested." + req_item + ".reward.material");
                    int reward_amount = plugin.getConfig().getInt("bounties.items.requested." + req_item + ".reward.amount");
                    ItemStack item_requested = new ItemStack(Material.valueOf(material), amount);
                    ItemMeta item_requested_meta = item_requested.getItemMeta();
                    ArrayList<String> item_requested_lore = new ArrayList<>();
                    item_requested_lore.add("§2§lPrize: §a" + reward_amount + "x " + reward_material);
                    if (!plugin.getConfig().getBoolean("bounties.items.requested." + req_item + ".removable")) {
                        item_requested_lore.add("§4§lUnlimited");
                    }
                    if (plugin.getConfig().getString("bounties.items.requested." + req_item + ".user")!=null){
                        item_requested_lore.add("§2User: "+plugin.getConfig().getString("bounties.items.requested." + req_item + ".user"));
                    }
                    item_requested_lore.add("§7§o(Click to redeem)");
                    item_requested_meta.setLore(item_requested_lore);
                    item_requested_meta.setDisplayName("§3§lRequested: §a" + material);
                    item_requested.setItemMeta(item_requested_meta);
                    requested_items_list.add(item_requested);
                }
                // Add items to sites
                ItemStack left_arrow = new ItemStack(Material.ARROW);
                ItemMeta left_arrow_meta = left_arrow.getItemMeta();
                left_arrow_meta.setDisplayName("§c§lPrevious Page");
                left_arrow.setItemMeta(left_arrow_meta);

                ItemStack right_arrow = new ItemStack(Material.ARROW);
                ItemMeta right_arrow_meta = right_arrow.getItemMeta();
                right_arrow_meta.setDisplayName("§a§lNext Page");
                right_arrow.setItemMeta(right_arrow_meta);


                site_1.setItem(53, right_arrow);
                site_2.setItem(45, left_arrow);
                site_2.setItem(53, right_arrow);
                site_3.setItem(45, left_arrow);
                site_3.setItem(53, right_arrow);
                site_4.setItem(45, left_arrow);
                site_4.setItem(53, right_arrow);
                site_5.setItem(45, left_arrow);
                site_5.setItem(53, right_arrow);
                site_6.setItem(45, left_arrow);
                site_6.setItem(53, right_arrow);
                site_7.setItem(45, left_arrow);
                site_7.setItem(53, right_arrow);
                site_8.setItem(45, left_arrow);
                site_8.setItem(53, right_arrow);
                site_9.setItem(45, left_arrow);

                for (int i = 0; i < requested_items_list.size(); i++) {
                    if (i < 45) {
                        site_1.setItem(i, requested_items_list.get(i));
                    } else if (i < 90) {
                        site_2.setItem(i - 45, requested_items_list.get(i));
                    } else if (i < 135) {
                        site_3.setItem(i - 90, requested_items_list.get(i));
                    } else if (i < 180) {
                        site_4.setItem(i - 135, requested_items_list.get(i));
                    } else if (i < 225) {
                        site_5.setItem(i - 180, requested_items_list.get(i));
                    } else if (i < 270) {
                        site_6.setItem(i - 225, requested_items_list.get(i));
                    } else if (i < 315) {
                        site_7.setItem(i - 270, requested_items_list.get(i));
                    } else if (i < 360) {
                        site_8.setItem(i - 315, requested_items_list.get(i));
                    } else if (i < 405) {
                        site_9.setItem(i - 360, requested_items_list.get(i));
                    }
                }
            }
            // Show page 1
            try {
                if (args[0].equalsIgnoreCase("1")) {
                    ((Player) sender).openInventory(site_1);
                } else if (args[0].equalsIgnoreCase("2")) {
                    ((Player) sender).openInventory(site_2);
                } else if (args[0].equalsIgnoreCase("3")) {
                    ((Player) sender).openInventory(site_3);
                } else if (args[0].equalsIgnoreCase("4")) {
                    ((Player) sender).openInventory(site_4);
                } else if (args[0].equalsIgnoreCase("5")) {
                    ((Player) sender).openInventory(site_5);
                } else if (args[0].equalsIgnoreCase("6")) {
                    ((Player) sender).openInventory(site_6);
                } else if (args[0].equalsIgnoreCase("7")) {
                    ((Player) sender).openInventory(site_7);
                } else if (args[0].equalsIgnoreCase("8")) {
                    ((Player) sender).openInventory(site_8);
                } else if (args[0].equalsIgnoreCase("9")) {
                    ((Player) sender).openInventory(site_9);
                } else {
                    ((Player) sender).openInventory(site_1);
                }
            } catch (Exception e) {
                ((Player) sender).openInventory(site_1);
            }
        } else {
            sender.sendMessage(menu_title+" §7You do not have permission");
        }
        return true;
    }
    public static @Nullable String name_of_section_with_the_same_slot(int slot, @NotNull Plugin plgi){
        String [] req_items = plgi.getConfig().getConfigurationSection("bounties.items.requested").getKeys(false).toArray(new String[0]);
        for (String req_item : req_items) {
            int item_slot = plgi.getConfig().getInt("bounties.items.requested." + req_item + ".slot");
            if (slot == item_slot) {
                return req_item;
            }
        }
        return null;
    }

    public static @NotNull List<Integer> taken_slots_list(@NotNull Plugin plgi){
        String [] req_items = plgi.getConfig().getConfigurationSection("bounties.items.requested").getKeys(false).toArray(new String[0]);
        List<Integer> taken_slots = new ArrayList<>();

        for (String req_item : req_items){
            int slot = plgi.getConfig().getInt("bounties.items.requested." + req_item + ".slot");
            taken_slots.add(slot);
        }
        return taken_slots;
    }
}
