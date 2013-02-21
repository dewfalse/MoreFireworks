package morefireworks;

import java.awt.Color;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ItemFireworkPaper extends Item {

	public ItemFireworkPaper(int par1) {
		super(par1);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean onItemUseFirst(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World,
			int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		return false;
		/*if(side == 0 || side == 1) {
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
		else {
			NBTTagCompound tag = new NBTTagCompound();
			int [] coord = {x, y, z};
			NBTTagIntArray tagCoord = new NBTTagIntArray("Coord", coord);
			tag.setTag("Start", tagCoord);
	        --par1ItemStack.stackSize;
	        if(par1ItemStack.stackSize >= 0) {
	        	ItemStack itemStack = new ItemStack(Items.recipePaper.itemID, par1ItemStack.stackSize, 0);
	        	if(!par2EntityPlayer.inventory.addItemStackToInventory(itemStack.copy())) {
	        		par2EntityPlayer.dropPlayerItem(itemStack);
	        	}
	        }
			par1ItemStack.setTagCompound(tag);
			par1ItemStack.stackSize = 1;
			return true;
		}*/
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
			return false;
		}
		else {
			NBTTagCompound tag = new NBTTagCompound();
			int [] coord = {x, y, z};
			NBTTagIntArray tagCoord = new NBTTagIntArray("Coord", coord);
			tag.setTag("Start", tagCoord);
			--par1ItemStack.stackSize;
			int nEmptyPaper = par1ItemStack.stackSize;
			if(nEmptyPaper <= 0) {
				par1ItemStack.itemID = Items.recipePaper.itemID;
				par1ItemStack.stackSize = 1;
				par1ItemStack.setTagCompound(tag);
				return true;
			}
			else {
				ItemStack itemStack = new ItemStack(Items.recipePaper.itemID, 1, 0);
				itemStack.setTagCompound(tag);
				if (!par2EntityPlayer.inventory.addItemStackToInventory(itemStack.copy())) {
					par2EntityPlayer.dropPlayerItem(itemStack);
				}
			}
			return false;
		}
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return Color.PINK.getRGB();
	}

}
