package net.balq.firstmod.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.balq.firstmod.item.MachineItem;
import net.balq.firstmod.tileentity.BombTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BalqBlocks {
	public static Block gemBlock;
	public static Block gemOre;
	public static Block machineBlock;
	public static Block bomb;
	
	public static void init() {
		gemBlock = new GemBlock();
		GameRegistry.registerBlock(gemBlock, "gemBlock");

		gemOre = new GemOre(Material.rock);
		GameRegistry.registerBlock(gemOre, "gemOre");
		
		machineBlock = new MachineBlock();
		GameRegistry.registerBlock(machineBlock, MachineItem.class, "machineBlock");
		
		bomb = new BombBlock();
		GameRegistry.registerBlock(bomb, "bomb");
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(BombTileEntity.class, "BombTE");
	}
	
}
