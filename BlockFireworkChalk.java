package morefireworks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockFireworkChalk extends Block {

	public static int renderId;

	public BlockFireworkChalk(int par1, int par2, int par3) {
		super(par1, par2, Material.circuits);
		if(par3 == 0) {
			setBlockBounds(0.0F, 0.0F, 1.0F - 0.0625F, 1.0F, 1.0F, 1.0F);
		}
		if(par3 == 1) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
		}
		if(par3 == 2) {
			setBlockBounds(1.0F - 0.0625F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		if(par3 == 3) {
			setBlockBounds(0.0F , 0.0F, 0.0F, 0.0625F, 1.0F, 1.0F);
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return renderId;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return blockIndexInTexture;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		int sideHit = this.blockID - Config.blockFireworkChalkId;
		int blockId = 0;
		if(sideHit == 0) {
			blockId = par1World.getBlockId(par2, par3, par4 + 1);
		}
		if(sideHit == 1) {
			blockId = par1World.getBlockId(par2, par3, par4 - 1);
		}
		if(sideHit == 2) {
			blockId = par1World.getBlockId(par2 + 1, par3, par4);
		}
		if(sideHit == 3) {
			blockId = par1World.getBlockId(par2 - 1, par3, par4);
		}
		int metadata = par1World.getBlockMetadata(par2, par3, par4);
		if(blockId == 0) {
			dropBlockAsItem(par1World, par2, par3, par4, metadata, 0);
			par1World.setBlockWithNotify(par2, par3, par4, 0);
			return;
		}
		Block block = Block.blocksList[blockId];
		if(block == null) {
			dropBlockAsItem(par1World, par2, par3, par4, metadata, 0);
			par1World.setBlockWithNotify(par2, par3, par4, 0);
			return;
		}
		if(block.isOpaqueCube() == false) {
			dropBlockAsItem(par1World, par2, par3, par4, metadata, 0);
			par1World.setBlockWithNotify(par2, par3, par4, 0);
			return;
		}
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Config.itemFireworkChalkId;
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

}
