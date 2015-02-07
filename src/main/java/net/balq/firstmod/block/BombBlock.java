package net.balq.firstmod.block;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.balq.firstmod.BalqFisrtMod;
import net.balq.firstmod.tileentity.BombTileEntity;
import net.balq.firstmod.util.ModInformation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BombBlock extends BlockContainer {
	
	@SideOnly(Side.CLIENT)
	private IIcon idleIcon;
	
	public BombBlock() {
		super(Material.iron);
		setHardness(3.0f);
		setResistance(5.0f);
		setStepSound(soundTypeMetal);
		setCreativeTab(BalqFisrtMod.tabBalqMod);
		setBlockTextureName(ModInformation.ID+":bomb");
	}
	
	@Override
	public String getUnlocalizedName() {
		return "block.bomb";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);
		 idleIcon = register.registerIcon(ModInformation.ID+":idle_bomb");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new BombTileEntity();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		return metadata==0?blockIcon:idleIcon;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return new ArrayList<ItemStack>();
	}
	
	//public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
	//	BombTileEntity tile = (BombTileEntity) world.getTileEntity(x, y, z);
	//	return tile.isIdle()?idleIcon:blockIcon;
	//}
}
