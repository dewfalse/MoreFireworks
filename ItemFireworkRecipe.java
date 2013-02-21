package morefireworks;

import java.awt.Color;
import java.util.List;

import cpw.mods.fml.common.ICraftingHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ItemFireworkRecipe extends Item implements ICraftingHandler {

	private boolean written = false;

	public ItemFireworkRecipe(int par1) {
		super(par1);
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if(par1ItemStack.hasTagCompound()) {
			NBTTagCompound tag = par1ItemStack.getTagCompound();
			if(tag.hasKey("Start")) {
				return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
			}
		}
		par3EntityPlayer.openGui(MoreFireworks.instance, Config.guiRecipePaperId, par2World, (int)par3EntityPlayer.posX, (int)par3EntityPlayer.posY, (int)par3EntityPlayer.posZ);
		// TODO 自動生成されたメソッド・スタブ
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("Lines");
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World,
			int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(side == 0 || side == 1) {
			return false;
		}
		int blockId = par3World.getBlockId(x, y, z);
		if(blockId == 0) {
			return false;
		}
		Block block = Block.blocksList[blockId];
		if(block == null) {
			return false;
		}
		if(block instanceof BlockFireworkChalk == false) {
			if(side == 2) {
				z--;
			}
			if(side == 3) {
				z++;
			}
			if(side == 4) {
				x--;
			}
			if(side == 5) {
				x++;
			}
		}
		if(par1ItemStack.hasTagCompound()) {
			NBTTagCompound tag = par1ItemStack.getTagCompound();
			if(tag.hasKey("Start")) {
				int [] coord = tag.getIntArray("Start");
				NBTBase tagCoord = tag.getTag("Start");
				if(coord[0] == x) {
					if(coord[2] == z) {
						return false;
					}
					{
						NBTTagList tagList = new NBTTagList("Lines");
						NBTTagIntArray tagSize = new NBTTagIntArray("Size", new int[]{Math.abs(coord[2] - z) + 1, Math.abs(coord[1] - y) + 1});

						boolean bFound = false;
						int[][] design = new int[Math.abs(coord[2] - z) + 1][Math.abs(coord[1] - y) + 1];

						for(int j = Math.max(coord[1], y); j >= Math.min(coord[1], y); --j) {
							for(int k = Math.min(coord[2], z); k <= Math.max(coord[2], z); ++k) {

								int id = par3World.getBlockId(x, j, k);
								design[k - Math.min(coord[2], z)][j - Math.min(coord[1], y)] = -1;

								if(id != 0 && Block.blocksList[id] instanceof BlockFireworkChalk) {
									int metadata = par3World.getBlockMetadata(x, j, k);
									design[k - Math.min(coord[2], z)][j - Math.min(coord[1], y)] = metadata;
									bFound = true;
								}
							}
						}
						if(bFound == false) {
							return false;
						}
						List<int[]> list = FireworkDesign.pointsToLines(design);
						for(Object obj : list) {
							int[] line = (int[])obj;
							NBTTagIntArray tagArray = new NBTTagIntArray("Line", line);
							tagList.appendTag(tagArray);
						}
						MoreFireworks.proxy.saveLines(list);
						NBTTagCompound tagNew = new NBTTagCompound();
						tagNew.setTag("Size", tagSize);
						tagNew.setTag("Lines", tagList);
						par1ItemStack.setTagCompound(tagNew);
						return true;
					}
				}
				else if(coord[2] == z) {
					{
						NBTTagList tagList = new NBTTagList("Lines");
						NBTTagIntArray tagSize = new NBTTagIntArray("Size", new int[]{Math.abs(coord[0] - x) + 1, Math.abs(coord[1] - y) + 1});

						boolean bFound = false;
						int[][] design = new int[Math.abs(coord[0] - x) + 1][Math.abs(coord[1] - y) + 1];

						for(int j = Math.max(coord[1], y); j >= Math.min(coord[1], y); --j) {
							for(int i = Math.min(coord[0], x); i <= Math.max(coord[0], x); ++i) {

								int id = par3World.getBlockId(i, j, z);
								design[i - Math.min(coord[0], x)][j - Math.min(coord[1], y)] = -1;

								if(id != 0 && Block.blocksList[id] instanceof BlockFireworkChalk) {
									int metadata = par3World.getBlockMetadata(i, j, z);
									design[i - Math.min(coord[0], x)][j - Math.min(coord[1], y)] = metadata;
									bFound = true;
								}
							}
						}
						if(bFound == false) {
							return false;
						}
						List<int[]> list = FireworkDesign.pointsToLines(design);
						for(Object obj : list) {
							int[] line = (int[])obj;
							NBTTagIntArray tagArray = new NBTTagIntArray("Line", line);
							tagList.appendTag(tagArray);
						}
						MoreFireworks.proxy.saveLines(list);
						NBTTagCompound tagNew = new NBTTagCompound();
						tagNew.setTag("Size", tagSize);
						tagNew.setTag("Lines", tagList);
						par1ItemStack.setTagCompound(tagNew);
						return true;
					}
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		if(par1ItemStack.hasTagCompound()) {
			NBTTagCompound tag = par1ItemStack.getTagCompound();
			if(tag.hasKey("Start")) {
				par3List.add("Recording...");
			}
			else if(tag.hasKey("Size")) {
				int[] size = tag.getIntArray("Size");
				par3List.add(String.valueOf(size[0]) + " x " + String.valueOf(size[1]) + " size design");
			}
		}
	}

	@Override
	public ItemStack getContainerItemStack(ItemStack itemStack) {
		return itemStack.copy();
	}

	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return Color.PINK.getRGB();
	}

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,
			IInventory craftMatrix) {
		NBTTagCompound tag = item.getTagCompound();

		written  = tag.hasKey("Size");

	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
