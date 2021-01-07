package ru.utils.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_16_R2.Enchantments;

import org.additions.MinecraftConsoleAPI;
import org.additions.main;
import org.apache.commons.lang.Validate;

@SuppressWarnings("unused")
public class GUI extends Storage {
	
	public static MinecraftConsoleAPI log = new MinecraftConsoleAPI();
	
	protected String GUI_name = "none";
	protected int GUI_size_Type1 = 54;
	protected int GUI_size_Type2 = 27;
	protected int GUI_size_Type3 = 9;
	protected static Inventory Inv;
	
	private main plugin = null;
	
	protected static class GUI_Items {
		public ItemStack Close_Item = new ItemStack(Material.BARRIER, 1);
		public ItemStack Next_Page_Item = new ItemStack(Material.PAPER, 1);
		public ItemStack Previous_Page_Item = new ItemStack(Material.PAPER, 1);
		public ItemStack Background_Item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		public ItemStack Info_Item = new ItemStack(Material.KNOWLEDGE_BOOK, 1);
		public ItemStack Info_Item2 = new ItemStack(Material.BOOK);
		
		public GUI_Items() {}
	}
	
	public static class GUI_Tools extends GUI_Items {
		public void hideAttributes(ItemStack Item) {
			Item.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		}
		
		public void hideEnchants(ItemStack Item) {
			Item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		
		public void hideUnbreakable(ItemStack Item) {
			Item.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		}
		
		private void setupDefaultItems() {
			ItemMeta TempMeta = null;
			
			TempMeta = Close_Item.getItemMeta();
			TempMeta.setDisplayName("§c❌ §4§lClose menu §c❌");
			this.Close_Item.setItemMeta(TempMeta);
			
			TempMeta = Next_Page_Item.getItemMeta();
			TempMeta.setDisplayName("§e§lNext page §8➡");
			this.Next_Page_Item.setItemMeta(TempMeta);
			
			TempMeta = Previous_Page_Item.getItemMeta();
			TempMeta.setDisplayName("§8⬅ §e§lPrevious page");
			this.Previous_Page_Item.setItemMeta(TempMeta);
			
			TempMeta = Background_Item.getItemMeta();
			TempMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
			TempMeta.setDisplayName("§8§l► §9§lNothing to see here §8§l◄");
			this.Background_Item.setItemMeta(TempMeta);
			
			TempMeta = Info_Item.getItemMeta();
			TempMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
			TempMeta.setDisplayName("§8🛈 §9§lInformation §8🛈");
			this.Info_Item.setItemMeta(TempMeta);
			
			hideEnchants(this.Info_Item);
			hideEnchants(this.Background_Item);
		}
		
		public GUI_Tools() {
			this.setupDefaultItems();
		}
	}
	
	public void createGUI_Type1() {
		Inv = Bukkit.createInventory(null, this.GUI_size_Type1, this.GUI_name);
		GUI_Tools Tool = new GUI_Tools();
		
		for (int i = 0; i < 9; i++) {
			Inv.setItem(i,  Tool.Background_Item); 
			ItemsList.add(Tool.Background_Item);
			ItemsIndex.add(i);
		}
		
		for (int i = 45; i < 48; i++) {
			Inv.setItem(i,  Tool.Background_Item); 
			ItemsList.add(Tool.Background_Item);
			ItemsIndex.add(i);
		}
		
		for (int i = 51; i < 54; i++) {
			Inv.setItem(i,  Tool.Background_Item); 
			ItemsList.add(Tool.Background_Item);
			ItemsIndex.add(i);
		}
		
		Inv.setItem(48, Tool.Previous_Page_Item); ItemsList.add(Tool.Previous_Page_Item); ItemsIndex.add(48);
		Inv.setItem(49, Tool.Close_Item); ItemsList.add(Tool.Close_Item); ItemsIndex.add(49);
		Inv.setItem(50, Tool.Next_Page_Item); ItemsList.add(Tool.Next_Page_Item); ItemsIndex.add(50);
	}
	
	public void createGUI(int Size) {
		Inv = Bukkit.createInventory(null, Size, this.GUI_name);
		GUI_Tools Tool = new GUI_Tools();
		
	}
	
	public void setName(String GUI_Name) {
		Validate.notNull(GUI_Name);
		GUI_name = GUI_Name;
	}
	
	public void includeHandler() {
		Titles.add(GUI_name);
	}
	
	public String getName() {
		return this.GUI_name;
	}
	
	public void setItem(int Cell_Index, ItemStack Item) {
		if (ItemsIndex.contains(Cell_Index)) {
			Inv.setItem(Cell_Index, Item);
			int index = 0;
			for (int i = 0; i < ItemsIndex.size(); i++)
				if (ItemsIndex.get(i) == Cell_Index)
					index = i;
			
			ItemsList.set(index, Item);
		} else {
			Inv.setItem(Cell_Index, Item);
			ItemsIndex.add(Cell_Index);
			ItemsList.add(Item);
		}
	}
	
	public void removeItem(int Cell_Index) {
		ItemsList.remove(Cell_Index);
		ItemsIndex.remove(Cell_Index);
	}
	
	public void openGUI(Player player) {
		player.openInventory(Inv);
	}
	
	public void openGUI(CommandSender sender) {
		Player player = (Player) sender;
		player.openInventory(Inv);
	}
	
	public GUI (main plugin) {
		this.plugin = plugin;
	}
	
	public GUI() {}
}
