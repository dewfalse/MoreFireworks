package morefireworks;

import java.awt.Color;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFireworkChalk extends Item {

	public ItemFireworkChalk(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	@Override
	public int getIconFromDamage(int par1) {
		return this.iconIndex;
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return ItemDye.dyeColors[par1ItemStack.getItemDamage()];
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < ItemDye.dyeColorNames.length; ++i) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getItemNameIS(ItemStack itemStack) {
		return (new StringBuilder()).append(super.getItemName()).append(".").append(ItemDye.dyeColorNames[itemStack.getItemDamage()]).toString();
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int i, int j,
			int k, int sideHit, float par8, float par9, float par10) {
		if(par2EntityPlayer.canPlayerEdit(i, j, k, sideHit, null) == false) {
			return false;
		}
		if(sideHit == 0 || sideHit == 1) {
			return false;
		}
		Block block = Block.blocksList[par3World.getBlockId(i, j, k)];
		if(block == null || block instanceof BlockFireworkChalk) {
			return false;
		}
		if(sideHit == 2) {
			k--;
		}
		if(sideHit == 3) {
			k++;
		}
		if(sideHit == 4) {
			i--;
		}
		if(sideHit == 5) {
			i++;
		}
		if(par3World.setBlockAndMetadataWithNotify(i, j, k, Config.blockFireworkChalkId + sideHit - 2, par1ItemStack.getItemDamage())) {
			if(par2EntityPlayer.capabilities.isCreativeMode == false) {
				if(--par1ItemStack.stackSize == 0) {
					par1ItemStack = null;
				}
			}
			return true;
		}
		return false;

	}
}
