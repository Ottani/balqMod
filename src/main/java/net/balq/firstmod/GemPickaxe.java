package net.balq.firstmod;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class GemPickaxe extends ItemPickaxe {
	public GemPickaxe(ToolMaterial toolMaterial) {
		super(toolMaterial);
		setCreativeTab(BalqFisrtMod.tabBalqMod);
		setTextureName("balqmod:pickaxe");
		setUnlocalizedName("gempickaxe");
	}

	
	
}
