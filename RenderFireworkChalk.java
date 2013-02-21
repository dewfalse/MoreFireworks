package morefireworks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemDye;
import net.minecraft.src.ModLoader;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderFireworkChalk implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		RenderEngine renderEngine = ModLoader.getMinecraftInstance().renderEngine;
		//renderEngine.bindTexture(renderEngine.getTexture(Blocks.CHALK_PNG));
		renderEngine.bindTexture(renderEngine.getTexture("/terrain.png"));
		renderChalkPatterns(block, x, y, z, world);
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		renderEngine.bindTexture(renderEngine.getTexture("/terrain.png"));
		return true;
	}

	private void renderChalkPatterns(Block block, int x, int y, int z, IBlockAccess world) {
		int sideHit = block.blockID + 2 - Config.blockFireworkChalkId;
		double d = (float) 4 / 16f;
		double d2 = ((float) 4 + 0.99F) / 16f;
		double d4 = (float) 10 / 16f;
		double d6 = ((float) 10 + 0.99F) / 16f;
		if(sideHit == 2) {
			Tessellator tessellator = Tessellator.instance;
			tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
			tessellator.setColorOpaque_F(1, 1, 1);
			float f5 = x + 0;
			float f6 = x + 1;
			float f7 = y + 0;
			float f8 = y + 1;
			double hu = 1.0D - 0.015625D;
			double hj = 1.0D - 0.012D;
			double hi = 0.03125D;
			int metadata = world.getBlockMetadata(x, y, z);
			tessellator.setColorRGBA_I(ItemDye.dyeColors[metadata], 255);
			tessellator.addVertexWithUV(f6, f8, (double) z + hu, d, d6);
			tessellator.addVertexWithUV(f6, f7, (double) z + hu, d2, d6);
			tessellator.addVertexWithUV(f5, f7, (double) z + hu, d2, d4);
			tessellator.addVertexWithUV(f5, f8, (double) z + hu, d, d4);

			//tessellator.setColorOpaque_F(0, 0, 0);
			//tessellator.addVertexWithUV(f6 + hi, f8, (double) z + hj, d, d6);
			//tessellator.addVertexWithUV(f6 + hi, f7, (double) z + hj, d2, d6);
			//tessellator.addVertexWithUV(f5 + hi, f7, (double) z + hj, d2, d4);
			//tessellator.addVertexWithUV(f5 + hi, f8, (double) z + hj, d, d4);
			return;
		}
		if(sideHit == 3) {
			Tessellator tessellator = Tessellator.instance;
			tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
			tessellator.setColorOpaque_F(1, 1, 1);
			float f5 = x + 1;
			float f6 = x + 0;
			float f7 = y + 0;
			float f8 = y + 1;
			double hu = 0.012D;
			double hj = 0.015625D;
			double hi = 0.03125D;
			int metadata = world.getBlockMetadata(x, y, z);
			tessellator.setColorRGBA_I(ItemDye.dyeColors[metadata], 255);
			tessellator.addVertexWithUV(f6, f8, (double) z + hu, d, d6);
			tessellator.addVertexWithUV(f6, f7, (double) z + hu, d2, d6);
			tessellator.addVertexWithUV(f5, f7, (double) z + hu, d2, d4);
			tessellator.addVertexWithUV(f5, f8, (double) z + hu, d, d4);

			//tessellator.setColorOpaque_F(0, 0, 0);
			//tessellator.addVertexWithUV(f6 + hi, f8, (double) z + hj, d, d6);
			//tessellator.addVertexWithUV(f6 + hi, f7, (double) z + hj, d2, d6);
			//tessellator.addVertexWithUV(f5 + hi, f7, (double) z + hj, d2, d4);
			//tessellator.addVertexWithUV(f5 + hi, f8, (double) z + hj, d, d4);
			return;
		}
		if(sideHit == 4) {
			Tessellator tessellator = Tessellator.instance;
			tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
			tessellator.setColorOpaque_F(1, 1, 1);
			float f5 = z + 1;
			float f6 = z + 0;
			float f7 = y + 0;
			float f8 = y + 1;
			double hu = 1.0D - 0.015625D;
			double hj = 1.0D - 0.012D;
			double hi = 0.03125D;
			int metadata = world.getBlockMetadata(x, y, z);
			tessellator.setColorRGBA_I(ItemDye.dyeColors[metadata], 255);
			tessellator.addVertexWithUV((double) x + hu, f8, f6, d, d6);
			tessellator.addVertexWithUV((double) x + hu, f7, f6, d2, d6);
			tessellator.addVertexWithUV((double) x + hu, f7, f5, d2, d4);
			tessellator.addVertexWithUV((double) x + hu, f8, f5, d, d4);

			//tessellator.setColorOpaque_F(0, 0, 0);
			//tessellator.addVertexWithUV((double) x + hj, f8, f6 + hi, d, d6);
			//tessellator.addVertexWithUV((double) x + hj, f7, f6 + hi, d2, d6);
			//tessellator.addVertexWithUV((double) x + hj, f7, f5 + hi, d2, d4);
			//tessellator.addVertexWithUV((double) x + hj, f8, f5 + hi, d, d4);
			return;
		}
		if(sideHit == 5) {
			Tessellator tessellator = Tessellator.instance;
			tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
			tessellator.setColorOpaque_F(1, 1, 1);
			float f5 = z + 0;
			float f6 = z + 1;
			float f7 = y + 0;
			float f8 = y + 1;
			double hu = 0.015625D;
			double hj = 0.012D;
			double hi = 0.03125D;
			int metadata = world.getBlockMetadata(x, y, z);
			tessellator.setColorRGBA_I(ItemDye.dyeColors[metadata], 255);
			tessellator.addVertexWithUV((double) x + hu, f8, f6, d, d6);
			tessellator.addVertexWithUV((double) x + hu, f7, f6, d2, d6);
			tessellator.addVertexWithUV((double) x + hu, f7, f5, d2, d4);
			tessellator.addVertexWithUV((double) x + hu, f8, f5, d, d4);

			//tessellator.setColorOpaque_F(0, 0, 0);
			//tessellator.addVertexWithUV((double) x + hj, f8, f6 + hi, d, d6);
			//tessellator.addVertexWithUV((double) x + hj, f7, f6 + hi, d2, d6);
			//tessellator.addVertexWithUV((double) x + hj, f7, f5 + hi, d2, d4);
			//tessellator.addVertexWithUV((double) x + hj, f8, f5 + hi, d, d4);
			return;
		}
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return BlockFireworkChalk.renderId;
	}

}
