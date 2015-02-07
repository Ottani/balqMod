package net.balq.firstmod.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.balq.firstmod.GemPickaxe;
import net.balq.firstmod.util.ConfigManager;
import net.balq.firstmod.util.ModInformation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class BalqItems {
	
	public static Item gem;
	public static ToolMaterial gemToolMaterial;
	public static Item gemPickaxe;
	public static Item wand;
	public static Item card;
	
	public static void init() {
		gem = new GemItem();
		GameRegistry.registerItem(gem, "Balq Gem", ModInformation.ID);

		gemToolMaterial = EnumHelper.addToolMaterial("gemMaterial", 3, ConfigManager.pickaxeMaxUses, 15.0f, 3.0f, 22);
		
		gemPickaxe = new GemPickaxe(gemToolMaterial);
		GameRegistry.registerItem(gemPickaxe, "Balq Gem Pickaxe", ModInformation.ID);

		wand = new WandItem();
		GameRegistry.registerItem(wand, "Wand", ModInformation.ID);
		
		card = new CardItem();
		GameRegistry.registerItem(card, "card", ModInformation.ID);

	}
	
	
}
