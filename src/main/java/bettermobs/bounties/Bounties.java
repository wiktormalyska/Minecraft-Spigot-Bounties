package bettermobs.bounties;

import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

public final class Bounties extends JavaPlugin implements Listener {

    String plugin_name="Bounties";
    String command_prefix="bounties";
    @Override
    public void onEnable() {

        // Plugin startup logic
        System.out.println("["+plugin_name+"] Started successfully!");

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("["+plugin_name+"] Stopped!");
    }

    @EventHandler

    public void onCommand(PlayerCommandSendEvent event){

    }
}
