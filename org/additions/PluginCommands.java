package org.additions;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.additions.recipes.API;
import org.additions.recipes.APIDS;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import ru.utils.gui.GUI;
import ru.utils.gui.GUI.GUI_Tools;

import org.additions.files.Files;

public class PluginCommands implements CommandExecutor, Listener{
	MinecraftConsoleAPI log = new MinecraftConsoleAPI();
	private APIDS.Storage Storage = new APIDS.Storage();
	private main plugin;
	Files FileMaster = new Files(plugin);
	APIDS APIDSystem = new APIDS();
	
	public PluginCommands(main plugin) { this.plugin = plugin; }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (args.length > 0) {
			/////////////////////////////////////////
			//			   LIST COMMAND		       //
			/////////////////////////////////////////
			if (args[0].equalsIgnoreCase("list"))
			{
				//////////////////////////////////////
				// 			PERMISSION CHECK		//
				//////////////////////////////////////
				if (!sender.hasPermission("additions.items_list") || !sender.isOp()) {
					sender.sendMessage(plugin.getConfig().getString("messages.no-permission").replace("&", "§"));
					return true;
				}
				
				if (Storage.getItemsAmount() > 0) {
					if (sender instanceof Player) 
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Item list:"));
					else
						sender.sendMessage(" §3Additions §9Item list:");
					
					for (int i = 0; i < Storage.getItemsAmount(); i++)
						sender.sendMessage(" §8[§b" + i + "§8] §c" + Storage.getDefaultItemName(i) + " §7: §3" + Storage.getFileName(i));
				} else {
					if (sender instanceof Player)
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "No custom items found!"));
					else
						sender.sendMessage(" §3Additions §9No custom items found!");
				}
				return true;
			} 
			/////////////////////////////////////////
			//			  PLUGIN RELOAD		       //
			/////////////////////////////////////////
			else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl"))
			{
				//////////////////////////////////////
				// 			PERMISSION CHECK		//
				//////////////////////////////////////
				if (!sender.hasPermission("additions.reload") || !sender.isOp()) {
					sender.sendMessage(plugin.getConfig().getString("messages.no-permission").replace("&", "§"));
					return true;
				}
				
				try {
				    this.plugin.saveDefaultConfig();
				    this.plugin.reloadConfig();
				    this.plugin.saveConfig();
				    
			    	
			    	Storage.removeData(true);
			    	this.plugin.getServer().clearRecipes();
			    	this.plugin.getServer().resetRecipes();
			    	main.lst.clear();
			    	
			    	API recipe = new API(plugin);
			    	
					if (plugin.getConfig().getBoolean("modules.W-Module.Enable")) {
						recipe.createRecipes("plugins/Additions/Workbench/");
						log.info(plugin.getConfig().getString("messages.Modules.Workbench").replace("&", "§"));
						log.info("Loaded " + Storage.getItemsAmount() + " custom items.");
					}
				    
					if (sender instanceof Player)
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + plugin.getConfig().getString("messages.reload.successfully")));
					else
						sender.sendMessage(" §3Additions §a" + plugin.getConfig().getString("messages.reload.successfully"));
				} catch (Exception e) {
					if (sender instanceof Player)
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + plugin.getConfig().getString("messages.reload.unsuccessfully")));
					else
						sender.sendMessage(" §3Additions §c" + plugin.getConfig().getString("messages.reload.unsuccessfully"));
				}
				return true;
			} 
			/////////////////////////////////////////
			//			  	PLUGIN INFO	           //
			/////////////////////////////////////////
			else if (args[0].equalsIgnoreCase("info"))
			{
				//////////////////////////////////////
				// 			PERMISSION CHECK		//
				//////////////////////////////////////
				if (!(sender instanceof Player)) {
					sender.sendMessage(" §3Additions §9Console cannot use this command! Use '/?'");
					return true;
				}
				
				if (!sender.hasPermission("additions.info") || !sender.isOp()) {
					sender.sendMessage(plugin.getConfig().getString("messages.no-permission").replace("&", "§"));
					return true;
				}
				
				else {
					for (int i = 0; i < 100; i++)
						sender.sendMessage("");
					sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "/as export <key> - §fexport a custom item by a special key (will be done in the next updates)"));
					sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "/as get <custom_item_id> [amount] - §fget a custom item from the list"));
					sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "/as give <player> <custom_item_id> [amount] - §fgives a custom item from the list"));
					sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "/as import <custom_item_id> - §fget a special key of item (will be done in the next updates"));
					sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "/as info - §fget information about plugin commands"));
					sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "/as list - §fget a list of custom items by their id (sort from 0)"));
					sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "/as recipes - §fthe menu where custom items and their crafting are located"));
					sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "/as reload - §freloads API functions, configuration file,"));
					sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Developed by §fIlucious"));
				}
			} 
			/////////////////////////////////////////
			//			 ADDITIONS STATS		   //
			/////////////////////////////////////////
			// TODO: Finish method.
			else if (args[0].equalsIgnoreCase("stats"))
			{
				//////////////////////////////////////
				// 			PERMISSION CHECK		//
				//////////////////////////////////////
				if (!sender.hasPermission("additions.stats") || !sender.isOp()) {
					sender.sendMessage(plugin.getConfig().getString("messages.no-permission").replace("&", "§"));
					return true;
				}

				else if (!(sender instanceof Player)) {
					sender.sendMessage(" §3Additions §9Cannot show GUI to console");
					sender.sendMessage(" §3Additions §9Just wait for 'console' update!");
					return true;
				}
				
				else {
					if (sender instanceof Player) {
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Currently not working! Wait for update . . "));
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Just wait for 'statistics' update!"));
					}
					else {
						sender.sendMessage(" §3Additions §9Currently not working! Wait for update . . ");
						sender.sendMessage(" §3Additions §9Just wait for 'statistics' update!");
					}
				}
			}
			/////////////////////////////////////////
			//			  GET IMPORT KEY		   //
			/////////////////////////////////////////
			else if (args[0].equalsIgnoreCase("import"))
			{
				//////////////////////////////////////
				// 			PERMISSION CHECK		//
				//////////////////////////////////////
				if (!sender.hasPermission("additions.import_items") || !sender.isOp()) {
					sender.sendMessage(plugin.getConfig().getString("messages.no-permission").replace("&", "§"));
					return true;
				}
				else {
					if (sender instanceof Player) {
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Currently not working! Wait for update . . "));
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Just wait for 'drag-and-drop' update!"));
					}
					else {
						sender.sendMessage(" §3Additions §9Currently not working! Wait for update . . ");
						sender.sendMessage(" §3Additions §9Just wait for 'drag-and-drop' update!");
					}
				}
			}
			/////////////////////////////////////////
			//	     GET ITEM FROM IMPORT KEY	   //
			/////////////////////////////////////////
			else if (args[0].equalsIgnoreCase("export"))
			{
				//////////////////////////////////////
				// 			PERMISSION CHECK		//
				//////////////////////////////////////
				if (!sender.hasPermission("additions.export_items") || !sender.isOp()) {
					sender.sendMessage(plugin.getConfig().getString("messages.no-permission").replace("&", "§"));
					return true;
				}
				else {
					if (sender instanceof Player) {
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Currently not working! Wait for update . . "));
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Just wait for 'drag-and-drop' update!"));
					}
					else {
						sender.sendMessage(" §3Additions §9Currently not working! Wait for update . . ");
						sender.sendMessage(" §3Additions §9Just wait for 'drag-and-drop' update!");
					}
				}
			}
			/////////////////////////////////////////
			//			GET CUSTOM ITEM		       //
			/////////////////////////////////////////
			else if (args[0].equalsIgnoreCase("get"))
			{
				//////////////////////////////////////
				// 			PERMISSION CHECK		//
				//////////////////////////////////////
				if (!sender.hasPermission("additions.get_items") || !sender.isOp()) {
					sender.sendMessage(plugin.getConfig().getString("messages.no-permission").replace("&", "§"));
					return true;
				}
				
				else if (!(sender instanceof Player)) {
					sender.sendMessage(" §3Additions §9Cannot give item to console");
					return true;
				}
				else if (args.length == 1) {
					sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Provide an custom item number"));
					return true;
				} 
				else if (args.length == 2) {
					Player p = (Player) sender;
					try {
						int counter = 0;
						for(ItemStack it : p.getInventory().getContents())
						    if(it != null) counter++;
						
						if (counter < 36) {
							p.getInventory().addItem(Storage.getItem(Integer.valueOf(args[1])));
							sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "You successfully get item"));
						} else {
							sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Your inventory is full"));
						}
					} catch (Exception e) {
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "The item you were looking for was not found"));
					}
					return true;
				} 
				else if (args.length == 3) {
					Player p = (Player) sender;
					try {
						int counter = 0;
						for(ItemStack it : p.getInventory().getContents())
						    if(it != null) counter++;
						
						if (counter < 36) {
							try {
								for (int i = 0; i < Integer.valueOf(args[2]); i++)
									p.getInventory().addItem(Storage.getItem(Integer.valueOf(args[1])));
								sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "You successfully get item"));
							} catch (Exception e) {
								sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Provide a correct number"));
							}
						} else {
							sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Your inventory is full"));
						}
					} catch (Exception e) {
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "The item you were looking for was not found"));
					}
					return true;
				}
			} 
			/////////////////////////////////////////
			//			GIVE CUSTOM ITEM		   //
			/////////////////////////////////////////
			else if (args[0].equalsIgnoreCase("give"))
			{
				//////////////////////////////////////
				// 			PERMISSION CHECK		//
				//////////////////////////////////////
				if (!sender.hasPermission("additions.give_items") || !sender.isOp()) {
					sender.sendMessage(plugin.getConfig().getString("messages.no-permission").replace("&", "§"));
					return true;
				}
				
				if (args.length == 1) {
					if (sender instanceof Player)
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Provide player name"));
					else
						sender.sendMessage(" §3Additions §9Provide player name");
					return true;
				} 
				else if (args.length == 2) {
					boolean found = false;
					for (Player item : Bukkit.getServer().getOnlinePlayers()) {
						if (args[1].equalsIgnoreCase(item.getName())) {
							found = true;
							break;
						}
					}
					if (found) {
						if (sender instanceof Player)
							sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Provide an custom item number"));
						else
							sender.sendMessage(" §3Additions §9Provide an custom item number");
						
						// §3Additions §9
					}
					else {
						if (sender instanceof Player)
							sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Player not found"));
						else
							sender.sendMessage(" §3Additions §9Player not found");
					}
					return true;
				} 
				else if (args.length == 3) {
					boolean found = false;
					Player target = null;
					
					for (Player item : Bukkit.getServer().getOnlinePlayers()) {
						if (args[1].equalsIgnoreCase(item.getName())) {
							target = item;
							found = true;
							break;
						}
					}
					if (!found) {
						if (sender instanceof Player)
							sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Player not found"));
						else
							sender.sendMessage(" §3Additions §9Player not found");
						return true;
					}
					
					try {
						int counter = 0;
						for(ItemStack it : target.getInventory().getContents())
						    if(it != null) counter++;
						
						if (counter < 36) {
							target.getInventory().addItem(Storage.getItem(Integer.valueOf(args[2])));
							sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Player successfully get item"));
						} else {
							sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Player's inventory is full"));
						}
					} catch (Exception e) {
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "The item you were looking for was not found"));
					}
					return true;
				}
				else if (args.length == 4) {
					boolean found = false;
					Player target = null;
					
					for (Player item : Bukkit.getServer().getOnlinePlayers()) {
						if (args[1].equalsIgnoreCase(item.getName())) {
							target = item;
							found = true;
							break;
						}
					}
					if (!found) {
						if (sender instanceof Player)
							sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Player not found"));
						else
							sender.sendMessage(" §3Additions §9Player not found");
						return true;
					}
					
					try {
						int counter = 0;
						for(ItemStack it : target.getInventory().getContents())
						    if(it != null) counter++;
						
						if (counter < 36) {
							try {
								for (int i = 0; i < Integer.valueOf(args[3]); i++)
									target.getInventory().addItem(Storage.getItem(Integer.valueOf(args[2])));
								sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Player successfully get item"));
							} catch (Exception e) {
								sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Provide a correct number"));
							}
						} else {
							sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Player's inventory is full"));
						}
					} catch (Exception e) {
						sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "The item you were looking for was not found"));
					}
					return true;
				}
			}
			/////////////////////////////////////////
			//			  RECIPES GUI		       //
			/////////////////////////////////////////
			else if (args[0].equalsIgnoreCase("recipes")) 
			{
				//////////////////////////////////////
				// 			PERMISSION CHECK		//
				//////////////////////////////////////
				if (!sender.hasPermission("additions.gui") || !sender.isOp()) {
					sender.sendMessage(plugin.getConfig().getString("messages.no-permission").replace("&", "§"));
					return true;
				}
				
				else if (!(sender instanceof Player)) {
					sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Cannot show GUI to console"));
					return true;
				}
				GUI gui = new GUI();
				
				gui.setName(ChatFormat("#7D05A3&lRecipe's list"));
				gui.createGUI_Type1();
				gui.includeHandler();
				
				int counter = 0;
				for (int i = 9; i < 45; i++) {
					if (counter < Storage.getItemsAmount())
						gui.setItem(i, Storage.getItem(counter));
					counter++;
				}
				
				gui.openGUI(sender);
				
			}
			else {
				sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889" + "Unknown command!"));
			}
			return true;
		}
		else if (args.length == 0) {
			/////////////////////////////////////////
			//			   PLUGIN INFO	           //
			/////////////////////////////////////////
			if (sender instanceof Player) {
				sender.sendMessage(ChatFormat(" #7D05A3&lAdditions #FFF889Additions ")
						+ plugin.getDescription().getVersion() + " ~"
						+ plugin.getDescription().getAuthors().toArray()[0].toString().replace("[", "").replace("]", ""));
			}
			else {
				sender.sendMessage(ChatFormat(" §3Additions §9Additions ")
						+ plugin.getDescription().getVersion() + " ~"
						+ plugin.getDescription().getAuthors().toArray()[0].toString().replace("[", "").replace("]", ""));
			}
			return true;
		} 
		return true;
	}
	
	private final static Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
	public static String ChatFormat(String message) {
		if (Bukkit.getVersion().contains("1.16")) {
			// hex colors
			Matcher match = pattern.matcher(message);
			while (match.find()) {
				String color = message.substring(match.start(), match.end());
				message = message.replace(color, ChatColor.of(color) + "");
				match = pattern.matcher(message);
			}
		}
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
