package net.balq.firstmod.entity;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySpaceShip extends Entity implements IEntityAdditionalSpawnData {
	private boolean charged;

	public EntitySpaceShip(World world) {
		super(world);
		setSize(1.5f, 0.6f);
	}
	
	public boolean isCharged() {
		return charged;
	}
	
	public void setCharged() {
		charged = true;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entity) {
		if (entity != riddenByEntity) {
			return entity.boundingBox;
		}
		return null;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return !isDead;
	}
	
	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (!worldObj.isRemote && riddenByEntity == null) {
			player.mountEntity(this);
		}
		return true;
	}
	
	@Override
	public double getMountedYOffset() {
		return -0.15;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (riddenByEntity != null) {
				motionY = 0.2;
			} else if (worldObj.isAirBlock((int)posX, (int)posY - 1, (int)posZ)) {
				motionY = -0.1;
			} else {
				motionY = 0.0;
			}
			
		}
		setPosition(posX+motionX, posY+motionY, posZ+motionZ);

	}
	
	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		charged = nbt.getBoolean("charged");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("charged", charged);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeBoolean(charged);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		charged = additionalData.readBoolean();
	}

}
