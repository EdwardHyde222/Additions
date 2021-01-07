package org.additions.recipes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class Object_Identification {
	protected Enchantment EnchantmentTypeResolve(String enchantmentType) {
		Enchantment result = null;
		enchantmentType = enchantmentType.toLowerCase();
		if (enchantmentType.equalsIgnoreCase("arrow_damage"))
			result = Enchantment.ARROW_DAMAGE;
		else if (enchantmentType.equalsIgnoreCase("arrow_fire"))
			result = Enchantment.ARROW_FIRE;
		else if (enchantmentType.equalsIgnoreCase("arrow_infinite") || enchantmentType.equalsIgnoreCase("infinite") || enchantmentType.equalsIgnoreCase("infinity"))
			result = Enchantment.ARROW_INFINITE;
		else if (enchantmentType.equalsIgnoreCase("arrow_knockback"))
			result = Enchantment.ARROW_KNOCKBACK;
		else if (enchantmentType.equalsIgnoreCase("binding_curse"))
			result = Enchantment.BINDING_CURSE;
		else if (enchantmentType.equalsIgnoreCase("arrow_channeling") || enchantmentType.equalsIgnoreCase("channeling"))
			result = Enchantment.CHANNELING;
		else if (enchantmentType.equalsIgnoreCase("damage_all"))
			result = Enchantment.DAMAGE_ALL;
		else if (enchantmentType.equalsIgnoreCase("damage_arthropods"))
			result = Enchantment.DAMAGE_ARTHROPODS;
		else if (enchantmentType.equalsIgnoreCase("damage_undead"))
			result = Enchantment.DAMAGE_UNDEAD;
		else if (enchantmentType.equalsIgnoreCase("depth_strider"))
			result = Enchantment.DEPTH_STRIDER;
		else if (enchantmentType.equalsIgnoreCase("dig_speed"))
			result = Enchantment.DIG_SPEED;
		else if (enchantmentType.equalsIgnoreCase("durability"))
			result = Enchantment.DURABILITY;
		else if (enchantmentType.equalsIgnoreCase("fire_aspect"))
			result = Enchantment.FIRE_ASPECT;
		else if (enchantmentType.equalsIgnoreCase("frost_walker"))
			result = Enchantment.FROST_WALKER;
		else if (enchantmentType.equalsIgnoreCase("impaling"))
			result = Enchantment.IMPALING;
		else if (enchantmentType.equalsIgnoreCase("knockback"))
			result = Enchantment.KNOCKBACK;
		else if (enchantmentType.equalsIgnoreCase("loot_bonus_block"))
			result = Enchantment.LOOT_BONUS_BLOCKS;
		else if (enchantmentType.equalsIgnoreCase("loot_bonus_mobs"))
			result = Enchantment.LOOT_BONUS_MOBS;
		else if (enchantmentType.equalsIgnoreCase("loyality"))
			result = Enchantment.LOYALTY;
		else if (enchantmentType.equalsIgnoreCase("luck"))
			result = Enchantment.LUCK;
		else if (enchantmentType.equalsIgnoreCase("lure"))
			result = Enchantment.LURE;
		else if (enchantmentType.equalsIgnoreCase("mending"))
			result = Enchantment.MENDING;
		else if (enchantmentType.equalsIgnoreCase("multishot"))
			result = Enchantment.MULTISHOT;
		else if (enchantmentType.equalsIgnoreCase("oxygen"))
			result = Enchantment.OXYGEN;
		else if (enchantmentType.equalsIgnoreCase("piercing"))
			result = Enchantment.PIERCING;
		else if (enchantmentType.equalsIgnoreCase("protection_environmental") || enchantmentType.equalsIgnoreCase("protection"))
			result = Enchantment.PROTECTION_ENVIRONMENTAL;
		else if (enchantmentType.equalsIgnoreCase("protection_explosions"))
			result = Enchantment.PROTECTION_EXPLOSIONS;
		else if (enchantmentType.equalsIgnoreCase("protection_fall"))
			result = Enchantment.PROTECTION_FALL;
		else if (enchantmentType.equalsIgnoreCase("protection_fire"))
			result = Enchantment.PROTECTION_FIRE;
		else if (enchantmentType.equalsIgnoreCase("protection_projectile"))
			result = Enchantment.PROTECTION_PROJECTILE;
		else if (enchantmentType.equalsIgnoreCase("quick_charge"))
			result = Enchantment.QUICK_CHARGE;
		else if (enchantmentType.equalsIgnoreCase("riptide"))
			result = Enchantment.RIPTIDE;
		else if (enchantmentType.equalsIgnoreCase("silk_touch"))
			result = Enchantment.SILK_TOUCH;
		else if (enchantmentType.equalsIgnoreCase("sweeping_edge"))
			result = Enchantment.SWEEPING_EDGE;
		else if (enchantmentType.equalsIgnoreCase("thorns"))
			result = Enchantment.THORNS;
		else if (enchantmentType.equalsIgnoreCase("vanishing_curse"))
			result = Enchantment.VANISHING_CURSE;
		else if (enchantmentType.equalsIgnoreCase("water_worker"))
			result = Enchantment.WATER_WORKER;
		
		return result;
	}
	
	protected double getItemDamage(ItemStack Item) {
		double damageValue = 0.0;
		//////////////////////////////////////////////
		//					 SWORD					//
		//////////////////////////////////////////////
		if (Item.getType() == Material.AIR)
			damageValue = 0.0;
		else if (Item.getType() == Material.WOODEN_SWORD)
			damageValue = 4.0;
		else if (Item.getType() == Material.GOLDEN_SWORD)
			damageValue = 4.0;
		else if (Item.getType() == Material.STONE_SWORD)
			damageValue = 5.0;
		else if (Item.getType() == Material.IRON_SWORD)
			damageValue = 6.0;
		else if (Item.getType() == Material.DIAMOND_SWORD)
			damageValue = 7.0;
		else if (Item.getType() == Material.NETHERITE_SWORD)
			damageValue = 8.0;
		//////////////////////////////////////////////
		//					  AXE					//
		//////////////////////////////////////////////
		else if (Item.getType() == Material.WOODEN_AXE)
			damageValue = 7.0;
		else if (Item.getType() == Material.GOLDEN_AXE)
			damageValue = 7.0;
		else if (Item.getType() == Material.STONE_AXE)
			damageValue = 9.0;
		else if (Item.getType() == Material.IRON_AXE)
			damageValue = 9.0;
		else if (Item.getType() == Material.DIAMOND_AXE)
			damageValue = 9.0;
		else if (Item.getType() == Material.NETHERITE_AXE)
			damageValue = 10.0;
		//////////////////////////////////////////////
		//					  HOE					//
		//////////////////////////////////////////////
		else if (Item.getType() == Material.WOODEN_HOE)
			damageValue = 1.0;
		else if (Item.getType() == Material.GOLDEN_HOE)
			damageValue = 1.0;
		else if (Item.getType() == Material.STONE_HOE)
			damageValue = 1.0;
		else if (Item.getType() == Material.IRON_HOE)
			damageValue = 1.0;
		else if (Item.getType() == Material.DIAMOND_HOE)
			damageValue = 1.0;
		else if (Item.getType() == Material.NETHERITE_HOE)
			damageValue = 1.0;
		//////////////////////////////////////////////
		//					PICKAXE				    //
		//////////////////////////////////////////////
		else if (Item.getType() == Material.WOODEN_PICKAXE)
			damageValue = 2.0;
		else if (Item.getType() == Material.GOLDEN_PICKAXE)
			damageValue = 2.0;
		else if (Item.getType() == Material.STONE_PICKAXE)
			damageValue = 3.0;
		else if (Item.getType() == Material.IRON_PICKAXE)
			damageValue = 4.0;
		else if (Item.getType() == Material.DIAMOND_PICKAXE)
			damageValue = 5.0;
		else if (Item.getType() == Material.NETHERITE_PICKAXE)
			damageValue = 6.0;
		//////////////////////////////////////////////
		//					SHOVEL				    //
		//////////////////////////////////////////////
		else if (Item.getType() == Material.WOODEN_SHOVEL)
			damageValue = 2.5;
		else if (Item.getType() == Material.GOLDEN_SHOVEL)
			damageValue = 2.5;
		else if (Item.getType() == Material.STONE_SHOVEL)
			damageValue = 3.5;
		else if (Item.getType() == Material.IRON_SHOVEL)
			damageValue = 4.5;
		else if (Item.getType() == Material.DIAMOND_SHOVEL)
			damageValue = 5.5;
		else if (Item.getType() == Material.NETHERITE_SHOVEL)
			damageValue = 6.5;
		//////////////////////////////////////////////
		//					TRIDENT				    //
		//////////////////////////////////////////////
		else if (Item.getType() == Material.TRIDENT)
			damageValue = 9.0;
		else
			damageValue = 1.0; // other blocks & items
		
		return damageValue;
	}
	
	protected double getItemSpeed(ItemStack Item) {
		double speedValue = 0.0f;
		//////////////////////////////////////////////
		//					 SWORD					//
		//////////////////////////////////////////////
		if (Item.getType() == Material.AIR)
			speedValue = 0.0;
		else if (Item.getType() == Material.WOODEN_SWORD)
			speedValue = 1.6;
		else if (Item.getType() == Material.GOLDEN_SWORD)
			speedValue = 1.6;
		else if (Item.getType() == Material.STONE_SWORD)
			speedValue = 1.6;
		else if (Item.getType() == Material.IRON_SWORD)
			speedValue = 1.6;
		else if (Item.getType() == Material.DIAMOND_SWORD)
			speedValue = 1.6;
		else if (Item.getType() == Material.NETHERITE_SWORD)
			speedValue = 1.6;
		//////////////////////////////////////////////
		//					  AXE					//
		//////////////////////////////////////////////
		else if (Item.getType() == Material.WOODEN_AXE)
			speedValue = 0.8;
		else if (Item.getType() == Material.GOLDEN_AXE)
			speedValue = 1.0;
		else if (Item.getType() == Material.STONE_AXE)
			speedValue = 0.8;
		else if (Item.getType() == Material.IRON_AXE)
			speedValue = 0.9;
		else if (Item.getType() == Material.DIAMOND_AXE)
			speedValue = 1.0;
		else if (Item.getType() == Material.NETHERITE_AXE)
			speedValue = 1.0;
		//////////////////////////////////////////////
		//					  HOE					//
		//////////////////////////////////////////////
		else if (Item.getType() == Material.WOODEN_HOE)
			speedValue = 1.0;
		else if (Item.getType() == Material.GOLDEN_HOE)
			speedValue = 1.0;
		else if (Item.getType() == Material.STONE_HOE)
			speedValue = 2.0;
		else if (Item.getType() == Material.IRON_HOE)
			speedValue = 3.0;
		else if (Item.getType() == Material.DIAMOND_HOE)
			speedValue = 4.0;
		else if (Item.getType() == Material.NETHERITE_HOE)
			speedValue = 4.0;
		//////////////////////////////////////////////
		//					PICKAXE				    //
		//////////////////////////////////////////////
		else if (Item.getType() == Material.WOODEN_PICKAXE)
			speedValue = 1.2;
		else if (Item.getType() == Material.GOLDEN_PICKAXE)
			speedValue = 1.2;
		else if (Item.getType() == Material.STONE_PICKAXE)
			speedValue = 1.2;
		else if (Item.getType() == Material.IRON_PICKAXE)
			speedValue = 1.2;
		else if (Item.getType() == Material.DIAMOND_PICKAXE)
			speedValue = 1.2;
		else if (Item.getType() == Material.NETHERITE_PICKAXE)
			speedValue = 1.2;
		//////////////////////////////////////////////
		//					SHOVEL				    //
		//////////////////////////////////////////////
		else if (Item.getType() == Material.WOODEN_SHOVEL)
			speedValue = 1.0;
		else if (Item.getType() == Material.GOLDEN_SHOVEL)
			speedValue = 1.0;
		else if (Item.getType() == Material.STONE_SHOVEL)
			speedValue = 1.0;
		else if (Item.getType() == Material.IRON_SHOVEL)
			speedValue = 1.0;
		else if (Item.getType() == Material.DIAMOND_SHOVEL)
			speedValue = 1.0;
		else if (Item.getType() == Material.NETHERITE_SHOVEL)
			speedValue = 1.0;
		//////////////////////////////////////////////
		//					TRIDENT				    //
		//////////////////////////////////////////////
		else if (Item.getType() == Material.TRIDENT)
			speedValue = 1.1;
		else
			speedValue = 0.0; // other blocks & items
		return speedValue;
	}
	
	protected double getItemArmor(ItemStack Item) {
		double armorValue = 0.0f;
		//////////////////////////////////////////////
		//					HELMET 					//
		//////////////////////////////////////////////
		if (Item.getType() == Material.LEATHER_HELMET)
			armorValue = 1.0;
		else if (Item.getType() == Material.CHAINMAIL_HELMET)
			armorValue = 2.0;
		else if (Item.getType() == Material.IRON_HELMET)
			armorValue = 2.0;
		else if (Item.getType() == Material.DIAMOND_HELMET)
			armorValue = 3.0;
		else if (Item.getType() == Material.GOLDEN_HELMET)
			armorValue = 2.0;
		else if (Item.getType() == Material.NETHERITE_HELMET)
			armorValue = 3.0;
		else if (Item.getType() == Material.TURTLE_HELMET)
			armorValue = 2.0;
		//////////////////////////////////////////////
		//				 CHESTPLATE					//
		//////////////////////////////////////////////
		else if (Item.getType() == Material.LEATHER_CHESTPLATE)
			armorValue = 3.0;
		else if (Item.getType() == Material.CHAINMAIL_CHESTPLATE)
			armorValue = 5.0;
		else if (Item.getType() == Material.IRON_CHESTPLATE)
			armorValue = 6.0;
		else if (Item.getType() == Material.DIAMOND_CHESTPLATE)
			armorValue = 8.0;
		else if (Item.getType() == Material.GOLDEN_CHESTPLATE)
			armorValue = 5.0;
		else if (Item.getType() == Material.NETHERITE_CHESTPLATE)
			armorValue = 8.0;
		//////////////////////////////////////////////
		//				  LEGGINGS					//
		//////////////////////////////////////////////
		else if (Item.getType() == Material.LEATHER_LEGGINGS)
			armorValue = 3.0;
		else if (Item.getType() == Material.CHAINMAIL_LEGGINGS)
			armorValue = 5.0;
		else if (Item.getType() == Material.IRON_LEGGINGS)
			armorValue = 6.0;
		else if (Item.getType() == Material.DIAMOND_LEGGINGS)
			armorValue = 8.0;
		else if (Item.getType() == Material.GOLDEN_LEGGINGS)
			armorValue = 5.0;
		else if (Item.getType() == Material.NETHERITE_LEGGINGS)
			armorValue = 8.0;
		//////////////////////////////////////////////
		//					OTHER					//
		//////////////////////////////////////////////
		else 
			armorValue = 0.f;
		return armorValue;
	}
	
	protected EquipmentSlot getSlot(String Line) {
		EquipmentSlot Slot = EquipmentSlot.HAND;
		Line = Line.toLowerCase();
		if (Line.equalsIgnoreCase("hand") || Line.equalsIgnoreCase("mainhand") || Line.equalsIgnoreCase("main_hand"))
			Slot = EquipmentSlot.HAND;
		else if (Line.equalsIgnoreCase("anotherhand") || Line.equalsIgnoreCase("another_hand") || Line.equalsIgnoreCase("offhand") || Line.equalsIgnoreCase("off_hand"))
			Slot = EquipmentSlot.OFF_HAND;
		else if (Line.equalsIgnoreCase("head"))
			Slot = EquipmentSlot.HEAD;
		else if (Line.equalsIgnoreCase("chest"))
			Slot = EquipmentSlot.CHEST;
		else if (Line.equalsIgnoreCase("legs") || Line.equalsIgnoreCase("leg"))
			Slot = EquipmentSlot.LEGS;
		else if (Line.equalsIgnoreCase("feet"))
			Slot = EquipmentSlot.FEET;
		return Slot;
	}
	
	protected EquipmentSlot getSlotV2(String Line) {
		return EquipmentSlot.valueOf(Line);
	}
}
