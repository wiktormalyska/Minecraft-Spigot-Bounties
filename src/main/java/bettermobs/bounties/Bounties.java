package bettermobs.bounties;

import bettermobs.bounties.gui.gui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.data.type.Switch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.Reader;

public final class Bounties extends JavaPlugin implements Listener {

PluginDescriptionFile plugin_desc = this.getDescription();
    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        System.out.println("Started successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Stopped!");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        String commands_prefix =""+ChatColor.DARK_GREEN+ChatColor.BOLD+"Bounties"+ChatColor.GRAY+ChatColor.BOLD+" | ";

        if(command.getName().equalsIgnoreCase("bainfo") && sender instanceof Player){
            sender.sendMessage(commands_prefix+"\n"+ChatColor.AQUA+ChatColor.BOLD+"Version: "+ChatColor.DARK_GREEN+plugin_desc.getVersion());
        }
        if(command.getName().equalsIgnoreCase("bagui") && sender instanceof Player){
            sender.sendMessage(commands_prefix+"Opening gui...");
            gui inventory = new gui();
            ((Player) sender).openInventory(inventory.gui);
        }

        return true;
    }
}
