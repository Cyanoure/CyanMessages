package ga.cyanoure.cyanmessages.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ToggleCommand extends Command{
	Main plugin;
	public ToggleCommand(Main plugin) {
		super("msgtoggle","cyanmessages.private");
		this.plugin = plugin;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer p = (ProxiedPlayer)sender;
		if(plugin.pm.DisabledList.contains(p.getUniqueId())) {
			plugin.pm.DisabledList.remove(plugin.pm.DisabledList.indexOf(p.getUniqueId()));
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.lang.GetText("pm-enabled")));
		}else {
			plugin.pm.DisabledList.add(p.getUniqueId());
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.lang.GetText("pm-disabled")));
		}
	}
}
