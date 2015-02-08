package net.balq.firstmod.proxy;

import net.balq.firstmod.client.RenderSpaceShip;
import net.balq.firstmod.entity.EntitySpaceShip;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void initRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySpaceShip.class, new RenderSpaceShip());
	}
	
}
