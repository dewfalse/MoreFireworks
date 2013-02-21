package morefireworks;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void spawnParticleStarterFX(double posX, double posY, double posZ,
			double motionX, double motionY, double motionZ, NBTTagCompound var3) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void init() {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void saveLines(List<int[]> list) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
