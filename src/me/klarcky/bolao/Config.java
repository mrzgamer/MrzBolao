package me.klarcky.bolao;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	private String FName;
	private Main plugin;

	public Config(Main plugin, String FN) {
		this.FName = FN;
		this.plugin = plugin;
	}

	public Config(Main plugin) {
		this.FName = "config";
		this.plugin = plugin;
	}

	private FileConfiguration customConfig = null;
	private File customConfigFile = null;

	public void reloadConfig() throws UnsupportedEncodingException {
		if (customConfigFile == null) {
			customConfigFile = new File(plugin.getDataFolder(), FName + ".yml");
		}
		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

		// Look for defaults in the jar
		Reader defConfigStream = new InputStreamReader(plugin.getResource(FName + ".yml"), "UTF8");
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			customConfig.setDefaults(defConfig);
		}
	}

	public FileConfiguration getConfig() throws UnsupportedEncodingException {
		if (customConfig == null) {
			reloadConfig();
		}
		return customConfig;
	}

	public void SaveConfig() throws IOException {
		if (customConfig == null || customConfigFile == null) {
			return;
		}
		try {
			getConfig().save(customConfigFile);
		} catch (IOException ex) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
		}
	}

	public void SaveDefault() {
		if (customConfigFile == null) {
			customConfigFile = new File(plugin.getDataFolder(), FName + ".yml");
		}
		if (!customConfigFile.exists()) {
			plugin.saveResource(FName + ".yml", false);
		}
	}

	public String getDataFolder() {
		// TODO Auto-generated method stub
		return null;
	}
}
