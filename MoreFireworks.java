package morefireworks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(name="MoreFireworks", modid="MoreFireworks", version="1.0")
@NetworkMod
public class MoreFireworks {
	@SidedProxy(clientSide = "morefireworks.ClientProxy", serverSide = "morefireworks.CommonProxy")
	public static CommonProxy proxy;

	@Instance("MoreFireworks")
	public static MoreFireworks instance;

	@Mod.Init
	public void load(FMLInitializationEvent event) {
		Items.init();
		Blocks.init();
		Entities.init();
		Recipes.init();
		proxy.init();
		NetworkRegistry.instance().registerGuiHandler(instance,  proxy);
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(
				event.getSuggestedConfigurationFile());

		try {
			cfg.load();
			Config.entityCustomFireworkRocketId = cfg.get(Configuration.CATEGORY_GENERAL, "entityCustomFireworkRocketId", Config.entityCustomFireworkRocketId).getInt();
			Config.itemCustomFireworkStarId = cfg.get(Configuration.CATEGORY_ITEM, "itemCustomFireworkStarId", Config.itemCustomFireworkStarId).getInt();
			Config.itemCustomFireworkRocketId = cfg.get(Configuration.CATEGORY_ITEM, "itemCustomFireworkId", Config.itemCustomFireworkRocketId).getInt();
			Config.itemFireworkChalkId = cfg.get(Configuration.CATEGORY_ITEM, "itemFireworksChalkId", Config.itemFireworkChalkId).getInt();
			Config.itemFireworkRecipeId = cfg.get(Configuration.CATEGORY_ITEM, "itemFireworkRecipeId", Config.itemFireworkRecipeId).getInt();
			Config.itemFireworkPaperId = cfg.get(Configuration.CATEGORY_ITEM, "itemFireworkPaperId", Config.itemFireworkPaperId).getInt();
			Config.blockFireworkChalkId = cfg.get(Configuration.CATEGORY_BLOCK, "blockFireworksChalkId", Config.blockFireworkChalkId).getInt();
			Config.guiRecipePaperId = cfg.get(Configuration.CATEGORY_GENERAL, "guiRecipePaperId", Config.guiRecipePaperId).getInt();
			Config.enableSaveDesign = cfg.get(Configuration.CATEGORY_GENERAL, "enableSaveDesign", Config.enableSaveDesign).getBoolean(true);
			cfg.save();
		}
		catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "MoreFireworks load config exception");
		} finally {
			cfg.save();
		}
	}

}
