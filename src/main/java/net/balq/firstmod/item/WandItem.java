package net.balq.firstmod.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.balq.firstmod.BalqFisrtMod;
import net.balq.firstmod.util.ModInformation;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class WandItem extends Item {
	
	@SideOnly(Side.CLIENT)
	private IIcon chargedIcon;
	
	public WandItem() {
		setCreativeTab(BalqFisrtMod.tabBalqMod);
		setMaxStackSize(1);
		setUnlocalizedName("wand");
		setTextureName(ModInformation.ID+":wand");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister register) {
		super.registerIcons(register);
		chargedIcon = register.registerIcon(ModInformation.ID+":charged_wand");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int dmg) {
		return isCharged(dmg)?chargedIcon:itemIcon;
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target) {
		if (!target.worldObj.isRemote) {
			target.motionY = 1.0;
			int dmg = itemStack.getItemDamage();
			if (isCharged(dmg)) {
				dmg = -1;
				target.motionX = (target.posX - player.posX);
				target.motionZ = (target.posZ - player.posZ);
			}
			itemStack.setItemDamage(++dmg);
		}
		return false;
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean useExtraInfo) {
		info.add("Uses: " + itemStack.getItemDamage());
	}
	
	
	private boolean isCharged(int dmg) {
		return dmg>=5;
	}
}
