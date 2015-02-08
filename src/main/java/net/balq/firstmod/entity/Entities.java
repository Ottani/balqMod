package net.balq.firstmod.entity;

import net.balq.firstmod.BalqFisrtMod;
import cpw.mods.fml.common.registry.EntityRegistry;

public class Entities {
	
	public static void init() {
		EntityRegistry.registerModEntity(EntitySpaceShip.class, "EntitySpaceShip", 0, BalqFisrtMod.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBomb.class, "EntityBomb", 1, BalqFisrtMod.instance, 80, 3, false);
	}
}
