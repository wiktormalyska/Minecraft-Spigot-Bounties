package bettermobs.bounties.commands.on.player.join;

import bettermobs.bounties.data.config_file;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void player_joined(@NotNull PlayerJoinEvent event, Plugin plugin){
        if(users(plugin).contains(event.getPlayer().getName())){
            event.getPlayer().sendMessage("§4§l"+plugin.getConfig().getString("bounties.menu.title")+" §r§7Your items has been traded. Use §2§l/botake §r§7to take your items!");
        }
    }

    public static @NotNull List<String> users(Plugin plugin){
        List<String> user = new ArrayList<>();
        String [] req_items = config_file.get_requested_items(plugin);
        for (String req_item : req_items) {
            String user_name = plugin.getConfig().getString("bounties.data." + req_item + ".user");
            user.add(user_name);
        }
        return user;
    }
}

