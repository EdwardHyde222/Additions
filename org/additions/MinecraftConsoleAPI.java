package org.additions;

import java.util.logging.Logger;

import org.bukkit.ChatColor;

public class MinecraftConsoleAPI {
	
	private String prefix = "§9[Additions] ";
	private Boolean include_colors = false;
	private Logger log = Logger.getLogger("Minecraft");
	
	public void info(String message) {
		if (include_colors) log.info(prefix + ChatColor.GRAY + message);
		else log.info(prefix + message.replace('&', '§'));
	}
	
	public void warning(String message) {
		if (include_colors) log.warning(prefix + ChatColor.GOLD + message);
		else log.warning(prefix + message.replace('&', '§'));
	}
	
	public void error(String message) {
		if (include_colors) log.severe(prefix + ChatColor.RED + message);
		else log.severe(prefix + message.replace('&', '§'));
	}
	
	//////////////////////////////////////
	//				SETTERS				//
	//////////////////////////////////////
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void includeAnotherColors(boolean include) {
		this.include_colors = include;
	}
	
	public void setAnotherLogger(String loggerName) {
		this.log = Logger.getLogger(loggerName);
	}
	
	//////////////////////////////////////
	//				GETTERS				//
	//////////////////////////////////////
	public String getPrefix() {
		return this.prefix;
	}
	
	public boolean getAnotherColorStatement() {
		return this.include_colors;
	}
	
	public Logger getLogger() {
		return this.log;
	}
}