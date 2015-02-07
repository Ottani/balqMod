package net.balq.firstmod.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigManager {
	public static int pickaxeMaxUses;

	public static void init(File file) {
		System.out.println(file.getAbsolutePath());
		Configuration config = new Configuration(file);
		config.load();
		pickaxeMaxUses = config.getInt("pickaxeMaxUses", "items", 1561, 0, 5000, "MAx uses for gem pickaxe");
		config.save();
	}
}
