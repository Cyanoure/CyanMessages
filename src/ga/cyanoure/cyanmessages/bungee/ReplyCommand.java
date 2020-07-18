package ga.cyanoure.cyanmessages.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReplyCommand extends Command{
	Main plugin;
	public ReplyCommand(Main plugin) {
		super("r","cyanmessages.private","reply","replyto","ereply","er");
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
				
			this.plugin.pm.replyMessage(from, msg);
		}else {
			from.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.lang.GetText("command-usage").replace("<usage>", "/r <"+this.plugin.lang.GetText("message")+">")));
		}
	}
}
