package ga.cyanoure.cyanmessages.bungee;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Language {
	public String LangCode = "hu";
	public Configuration LangConfig;
	private Main Plugin;
	
	public Language(Main plugin,String code) {
		LangCode = code;
		Plugin = plugin;
		LoadLanguage();
	}
	
	public void LoadLanguage() {
		if(!Plugin.getDataFolder().exists()) {
			Plugin.getDataFolder().mkdir();
		}
		
		File langFolder = new File(Plugin.getDataFolder(),"lang");
		if(!langFolder.exists()) {
			langFolder.mkdir();
		}
		
		File f = new File(new File(Plugin.getDataFolder(),"lang"),LangCode+".yml");
		if(!f.exists()) {
			try (InputStream in = Plugin.getResourceAsStream("lang/"+LangCode+".yml")){
				if(in != null) {
					//Files.copy(in, f.toPath());
					LangConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(in);
				}else {
					Plugin.logMSG("&4ERROR! Language file not found. ("+LangCode+".yml)");
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				LangConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Plugin.getDataFolder(), "lang/"+LangCode+".yml"));
			} catch (IOException e) {
				Plugin.logMSG("&4ERROR!");
				e.printStackTrace();
			}
		}
	}
	
	public String GetText(String name) {
		return LangConfig.getString(name);
	}
}
