package bettermobs.bounties;

import bettermobs.bounties.commands.*;
import bettermobs.bounties.commands.menu.click.MenuClick;
import bettermobs.bounties.commands.on.player.join.OnPlayerJoin;
import bettermobs.bounties.data.config_file;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import java.util.Objects;

public final class Bounties extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        PluginDescriptionFile description_file = this.getDescription();
        String title = this.getConfig().getString("bounties.menu.title");
        List<Integer> taken_slots = open.taken_slots_list(this);


        getServer().getPluginManager().registerEvents(new MenuClick(this), this);
        getCommand("boinfo").setExecutor(new info(description_file));
        getCommand("boopen").setExecutor(new open( title, this));
        getCommand("boreload").setExecutor(new reload(this));
        getCommand("boadd").setExecutor(new add(this, taken_slots));
        getCommand("botake").setExecutor(new take(this, title));
        //Objects.requireNonNull(getCommand("botake")).setExecutor(new take(this));
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
        this.getLogger().log(java.util.logging.Level.INFO, "Started successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().log(java.util.logging.Level.INFO, "Stopped successfully!");

    }



    }
