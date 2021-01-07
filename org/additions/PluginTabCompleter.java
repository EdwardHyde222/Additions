package org.additions;

import java.util.ArrayList;
import java.util.List;

import org.additions.recipes.APIDS;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class PluginTabCompleter implements TabCompleter {
	private List<String> autoCompletes = new ArrayList<>();
	private APIDS.Storage Storage = new APIDS.Storage();
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		//////////////////////////
		// AUTO COMMAND BLOCKER //
		//////////////////////////
		// TODO: Finish this method
		/*if (!sender.isOp()) {
			String lastWord = args[args.length - 1];

	        Player senderPlayer = sender instanceof Player ? (Player) sender : null;

	        ArrayList<String> matchedPlayers = new ArrayList<String>();
	        for (Player player : sender.getServer().getOnlinePlayers()) {
	            String name = player.getName();
	            if ((senderPlayer == null || senderPlayer.canSee(player)) && StringUtil.startsWithIgnoreCase(name, lastWord)) {
	                matchedPlayers.add(name);
	            }
	        }

	        Collections.sort(matchedPlayers, String.CASE_INSENSITIVE_ORDER);
	        return matchedPlayers;
		}*/
	    
		if (autoCompletes.isEmpty()) {										
			autoCompletes.add("export");
			autoCompletes.add("get");
			autoCompletes.add("give");
			autoCompletes.add("import");
			autoCompletes.add("info");
			autoCompletes.add("list");
			autoCompletes.add("recipes");
			autoCompletes.add("reload");
		} 
		
		List<String> result = new ArrayList<>();
		if (args.length == 1) {
			for (String a : autoCompletes) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase()))
					result.add(a);
			}
			return result;
		}
		
		else if (args[0].equalsIgnoreCase("recipes") || args[0].equalsIgnoreCase("info")
				|| args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("export")
				|| args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
			List<String> autoCompletes = new ArrayList<>();

			autoCompletes.add("");

			return autoCompletes;
		}
		//////////////////////////////
		// GIVE COMMAND COMIPLATION //
		//////////////////////////////
		else if (args[0].equalsIgnoreCase("give") && args.length == 2) {		
			List<String> autoCompletes = new ArrayList<>();
			
			for (Player item : Bukkit.getServer().getOnlinePlayers())
				autoCompletes.add(item.getName());
			
			return autoCompletes;
		}
		else if (args[0].equalsIgnoreCase("give") && args.length == 3) {		
			List<String> autoCompletes = new ArrayList<>();
			
			for (int i = 0; i < Storage.getItemsAmount(); i++)
				autoCompletes.add(String.valueOf(i));
			
			return autoCompletes;
		}
		else if (args[0].equalsIgnoreCase("give") && args.length == 4) {		
			List<String> autoCompletes = new ArrayList<>();
			
			autoCompletes.add("64");
			autoCompletes.add("32");
			autoCompletes.add("16");
			
			return autoCompletes;
		}
		else if (args[0].equalsIgnoreCase("give") && args.length > 4) {		
			List<String> autoCompletes = new ArrayList<>();
			
			autoCompletes.add("");
			
			return autoCompletes;
		}
		//////////////////////////////
		// GET COMMAND COMIPLATION  //
		//////////////////////////////
		else if (args[0].equalsIgnoreCase("get") && args.length == 2) {		
			List<String> autoCompletes = new ArrayList<>();
			
			for (int i = 0; i < Storage.getItemsAmount(); i++)
				autoCompletes.add(String.valueOf(i));
			
			return autoCompletes;
		}
		else if (args[0].equalsIgnoreCase("get") && args.length == 3) {		
			List<String> autoCompletes = new ArrayList<>();
			
			autoCompletes.add("64");
			autoCompletes.add("32");
			autoCompletes.add("16");
			
			return autoCompletes;
		}
		else if (args[0].equalsIgnoreCase("get") && args.length > 3) {		
			List<String> autoCompletes = new ArrayList<>();
			
			autoCompletes.add("");
			
			return autoCompletes;
		}	
		////////////////////////////////
		// IMPORT COMMAND COMPILATION //
		////////////////////////////////
		else if (args[0].equalsIgnoreCase("import") && args.length == 2) {	
			List<String> autoCompletes = new ArrayList<>();
			
			for (int i = 0; i < Storage.getItemsAmount(); i++)
				autoCompletes.add(String.valueOf(i));
			
			return autoCompletes;
		}
		else if (args[0].equalsIgnoreCase("import") && args.length > 2) {	
			List<String> autoCompletes = new ArrayList<>();
			
			autoCompletes.add("");
			
			return autoCompletes;
		}
		return null;
	}
}
