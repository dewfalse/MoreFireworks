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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public void spawnParticleStarterFX(double posX, double posY, double posZ,
			double motionX, double motionY, double motionZ, NBTTagCompound var3) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public void init() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public void saveLines(List<int[]> list) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

}
