package morefireworks;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class RecipesCustomFireworkStar implements IRecipe {

	private ItemStack output = null;
	@Override
	public boolean matches(InventoryCrafting inventory, World world) {
		ItemStack fireworkStar = null;
		ItemStack paper = null;
		for(int i = 0; i < inventory.getSizeInventory(); ++i) {
			ItemStack itemStack = inventory.getStackInSlot(i);
			if(itemStack == null) {
				continue;
			}

			if(itemStack.itemID == Item.field_92054_bV.itemID) {
				if(fireworkStar != null) {
					return false;
				}
				fireworkStar = itemStack;
			}
			else if(itemStack.itemID == Items.recipePaper.itemID || itemStack.itemID == Items.emptyPaper.itemID) {
				if(paper != null) {
					return false;
				}
				paper = itemStack;
			}
			else {
				return false;
			}
		}

		if(fireworkStar != null && paper != null) {
			output = new ItemStack(Items.customFireworkStar, 1, fireworkStar.getItemDamage());
			NBTTagCompound tag = fireworkStar.getTagCompound();
			if(tag == null) {
				return false;
			}
			if(tag.hasKey("Explosion") == false) {
				return false;
			}
			NBTTagCompound tagRoot = paper.hasTagCompound() ? paper.getTagCompound() : new NBTTagCompound();
			NBTTagList tagList = tagRoot.hasKey("Lines") ? tagRoot.getTagList("Lines") : new NBTTagList();
			int[] dummy = {1,1};
			NBTTagIntArray tagSize = (NBTTagIntArray) (tagRoot.hasKey("Lines") ? tagRoot.getTag("Size") : new NBTTagIntArray("Size", dummy));

			NBTTagCompound tagExplosion = tag.getCompoundTag("Explosion");
			tagExplosion.setTag("Size", tagSize);
			tagExplosion.setTag("Lines", tagList);
			output.setTagCompound(tag);
			return true;
		}
		return false;
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
