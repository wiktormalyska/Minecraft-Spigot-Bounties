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
        String commands_prefix ="§4§l"+plugin.getConfig().getString("bounties.menu.title");
        String title = config_file.get_title(plugin);
        if (event.getView().getTitle().equalsIgnoreCase("§4§l"+title)) {
            event.setCancelled(true);
            try {
                List<String> lore = event.getCurrentItem().getItemMeta().getLore();
                String bounty_adder = lore.get(1).replace("§2User: ", "");
                if (!event.getWhoClicked().getName().equals(bounty_adder)) {
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

                    if (event.getWhoClicked().getInventory().containsAtLeast(item_stack, item_amount)) {
                        int current_slot = event.getSlot();
                        String name_of_equal_section_to_slot = open.name_of_section_with_the_same_slot(current_slot, plugin);
                        if (plugin.getConfig().getBoolean("bounties.items.requested." + name_of_equal_section_to_slot + ".removable")) {
                            plugin.getConfig().set("bounties.items.requested." + name_of_equal_section_to_slot, null);
                            //save config
                            plugin.saveConfig();
                        }
                        event.getWhoClicked().getInventory().addItem(reward);
                        event.getWhoClicked().getInventory().removeItem(item_stack);
                        event.getWhoClicked().sendMessage(commands_prefix + " §2§lYou got an reward: §a" + prize_amount + "x " + prize + "!");

                        if (event.getCurrentItem().getItemMeta().getLore().get(1).startsWith("§2User: ")) {
                            Player player_to_get_reward = plugin.getServer().getPlayer(bounty_adder);
                            if (plugin.getServer().getPlayer(bounty_adder).isOnline()) {
                                player_to_get_reward.sendMessage("§4§l" + title + " §r§7You got your requested item: §r§a" + event.getCurrentItem().getAmount() + "x" + event.getCurrentItem().getType());
                                player_to_get_reward.getInventory().setItem(player_to_get_reward.getInventory().firstEmpty(), new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount()));
                            } else {
                                plugin.getConfig().set("bounties.data." + event.getWhoClicked().getName() + event.getCurrentItem().getType() + ".material", (event.getCurrentItem().getType()).toString());
                                plugin.getConfig().set("bounties.data." + event.getWhoClicked().getName() + event.getCurrentItem().getType() + ".amount", event.getCurrentItem().getAmount());
                                plugin.getConfig().set("bounties.data." + event.getWhoClicked().getName() + event.getCurrentItem().getType() + ".user", player_to_get_reward.getName());
                            }

                        }
                        event.getWhoClicked().closeInventory();

                    } else {
                        event.getWhoClicked().sendMessage(commands_prefix + "§c§lNot enough item: §a" + item_amount + "x " + item_name);
                        event.getWhoClicked().closeInventory();
                    }
                    event.setCancelled(true);
                } else {
                    event.getWhoClicked().sendMessage("§4§l" + title + "§r§7You cannot buy from yourself!");
                    event.getWhoClicked().closeInventory();
                }
            }catch (Exception e){

            }
        }
    }
}
