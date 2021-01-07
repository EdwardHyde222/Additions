package org.additions.statistics;

import org.additions.MinecraftConsoleAPI;
import org.additions.main;
import org.additions.recipes.APIDS;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StatisticsEventHandler implements Listener {
	
	private MinecraftConsoleAPI log = new MinecraftConsoleAPI();
	private APIDS.Storage Storage = new APIDS.Storage();
	public StatisticsEventHandler() {}
	
	@EventHandler
	public void onCraft(CraftItemEvent e) throws Exception {
		if (e.getView().getPlayer() instanceof Player) {
			Player player = (Player) e.getView().getPlayer();

			for (int i = 0; i < Storage.getItemsAmount(); i++) {
				ItemStack result = e.getRecipe().getResult();

				if (Storage.getItem(i).isSimilar(result)) {
					if (!player.hasPermission("additions.craft." + Storage.getCraftPermission(i))
							&& !Storage.getCraftPermission(i).equalsIgnoreCase("NO_PERM_BY_ADMIN")) {
						player.sendMessage("§cYou don't have permission to craft this item!");
						e.setCancelled(true);
						break;
					} else {
						ItemStack craftedItem = e.getInventory().getResult(); // Get result of recipe
						Inventory Inventory = e.getInventory(); // Get crafting inventory
						ClickType clickType = e.getClick();
						
						int realAmount = 0;
						
						ItemStack ItemInCursor = new ItemStack(e.getCursor().getType(), 1);
						ItemStack ItemToCompare = new ItemStack(Material.AIR, 1);
						
						if (ItemInCursor.isSimilar(ItemToCompare)) {
							if (clickType.isShiftClick()) {
								if (clickType.isKeyboardClick())
									realAmount = 1;
								else {
								int lowerAmount = craftedItem.getMaxStackSize() + 1000; // Set lower at recipe result
																						// max stack size + 1000
																						// (or just higher max stacksize
																						// of reciped item)
								for (ItemStack actualItem : Inventory.getContents()) // For each item in crafting
																						// inventory
								{
									// if slot is not air && lowerAmount is
									// highter than this slot amount && it's not the recipe amount
									if (!actualItem.getType().isAir() && lowerAmount > actualItem.getAmount()
											&& !actualItem.isSimilar(craftedItem))
										lowerAmount = actualItem.getAmount(); // Set new lower amount
								}

								int cells = 0;
								for (ItemStack it : player.getInventory().getContents()) {
									if (it != null)
										cells++;
								}
								int fullOfCells = 36 - cells;

								// If amount of crafted items will be more then void cells
								// Set amount of crafted items to amount of void cells
								if (lowerAmount > fullOfCells)
									realAmount = fullOfCells;

								// Calculate the final amount
								else
									realAmount = lowerAmount * craftedItem.getAmount();
								}
							} 
							else if (clickType.isKeyboardClick())
								realAmount = 1;
							else
								realAmount = craftedItem.getAmount();
						} else {
							if (clickType.isKeyboardClick())
								realAmount = 0;
							else if (clickType.isShiftClick()) {
								if (clickType.isKeyboardClick())
									realAmount = 1;
								else {
								int lowerAmount = craftedItem.getMaxStackSize() + 1000; // Set lower at recipe result
																						// max stack size + 1000
																						// (or just higher max stacksize
																						// of reciped item)
								for (ItemStack actualItem : Inventory.getContents()) // For each item in crafting
																						// inventory
								{
									// if slot is not air && lowerAmount is
									// highter than this slot amount && it's not the recipe amount
									if (!actualItem.getType().isAir() && lowerAmount > actualItem.getAmount()
											&& !actualItem.isSimilar(craftedItem))
										lowerAmount = actualItem.getAmount(); // Set new lower amount
								}

								int cells = 0;
								for (ItemStack it : player.getInventory().getContents()) {
									if (it != null)
										cells++;
								}
								int fullOfCells = 36 - cells;

								// If amount of crafted items will be more then void cells
								// Set amount of crafted items to amount of void cells
								if (lowerAmount > fullOfCells)
									realAmount = fullOfCells;

								// Calculate the final amount
								else
									realAmount = lowerAmount * craftedItem.getAmount();
								}
							} 
						}
						
						log.info("realAmount: " + realAmount);
						// Writting logs to stats file
						if (realAmount > 0) {
							// Clearing file name from it's expansion
							String v1 = (Storage.getFileName(i).replace(".", " ")).split(" ")[0];
							int v2 = ((Storage.getFileName(i).replace(".", " ")).split(" ")).length;
							String fileName = v1 + " (" + (Storage.getFileName(i).replace(".", " ")).split(" ")[v2 - 1] + ")";
							
							// Creating folders to save data about crafting
							Statistics.createFolder(Folders.ALL);
							Statistics.createFile(Files.DATA);
							Statistics.setData(fileName, (int) Statistics.getData(fileName) + realAmount);
//							if (!Statistics.getData(fileName + ".CraftedBy").toString().contains(player.getName())) {
//								if ((int) Statistics.getData(fileName + ".CraftedBy") == 0)
//									Statistics.setData(fileName, player.getName() + ", ");
//								else
//									Statistics.setData(fileName, (String) Statistics.getData(fileName + ".CraftedBy") + player.getName() + ", ");
//							}
//							Statistics.createFile(Files.README);
//							e.getView().getPlayer().sendMessage("Statistics updated!");
						}
					}
				}
			}
		}
	} 
}
