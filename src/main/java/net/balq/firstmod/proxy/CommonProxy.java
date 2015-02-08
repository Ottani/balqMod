package net.balq.firstmod.proxy;

import cpw.mods.fml.common.registry.GameRegistry;
import net.balq.firstmod.BalqFisrtMod;
import net.balq.firstmod.block.BalqBlocks;
import net.balq.firstmod.entity.Entities;
import net.balq.firstmod.item.BalqItems;
import net.balq.firstmod.network.PacketHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CommonProxy {

	public void preInit() {
		BalqItems.init();
		BalqBlocks.init();
	}
	
	public void load() {
		BalqBlocks.registerTileEntities();
		Entities.init();
		
		
		GameRegistry.addShapedRecipe(new ItemStack(BalqItems.gem, 1), new Object[] {
			" D ", "D D", " D ", 'D', Blocks.oak_stairs });
		GameRegistry.addShapedRecipe(new ItemStack(BalqBlocks.gemBlock, 1), new Object[] {
			" D ", "D D", " D ", 'D', BalqItems.gem });
		GameRegistry.addShapedRecipe(new ItemStack(BalqItems.gemPickaxe, 1), new Object[] {
				"DDD", " S ", " S ", 'D', BalqItems.gem, 'S', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(BalqItems.wand, 1), new Object[] {
			"  D", " S ", "S  ", 'D', BalqItems.gem, 'S', Items.stick });
		
		PacketHandler.init();
	}
	
	public void initRenderers() {
		
	}
	
}
