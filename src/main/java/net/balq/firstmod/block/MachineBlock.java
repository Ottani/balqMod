package net.balq.firstmod.block;

import java.util.List;

import net.balq.firstmod.BalqFisrtMod;
import net.balq.firstmod.util.ModInformation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachineBlock extends Block {
	
	//private static final int ACTIVATED = 1;
	//private static final int CARD_BOX_INS = 2;
	//private static final int CARD_PLUS_INS = 4;
	//private static final int CARD_EMPTY_INS = 8;

	protected MachineBlock() {
		super(Material.iron);
		setCreativeTab(BalqFisrtMod.tabBalqMod);
		setHardness(2.0f);
		setResistance(5.0f);
		setStepSound(soundTypeMetal);
	}

	@Override
	public String getUnlocalizedName() {
		return "block.machineBlock";
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		icons = new IIcon[7];
		int i=0;
		icons[0] = register.registerIcon(ModInformation.ID+":machine_bottom");
		icons[1] = register.registerIcon(ModInformation.ID+":dis_machine_top");
		icons[2] = register.registerIcon(ModInformation.ID+":machine_side");
		icons[3] = register.registerIcon(ModInformation.ID+":act_machine_top");
		icons[4] = register.registerIcon(ModInformation.ID+":machine_side_plus");
		icons[5] = register.registerIcon(ModInformation.ID+":machine_side_box");
		icons[6] = register.registerIcon(ModInformation.ID+":machine_side_empty");
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		switch (side) {
			case 0: return icons[0];
			case 1: return meta%2==0?icons[1]:icons[3];
			case 2:
			case 3:
			case 4:
			case 5: {
				switch (meta/2) {
					case 0: return icons[2];
					case 1: return icons[4];
					case 2: return icons[5];
					case 3: return icons[6];
				}
			}
		}
		return icons[0];
	}

	private void spawnAnvil(World world, int x, int y, int z) {
		if (world.isAirBlock(x, y, z)) {
			world.setBlock(x, y, z, Blocks.anvil);
		}
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if (!world.isRemote && world.getBlockMetadata(x, y, z)%2!=0) {
			spawnAnvil(world, x, y+20, z);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!world.isRemote && world.getBlockMetadata(x, y, z)%2!=0 && world.isBlockIndirectlyGettingPowered(x, y, z)) {
			int delta = (world.getBlockMetadata(x, y, z)/2);
			//for (int i=-2; i<=2; ++i) {
			//	for (int j=-2; j<=2; ++j) {
					spawnAnvil(world, x+delta, y+20, z+delta);
			//	}
			//}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			int meta = world.getBlockMetadata(x, y, z);
			int active = (meta%2==0)?1:0;
			int card = meta/2;
			int newMeta = (card*2)+active;
			world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
		}
		return true;
	}
	
	//private int installedCard(int metadata) {
	//	return metadata%2;
	//}
	
	@Override
	public int damageDropped(int meta) {
		// TODO Auto-generated method stub
		return meta;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item,1,0));
		list.add(new ItemStack(item,1,2));
		list.add(new ItemStack(item,1,4));
		list.add(new ItemStack(item,1,6));
	}
	
}
