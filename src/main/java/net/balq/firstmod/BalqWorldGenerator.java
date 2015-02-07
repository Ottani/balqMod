package net.balq.firstmod;

import java.util.Random;

import net.balq.firstmod.block.BalqBlocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class BalqWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int cx, int cz, World world, IChunkProvider arg4, IChunkProvider arg5) {
		if (world.provider.dimensionId==0) {
			generateSurface(world, random, cx*16, cz*16);
		}
	}
	
	private void generateSurface(World world, Random random, int x, int z) {
		for (int i=0; i<40; ++i) {
			int px = x + random.nextInt(16);
			int pz = z + random.nextInt(16);
			int py = random.nextInt(32)+10;
			new WorldGenMinable(BalqBlocks.gemOre, 12).generate(world, random, px, py, pz);
		}
	}

}
