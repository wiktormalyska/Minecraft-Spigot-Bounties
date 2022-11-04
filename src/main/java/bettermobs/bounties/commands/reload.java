package bettermobs.bounties.commands;

import bettermobs.bounties.Bounties;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class reload implements CommandExecutor {
    Plugin plugin;
    public reload(Plugin plug) {
        plugin = plug;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player && commandSender.hasPermission("bounties.reload")) {
            plugin.reloadConfig();
            commandSender.sendMessage("§4§l[Bounties] §7Reloaded Config!");
        }
        return true;
    }
}
