package bettermobs.bounties;

import bettermobs.bounties.commands.add;
import bettermobs.bounties.commands.info;
import bettermobs.bounties.commands.menu.click.MenuClick;
import bettermobs.bounties.commands.on.player.join.OnPlayerJoin;
import bettermobs.bounties.commands.open;
import bettermobs.bounties.commands.reload;
import bettermobs.bounties.data.config_file;
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
        String [] requested_items = config_file.get_requested_items(this);
        String title = config_file.get_title(this);
        List<Integer> taken_slots = open.taken_slots_list(this);


        getServer().getPluginManager().registerEvents(new MenuClick(this), this);
        getCommand("boinfo").setExecutor(new info(description_file));
        getCommand("boopen").setExecutor(new open( title, this));
        getCommand("boreload").setExecutor(new reload(this));
        getCommand("boadd").setExecutor(new add(this, taken_slots));
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        System.out.println("Started successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Stopped!");

    }



    }
