package net.balq.firstmod.tileentity;

import net.balq.firstmod.block.BalqBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.WorldEvent;

public class BombTileEntity extends TileEntity {
	private static final short SPREAD_TIMER = 5;
	private static final short SPREAD_LEVEL = 10;

	private short timer;
	private short level;

	public BombTileEntity() {
		timer = SPREAD_TIMER;
		level = 0;
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (timer == 0 && level < SPREAD_LEVEL) {
				spread(xCoord + 1, yCoord, zCoord);
				spread(xCoord - 1, yCoord, zCoord);
				spread(xCoord, yCoord, zCoord + 1);
				spread(xCoord, yCoord, zCoord - 1);
				// worldObj.addBlockEvent(xCoord, yCoord, zCoord, BalqBlocks.bomb, 1, timer);
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
			} else if (timer == SPREAD_TIMER * (level - SPREAD_LEVEL)) {
				worldObj.createExplosion(null, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 4, true);
			}
			timer--;
		}
	}

	private void spread(int x, int y, int z) {
		if (worldObj.isAirBlock(x, y, z)) {
			worldObj.setBlock(x, y, z, BalqBlocks.bomb);
			TileEntity te = worldObj.getTileEntity(x, y, z);
			if (te != null && te instanceof BombTileEntity) {
				((BombTileEntity) te).level = (short) (level + 1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("Timer", timer);
		nbt.setShort("Level", level);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		timer = nbt.getShort("Timer");
		level = nbt.getShort("Level");
	}

	public boolean isIdle() {
		return timer < 0;
	}

	// @Override
	// public boolean receiveClientEvent(int id, int value) {
	// if (worldObj.isRemote && id==1) {
	// timer = (short)value;
	// worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	// }
	// return true;
	// }
}
