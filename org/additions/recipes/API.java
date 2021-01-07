package org.additions.recipes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.additions.MinecraftConsoleAPI;
import org.additions.main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("deprecation")
public class API extends Object_Identification {
	/*
	 * LOCAL DATA
	 * RECODED API V 2.0
	 * BY ILUCIOUS
	 */
	
	private 	MinecraftConsoleAPI 	log = new MinecraftConsoleAPI();
	private 	main 					plugin = null;
	private 	FileConfiguration 		config = null;
	private 	List<String>			ErrorDataCollection = new ArrayList<String>();
	
	private	String			ItemAmount = null;
	private	String			Durability = null;
	private String 			FilePath = null;
	private ItemStack 		Item = null;
	private ItemMeta 		meta = null;
	private	boolean			Unbreakable = false;
	private boolean 		Working = false;
	private	String			CustomModelData = null;
	private	String			RecipePermission = null;
	private String 			ItemName = null;
	private String 			DefaultItemName = null;
	private String 			FileName = null;
	private List<String> 	ItemRecipe = null;
	
	private List<ItemStack> v1 = new ArrayList<ItemStack>();
	private List<ItemStack> v2 = new ArrayList<ItemStack>();
	private List<ItemStack> v3 = new ArrayList<ItemStack>();
	
	private List<String> 	ItemLore = null;
	private	List<String> 	fileList = new ArrayList<String>();
	private	List<String> 	Enchantments = null;
	
	private	String			AttackDamage = null;
	private	String			AttackSpeed = null;
	
	private ItemStack solveItem(String line, List<String> fileList) {
		ItemStack itemEx = new ItemStack(Material.AIR);
		try {
			itemEx = new ItemStack(Material.matchMaterial(line.replace("null", "AIR")), 1);
		} catch (Exception e) {
			for (int i = 0; i < fileList.size(); i++) {
				if (line.equalsIgnoreCase(fileList.get(i).replace(".yml", ""))) {
					API newAPI = new API(this.plugin);
					newAPI.createRecipe("plugins/Additions/Workbench/" + fileList.get(i));
					itemEx = newAPI.Item;
					break;
				}
			}
		}
		return itemEx;
	}
	
	private List<Enchantment> getEnchantments() {
		List<Enchantment> temp = new ArrayList<Enchantment>();
		for (int i = 0; i < Enchantments.size(); i++) {
			Enchantment tempv1 = EnchantmentTypeResolve(Enchantments.get(i));
			temp.add(tempv1);
		}
		return temp;
	}
	
	private void createRecipe(String path, List<String> fileList) {
		try {
			for (int i = 0; i < fileList.size(); i++) {
				this.config = YamlConfiguration.loadConfiguration(new FileReader(path + "\\" + fileList.get(i))); // <--- Creating JSON configuration reader
				this.FilePath = path; // <--- Saving path to current file (for debug may be)
				this.FileName = fileList.get(i); // <--- Getting file name from the path
				this.log.setPrefix("§e[Additions : API] "); // <--- Setting up prefix to console logger
				this.RegisterRecipe(); // <--- Creating recipe with metadata
			}
		} catch (Exception e) {
			this.log.setPrefix("[Additions : API] "); // <--- Setting up prefix to console logger
			log.error(path + " --> " + plugin.getConfig().getString("messages.bad_path").replace("&", "§"));
			this.Working = false;
		}
	}
	
	public void createRecipes(String path) {
		try {
			File dir = new File(path);
			for ( File fileT : dir.listFiles() ){
				if ( fileT.isFile() )
					this.fileList.add(fileT.getName());
			}
			this.createRecipe(path, fileList);
		} catch (Exception e) {
			this.log.setPrefix("[Additions : API] "); // <--- Setting up prefix to console logger
			log.error(plugin.getConfig().getString("messages.bad_path").replace("&", "§"));
			this.Working = false;
		}
	}
	
	private void createRecipe(String path) {
		try {
			this.config = YamlConfiguration.loadConfiguration(new FileReader(path)); // <--- Creating JSON configuration reader
			FilePath = path; // <--- Saving path to current file (for debug may be)
			FileName = path.split("/")[3]; // <--- Getting file name from the path
			Item = new ItemStack(Material.matchMaterial(this.config.getString("item")));
			meta = Item.getItemMeta(); // <--- Just needed to link with current item
			setMetaData(meta); // <--- Call method that will setup item and meta
			Item.setItemMeta(meta); // <--- Setting up metadata for item
		} catch (Exception e) {}
	}
	
	private void setMetaData(ItemMeta meta) {	
		try {ItemAmount = config.getString("amount");} catch (Exception e) {}
		
		try {ItemName = config.getString("name"); meta.setDisplayName(ItemName.replace("&", "§")); } catch (Exception e) {}
		
		try {ItemLore = config.getStringList("lore"); try {if (!ItemLore.isEmpty()) {for (int i = 0; i < ItemLore.size(); i++) {ItemLore.set(i, ItemLore.get(i).replace("&", "§"));}meta.setLore(ItemLore);} else {}} catch (Exception e) {}} catch (Exception e) {}
		
		try {Enchantments = config.getStringList("enchantment"); try {if (!Enchantments.isEmpty()) {for (int i = 0; i < Enchantments.size(); i++) {try {String method[] = Enchantments.get(i).split(" ");if (method.length > 1) {meta.addEnchant(EnchantmentTypeResolve(method[0]), Integer.parseInt(method[1]), true);} else if (method.length == 1) {meta.addEnchant(EnchantmentTypeResolve(method[0]), 1, true);}} catch (Exception e) {try {String method[] = Enchantments.get(i).split(",");method[0] = method[0].replace(" ", "");method[1] = method[1].replace(" ", "");meta.addEnchant(EnchantmentTypeResolve(method[0]), Integer.parseInt(method[1]), true);} catch (Exception z) {}}}}} catch (Exception e) {}} catch (Exception z) {} 
		
		try {ItemRecipe = config.getStringList("craft");} catch (Exception e) {}
		
		try {Durability = config.getString("durability");} catch (Exception e) {}
		
		try {Unbreakable = config.getBoolean("unbreakable"); meta.setUnbreakable(Unbreakable);} catch (Exception e) {}
	}
	
	private void SearchVariables(ItemMeta meta) {
		try {
			ItemName = config.getString("name");
			meta.setDisplayName(ItemName.replace("&", "§"));
		} catch (Exception e) {
			if (Working)
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_name_not_found").replace("&", "§"));
		}
		
		try {
			ItemLore = config.getStringList("lore");
			try {
				if (!ItemLore.isEmpty()) {
					for (int i = 0; i < ItemLore.size(); i++) {
						ItemLore.set(i, ItemLore.get(i).replace("&", "§"));
					}
					meta.setLore(ItemLore);
				} else {
					if (Working)
						this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_lore_not_found").replace("&", "§"));
				}
			} catch (Exception e) {
				if (Working)
					this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_lore_not_found").replace("&", "§"));
			}
		} catch (Exception e) {
			if (Working)
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_lore_not_found").replace("&", "§"));
		}
		
		try {
			Enchantments = config.getStringList("enchantment");
			try {
				if (!Enchantments.isEmpty()) {
					for (int i = 0; i < Enchantments.size(); i++) {
						try {
							String method[] = Enchantments.get(i).split(" ");
							if (method.length > 1) {
								meta.addEnchant(EnchantmentTypeResolve(method[0]), Integer.parseInt(method[1]), true);
							} else if (method.length == 1) {
								meta.addEnchant(EnchantmentTypeResolve(method[0]), 1, true);
							}
						} catch (Exception e) {
							try {
								String method[] = Enchantments.get(i).split(",");
								method[0] = method[0].replace(" ", "");
								method[1] = method[1].replace(" ", "");
								meta.addEnchant(EnchantmentTypeResolve(method[0]), Integer.parseInt(method[1]), true);
							} catch (Exception z) {
								if (Working)
									this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_enchantments_not_found").replace("&", "§"));
							}
						}
					}
				} else {
					if (Working)
						this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_enchantments_not_found").replace("&", "§"));
				}
			} catch (Exception e) {
				if (Working)
					this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_enchantments_not_found").replace("&", "§"));
			}
		} catch (Exception z) {
			if (Working)
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_enchantments_not_found").replace("&", "§"));
		} 
		
		try {
			ItemRecipe = config.getStringList("craft");
		} catch (Exception e) {
			if (Working)
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_recipe_not_found").replace("&", "§"));
		}
		
		try {
			Unbreakable = config.getBoolean("unbreakable");
			meta.setUnbreakable(Unbreakable);
		} catch (Exception e) {
			if (Working)
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_unbreakable_state_not_found").replace("&", "§"));
		}
		
		try {
			CustomModelData = config.getString("model-data").toLowerCase();
			meta.setCustomModelData(Integer.parseInt(CustomModelData));
		} catch (Exception e) {
			if (Working)
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_model_data_not_found").replace("&", "§"));
		}
		
		try {
			AttackDamage = config.getString("attribute.attack-damage.value").toLowerCase();
			String Slot = null;
			try {
				Slot = config.getString("attribute.attack-damage.slot").toLowerCase();
			} catch (Exception e) {
				Slot = "MAIN_HAND";
			}
			AttributeModifier modifier;
			double FinalDamage = 0;
			if (AttackDamage.contains("+")) {
				double BaseDamage = getItemDamage(Item);
				double NewDamage = Integer.parseInt(AttackDamage);
				FinalDamage = BaseDamage + NewDamage - 1;
				modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", FinalDamage, Operation.ADD_NUMBER, getSlot(Slot));
			} else {
				FinalDamage = Integer.parseInt(AttackDamage);
				modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", FinalDamage - 1, Operation.ADD_NUMBER, getSlot(Slot));
			}
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
		} catch (Exception e) {
			if (Working)
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_modifiers_not_found").replace("&", "§"));
		}
		
		try {
			AttackSpeed = config.getString("attribute.attack-speed.value").toLowerCase();
			String Slot = null;
			try {
				Slot = config.getString("attribute.attack-speed.slot").toLowerCase();
			} catch (Exception e) {
				Slot = "MAIN_HAND";
			}
			AttributeModifier modifier;
			double FinalSpeed = 0;
			if (AttackSpeed.contains("+")) {
				double BaseSpeed = getItemSpeed(Item);
				double NewSpeed = Integer.parseInt(AttackSpeed);
				FinalSpeed = BaseSpeed + NewSpeed;
				modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", FinalSpeed, Operation.ADD_NUMBER, getSlot(Slot));
			} else {
				FinalSpeed = Integer.parseInt(AttackSpeed);
				modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", FinalSpeed, Operation.ADD_NUMBER, getSlot(Slot));
			}
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
			
		} catch (Exception e) {
			if (Working)
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_modifiers_not_found").replace("&", "§"));
		}
	}	
	
	private void SearchVariables(ItemStack Item) {
		try {
			Durability = config.getString("durability");
			int tempDurability = Integer.parseInt(Durability);
			
			if (tempDurability > 0) {
				int MaxDurability = (int) Item.getType().getMaxDurability();
				if (tempDurability > MaxDurability)
					tempDurability = MaxDurability;
				else if (tempDurability < MaxDurability)
					tempDurability = (int) Item.getType().getMaxDurability() - tempDurability;
			} else if (tempDurability < 0)
				tempDurability = (int) Item.getType().getMaxDurability();
			Durability = String.valueOf(tempDurability);
			Durability = "-100";
			Item.setDurability(Short.valueOf(Durability));
		} catch (Exception e){
			if (Working)
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_durability_not_found").replace("&", "§"));
			if (Item.getType().getMaxDurability() == 0)
				Durability = "-1";
			else
				Durability = String.valueOf(Item.getType().getMaxDurability());
			Item.setDurability(Short.valueOf(Durability));
		}
		
		try {
			ItemAmount = config.getString("amount");
			int tempItemAmount = Integer.parseInt(ItemAmount);
			
			if (tempItemAmount <= 0)
				tempItemAmount = 1;
			else if (tempItemAmount > 64)
				tempItemAmount = 64;
			
			Item.setAmount(tempItemAmount);
		} catch (Exception e) {
			if (Working)
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_amount_not_found").replace("&", "§"));
			ItemAmount = "1";
			Item.setAmount(1);
		}
	}
	
	private void SearchPermission() {
		try {
			RecipePermission = config.getString("permission").toLowerCase();
		} catch (Exception e) {
			if (Working)
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_recipe_permission_not_found").replace("&", "§"));
			RecipePermission = "NO_PERM_BY_ADMIN";
		}
	}
	
	private void RegisterRecipe() throws FileNotFoundException {
		try {
			Item = new ItemStack(Material.matchMaterial(this.config.getString("item")));
			Working = true;	
		} catch (Exception e) {
			this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_not_found").replace("&", "§"));
			Working = false;
		}
		
		if (Working) {
			ItemStack tempItem = Item;
			tempItem.getItemMeta().setDisplayName("");
			DefaultItemName = tempItem.getI18NDisplayName(); // <--- New method to get Default name
			//DefaultItemName = Item.getData().getItemType().name().replace("LEGACY_", ""); // <--- All custom item's has prefix "LEGACY_" and we need to remove it to get default item name
			meta = Item.getItemMeta(); // <--- Just needed to link with current item
			
			// <--- Call this methods to setup item and meta settings
			SearchVariables(Item);
			//log.info("Durability: " + this.Durability);
			SearchVariables(meta);
			SearchPermission(); // <--- Saving permission of crafting this item to local variable
			
			Item.setItemMeta(meta); // <--- Loading metadata for item
		}
		
		if (Working) {
			NamespacedKey Key = new NamespacedKey(plugin, FileName);
			ShapedRecipe MyRecipe = new ShapedRecipe(Key, Item);
			
			MyRecipe.shape("abc", "def", "ghi"); // <--- §§§§§§§§§§§§§ §§§§§ §§§§§§
			final String tempArray[][] = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}}; // <--- §§§§§§ §§§§§§§§§
			
			int logicCounter = 0; // <--- §§§§§§§§§ §§§§§§§§§§
			pointer: for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					try {
						MyRecipe.setIngredient(tempArray[i][j].charAt(0), new RecipeChoice.ExactChoice(this.solveItem(ItemRecipe.get(i).toString().split(" ")[j], fileList)));
						ItemStack temp = this.solveItem(ItemRecipe.get(i).toString().split(" ")[j], fileList);
						if (i == 0)
							v1.add(temp);
						if (i == 1)
							v2.add(temp);
						if (i == 2)
							v3.add(temp);
						if (this.solveItem(ItemRecipe.get(i).toString().split(" ")[j], fileList).getType() == Material.AIR)
							logicCounter++; // <--- §§§§§§§ §§§§ §§§§§§§§§§ §§§§§ §§§§§ §§§§§§§
					} catch (Exception e) { 
						this.ErrorDataCollection.clear(); // <--- §§§§§§§§§ §§§§§§§§§§§§§§
						this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_recipe_not_found").replace("&", "§"));
						Working = false; // <--- §§§§§§§§§ §§§ §§§§§§ §§ §§§§§§§§
						break pointer; // <--- §§§§§§ §§§§ §§ §§§§§§§§, §§§§§ §§ §§§§§§§§§§ §§§§§§ §§§§§§§ (§§§§§§§§ bruh)
					}
				}
			}
			if (logicCounter == 9) { // §§§§ §§§§§§ §§ §§§§§§§ §§ §§§§§§§§§, § §§§§§§ §§ §§§§§§§ §§ §§§§§§§§§ §§§§§
				this.ErrorDataCollection.clear(); // <--- §§§§§§§§§ §§§§§§§§§§§§§§
				this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.item_recipe_not_found").replace("&", "§"));
				Working = false; // <--- §§§§§§§§§ §§§ §§§§§§ §§ §§§§§§§§
			}
			
			if (Working) {
				plugin.getServer().addRecipe(MyRecipe); // <--- §§§§§§§§§§§§ §§§§§ §§§§§ §§ §§§§§§
				this.ImportData(); // <--- §§§§§§§§§ §§§§§§ §§§§§§§§/§§§§§ §§ §§§§§/§§§§§§ § §§§§§§
				if (ErrorDataCollection.isEmpty())
					ExtractSuccess(); // <--- §§§§§§§ §§§ §§ §§§§§§ § §§§§§§§§§§§§§§ §§§
				else
					ExtractWarnings(); // <--- §§§§§§§ §§§§§§ §§ ErrorDataCollection § §§§§ §§§§§§§§§§§§§§
			}
		}
		
		if (!Working) {
			this.ErrorDataCollection.add(this.FileName + " --> " + plugin.getConfig().getString("messages.api_error").replace("&", "§"));
			ExtractErrors(); // <--- §§§§§§§ §§§§§§ §§ ErrorDataCollection §§§§§ §§§§§§
		}
		
		this.ImportAnotherData(); // <--- §§§§§§§§§ §§§§§§ § §§§§§§§§§§§§§§§§§ §§§§§§ § §§§§§§§§§
		this.ErrorDataCollection.clear(); // <--- §§§§§§§§§ §§§§§§/§§§§§§§§§§§§§§
	}
	
	private void ExtractWarnings() {
		log.setPrefix("§e[Additions : API] ");
		
		for (String message : this.ErrorDataCollection) {
			if (message.contains(this.FileName))
				log.warning(message);
		}
	}
	
	private void ExtractErrors() {
		log.setPrefix("[Additions : API] ");
		for (String message : this.ErrorDataCollection) {
			if (message.contains(this.FileName))
				log.error( message);
		}
	}
	
	private void ExtractSuccess() {
		log.setPrefix("§a[Additions : API] ");
		log.info(this.FileName + " --> " + plugin.getConfig().getString("messages.api_success").replace("&", "§"));
	}
	
	private void ImportData() {
		APIDS.Storage.S_ItemList.add(Item);
		APIDS.Storage.S_DefaultItemName.add(DefaultItemName);
		APIDS.Storage.S_FileName.add(FileName);
		APIDS.Storage.S_FilePath.add(FilePath);
		APIDS.Storage.S_CraftPermission.add(RecipePermission);
		
		List<ItemStack> temp = new ArrayList<ItemStack>();
		for (ItemStack item : v1)
			temp.add(item);
		for (ItemStack item : v2)
			temp.add(item);
		for (ItemStack item : v3)
			temp.add(item);
		
		APIDS.Storage.S_ItemRecipeIngredients.add(temp);
		APIDS.Storage.S_ItemRecipe.add(ItemRecipe);
		
		v1.clear();
		v2.clear();
		v3.clear();
		
		APIDS.Storage.S_FileList.add(fileList);
		APIDS.Storage.S_ItemEnchantments.add(getEnchantments());
		APIDS.Storage.S_ItemEnchantments2.add(Enchantments);
		APIDS.Storage.S_ItemDurability.add(Durability);
		
	}
	
	private void ImportAnotherData() {
		APIDS.Storage.S_WorkOrNot.add(Working);
	}
	
	public API (main plugin) {
		this.plugin = plugin;
	}
}
