package morefireworks;

import java.awt.Color;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.google.common.io.Files;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;

@SideOnly(Side.CLIENT)
public class GuiRecipePaper extends GuiContainer {
	private GuiButton nextPageButton;
	private GuiButton prevPageButton;
	int listWidth = 96;

	List<File> fileList = new ArrayList();
	List<int[]> lines = new ArrayList();
	int selected = 0;
	int showing = -1;

	int xSize = 176;
	int ySize = 225;

	long lastClicked = 0;
	int page = 0;
	int item_num_per_page = 12;

	ItemStack itemStack = null;
	public GuiRecipePaper(InventoryPlayer inventory, ItemStack itemStack) {
		super(new Container() {

			@Override
			public boolean canInteractWith(EntityPlayer var1) {
				return false;
			}
		});

		this.itemStack = itemStack;
		if(this.itemStack.hasTagCompound()) {
			NBTTagCompound tag = this.itemStack.getTagCompound();
			if(tag.hasKey("Lines")) {
				NBTTagList tagLines = tag.getTagList("Lines");
				for(int i = 0; i < tagLines.tagCount(); ++i) {
					NBTTagIntArray tagLine = (NBTTagIntArray) tagLines.tagAt(i);
					int[] line = tagLine.intArray;
					this.lines.add(line);
				}
			}
		}

	}

	@Override
	public void initGui() {
		File root = new File(Minecraft.getMinecraftDir(), "/MoreFireworks/");
		if(root.isDirectory()) {
			File[] files = root.listFiles(getDesignFilter());
			fileList.clear();
			for(File file : files) {
				fileList.add(file);
			}
		}
        this.controlList.clear();

		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		prevPageButton = new GuiButton(0, j + 112, k + 123, 20, 20, "<");
		nextPageButton = new GuiButton(1, j + 134, k + 123, 20, 20, ">");

        controlList.add(prevPageButton);
        controlList.add(nextPageButton);

        Keyboard.enableRepeatEvents(true);
	}

	private FileFilter getDesignFilter() {
		return new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isFile() && (file.getName().endsWith(".design") || file.getName().endsWith(".simple") || file.getName().endsWith(".png"));
			}
		};
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		this.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/morefireworks/gfx/gui/paper.png"));
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {

		int i = 0;
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		for (File file : fileList) {
			String name = file.getName();
			if (name == null) {
				break;
			}
			if(i < page * item_num_per_page || (page + 1) * item_num_per_page <= i) {
				i++;
				continue;
			}
			int max_length = 22;
			if (name.length() > max_length) {
				name = name.substring(0, max_length);
			}

			if (i == selected) {
				int l1 = 8;
				int i2 = 24+81;
				drawGradientRect(j+l1, k+i2 + 9 * (i%item_num_per_page), j+l1 + 88, k+i2 + 9 * ((i%item_num_per_page) + 1), 0x80ffffff, 0x80ffffff);

			}

			fontRenderer.drawString(name, j+9, k+25+81 + 9 * (i%item_num_per_page), 0x404040);
			drawDesign(lines);

			i++;
		}
	}

	private void drawDesign(List<int[]> lines) {

		int minX = 0, minY = 0, maxX = 1, maxY = 1;
		for(int[] line : lines) {
			if(line[0] < minX) minX = line[0];
			if(maxX < line[0]) maxX = line[0];
			if(line[1] < minY) minY = line[1];
			if(maxY < line[1]) maxY = line[1];
			if(line[2] < minX) minX = line[2];
			if(maxX < line[2]) maxX = line[2];
			if(line[3] < minY) minY = line[3];
			if(maxY < line[3]) maxY = line[3];
		}
		int left = (width - xSize + 16) / 2;
		int top = (height - ySize) / 2;
		int bottom = height / 2 - 16;
		int width = xSize;
		int height = 80;
		for(int[] line : lines) {
			double t = Math.min(width / (maxX - minX + 1), height / (maxY - minY + 1));
			t *= 0.9;
			double startX = (line[0] - minX) * t;
			double startY = (line[1] - minY) * t;
			double endX = (line[2] - minX) * t;
			double endY = (line[3] - minY) * t;
			int color = line[4];
			Color c2 = new Color(color);
			c2.brighter();
			float a = (float) (c2.getAlpha() & 255) / 255.0F;
			float r = (float) (c2.getRed() & 255) / 255.0F;
			float g = (float) (c2.getGreen() & 255) / 255.0F;
			float b = (float) (c2.getBlue() & 255) / 255.0F;
			for(double d = 0.0D; d <= 1.0D; d+=0.25D) {
				Tessellator var9 = Tessellator.instance;
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(r, g, b, a);
				double x1 = startX + (endX - startX) * d;
				double y1 = startY + (endY - startY) * d;
				double x2 = startX + (endX - startX) * d + 2;
				double y2 = startY + (endY - startY) * d + 2;
				var9.startDrawingQuads();
				var9.addVertex((double)left + x1, (double)bottom - y1, (double)this.zLevel);
				var9.addVertex((double)left + x2, (double)bottom - y1, (double)this.zLevel);
		        var9.addVertex((double)left + x2, (double)bottom - y2, (double)this.zLevel);
		        var9.addVertex((double)left + x1, (double)bottom - y2, (double)this.zLevel);
				var9.draw();
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glDisable(GL11.GL_BLEND);
			}
		}	}

	@Override
	protected void mouseClicked(int i, int j, int k) {
		super.mouseClicked(i, j, k);

		int xMin = (width - xSize) / 2;
		int yMin = (height - ySize) / 2;

		int x = i - xMin;
		int y = j - yMin;

		if (x >= 8 && x <= 88) {
			int ySlot = (y - 24-81) / 9;

			if (ySlot >= 0 && ySlot <= 11) {
				if ( (ySlot + item_num_per_page * page) < fileList.size()) {
					if(selected%item_num_per_page == ySlot && Minecraft.getSystemTime() - this.lastClicked < 250L) {
						int index = item_num_per_page * page + selected;
						NBTTagCompound tagNew = new NBTTagCompound();
						NBTTagList tagList = new NBTTagList("Lines");
						NBTTagIntArray tagSize = new NBTTagIntArray("Size", new int[]{lines.get(0).length, lines.size()});
						for(Object obj : lines) {
							int[] line = (int[])obj;
							NBTTagIntArray tagArray = new NBTTagIntArray("Line", line);
							tagList.appendTag(tagArray);
						}
						tagNew.setTag("Size", tagSize);
						tagNew.setTag("Lines", tagList);
						tagNew.setString("Name", fileList.get(ySlot + item_num_per_page * page).getName());
						itemStack.setTagCompound(tagNew);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
						}
						this.mc.displayGuiScreen((GuiScreen)null);
						this.mc.thePlayer.addChatMessage(fileList.get(ySlot + item_num_per_page * page).getName() + " loaded!");
					}
					this.lastClicked = Minecraft.getSystemTime();
					selected = ySlot + item_num_per_page * page;
					if(fileList.get(selected).getName().endsWith(".design")) {
						if(showing != i) {
							lines = FireworkDesign.loadDesignFile(fileList.get(selected));
							showing = i;
						}
						drawDesign(lines);
					}
					else if(fileList.get(selected).getName().endsWith(".simple")) {
						if(showing != i) {
							lines = FireworkDesign.loadSimpleDesignFile(fileList.get(selected));
							showing = i;
						}
						drawDesign(lines);
					}
					else if(fileList.get(selected).getName().endsWith(".png")) {
						if(showing != i) {
							lines = FireworkDesign.loadPngDesignFile(fileList.get(selected));
							showing = i;
						}
						drawDesign(lines);
					}
				}
			}
		}
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton == nextPageButton) {
			if(fileList.size() / item_num_per_page > page) {
				page++;
			}
		}
		if (par1GuiButton == prevPageButton) {
			if(page > 0) {
				page--;
			}
		}
	}

}
