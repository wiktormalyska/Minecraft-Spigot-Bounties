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
        String title = config_file.get_title(this);
        List<Integer> taken_slots = open.taken_slots_list(this);


        getServer().getPluginManager().registerEvents(new MenuClick(this), this);
        Objects.requireNonNull(getCommand("boinfo")).setExecutor(new info(description_file));
        Objects.requireNonNull(getCommand("boopen")).setExecutor(new open( title, this));
        Objects.requireNonNull(getCommand("boreload")).setExecutor(new reload(this));
        Objects.requireNonNull(getCommand("boadd")).setExecutor(new add(this, taken_slots));
        //Objects.requireNonNull(getCommand("botake")).setExecutor(new take(this));
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
        System.out.println("Started successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Stopped!");

    }



    }
