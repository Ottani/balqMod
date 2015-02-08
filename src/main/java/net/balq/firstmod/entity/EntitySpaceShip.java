package net.balq.firstmod.entity;

import io.netty.buffer.ByteBuf;
import net.balq.firstmod.network.MessageShipDropBomb;
import net.balq.firstmod.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySpaceShip extends Entity implements IEntityAdditionalSpawnData {
	private boolean charged;

	public EntitySpaceShip(World world) {
		super(world);
		setSize(1.5f, 0.6f);
		System.out.println("New EntitySpaceShip on client? "+world.isRemote);
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
			
		} else {
			sendInformation();
		}
		setPosition(posX+motionX, posY+motionY, posZ+motionZ);

	}
	
	private boolean lastPressedState;
	
	private void sendInformation() {
		boolean state = Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed();
		if (state) {
			System.out.println("state="+state);
			System.out.println("lastPressedState="+lastPressedState);
			System.out.println("charged="+charged);
		}
		if (state && !lastPressedState && charged && riddenByEntity == Minecraft.getMinecraft().thePlayer) {
			MessageShipDropBomb message = new MessageShipDropBomb(this.getEntityId());
			PacketHandler.INSTANCE.sendToServer(message);;
			System.out.println("Sent message: "+message);
		}

		lastPressedState = state;
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

	public void doDrop() {
		EntityBomb bomb = new EntityBomb(worldObj);
		bomb.posX = posX;
		bomb.posY = posY;
		bomb.posZ = posZ;
		worldObj.spawnEntityInWorld(bomb);
	}

}
