package bettermobs.bounties.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Marker;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class add implements CommandExecutor {
    Plugin plugin;
    List<Integer> taken_slots;
    public add(Plugin plug, List<Integer> tkn_slots) {
        plugin = plug;
        taken_slots = tkn_slots;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            if (commandSender instanceof Player && commandSender.hasPermission("bounties.add")) {
                List<String> materials = new ArrayList<>();
                for (Material l : EnumSet.allOf(Material.class))
                {
                    materials.add(l.toString());
                }
                if (strings.length == 2) {

                    if(((Player) commandSender).getInventory().getItemInMainHand().getAmount() != 0) {
                        if (materials.contains(strings[0])) {
                            String path = "bounties.items.requested." + commandSender.getName() + (int) (Math.random() * (1000));
                            plugin.getConfig().set(path + ".enabled", true);
                            plugin.getConfig().set(path + ".removable", true);
                            plugin.getConfig().set(path + ".material", strings[0]);
                            plugin.getConfig().set(path + ".amount", ((Player) commandSender).getInventory().getItemInMainHand().getAmount());
                            int slot = 0;
                            for (int i = 0; i <= 35; i++) {
                                if (taken_slots.contains(i)) {
                                    continue;
                                }
                                slot = i;
                                break;
                            }
                            taken_slots.add(slot);
                            plugin.getConfig().set(path + ".slot", slot);
                            plugin.getConfig().set(path + ".reward.material", ((Player) commandSender).getInventory().getItemInMainHand().getType().toString());
                            plugin.getConfig().set(path + ".reward.amount", Integer.valueOf(strings[1]));
                            plugin.getConfig().set(path+".user",commandSender.getName());

                            ((Player) commandSender).getInventory().removeItem(((Player) commandSender).getInventory().getItemInMainHand());
                            commandSender.sendMessage("§4§l[Bounties] §a§lAdded your items to Bounties!");
                        } else {
                            commandSender.sendMessage("§4§l[Bounties] §7Provided argument is not material");
                        }
                    } else {
                        commandSender.sendMessage("§4§l[Bounties] §7You need to hold an item that you want to add to bounty");
                    }
                } else {
                    commandSender.sendMessage("§4§l[Bounties] §7Not enough arguments: /boadd <requested_item> <amount>");
                }
            }
        } catch (Exception e){
            commandSender.sendMessage("§4§l[Bounties] §7Not enough arguments: /boadd <requested_item> <amount>");
        }
        plugin.saveConfig();
        return true;
    }
}
