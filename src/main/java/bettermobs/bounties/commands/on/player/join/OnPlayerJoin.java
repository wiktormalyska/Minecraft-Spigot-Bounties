package bettermobs.bounties.commands.on.player.join;
import bettermobs.bounties.data.config_file;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class OnPlayerJoin implements Listener {
Plugin plugin;

    public OnPlayerJoin(Plugin plug) {
        plugin = plug;
    }

    @EventHandler
    public void player_joined(@NotNull PlayerJoinEvent event) {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("bounties.data");
        Set<String> all_ids = section.getKeys(false);
        for (String id : all_ids) {
            if (Objects.equals(plugin.getConfig().getString("bounties.data." + id + ".user"), event.getPlayer().getName())) {
                event.getPlayer().sendMessage("§4§l" + plugin.getConfig().getString("bounties.menu.title") + " §r§7Your items has been traded. Use §2/botake §r§7to take your items!");
            }
        }
    }
    }
