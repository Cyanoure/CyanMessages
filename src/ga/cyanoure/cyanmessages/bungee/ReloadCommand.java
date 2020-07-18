package ga.cyanoure.cyanmessages.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {
	Main plugin;
	public ReloadCommand(Main plugin) {
		super("msgreload","cyanmessages.reload");
		this.plugin = plugin;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		this.plugin.Reload();
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.prefix+this.plugin.lang.GetText("config-reloaded")));
	}
}
