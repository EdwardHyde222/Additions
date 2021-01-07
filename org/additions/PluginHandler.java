package org.additions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.additions.recipes.API;
import org.additions.recipes.APIDS;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ru.utils.gui.GUI;
import ru.utils.gui.GUI.GUI_Tools;

public class PluginHandler implements Listener {
	
	Logger log = Logger.getLogger("Minecraft");
	private APIDS.Storage Storage = new APIDS.Storage();
	private main plugin = null;
	
	public PluginHandler(main additionsMain) {
		this.plugin = additionsMain;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if (plugin.getConfig().getBoolean("custom-texturepack.Enabled"))
        	event.getPlayer().setResourcePack(plugin.getConfig().getString("custom-texturepack.Link"));
        return;
    }
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		GUI_Tools Tool = new GUI_Tools();
		boolean found = false;
		
		Player player = (Player) event.getWhoClicked();
		
		for (String item : ru.utils.gui.Storage.Titles) {
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
		
		for (int i = 0; i < Storage.getItemsAmount(); i++) {
			if (event.getCurrentItem().isSimilar(Storage.getItem(i))) {
				GUI gui = new GUI();
				
				List<String> Information1 = new ArrayList<String>();
				List<String> Information2 = new ArrayList<String>();
				List<String> Information3 = new ArrayList<String>();
				List<String> Information4 = new ArrayList<String>();
				List<String> Information5 = new ArrayList<String>();
				
				gui.setName("§a§lRecipe");
				gui.createGUI(27);
				gui.includeHandler();
				
				/*
				 * RECIPE SECTION
				 */
				gui.setItem(0, Storage.getIngredient(i, 1));
				gui.setItem(1, Storage.getIngredient(i, 2));
				gui.setItem(2, Storage.getIngredient(i, 3));
				
				gui.setItem(9, Storage.getIngredient(i, 4));
				gui.setItem(10, Storage.getIngredient(i, 5));
				gui.setItem(11, Storage.getIngredient(i, 6));
				
				gui.setItem(18, Storage.getIngredient(i, 7));
				gui.setItem(19, Storage.getIngredient(i, 8));
				gui.setItem(20, Storage.getIngredient(i, 9));
				
				/*
				 * ENCHANTMENTS SECTION
				 */	
				if (Storage.getMeta(i).hasEnchants()) {
					for (int j = 0; j < Storage.getEnchants(i).size(); j++) {
						if (j == 0)
							Information1.add("§c§lEnchantments: §f" + Storage.getEnchants(i).get(j).toString().toUpperCase());
						else
							Information1.add("                      §f" + Storage.getEnchants(i).get(j).toString().toUpperCase());
					}
				} 
				else
					Information1.add("§c§lEnchantments: §7§oNot specified");
				/*
				 * DISPLAY NAME SECTION
				 */
				if (Storage.getMeta(i).hasDisplayName())
					Information2.add("§c§lDisplay name: " + Storage.getItemName(i));
				else
					Information2.add("§c§lDisplay name: §7§oNot specified");
				/*
				 * LORE SECTION
				 */
				if (Storage.getMeta(i).hasLore()) {
					if (!Storage.getItemLore(i).isEmpty()) {
						for (int j = 0; j < Storage.getItemLore(i).size(); j++) {
							if (j == 0)
								Information3.add("§c§lLore: " + Storage.getItemLore(i).get(j));
							else
								Information3.add("         " + Storage.getItemLore(i).get(j));
						}
					}
				}
				else
					Information3.add("§c§lLore: §7§oNot specified");
				/*
				 * DURABILITY SECTION
				 */
				Information4.add("§c§lDurability: §f" + Storage.getDurability(i));
				/*
				 * AMOUNT SECTION
				 */
				Information4.add("§c§lAmount: §f" + Storage.getAmount(i));
				/*
				 * MODEL_DATA SECTION
				 */
				if (Storage.getMeta(i).hasCustomModelData())
					Information5.add("§c§lModel-Data: " + String.valueOf(Storage.getModelData(i)));
				else
					Information5.add("§c§lModel-Data: §7§oNot specified");
				
				//  4  5  6  7
				// 13 14 15 16 *
				// 22 23 24 25
				
				gui.setItem(3, Tool.Background_Item);
				gui.setItem(12, Tool.Background_Item);
				gui.setItem(21, Tool.Background_Item);
				gui.setItem(8, Tool.Background_Item);
				gui.setItem(26, Tool.Background_Item);
				
				
				Tool.Info_Item.setLore(Information1);
				gui.setItem(4, Tool.Info_Item);
				
				Tool.Info_Item.setLore(Information2);
				gui.setItem(5, Tool.Info_Item);
				
				Tool.Info_Item.setLore(Information3);
				gui.setItem(6, Tool.Info_Item);
				
				Tool.Info_Item.setLore(Information4);
				gui.setItem(7, Tool.Info_Item);
				
				Tool.Info_Item.setLore(Information5);
				gui.setItem(8, Tool.Info_Item);
				
				
				
				gui.setItem(17, Tool.Close_Item);
				
				gui.openGUI(player);
			}
		}
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent e) {
		if (e.getView().getPlayer() instanceof Player) {
			Player player = (Player) e.getView().getPlayer();

			for (int i = 0; i < Storage.getItemsAmount(); i++) {
				ItemStack result = e.getRecipe().getResult();

				if (Storage.getItem(i).isSimilar(result)) {
					if (!player.hasPermission("additions.craft." + Storage.getCraftPermission(i))
							&& !Storage.getCraftPermission(i).equalsIgnoreCase("NO_PERM_BY_ADMIN")) {
						player.sendMessage(plugin.getConfig().getString("messages.no-craft-permission"));
						e.setCancelled(true);
						break;
					}
				}
			}
		}
	}
}