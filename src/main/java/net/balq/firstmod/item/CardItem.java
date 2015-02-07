package net.balq.firstmod.item;

import java.util.List;

import net.balq.firstmod.BalqFisrtMod;
import net.balq.firstmod.block.BalqBlocks;
import net.balq.firstmod.block.MachineBlock;
import net.balq.firstmod.util.ModInformation;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CardItem extends Item {

	private static final String[] CARD_NAMES= { "card_plus", "card_box", "card_empty" };
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public CardItem() {
		setCreativeTab(BalqFisrtMod.tabBalqMod);
		setMaxStackSize(16);
		setHasSubtypes(true);
		//setUnlocalizedName("card");
		setTextureName(ModInformation.ID+":wand");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return "item."+CARD_NAMES[itemStack.getItemDamage()];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister register) {
		icons = new IIcon[CARD_NAMES.length];
		for (int i=0; i<CARD_NAMES.length; ++i) {
			icons[i] = register.registerIcon(ModInformation.ID+":"+CARD_NAMES[i]);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int dmg) {
		return icons[dmg];
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i=0; i<CARD_NAMES.length; ++i) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && world.getBlock(x, y, z).equals(BalqBlocks.machineBlock)) {
			int meta = world.getBlockMetadata(x, y, z);
			int active = meta%2;
			int newMeta = ((stack.getItemDamage()+1)*2)+active;
			world.setBlockMetadataWithNotify(x, y, z,newMeta, 3);
			--stack.stackSize;
			return true;
		}
		return false;
	}
}
