package morefireworks;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFireworkCharge;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemCustomFireworkStar extends ItemFireworkCharge {

	public ItemCustomFireworkStar(int par1) {
		super(par1);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		if(par1ItemStack.hasTagCompound()) {
			NBTTagCompound tag = par1ItemStack.getTagCompound();
			if(tag.hasKey("Explosion")) {
				NBTTagCompound tagExplosion = tag.getCompoundTag("Explosion");
				if(tagExplosion.hasKey("Size")) {
					int[] size = tagExplosion.getIntArray("Size");
					par3List.add(String.valueOf(size[0]) + " x " + String.valueOf(size[1]) + " size design");
				}
			}
		}
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

}
