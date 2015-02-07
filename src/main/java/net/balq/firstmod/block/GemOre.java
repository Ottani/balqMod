package net.balq.firstmod.block;

import java.util.ArrayList;
import java.util.Random;

import net.balq.firstmod.BalqFisrtMod;
import net.balq.firstmod.item.BalqItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GemOre extends Block {
	
	public GemOre(Material material) {
		super(material);
		setHardness(3.0f);
		setResistance(5.0f);
		setStepSound(soundTypeStone);
		setCreativeTab(BalqFisrtMod.tabBalqMod);
		setBlockTextureName("balqmod:gemOre");
	}
	
	@Override
	public String getUnlocalizedName() {
		return "gemOre";
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int qty = quantityDropped(metadata, fortune, world.rand);
		world.playerEntities.get(0);
		for(int i=0; i<qty; ++i) {
			ret.add(new ItemStack(BalqItems.gem));
		}
		return ret;
	}
}
