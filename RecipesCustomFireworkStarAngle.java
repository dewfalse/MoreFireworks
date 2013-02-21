package morefireworks;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipesCustomFireworkStarAngle implements IRecipe {

	private ItemStack output = null;
	private int angle;

	@Override
	public boolean matches(InventoryCrafting inventory, World var2) {
		int size = inventory.getSizeInventory();
		int width = 0, height = 0;
		if(size == 4) {
			width = 2;
			height = 2;
		}else if(size == 9) {
			width = 3;
			height = 3;
		}
		else {
			return false;
		}

		ItemStack fireworkStar = null;
		ItemStack redstonepowder = null;
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		for(int i = 0; i < width; ++i) {
			for(int j = 0; j < height; ++j) {
				ItemStack itemStack = inventory.getStackInSlot(height * j + i);
				if(itemStack == null) {
					continue;
				}
				if(itemStack.itemID == Items.customFireworkStar.itemID) {
					if(fireworkStar != null) {
						return false;
					}
					fireworkStar = itemStack.copy();
					x1 = i;
					y1 = j;
				}
				else if(itemStack.itemID == Item.redstone.itemID) {
					if(redstonepowder != null) {
						return false;
					}
					redstonepowder = itemStack;
					x2 = i;
					y2 = j;
				}
			}
		}
		if(fireworkStar == null) {
			return false;
		}
		if(redstonepowder == null) {
			return false;
		}
		int x = x2 - x1;
		int y = y2 - y1;
		angle = 0;
		if(x == 1 && y == 0) {
			angle = 0;
		}
		else if(x == 1 && y == -1) {
			angle = 1;
		}
		else if(x == 0 && y == -1) {
			angle = 2;
		}
		else if(x == -1 && y == -1) {
			angle = 3;
		}
		else if(x == -1 && y == 0) {
			angle = 4;
		}
		else if(x == -1 && y == 1) {
			angle = 5;
		}
		else if(x == 0 && y == 1) {
			angle = 6;
		}
		else if(x == 1 && y == 1) {
			angle = 7;
		}
		else {
			return false;
		}

		NBTTagCompound tag = fireworkStar.getTagCompound();
		if(tag == null) {
			return false;
		}

		tag.setFloat("Angle", angle / 8.0F);
		output = fireworkStar;
		output.stackSize = 1;
		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		return output.copy();
	}

	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return output;
	}

}
