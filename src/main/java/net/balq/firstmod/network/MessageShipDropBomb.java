package net.balq.firstmod.network;

import net.balq.firstmod.entity.EntitySpaceShip;
import net.minecraft.entity.Entity;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageShipDropBomb implements IMessage, IMessageHandler<MessageShipDropBomb, IMessage> {
	
	private int entityId;

	public MessageShipDropBomb() {
	}
	
	public MessageShipDropBomb(int entityId) {
		this.entityId = entityId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		entityId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(entityId);
	}
	
	@Override
	public IMessage onMessage(MessageShipDropBomb message, MessageContext ctx) {
		System.out.println("Received message: "+message);
		Entity entity = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityId);
		if (entity!=null && entity instanceof EntitySpaceShip && entity.riddenByEntity == ctx.getServerHandler().playerEntity) {
			((EntitySpaceShip)entity).doDrop();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "MessageShipDropBomb [entityId=" + entityId + "]";
	}


}
