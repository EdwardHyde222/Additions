package org.additions.files;

import java.io.File;

import org.additions.MinecraftConsoleAPI;
import org.additions.main;

public class Files {
	private static main plugin = null;
	private static MinecraftConsoleAPI log = new MinecraftConsoleAPI();
	private final static File v1 = new File("plugins/Additions/Modules");
	
	public Files(main plugin) {
		Files.plugin = plugin;
	}
	
	public static void createFile(FileType Type) {
		if (Type == FileType.CONFIG) {
			File config = new File("plugins/Additions/config.yml");
			if (!config.exists()) {
				if (plugin.getConfig().getBoolean("messages.Enabled")) 
					log.info(plugin.getConfig().getString("messages.Configuration.successfully").replace("&", "§"));
			} else {
				if (plugin.getConfig().getBoolean("messages.Enabled")) 
					log.info(plugin.getConfig().getString("messages.Configuration.unsuccessfully").replace("&", "§"));
			}
		}
		
		else if (Type == FileType.PLAYER_JOIN_EVENT) {
			File event = new File(v1 + "\\PlayerJoinEvent.jar");
			try {event.createNewFile();} catch (Exception e) {}
		}
		
		else if (Type == FileType.PLAYER_DEATH_EVENT) {
			File event = new File(v1 + "\\PlayerDeathEvent.jar");
			try {event.createNewFile();} catch (Exception e) {}
		}
		
		else if (Type == FileType.PLAYER_RESPAWN_EVENT) {
			File event = new File(v1 + "\\PlayerRespawnEvent.jar");
			try {event.createNewFile();} catch (Exception e) {}
		}
		
		else if (Type == FileType.PLAYER_CHAT_EVENT) {
			File event = new File(v1 + "\\PlayerChatEvent.jar");
			try {event.createNewFile();} catch (Exception e) {}
		}
		
		else if (Type == FileType.PLAYER_INTERACT_EVENT) {
			File event = new File(v1 + "\\PlayerInteractEvent.jar");
			try {event.createNewFile();} catch (Exception e) {}
		}
		
		else if (Type == FileType.ENTITY_DEATH_EVENT) {
			File event = new File(v1 + "\\EntityDeathEvent.jar");
			try {event.createNewFile();} catch (Exception e) {}
		}
	}
	
	public static void createFolder(Folders Folder) {
		if (Folder == Folders.MAIN) {
			File folder = new File("plugins/Additions");
			boolean flag = folder.mkdir(); 
			if (flag) {
				if (plugin.getConfig().getBoolean("messages.Enabled")) 
					log.error(plugin.getConfig().getString("messages.Folders.Additions.successfully").replace("&", "§")); 
			} else {
				if (plugin.getConfig().getBoolean("messages.Enabled"))
					log.error(plugin.getConfig().getString("messages.Folders.Additions.unsuccessfully").replace("&", "§"));
			}
		}
		
		else if (Folder == Folders.WORKBENCH) {
			File folder = new File("plugins/Additions/Workbench");
			boolean flag = folder.mkdir(); 
			if (flag) {
				if (plugin.getConfig().getBoolean("messages.Enabled")) 
					log.error(plugin.getConfig().getString("messages.Folders.Workbench.successfully").replace("&", "§")); 
			} else {
				if (plugin.getConfig().getBoolean("messages.Enabled"))
					log.error(plugin.getConfig().getString("messages.Folders.Workbench.unsuccessfully").replace("&", "§"));
			}
		}
		
		else if (Folder == Folders.MODULES) {
			File folder = new File("plugins/Additions/Modules");
			boolean flag = folder.mkdir(); 
			if (flag) {
				if (plugin.getConfig().getBoolean("messages.Enabled")) 
					log.error(plugin.getConfig().getString("messages.Folders.Modules.successfully").replace("&", "§")); 
			} else {
				if (plugin.getConfig().getBoolean("messages.Enabled"))
					log.error(plugin.getConfig().getString("messages.Folders.Modules.unsuccessfully").replace("&", "§"));
			}
		}
	}
	
	public void setupFiles() {
		//////////////////////////////////////
		// PLUGIN TREE:
		//   - [ Additions ]:
		//		-- [ Modules ]:
		//		   --- PlayerJoinEvent.jar
		//		   --- PlayerDeathEvent.jar
		//		   --- PlayerRespawnEvent.jar
		//		   --- PlayerChatEvent.jar
		//		   --- PlayerInteractEvent.jar
		//		   --- EntityDeathEvent.jar
		//		-- [ Workbench ]:
		//		   --- *some files..*
		//		- config.yml
		//////////////////////////////////////
		// Files package by ILucious 
		
		// ---> Creating folder 'Additions'
		createFolder(Folders.MAIN);
			// ---> Creating folder 'Modules'
			createFolder(Folders.MODULES);
				// ---> Creating event files
				createFile(FileType.PLAYER_CHAT_EVENT);
				createFile(FileType.PLAYER_DEATH_EVENT);
				createFile(FileType.PLAYER_INTERACT_EVENT);
				createFile(FileType.PLAYER_RESPAWN_EVENT);
				createFile(FileType.PLAYER_JOIN_EVENT);
				createFile(FileType.ENTITY_DEATH_EVENT);
			// ---> Creating folder 'Workbench'
			createFolder(Folders.WORKBENCH);
			// ---> Creating 'config.yml' file
			createFile(FileType.CONFIG);
			
		// End of setup //
	}
}
