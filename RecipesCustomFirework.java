package morefireworks;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class RecipesCustomFirework implements IRecipe {

	private ItemStack output = null;
	@Override
	public boolean matches(InventoryCrafting inventory, World world) {
		output = null;
		int nPaper = 0;
		int nGunPowder = 0;
		int nDyePowder = 0;
		int nStar = 0;
		int nLightStoneDust = 0;
		int nFireballCharge = 0;

		for (int i = 0; i < inventory.getSizeInventory(); ++i) {
			ItemStack itemStack = inventory.getStackInSlot(i);

			if (itemStack != null) {
				if (itemStack.itemID == Item.gunpowder.itemID) {
					++nGunPowder;
				} else if (itemStack.itemID == Items.customFireworkStar.itemID) {
					++nStar;
				} else if (itemStack.itemID == Item.dyePowder.itemID) {
					++nDyePowder;
				} else if (itemStack.itemID == Item.paper.itemID) {
					++nPaper;
				} else if (itemStack.itemID == Item.lightStoneDust.itemID) {
					++nLightStoneDust;
				} else if (itemStack.itemID == Item.diamond.itemID) {
					++nLightStoneDust;
				} else if (itemStack.itemID == Item.fireballCharge.itemID) {
					++nFireballCharge;
				} else if (itemStack.itemID == Item.feather.itemID) {
					++nFireballCharge;
				} else if (itemStack.itemID == Item.goldNugget.itemID) {
					++nFireballCharge;
				} else {
					if (itemStack.itemID != Item.skull.itemID) {
						return false;
					}

					++nFireballCharge;
				}
			}
		}

		nLightStoneDust += nDyePowder + nFireballCharge;

		if (nGunPowder <= 3 && nPaper <= 1) {
			NBTTagCompound tag;
			NBTTagCompound tagFireworks;

			if (nGunPowder >= 1 && nPaper == 1 && nLightStoneDust == 0) {
				output = new ItemStack(Items.customFireworkRocket);

				tag = new NBTTagCompound();
				if (nStar > 0) {
					tagFireworks = new NBTTagCompound("Fireworks");
					NBTTagList var25 = new NBTTagList("Explosions");

					for (int var22 = 0; var22 < inventory
							.getSizeInventory(); ++var22) {
						ItemStack var26 = inventory
								.getStackInSlot(var22);

						if (var26 != null
								&& var26.itemID == Items.customFireworkStar.itemID
								&& var26.hasTagCompound()
								&& var26.getTagCompound().hasKey("Explosion")) {
							var25.appendTag(var26.getTagCompound()
									.getCompoundTag("Explosion"));
						}
					}

					tagFireworks.setTag("Explosions", var25);
					tagFireworks.setByte("Flight", (byte) nGunPowder);
					tag.setTag("Fireworks", tagFireworks);
				}

				output.setTagCompound(tag);
				return true;
			}
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
