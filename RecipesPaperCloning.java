package morefireworks;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipesPaperCloning implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting inventory, World world) {
		int emptyPapers = 0;
		ItemStack recipePaper = null;

		for(int i = 0; i < inventory.getSizeInventory(); ++i) {
			ItemStack itemStack = inventory.getStackInSlot(i);
			if(itemStack == null) {
				continue;
			}

			if(itemStack.itemID != Items.recipePaper.itemID && itemStack.itemID != Items.emptyPaper.itemID) {
				return false;
			}

			// written paper
			if(itemStack.itemID == Items.recipePaper.itemID) {
				if(recipePaper != null) {
					return false;
				}
				if(itemStack.hasTagCompound() == false) {
					return false;
				}
				NBTTagCompound tag = itemStack.getTagCompound();
				if(tag.hasKey("Size") == false) {
					return false;
				}
				recipePaper = itemStack;
			}
			else {
				emptyPapers++;
			}
		}

		return recipePaper != null && emptyPapers > 0;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		int emptyPapers = 0;
		ItemStack recipePaper = null;

		for(int i = 0; i < inventory.getSizeInventory(); ++i) {
			ItemStack itemStack = inventory.getStackInSlot(i);
			if(itemStack == null) {
				continue;
			}

			if(itemStack.itemID != Items.recipePaper.itemID && itemStack.itemID != Items.emptyPaper.itemID) {
				continue;
			}

			// written paper
			if(itemStack.itemID == Items.recipePaper.itemID) {
				if(recipePaper != null) {
					return null;
				}
				recipePaper = itemStack;
			}
			else {
				emptyPapers++;
			}
		}

		if(recipePaper != null && emptyPapers >= 0) {
			ItemStack writtenPapers = new ItemStack(Items.recipePaper, emptyPapers, 0);
			writtenPapers.setTagCompound(recipePaper.getTagCompound());
			return writtenPapers;
		}
		return null;
	}

	@Override
	public int getRecipeSize() {
		return 9;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

}
