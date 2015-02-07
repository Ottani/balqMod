package net.balq.firstmod.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class MachineItem extends ItemBlock {

	public MachineItem(Block block) {
		super(block);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int dmg) {
		return dmg;
	}

}
