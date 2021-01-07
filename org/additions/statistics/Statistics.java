package org.additions.statistics;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.additions.main;
import org.additions.recipes.API;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.CraftItemEvent;

public class Statistics extends API{
	
	public Statistics(main plugin) {
		super(plugin);
	}
	
	public static void createFolder(Folders Folder) {
		if (Folder == Folders.AI) {
			File MainFolder = new File("plugins/Additions/Statistics");
			MainFolder.mkdir();
		}
		else if (Folder == Folders.AI) {
			File AIFolder = new File("plugins/Additions/Statistics/AI-Data");
			AIFolder.mkdir();
		}
		else if (Folder == Folders.DATA) {
			File DataFolder = new File("plugins/Additions/Statistics/Data");
			DataFolder.mkdir();
		}
		else if (Folder == Folders.TEMPLATE) {
			File TemplatesFolder = new File("plugins/Additions/Statistics/templates");
			TemplatesFolder.mkdir();
		}
		else if (Folder == Folders.ALL) {
			File MainFolder = new File("plugins/Additions/Statistics");
			MainFolder.mkdir();
			File AIFolder = new File("plugins/Additions/Statistics/AI-Data");
			AIFolder.mkdir();
			File DataFolder = new File("plugins/Additions/Statistics/Data");
			DataFolder.mkdir();
			File TemplatesFolder = new File("plugins/Additions/Statistics/templates");
			TemplatesFolder.mkdir();
		}
	}
	
	public static void createFile(Files File) {
		if (File == Files.DATA) {
			File Data = new File("plugins/Additions/Statistics/Data" + "//data.yml");
			try {
				Data.createNewFile();
			} catch (Exception e1) {}
		}
		else if (File == Files.README) {
			try {
				File README = new File("plugins/Additions/Statistics/Data/README");
				README.createNewFile();
				FileWriter myWriter = new FileWriter(README);
			    myWriter.write("The entire implementation of collecting crafting statistics is implemented by the PC World team." + "\n");
			    myWriter.write("The statistics functions have passed many error checks and have been thoroughly processed." + "\n");
			    myWriter.write("Not all functions are fully implemented and some data may be incorrect due to unexpected situations/bugs/errors." + "\n");
			    myWriter.write("All errors will accumulate in the templates folder, which will be encrypted with a special key for security purposes." + "\n");
			    myWriter.write("You can send data about errors to developers if you want to fix them in the future." + "\n");
			    myWriter.write(" " + "\n");
			    myWriter.write("PS -> Dedicated to one of the main developers of the server." + "\n");
			    myWriter.write("The implementation of these functions took a very long time," + "\n");
			    myWriter.write("but we are glad that we were able to help you, ILucious." + "\n");
			    myWriter.write("This was the last work of our team, and I think you can understand why we left." + "\n");
			    myWriter.write("Everyone was very happy to spend time coding together (by RBRMarkWebber and other team members)" + "\n");
			    myWriter.write(" " + "\n");
			    myWriter.write("Last code update: 02.07.2020" + "\n");
			    myWriter.write("ILucious quote: " + "\n");
			    myWriter.write("		As long as I remain the winner," + "\n");
			    myWriter.write("						the rest doesn't matter.");
			    myWriter.close();
			} catch (Exception z) {}
		}
	}
	
	public static File getDataFile() {
		File Data = new File("plugins/Additions/Statistics/Data" + "//data.yml");
		return Data;
	}
	
	public static void setData(String key, Object value) throws Exception {
		FileConfiguration AdditionalData = YamlConfiguration.loadConfiguration(new FileReader(Statistics.getDataFile()));
		AdditionalData.set(key, value);
		AdditionalData.options().copyDefaults(true);
		AdditionalData.save(Statistics.getDataFile());
	}
	
	public static Object getData(String key) throws Exception {
		FileConfiguration AdditionalData = YamlConfiguration.loadConfiguration(new FileReader(Statistics.getDataFile()));
		if (AdditionalData.get(key) == null)
			return 0;
		else
			return AdditionalData.get(key);
	}
}
