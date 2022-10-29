package bettermobs.bounties.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

/*Info to show:
-Owner of licence(if done)
-Version of plugin
-Description of plugin from plugin.yml
-Help on discord
* */
public class info implements CommandExecutor {
    PluginDescriptionFile main;
    public info(PluginDescriptionFile description_file) {
        main = description_file;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player&& sender.hasPermission("bounties.info")){
            String commands_prefix =""+ChatColor.DARK_RED+ChatColor.BOLD+"[Bounties]";
            sender.sendMessage(commands_prefix+"" +
                    //"\n"+ ChatColor.AQUA+"\nLicence Owner: "+ChatColor.DARK_GREEN+descriptionFile.getVersion()+
                    "\n"+ChatColor.AQUA+"Version: "+ChatColor.DARK_GREEN+main.getVersion()+
                    "\n"+ChatColor.AQUA+"Description: "+ChatColor.DARK_GREEN+main.getDescription()+
                    "\n"+ChatColor.AQUA+"Help: "+ChatColor.DARK_GREEN+main.getWebsite());
        }
        return true;
    }

}
