package bettermobs.bounties;

import bettermobs.bounties.commands.info;
import bettermobs.bounties.commands.menu.click.MenuClick;
import bettermobs.bounties.commands.open;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Bounties extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        PluginDescriptionFile description_file = this.getDescription();
        //Get object czy coś
        String [] requested_items = getConfig().getObject("bounties.items.requested").toArray(new String[0]);
        String title = getConfig().getString("bounties.menu.title");
        System.out.println(title);

        getServer().getPluginManager().registerEvents(new MenuClick(title), this);
        getCommand("boinfo").setExecutor(new info(description_file));
        getCommand("boopen").setExecutor(new open(requested_items, title, this));
        System.out.println("Started successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Stopped!");

    }



    }
