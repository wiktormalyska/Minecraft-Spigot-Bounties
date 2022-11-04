package bettermobs.bounties;

import bettermobs.bounties.commands.add;
import bettermobs.bounties.commands.info;
import bettermobs.bounties.commands.menu.click.MenuClick;
import bettermobs.bounties.commands.open;
import bettermobs.bounties.commands.reload;
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
        String [] requested_items = getConfig().getConfigurationSection("bounties.items.requested").getKeys(false).toArray(new String[0]);
        String title = getConfig().getString("bounties.menu.title");
        List<Integer> taken_slots = open.taken_slots_list(this);


        getServer().getPluginManager().registerEvents(new MenuClick(title, this), this);
        getCommand("boinfo").setExecutor(new info(description_file));
        getCommand("boopen").setExecutor(new open( title, this));
        getCommand("boreload").setExecutor(new reload(this));
        getCommand("boadd").setExecutor(new add(this, taken_slots));
        System.out.println("Started successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Stopped!");

    }



    }
