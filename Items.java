package morefireworks;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDispenseFirework;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.MinecraftForgeClient;

public class Items {

	//private static String ITEMS_PNG = "/morefireworks/gfx/items.png";

	public static Item customFireworkRocket;
	public static Item customFireworkStar;
	public static Item recipeChalk;
	public static Item recipePaper;
	public static Item emptyPaper;

	public static void init() {
		customFireworkStar = new ItemCustomFireworkStar(Config.itemCustomFireworkStarId - 256).setItemName("customFireworkStar").setIconCoord(10, 12);
		LanguageRegistry.instance().addNameForObject(customFireworkStar, "en_US", "Custom Firework Star");
		customFireworkRocket = new ItemCustomFirework(Config.itemCustomFireworkRocketId - 256).setItemName("customFireworkRocket").setIconCoord(9, 12);
		LanguageRegistry.instance().addNameForObject(customFireworkRocket, "en_US", "Custom Firework Rocket");
		BehaviorCustomDispenseFirework var2 = new BehaviorCustomDispenseFirework(MinecraftServer.getServer());
		BlockDispenser.dispenseBehaviorRegistry.putObject(customFireworkRocket, var2);

		recipeChalk = new ItemFireworkChalk(Config.itemFireworkChalkId - 256).setIconIndex(13).setItemName("fireworkChalk").setCreativeTab(CreativeTabs.tabMisc);
		recipePaper = new ItemFireworkRecipe(Config.itemFireworkRecipeId - 256).setIconIndex(60).setItemName("fireworkRecipe").setCreativeTab(CreativeTabs.tabMisc);;
		emptyPaper = new ItemFireworkPaper(Config.itemFireworkPaperId - 256).setIconCoord(10, 3).setItemName("fireworkPaper").setCreativeTab(CreativeTabs.tabMisc);
		for(int i = 0; i < ItemDye.dyeColorNames.length; ++i) {
			LanguageRegistry.instance().addNameForObject(new ItemStack(Config.itemFireworkChalkId, 0, i), "en_US", "Firework Chalk (" + ItemDye.dyeColorNames[i] + ")");
		}
		LanguageRegistry.instance().addNameForObject(recipePaper, "en_US", "Firework Recipe");
		LanguageRegistry.instance().addNameForObject(emptyPaper, "en_US", "Firework Paper");
	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures() {
		//MinecraftForgeClient.preloadTexture(ITEMS_PNG);
	}
}
