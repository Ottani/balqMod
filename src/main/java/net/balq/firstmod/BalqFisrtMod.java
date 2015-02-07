package net.balq.firstmod;

import net.balq.firstmod.proxy.CommonProxy;
import net.balq.firstmod.util.ConfigManager;
import net.balq.firstmod.util.ModInformation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION)
public class BalqFisrtMod {
	

	public static CreativeTabs tabBalqMod = new CreativeTabs("BalqModTab") {
		@SideOnly(Side.CLIENT)
		@Override
		public Item getTabIconItem() {
			return Items.blaze_rod;
		}
	};

	@Instance(ModInformation.ID)
	public static BalqFisrtMod instance;
	
	@SidedProxy(clientSide="net.balq.firstmod.proxy.ClientProxy", serverSide="net.balq.firstmod.proxy.CommonProxy")
	public static CommonProxy proxy;

	// Run before anything else. Read your config, create blocks, items, etc,
	// and register them with the GameRegistry.
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigManager.init(event.getSuggestedConfigurationFile());
		proxy.initItems();

		GameRegistry.registerWorldGenerator(new BalqWorldGenerator(), 1);
	}

	// Do your mod setup. Build whatever data structures you care about.
	// Register recipes, send FMLInterModComms messages to other mods.
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.initRecipes();
		proxy.initTiles();
	}

	// Handle interaction with other mods, complete your setup based on this.
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
