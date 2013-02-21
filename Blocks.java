package morefireworks;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraftforge.client.MinecraftForgeClient;

public class Blocks {

	//public static String BLOCKS_PNG = "/morefireworks/gfx/blocks.png";
	//public static String CHALK_PNG = "/morefireworks/gfx/chalk.png";
	public static Block blockChalk0;
	public static Block blockChalk1;
	public static Block blockChalk2;
	public static Block blockChalk3;

	public static void init() {
		blockChalk0 = (new BlockFireworkChalk(Config.blockFireworkChalkId+0, 0, 0)).setHardness(0f).setStepSound(Block.soundPowderFootstep).setBlockName("Chalk");
		blockChalk1 = (new BlockFireworkChalk(Config.blockFireworkChalkId+1, 0, 1)).setHardness(0f).setStepSound(Block.soundPowderFootstep).setBlockName("Chalk");
		blockChalk2 = (new BlockFireworkChalk(Config.blockFireworkChalkId+2, 0, 2)).setHardness(0f).setStepSound(Block.soundPowderFootstep).setBlockName("Chalk");
		blockChalk3 = (new BlockFireworkChalk(Config.blockFireworkChalkId+3, 0, 3)).setHardness(0f).setStepSound(Block.soundPowderFootstep).setBlockName("Chalk");
		GameRegistry.registerBlock(blockChalk0);
		GameRegistry.registerBlock(blockChalk1);
		GameRegistry.registerBlock(blockChalk2);
		GameRegistry.registerBlock(blockChalk3);
		LanguageRegistry.addName(blockChalk0, "Chalk");
		LanguageRegistry.addName(blockChalk1, "Chalk");
		LanguageRegistry.addName(blockChalk2, "Chalk");
		LanguageRegistry.addName(blockChalk3, "Chalk");
		BlockFireworkChalk.renderId = RenderingRegistry.getNextAvailableRenderId();
	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures() {
		//MinecraftForgeClient.preloadTexture(BLOCKS_PNG);
		//MinecraftForgeClient.preloadTexture(CHALK_PNG);
		RenderingRegistry.registerBlockHandler(new RenderFireworkChalk());
	}
}
