package net.balq.firstmod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.balq.firstmod.BalqFisrtMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GemBlock extends Block {
	
	public GemBlock() {
		super(Material.iron);
		setHardness(3.0f);
		setResistance(5.0f);
		setStepSound(soundTypeMetal);
		setCreativeTab(BalqFisrtMod.tabBalqMod);
		setBlockTextureName("balqmod:gemBlock");
	}

	@Override
	public String getUnlocalizedName() {
		return "block.gemBlock";
	}

}
