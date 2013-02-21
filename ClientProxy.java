package morefireworks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		Items.registerTextures();
		Blocks.registerTextures();
		Entities.registerTextures();
		File root = new File(Minecraft.getMinecraftDir(), "/MoreFireworks/");
		root.mkdir();
		try {
			File sample1 = new File(root, "Sample1.simple");
			copyFromResource(sample1, "/samples/Sample1.simple");
			File sample2 = new File(root, "Sample2.design");
			copyFromResource(sample2, "/samples/Sample2.design");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void copyFromResource(File file, String res) throws IOException {
		InputStream inputRes = this.getClass().getResourceAsStream(res);
		if(inputRes == null) {
			return;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputRes));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(
				file, true)));

		try {
			while (reader.ready()) {
				writer.println(reader.readLine());
			}
		} finally {
			reader.close();
			writer.close();
		}
	}

	@Override
	public void spawnParticleStarterFX(double posX, double posY, double posZ,
			double motionX, double motionY, double motionZ, NBTTagCompound var3) {
		Minecraft mc = Minecraft.getMinecraft();
		mc.effectRenderer.addEffect(new EntityCustomFireworkStarterFX(mc.theWorld, posX, posY, posZ, motionX, motionY, motionZ, mc.effectRenderer, var3));

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == Config.guiRecipePaperId) {
			ItemStack itemStack = player.getCurrentEquippedItem();
			return new GuiRecipePaper(player.inventory, itemStack);

		}
		// TODO 自動生成されたメソッド・スタブ
		return super.getClientGuiElement(ID, player, world, x, y, z);
	}

	@Override
	public void saveLines(List<int[]> list) {
		if(Config.enableSaveDesign) {
			File root = new File(Minecraft.getMinecraftDir(), "/MoreFireworks/");
			if(root.isDirectory()) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
					String t = sdf.format(new java.util.Date());
					File file = new File(root.getPath(), t + ".design");
					FileWriter fw;
					fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);

					for(int[] line : list) {
						String s = "";
						s += String.valueOf(line[0]);
						s += ",";
						s += String.valueOf(line[1]);
						s += ",";
						s += String.valueOf(line[2]);
						s += ",";
						s += String.valueOf(line[3]);
						s += ",";
						s += String.valueOf(line[4]);
						s += "\r\n";
						bw.write(s);
					}

					bw.close();
					fw.close();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

			}
		}
	}
}
