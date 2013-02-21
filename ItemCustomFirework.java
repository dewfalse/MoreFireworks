package morefireworks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFirework;
import net.minecraft.item.ItemFireworkCharge;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemCustomFirework extends ItemFirework {

	public ItemCustomFirework(int par1) {
		super(par1);
		// TODO 自動生成されたコンストラクター・スタブ
	}


	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par3World.isRemote)
        {
        	ItemStack itemStack = par1ItemStack.copy();
        	int[] color = {Color.RED.getRGB()};
        	int[] fadeColor = {};
        	int[][] vectorList = {};

        	NBTTagCompound tagExplosion = buildTagExplosion(false, false, (byte) 3, color, fadeColor, vectorList);
        	List<NBTTagCompound> explosions = new ArrayList();
        	explosions.add(tagExplosion);
        	setTagFireworks(itemStack, (byte) 0, explosions);
        	EntityCustomFireworkRocket var11 = new EntityCustomFireworkRocket(par3World, (double)((float)par4 + par8), (double)((float)par5 + par9), (double)((float)par6 + par10), par1ItemStack);
//        	var11.motionX = 0.05;
            par3World.spawnEntityInWorld(var11);

            if (!par2EntityPlayer.capabilities.isCreativeMode)
            {
                --par1ItemStack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

	public NBTTagCompound buildTagExplosion(boolean flicker, boolean trail, byte type, int[] colors, int[] fadeColors, int[][] vectorList) {
		NBTTagCompound tagExplosion = new NBTTagCompound("Explosion");
		if(flicker) {
			tagExplosion.setBoolean("Flicker", true);
		}
		if(trail) {
			tagExplosion.setBoolean("Trail", true);
		}
		tagExplosion.setIntArray("Colors", colors);
		tagExplosion.setIntArray("FadeColors", fadeColors);
		tagExplosion.setByte("Type", type);

		if(vectorList.length > 0) {
			NBTTagList tagLines = new NBTTagList("Lines");
			for(int[] vec : vectorList) {
				if(vec.length != 4) {
					continue;
				}
				int startX = vec[0];
				int startZ = vec[1];
				int endX = vec[2];
				int endZ = vec[3];
				int[] line = {startX, startZ, endX, endZ};
				NBTTagIntArray tagCoordPair = new NBTTagIntArray("CoordPair", line);
				tagLines.appendTag(tagCoordPair);
			}
			tagExplosion.setTag("Lines", tagLines);
		}

		NBTTagCompound tagRoot = new NBTTagCompound();
		tagRoot.setTag("Explosion", tagExplosion);
		return tagRoot;
	}

	public void setTagFireworks(ItemStack par1ItemStack, byte flight, List<NBTTagCompound> explosions) {
		NBTTagCompound tagRoot = par1ItemStack.hasTagCompound() ? par1ItemStack.getTagCompound() : new NBTTagCompound();
		NBTTagCompound tagFireworks = tagRoot.hasKey("Fireworks") ? tagRoot.getCompoundTag("Fireworks") : new NBTTagCompound();
		if(explosions.size() > 0) {
			NBTTagList tagExplosions = tagRoot.hasKey("Explosions") ? tagRoot.getTagList("Explosions") : new NBTTagList("Explosions");
			for(NBTTagCompound explosion : explosions) {
				tagExplosions.appendTag(explosion.getCompoundTag("Explosion"));
			}
			tagFireworks.setTag("Explosions", tagExplosions);
		}

		tagFireworks.setByte("Flight", flight);
        tagRoot.setTag("Fireworks", tagFireworks);
        par1ItemStack.setTagCompound(tagRoot);
	}


	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.hasTagCompound())
        {
            NBTTagCompound var5 = par1ItemStack.getTagCompound().getCompoundTag("Fireworks");

            if (var5 != null)
            {
                if (var5.hasKey("Flight"))
                {
                    par3List.add(StatCollector.translateToLocal("item.fireworks.flight") + " " + var5.getByte("Flight"));
                }

                NBTTagList var6 = var5.getTagList("Explosions");

                if (var6 != null && var6.tagCount() > 0)
                {
                    for (int var7 = 0; var7 < var6.tagCount(); ++var7)
                    {
                        NBTTagCompound var8 = (NBTTagCompound)var6.tagAt(var7);
                        ArrayList var9 = new ArrayList();
                        String name = var8.getString("Name");
                        if(name != null) {
                        	var9.add(name);
                        }
                        if (var9.size() > 0)
                        {
                            par3List.addAll(var9);
                        }
                    }
                }
            }
        }
	}


	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

}
