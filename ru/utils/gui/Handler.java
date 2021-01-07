package ru.utils.gui;

import org.additions.MinecraftConsoleAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Handler extends GUI implements Listener {
	MinecraftConsoleAPI log = new MinecraftConsoleAPI();
	
	public Handler(org.additions.main plugin) {
		super(plugin);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		GUI_Tools Tool = new GUI_Tools();
		boolean found = false;
		
		Player player = (Player) event.getWhoClicked();
		
		for (String item : Titles) {
			if (event.getView().getTitle().contains(item)) {
				found = true;
			}
		}
		
		if (!found)
			return;
		if (event.getCurrentItem() == null)
			return;
		if (event.getCurrentItem().getItemMeta() == null)
			return;
		
		event.setCancelled(true);
		
		if (event.getCurrentItem().isSimilar(Tool.Close_Item)) {
			player.closeInventory();
		}
	}
}
