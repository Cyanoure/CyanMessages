package ga.cyanoure.cyanmessages.bungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Events implements Listener {
	private Main plugin;
	public Events(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onLeave(PlayerDisconnectEvent e) {
		ProxiedPlayer p = e.getPlayer();
		if(plugin.pm.replies.containsKey(p.getUniqueId())) {
			plugin.pm.replies.remove(p.getUniqueId());
		}
	}
}
