package net.balq.firstmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.balq.firstmod.BalqFisrtMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class GemItem extends Item {

	public GemItem() {
		super();
		setCreativeTab(BalqFisrtMod.tabBalqMod);
		setTextureName("balqmod:gem");
		setUnlocalizedName("gem");
	}
	
	/*@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister arg0) {
		this.itemIcon = arg0.registerIcon("net/balq/" + getUnlocalizedName());
	}*/
	
}
