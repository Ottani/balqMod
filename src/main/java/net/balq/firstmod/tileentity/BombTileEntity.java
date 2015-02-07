package net.balq.firstmod.tileentity;

import net.balq.firstmod.block.BalqBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.WorldEvent;

public class BombTileEntity extends TileEntity {
	private short timer;

	public BombTileEntity() {
		timer = 100;
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (timer == 0) {
				spread(xCoord + 1, yCoord, zCoord);
				spread(xCoord - 1, yCoord, zCoord);
				spread(xCoord, yCoord, zCoord + 1);
				spread(xCoord, yCoord, zCoord - 1);
				timer--;
				//worldObj.addBlockEvent(xCoord, yCoord, zCoord, BalqBlocks.bomb, 1, timer);
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
			} else {
				if (timer > 0)
					timer--;
			}
		}
	}

	private void spread(int x, int y, int z) {
		if (worldObj.isAirBlock(x, y, z)) {
			worldObj.setBlock(x, y, z, BalqBlocks.bomb);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("Timer", timer);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		timer = nbt.getShort("Timer");
	}

	public boolean isIdle() {
		return timer < 0;
	}
	
	//@Override
	//public boolean receiveClientEvent(int id, int value) {
	//	if (worldObj.isRemote && id==1) {
	//		timer = (short)value;
	//		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	//	}
	//	return true;
	//}
}
