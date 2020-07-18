package ga.cyanoure.cyanmessages.bungee;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Main extends Plugin{
	public Configuration config;
	public PrivateMessages pm;
	public Language lang;
	
	public String prefix = "&8[&3CyanMessages&8] &3";
	
	public void pluginMessage(String msg) {
		//String prefix = this.config.getString("prefix");
		getLogger().info(ChatColor.translateAlternateColorCodes('&', msg));
		//getProxy().getConsole().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	
	public void simpleMessage(String msg) {
		getProxy().getConsole().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	
	public void logMSG(String msg) {
		pluginMessage(msg);
	}
	
	@Override
    public void onEnable() {
		this.pm = new PrivateMessages(this);
        getProxy().getPluginManager().registerCommand(this, new PMCommand(this));
        getProxy().getPluginManager().registerCommand(this, new ReplyCommand(this));
        getProxy().getPluginManager().registerCommand(this, new HelpopCommand(this));
        getProxy().getPluginManager().registerCommand(this, new ToggleCommand(this));
        getProxy().getPluginManager().registerListener(this, new Events(this));
        getProxy().getPluginManager().registerCommand(this, new ReloadCommand(this));
        Load();
    }
	
	public void Load() {
		CopyConfig("config.yml");
		config = LoadConfig("config.yml");
		prefix = config.getString("prefix");
        
        lang = new Language(this,config.getString("language"));
	}
	
	public void Reload() {
		Load();
		pluginMessage(lang.GetText("config-reloaded"));
	}
	
	public Configuration LoadConfig(String file) {
		try {
			return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void CopyConfig(String file) {
		if(!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		
		File f = new File(getDataFolder(),file);
		if(!f.exists()) {
			try (InputStream in = getResourceAsStream(file)){
				Files.copy(in, f.toPath());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
