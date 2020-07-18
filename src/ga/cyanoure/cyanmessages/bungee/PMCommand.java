package ga.cyanoure.cyanmessages.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PMCommand extends Command{
	Main plugin;
	public PMCommand(Main plugin) {
		super("message","cyanmessages.private","m","pm","msg","tell","whisper","privatemsg","pm","w","t","emsg","etell","ewhisper");
		this.plugin = plugin;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer from = (ProxiedPlayer)sender;
		if(args.length >= 2) {
			ProxiedPlayer to = this.plugin.getProxy().getPlayer(args[0]);
			String msg = "";
			for(int i = 1; i < args.length; i++) {
				msg+=args[i]+" ";
			}
			msg = msg.trim();
				
			this.plugin.pm.sendMessage(from, to, msg);
		}else {
			from.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.lang.GetText("command-usage").replace("<usage>", "/m <"+this.plugin.lang.GetText("player")+"> <"+this.plugin.lang.GetText("message")+">")));
		}
	}
}
