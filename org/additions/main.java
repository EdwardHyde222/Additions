package org.additions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.additions.files.Files;
import org.additions.recipes.API;
import org.additions.recipes.APIDS;
import org.additions.statistics.StatisticsEventHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import ru.utils.gui.Handler;

@SuppressWarnings("unused")
public class main extends JavaPlugin {
	
	private static MinecraftConsoleAPI log = new MinecraftConsoleAPI();
	private static MinecraftConsoleAPI newLog = new MinecraftConsoleAPI();
	private static FileConfiguration config;
	private Files FileMaster = new Files(this);
	private API recipe = new API(this);
	private APIDS.Storage Storage = new APIDS.Storage();
	public static List<String> lst = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		config = getConfig();
		FileMaster.setupFiles(); // <--- Creating main files of plugin
		getConfig().options().copyDefaults(true); 
		saveDefaultConfig();
		
		createLogo();
		
		if (getConfig().getBoolean("modules.W-Module.Enable")) {
			log.info(this.getDataFolder().getAbsolutePath());
			recipe.createRecipes(this.getDataFolder().getAbsolutePath() + "\\Workbench");
			log.info(getConfig().getString("messages.Modules.Workbench").replace("&", "§"));
		}
		
		if (getConfig().getBoolean("modules.A-Module.Enable")) {
			getCommand("as").setExecutor(new PluginCommands(this));
			this.getServer().getPluginCommand("as").setTabCompleter(new PluginTabCompleter());
			log.info(getConfig().getString("messages.Modules.Abbreviation").replace("&", "§"));
		}
		
		if (getConfig().getBoolean("modules.C-Module.Enable")) {
			getCommand("additions").setExecutor(new PluginCommands(this));
			this.getServer().getPluginCommand("additions").setTabCompleter(new PluginTabCompleter());
			log.info(getConfig().getString("messages.Modules.Command").replace("&", "§"));
		}
		
		this.getServer().getPluginManager().registerEvents(new PluginHandler(this), this);
		this.getServer().getPluginManager().registerEvents(new StatisticsEventHandler(), this);
		this.getServer().getPluginManager().registerEvents(new Handler(this), this);
		
		// API --> {DATA} --> APIDS --> FUNCTIONS
		
		log.info("Loaded " + Storage.getItemsAmount() + " custom items.");
	}
	
	private void createLogo() {
		newLog.setPrefix("   ");
		newLog.info("§9§l╔═════╗ ╔════╗");
		newLog.info("§9§l║ ╔═╗ ║ ║ ╔══╝");
		newLog.info("§9§l║ ╚═╝ ║ ║ ╚══╗  §b§lAdditions §fV2.0.1");
		newLog.info("§9§l║ ╔═╗ ║ ╚══╗ ║  §8§lDeveloped by ILucious");
		newLog.info("§9§l║ ║ ║ ║ ╔══╝ ║");
		newLog.info("§9§l╚═╝ ╚═╝ ╚════╝");
	}
}
