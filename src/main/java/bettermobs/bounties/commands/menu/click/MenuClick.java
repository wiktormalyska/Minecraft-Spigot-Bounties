package bettermobs.bounties.commands.menu.click;

import bettermobs.bounties.commands.open;
import bettermobs.bounties.data.config_file;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class MenuClick implements Listener {
    String item_name, prize;
    Plugin plugin;
    public MenuClick(Plugin plu) {
        plugin = plu;
    }

    @EventHandler
    public void onMenuClick(@NotNull InventoryClickEvent event){
        String title = config_file.get_title(plugin);

        //Check what inventory is clicked
        if (event.getView().getTitle().equalsIgnoreCase("§4§l"+title)) {
            try {
                List<String> lore = event.getCurrentItem().getItemMeta().getLore();
                String bounty_adder = lore.get(1).replace("§2User: ", "");

                //Check if player is clicking on his own bounty
                if (!event.getWhoClicked().getName().equals(bounty_adder)) {

                    //Check if player is clicking item
                    if (event.getCurrentItem() == null) {
                        return;
                    }
                    item_name = Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName().replace("§3§lRequested: §a", "");
                    int item_amount = event.getCurrentItem().getAmount();
                    char prize_amount_char = lore.get(0).replace("§2§lPrize: §a", "").charAt(0);
                    int prize_amount = prize_amount_char - 48;
                    prize = lore.get(0).replace("§2§lPrize: §a" + prize_amount + "x ", "");
                    ItemStack reward = new ItemStack(Material.valueOf(prize), prize_amount);
                    ItemMeta reward_meta = reward.getItemMeta();
                    reward.setItemMeta(reward_meta);
                    ItemStack item_stack = new ItemStack(Material.valueOf(item_name.replace("§2§l", "")), item_amount);

                    // Check if player has required items
                    if (event.getWhoClicked().getInventory().containsAtLeast(item_stack, item_amount)) {
                        int current_slot = event.getSlot();
                        String name_of_equal_section_to_slot = open.name_of_section_with_the_same_slot(current_slot, plugin);

                        // Check if item is removable
                        if (plugin.getConfig().getBoolean("bounties.items.requested." + name_of_equal_section_to_slot + ".removable")) {
                            plugin.getConfig().set("bounties.items.requested." + name_of_equal_section_to_slot, null);
                            plugin.saveConfig();
                        }
                        event.getWhoClicked().sendMessage("§4§l" + title  + " §2§lYou got an reward: §a" + prize_amount + "x " + prize + "!");
                        event.getWhoClicked().getInventory().addItem(reward);
                        event.getWhoClicked().getInventory().removeItem(item_stack);
                        
                        // Check if item was published by player
                        if (event.getCurrentItem().getItemMeta().getLore().get(1).toLowerCase().contains("user:")) {
                            event.getWhoClicked().sendMessage("§4§l" + title + " §2§lThis item was added by: §r§a" + bounty_adder);
                            Player player_to_get_reward = plugin.getServer().getPlayer(bounty_adder);

                            // Check if publishing player is online
                            if (!plugin.getServer().getOnlinePlayers().contains(bounty_adder)) {
                                event.getWhoClicked().getInventory().addItem(reward);
                                event.getWhoClicked().getInventory().removeItem(item_stack);
                                player_to_get_reward.sendMessage("§4§l" + title + " §r§7You got your requested item: §r§a" + event.getCurrentItem().getAmount() + "x" + event.getCurrentItem().getType());
                                player_to_get_reward.getInventory().setItem(player_to_get_reward.getInventory().firstEmpty(), new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount()));
                                event.setCancelled(true);
                            } else {
                                event.getWhoClicked().getInventory().addItem(reward);
                                event.getWhoClicked().getInventory().removeItem(item_stack);
                                event.getWhoClicked().sendMessage("§4§l" + title + " §r§7The player §r§a" + bounty_adder + " §r§7is offline!");
                                String current_time = String.valueOf(System.currentTimeMillis());

                                // Check if player has offline rewards pending
                                if(plugin.getConfig().getConfigurationSection("bounties.data."+bounty_adder) != null){
                                    plugin.getConfig().createSection("bounties.data."+bounty_adder);
                                }
                                event.getWhoClicked().getInventory().addItem(reward);
                                event.getWhoClicked().getInventory().removeItem(item_stack);
                                plugin.getConfig().set("bounties.data." + bounty_adder+"." + current_time+".material", (event.getCurrentItem().getType()).toString());
                                plugin.getConfig().set("bounties.data." + bounty_adder+"." + current_time+".amount", (event.getCurrentItem().getAmount()));
                                event.setCancelled(true);
                                
                            }
                            event.setCancelled(true);
                            Player player_to_open = (Player) event.getWhoClicked();
                            event.getWhoClicked().closeInventory();
                            player_to_open.performCommand("boopen");
                        }
                        event.setCancelled(true);
                    } else {
                        event.getWhoClicked().sendMessage("§4§l" + title + "§c§lNot enough item: §a" + item_amount + "x " + item_name);
                        event.setCancelled(true);
                        
                    }
                    event.setCancelled(true);
                } else {
                    event.getWhoClicked().sendMessage("§4§l" + title + "§r§7You cannot buy from yourself!");
                    event.setCancelled(true);
                    
                }
            }catch (Exception e){
                event.setCancelled(true);
            }
        }
    }
}
