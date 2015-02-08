package net.balq.firstmod.entity;

import net.balq.firstmod.block.BalqBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBomb extends Entity {

	public EntityBomb(World world) {
		super(world);
		motionY = -0.6;
	}
	
	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (worldObj.isAirBlock((int)posX, (int)posY, (int)posZ)
				&& !worldObj.isAirBlock((int)posX, (int)posY - 1, (int)posZ)) {
				worldObj.setBlock((int)posX, (int)posY, (int)posZ, BalqBlocks.bomb);
				setDead();
			}
		}
		
		setPosition(posX + motionX, posY + motionY, posZ + motionZ);
	}

}
