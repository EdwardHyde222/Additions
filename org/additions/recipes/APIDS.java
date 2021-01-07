package org.additions.recipes;

import java.util.ArrayList;
import java.util.List;

import org.additions.MinecraftConsoleAPI;
import org.additions.main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ru.crypto.Aes256Class;

@SuppressWarnings("unused")
public class APIDS {

	static MinecraftConsoleAPI log = new MinecraftConsoleAPI();
	// TODO: Finish 'getItemFromKey()' method
	
	public static class Storage {	
		protected static List<ItemStack> S_ItemList = new ArrayList<ItemStack>();
		protected static List<String> S_DefaultItemName = new ArrayList<String>();
		protected static List<String> S_FileName = new ArrayList<String>();
		protected static List<String> S_FilePath = new ArrayList<String>();
		protected static List<String> S_CraftPermission = new ArrayList<String>();
		protected static List<Boolean> S_WorkOrNot = new ArrayList<Boolean>();
		protected static List<String> S_ItemDurability = new ArrayList<String>();
		
		protected static ArrayList<List <Enchantment>> S_ItemEnchantments = new ArrayList<List <Enchantment>>();
		protected static ArrayList<List <ItemStack>> S_ItemRecipeIngredients = new ArrayList<List <ItemStack>>();
		protected static ArrayList<List <String>> S_ItemEnchantments2 = new ArrayList<List <String>>();
		protected static ArrayList<List <String>> S_ItemRecipe = new ArrayList<List <String>>();
		protected static ArrayList<List <String>> S_FileList = new ArrayList<List <String>>();
		
		public int getAmount(int index) {
			return S_ItemList.get(index).getAmount();
		}
		
		public int getDurability(int index) {
			return Integer.parseInt(S_ItemDurability.get(index));
		}
		
		public ItemStack getItem(int index) {
			return S_ItemList.get(index);
		}
		
		public ItemMeta getMeta(int index) {
			return S_ItemList.get(index).getItemMeta();
		}
		
		public String getItemName(int index) {
			return S_ItemList.get(index).getItemMeta().getDisplayName();
		}
		
		public String getDefaultItemName(int index) {
			return S_DefaultItemName.get(index);
		}
		
		public List<String> getItemLore(int index) {
			return S_ItemList.get(index).getLore();
		}
		
		public String getCraftPermission(int index) {
			return S_CraftPermission.get(index);
		}
		
		public int getModelData(int index) {
			return S_ItemList.get(index).getItemMeta().getCustomModelData();
		}
		
		public List<Enchantment> getEnchantments(int index) {
			return S_ItemEnchantments.get(index);
		}
		
		public List<String> getEnchants(int index) {
			return S_ItemEnchantments2.get(index);
		}

		// Remaked to getIngredient(..) function
		/* public String[] getRecipeFullLine(int index, int line) {
			return S_ItemRecipe.get(index).get(line - 1).split(" ");
		}

		public String getRecipeItem(int index, int line, int position) {
			return S_ItemRecipe.get(index).get(line - 1).split(" ")[position - 1];
		} */
		
		public ItemStack getIngredient(int index, int position) {
			return S_ItemRecipeIngredients.get(index).get(position - 1);
		}
		
		public String getFileName(int index) {
			return S_FileName.get(index);
		}

		public String getFilePath(int index) {
			return S_FilePath.get(index);
		}

		public boolean isWorking(int index) {
			return S_WorkOrNot.get(index);
		}
		
		public int getItemsAmount() {
			return S_ItemList.size();
		}
		
		public void removeData(boolean remove) {
			if (remove) {
				S_ItemList.clear();
				S_DefaultItemName.clear();
				S_FileName.clear();
				S_FilePath.clear();
				S_CraftPermission.clear();
				S_WorkOrNot.clear();
				S_ItemEnchantments.clear();
				S_ItemEnchantments2.clear();
				S_ItemRecipe.clear();
				S_FileList.clear();
				S_ItemRecipeIngredients.clear();
			}
		}
	}

	//
	// What APIDS means?
	// - It is API Data Storage
	//
	// The implementation of this class allows you to save the item data in a single
	// line and later get it in full form
	// This will be very effective when importing custom items quickly
	// To finish up .. (don't know)

	private int ItemAmount = 1;
	private int Durability = 0;
	private int CustomModelData = 0;
	////////////////////////////////////////
	protected ItemStack Item = null;
	protected ItemMeta Meta = null;
	////////////////////////////////////////
	private String RecipePermission = null;
	private String ItemName = null;
	private String DefaultItemName = null;
	private String FileName = null;
	private List<String> ItemRecipe = null;
	private List<String> ItemLore = null;
	private List<String> fileList = null;
	private List<String> Enchantments = null;

	// <-------------------------> //
	// Initialization of an object //
	// <-------------------------> //
	public APIDS(ItemStack Item) {
		this.Item = Item;
	}

	public APIDS() {

	}

	// P.S. Currently these functions are not used
	// <----------------------> //
	// Getting data for the API //
	// <----------------------> //
	@SuppressWarnings("deprecation")
	private void searchValues(ItemStack Item) {
		this.ItemAmount = Item.getAmount();
		this.Durability = Item.getDurability();
		
		ItemStack tempItem = Item;
		tempItem.getItemMeta().setDisplayName("");
		this.DefaultItemName = tempItem.getI18NDisplayName(); // <--- New method to get Default name
		
		this.Meta = Item.getItemMeta();
		searchValues(Meta);
	}

	private void searchValues(ItemMeta Meta) {
		this.CustomModelData = Meta.getCustomModelData();
		this.ItemName = Meta.getDisplayName();
	}

	// P.S. Just thought about using that in cipher method.
	private final String Alphabet[][] = { { "A", "B", "C" }, { "D", "E", "F" }, { "G", "H", "I" }, { "J", "K", "L" },
			{ "M", "N", "O" }, { "P", "Q", "R" }, { "S", "T", "U" }, { "V", "W", "X" }, { "Y", "Z", " " } };

	// <------------------------------------> //
	// Forming an element from a cache string //
	// <------------------------------------> //
	public static ItemStack getItemFromKey(String key) {
		// TODO: Finish the item's enchantment list
		// TODO: Finish the item's custom_model_data

		String Data = key.toString();
		// Data = ItemStack{IRON_INGOT x 1, UNSPECIFIC_META:{meta-type=UNSPECIFIC,
		// display-name="§fСеребряный слиток", lore=[§aПревосходный слиток],
		// enchants={ARROW_INFINITE=1, DAMAGE_ALL=10}}}
		int length = Data.length();

		// How that works:
		// We taking part of the string with "ItemStack" because it is the same
		// everywhere for any values
		int MagicIndex = 0;
		for (int i = 0; i < length; i++) {
			if (Data.charAt(i) == ',') {
				MagicIndex = (i);
				break;
			}
		}

		// How that works:
		// Divide the previous line into two elements: This is the material from which
		// the item will be made and its quantity (value of the item)
		// "x" - is their separator so you can focus on it
		// 10 is the number of letters that the word "ItemStack{" occupies
		String v1 = Data.substring(10, MagicIndex);
		String NotFinalMaterial = v1.split("x")[0].replaceAll(" ", "");
		String NotFinalItemAmount = v1.split("x")[1].replaceAll(" ", "");

		// P.S. And here are the values obtained from the previous rows
		Material FinalMaterial = Material.matchMaterial(NotFinalMaterial);
		int FinalItemAmount = Integer.parseInt(NotFinalItemAmount);

		// <---------------------------------------------------> //
		// Extracting data from the remaining part of the string //
		// <---------------------------------------------------> //

		// Here we get the rest of the cache line which contains the values for the meta
		// of our item
		// 10 is the number of letters that the word "ItemStack{" occupies
		String v0 = "";
		for (int i = 10; i < length; i++)
			v0 += Data.substring(i, i + 1);
		String v2 = v0.replace(NotFinalMaterial + " x " + NotFinalItemAmount + ", ", "");

		// Initializing a new variable to change its data
		String v3 = v2;
		// Checking whether the item has a custom name
		boolean display_name_found = false;
		// The variable that the custom name will be written to
		String FinalItemName = "";

		// How that works:
		// Since the item's meta display-name indicates its custom name,
		// you can't find it using charAt() because String doesn't equal Char.
		// Accordingly, we replace it with a single character and search for it in the
		// string.
		// Next, we write the index of the letter located AFTER the replaced character.
		// Next, we look for the end of the given 'display-name' parameter, but we only
		// read up to two characters; why?
		// Because the item can have, for example, ONLY a custom name and
		// the string will end with the element '}' respectively that's why we read two
		// types
		v3 = v3.replace("display-name=", "©");
		if (v3.contains("©")) {
			display_name_found = true;
			int v4 = 0;
			for (int i = 0; i < v3.length(); i++) {
				if (v3.charAt(i) == '©') {
					v4 = (i + 1);
					break;
				}
			}
			int v5 = 0;
			for (int i = v4 + 1; i < v3.length(); i++) {
				if (v3.charAt(i) == ',' || v3.charAt(i) == '}') {
					v5 = i;
					break;
				}
			}
			FinalItemName = v3.substring(v4, v5);
		}

		// Initializing a new variable to change its data
		String v6 = v2;
		// Checking whether the item has a lore
		boolean item_lore_found = false;
		// The variable that the lore will be written to
		List<String> FinalItemLore = new ArrayList<String>();
		// A temporary variable that we will later convert to List
		String NotFinalItemLore[] = new String[100];

		// How that works:
		// Here is almost the same algorithm for calculating values as the custom item
		// name
		v6 = v6.replace("lore=", "©");
		if (v6.contains("©")) {
			item_lore_found = true;
			int v7 = 0;
			for (int i = 0; i < v6.length(); i++) {
				if (v6.charAt(i) == '©') {
					v7 = (i + 2);
					break;
				}
			}
			int v8 = 0;
			for (int i = v7 + 1; i < v6.length(); i++) {
				if (v6.charAt(i) == ']') {
					v8 = (i);
					break;
				}
			}

			String v9 = v6.substring(v7, v8);
			NotFinalItemLore = v9.split(",");

			FinalItemLore.add(NotFinalItemLore[0]);
			for (int i = 1; i < NotFinalItemLore.length; i++)
				FinalItemLore.add(NotFinalItemLore[i].replaceFirst(" ", ""));
		}
		// <---------------------------------------------------------------> //
		// Creating an item based on the data received from the cache string //
		// <---------------------------------------------------------------> //
		ItemStack Item = new ItemStack(FinalMaterial, FinalItemAmount);
		ItemMeta Meta = Item.getItemMeta();
		if (display_name_found)
			Meta.setDisplayName(FinalItemName);
		if (item_lore_found)
			Meta.setLore(FinalItemLore);
		Item.setItemMeta(Meta);
		return Item;
	}

	// P.S. Here we should get a key with cached data
	public String getKeyFromItem(ItemStack Item) {
		String result = Item.toString();
		// Additional data to result
		// result += Item.getDurability();
		return result;
	}
}
