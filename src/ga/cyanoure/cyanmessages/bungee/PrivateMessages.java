package ga.cyanoure.cyanmessages.bungee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PrivateMessages {
	public Main plugin;
	public Map<UUID,UUID> replies = new HashMap<UUID,UUID>();
	public PrivateMessages(Main plugin) {
		this.plugin = plugin;
	}
	public List<UUID> DisabledList = new ArrayList<UUID>();
	
	public void sendMessage(ProxiedPlayer from, ProxiedPlayer to, String message) {
		if(!DisabledList.contains(from.getUniqueId())) {
			if(to != null) {
				if(from != to/* || true*/) {
					if(!DisabledList.contains(to.getUniqueId()) && to.hasPermission("cyanmessages.private")) {
						String SenderName = from.getName();
						String ReceiverName = to.getName();
						//String msg1 = "&8[&c…n &3ª&6 "+ReceiverName+"&8] &7"+message;
						//String msg2 = "&8[&6"+SenderName+" &3ª&c …n&8] &7"+message;
						
						String msg1 = this.plugin.config.getString("pm-format").replace("<sender>", this.plugin.lang.GetText("me")).replace("<receiver>", ReceiverName).replace("<message>", message);
						String msg2 = this.plugin.config.getString("pm-format").replace("<receiver>", this.plugin.lang.GetText("me")).replace("<sender>", SenderName).replace("<message>", message);
						
						from.sendMessage(ChatColor.translateAlternateColorCodes('&', msg1));
						to.sendMessage(ChatColor.translateAlternateColorCodes('&', msg2));
						//this.plugin.simpleMessage("&8[&7["+from.getServer().getInfo().getName()+"] &6"+SenderName+" &3ª &7["+to.getServer().getInfo().getName()+"] &6"+ReceiverName+"&8] &7"+message);
						this.plugin.simpleMessage(this.plugin.config.getString("pm-format-console").replace("<sender_server>", from.getServer().getInfo().getName()).replace("<receiver_server>", to.getServer().getInfo().getName()).replace("<sender>", from.getName()).replace("<receiver>", to.getName()).replace("<message>", message));
						
						if(replies.containsKey(from.getUniqueId())) {
							replies.remove(from.getUniqueId());
						}
						if(replies.containsKey(to.getUniqueId())) {
							replies.remove(to.getUniqueId());
						}
						replies.put(from.getUniqueId(), to.getUniqueId());
						replies.put(to.getUniqueId(), from.getUniqueId());
					}else {
						from.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.lang.GetText("pm-receiver-disabled")));
					}
				}else {
					from.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.lang.GetText("pm-msg-self")));
				}
			}else {
				if(replies.containsKey(from.getUniqueId())) {
					replies.remove(from.getUniqueId());
				}
				from.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.lang.GetText("player-offline")));
			}
		}else {
			from.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.lang.GetText("pm-sender-disabled")));
		}
	}
	
	public void replyMessage(ProxiedPlayer from, String message) {
		if(replies.containsKey(from.getUniqueId())) {
			ProxiedPlayer to = plugin.getProxy().getPlayer(replies.get(from.getUniqueId()));
			this.sendMessage(from, to, message);
		}
	}
}
