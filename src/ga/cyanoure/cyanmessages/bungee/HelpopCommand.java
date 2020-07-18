package ga.cyanoure.cyanmessages.bungee;

import java.util.Collection;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HelpopCommand extends Command{
	Main plugin;
	public HelpopCommand(Main plugin) {
		super("helpop","cyanmessages.helpop.send");
		this.plugin = plugin;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer from = (ProxiedPlayer)sender;
		if(args.length >= 1) {
			String msg = "";
			for(int i = 0; i < args.length; i++) {
				msg+=args[i]+" ";
			}
			msg = msg.trim();
			
			//String message = "&4&lHelpOP &c» &6"+from.getServer().getInfo().getName()+" &c» &6"+from.getName()+" &3» &7"+msg;
			String message = this.plugin.config.getString("helpop-format").replace("<server>", from.getServer().getInfo().getName()).replace("<sender>", from.getName()).replace("<message>", msg);
			
			this.plugin.simpleMessage(message);
			Collection<ProxiedPlayer> players = plugin.getProxy().getPlayers();
			for(ProxiedPlayer item : players) {
				ProxiedPlayer player = item;
				if(player.hasPermission("cyanmessages.helpop.receive") || player == sender) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
				}
			}
		}else {
			from.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.lang.GetText("command-usage").replace("<usage>", "/helpop <"+this.plugin.lang.GetText("message")+">")));
		}
	}
}
