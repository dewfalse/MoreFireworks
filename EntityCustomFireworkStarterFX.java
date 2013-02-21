package morefireworks;

import java.awt.Color;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFireworkOverlayFX;
import net.minecraft.client.particle.EntityFireworkSparkFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityCustomFireworkStarterFX extends EntityFX {

	private int field_92042_ax = 0;
	private final EffectRenderer effectRenderer;
	private NBTTagList tagRoot;
	boolean field_92041_a;

	public EntityCustomFireworkStarterFX(World par1World, double par2,
			double par4, double par6, double par8, double par10, double par12,
			EffectRenderer par14EffectRenderer,
			NBTTagCompound par15NBTTagCompound) {
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.motionX = par8;
		this.motionY = par10;
		this.motionZ = par12;
		this.effectRenderer = par14EffectRenderer;
		this.particleMaxAge = 8;

		if (par15NBTTagCompound != null) {
			this.tagRoot = par15NBTTagCompound.getTagList("Explosions");

			if (this.tagRoot != null && this.tagRoot.tagCount() == 0) {
				this.tagRoot = null;
			} else if (this.tagRoot != null) {
				this.particleMaxAge = this.tagRoot.tagCount() * 2 - 1;

				for (int var16 = 0; var16 < this.tagRoot.tagCount(); ++var16) {
					NBTTagCompound var17 = (NBTTagCompound) this.tagRoot
							.tagAt(var16);

					if (var17.getBoolean("Flicker")) {
						this.field_92041_a = true;
						this.particleMaxAge += 15;
						break;
					}
				}
			}
		}
	}

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {}

	public void onUpdate() {
		boolean var1;

		if (this.field_92042_ax == 0 && this.tagRoot != null) {
			var1 = this.isFar();
			boolean var2 = false;

			if (this.tagRoot.tagCount() >= 3) {
				var2 = true;
			} else {
				for (int var3 = 0; var3 < this.tagRoot.tagCount(); ++var3) {
					NBTTagCompound var4 = (NBTTagCompound) this.tagRoot
							.tagAt(var3);

					if (var4.getByte("Type") == 1) {
						var2 = true;
						break;
					}
				}
			}

			String var15 = "fireworks." + (var2 ? "largeBlast" : "blast")
					+ (var1 ? "_far" : "");
			this.worldObj.playSound(this.posX, this.posY, this.posZ, var15,
					20.0F, 0.95F + this.rand.nextFloat() * 0.1F, true);
		}

        if (this.field_92042_ax % 2 == 0 && this.tagRoot != null && this.field_92042_ax / 2 < this.tagRoot.tagCount())
        {
            int var13 = this.field_92042_ax / 2;
            NBTTagCompound var14 = (NBTTagCompound)this.tagRoot.tagAt(var13);
            byte type = var14.getByte("Type");
            boolean trail = var14.getBoolean("Trail");
            boolean flicker = var14.getBoolean("Flicker");
            int[] colors = var14.getIntArray("Colors");
            int[] fadeColors = var14.getIntArray("FadeColors");
            //NBTTagList tagCoords = var14.getTagList("Coords");
            NBTTagList tagLines = var14.getTagList("Lines");
            int[] size = var14.getIntArray("Size");

        	float r = this.rand.nextFloat() * (float)Math.PI;
            if(var14.hasKey("Angle")) {
            	r = var14.getFloat("Angle");
            }
            if (type == 1)
            {
                this.makeSparks(0.5D, new double[][] {{0.0D, 0.0D},{0.0D, 0.1D},{0.0D, 0.2D},{0.1D, 0.1D},{0.1D, 0.0D},{0.1D, 0.1D},{0.2D, 0.2D},{0.2D, 0.1D},{0.2D, 0.0D}}, colors, fadeColors, r, trail, flicker, true, false);
                this.makeSparks(0.5D, new double[][] {{0.3D, 0.0D},{0.3D, 0.1D},{0.3D, 0.2D},}, colors, fadeColors, r, trail, flicker, true, false);
                this.makeSparks(0.5D, new double[][] {{0.4D, 0.0D},{0.4D, 0.1D},{0.4D, 0.2D},{0.5D, 0.1D},{0.6D, 0.0D},{0.6D, 0.1D},{0.6D, 0.2D},}, colors, fadeColors, r, trail, flicker, true, false);
                this.makeSparks(0.5D, new double[][] {{0.7D, 0.0D},{0.8D, 0.0D},{0.7D, 0.0D},{0.7D, 0.1D},{0.8D, 0.1D},{0.7D, 0.1D},{0.7D, 0.2D},{0.8D, 0.2D},{0.7D, 0.2D},}, colors, fadeColors, r, trail, flicker, true, false);
                this.makeSparks(0.5D, new double[][] {{1.0D, 0.18D},{0.98D, 0.2D},{0.92D, 0.2D},{0.9D, 0.18D},{0.9D, 0.02D},{0.92D, 0.0D},{0.98D, 0.0D},{1.0D, 0.02D},}, colors, fadeColors, r, trail, flicker, true, false);
                this.makeSparks(0.5D, new double[][] {{1.1D, 0.0D},{1.1D, 0.1D},{1.1D, 0.2D},{1.2D, 0.2D},{1.2D, 0.1D},{1.1D, 0.1D},{1.2D, 0.0D},}, colors, fadeColors, r, trail, flicker, true, false);
                this.makeSparks(0.5D, new double[][] {{1.35D, 0.1D},{1.3D, 0.0D},{1.35D, 0.1D},{1.4D, 0.2D},{1.45D, 0.1D},{1.5D, 0.0D},{1.45D, 0.1D},{1.35D, 0.1D},}, colors, fadeColors, r, trail, flicker, true, false);
                this.makeSparks(0.5D, new double[][] {{1.6D, 0.0D},{1.6D, 0.1D},{1.7D, 0.1D},{1.6D, 0.1D},{1.6D, 0.2D},{1.7D, 0.2D},}, colors, fadeColors, r, trail, flicker, true, false);
                this.makeSparks(0.5D, new double[][] {{1.9D, 0.2D},{1.8D, 0.2D},{1.9D, 0.2D},{2.0D, 0.2D},{1.9D, 0.2D},{1.9D, 0.1D},{1.9D, 0.0D},}, colors, fadeColors, r, trail, flicker, true, false);
            }
            else if (type == 2)
            {
            	// heart
                this.makeSparks(0.5D, new double[][] {{0.0D, 0.3D}, {0.1D, 0.4D}, {0.2D, 0.4D}, {0.3D, 0.3D}, {0.3D, 0.15D}, {0.3D, 0.0D}, {0.15D, -0.2D}, {0.0D, -0.4D},
                		{0.15D, -0.2D}, {0.3D, 0.0D}, {0.3D, 0.15D}, {0.3D, 0.3D}, {0.2D, 0.4D}, {0.1D, 0.4D}, {0.0D, 0.3D}},  colors, fadeColors, r, trail, flicker, true, true);
            }
            //else if (type == 3)
            //{
            //}
            else if (type == 4)
            {
            	// note
                this.makeSparks(0.5D, new double[][] {
                		{0.0D, 0.3D},
                		{0.1D, 0.2D},
                		{0.2D, 0.1D},
                		{0.1D, 0.0D},
                		{0.2D, 0.08D},
                		{0.1D, 0.18D},
                		{0.0D, 0.28D},
                		{0.0D, 0.2D},
                		{0.0D, 0.1D},
                		{0.0D, 0.0D},
                		{0.0D, -0.1D},
                		{0.0D, -0.2D},
                		{-0.05D, -0.25D},
                		{-0.1D, -0.25D},
                		{-0.1D, -0.2D},
                		{-0.05D, -0.15D},
                		{0.0D, -0.2D},
                		}, colors, fadeColors, r, trail, flicker, true, false);
            }
            else
            {
				int l = size[0];
				int m = size[1];
				double a = m / 2.0D;
				double hw = l / 2.0D;
				double hh = m / 2.0D;
				for (int i = 0; i < tagLines.tagCount(); ++i) {
					NBTTagIntArray tagLine = (NBTTagIntArray) tagLines.tagAt(i);
					int[] line = tagLine.intArray;
					double[][] points = {
							{ (line[0] - hw) / a, (line[1] - hh) / a },
							{ (line[2] - hw) / a, (line[3] - hh) / a }, };
					this.makeLine(0.5D, points, line[4], r, trail, flicker,
							true);
				}
			}

			int var8 = colors[0];
			float var9 = (float) ((var8 & 16711680) >> 16) / 255.0F;
			float var10 = (float) ((var8 & 65280) >> 8) / 255.0F;
			float var11 = (float) ((var8 & 255) >> 0) / 255.0F;
			EntityCustomeFireworkOverlayFX var12 = new EntityCustomeFireworkOverlayFX(this.worldObj, this.posX, this.posY, this.posZ);
			var12.setRBGColorF(var9, var10, var11);
			this.effectRenderer.addEffect(var12);
		}

		++this.field_92042_ax;

		if (this.field_92042_ax > this.particleMaxAge) {
			if (this.field_92041_a) {
				var1 = this.isFar();
				String var16 = "fireworks."
						+ (var1 ? "twinkle_far" : "twinkle");
				this.worldObj.playSound(this.posX, this.posY, this.posZ, var16,
						20.0F, 0.9F + this.rand.nextFloat() * 0.15F, true);
			}

			this.setDead();
		}
	}

	private boolean isFar() {
		Minecraft var1 = Minecraft.getMinecraft();
		return var1 == null
				|| var1.renderViewEntity == null
				|| var1.renderViewEntity.getDistanceSq(this.posX, this.posY,
						this.posZ) >= 256.0D;
	}

	private void makeSpark(double posX, double posY, double posZ,
			double motionX, double motionY, double motionZ, int[] colors,
			int[] fadeColors, boolean trail, boolean flicker) {
		EntityFireworkSparkFX var17 = new EntityFireworkSparkFX(this.worldObj,
				posX, posY, posZ, motionX, motionY, motionZ,
				this.effectRenderer);
		var17.func_92045_e(trail);
		var17.func_92043_f(flicker);
		int var18 = this.rand.nextInt(colors.length);
		var17.func_92044_a(colors[var18]);

		if (fadeColors != null && fadeColors.length > 0) {
			var17.func_92046_g(fadeColors[this.rand.nextInt(fadeColors.length)]);
		}

		this.effectRenderer.addEffect(var17);
	}

	private void makeSpark(double posX, double posY, double posZ,
			double motionX, double motionY, double motionZ, int color,
			boolean trail, boolean flicker) {
		EntityFireworkSparkFX var17 = new EntityFireworkSparkFX(this.worldObj,
				posX, posY, posZ, motionX, motionY, motionZ,
				this.effectRenderer);
		var17.func_92045_e(trail);
		var17.func_92043_f(flicker);
		var17.func_92044_a(color);

		this.effectRenderer.addEffect(var17);
	}

	private void makeSparks(double par1, double[][] points, int[] colors,
			int[] fadeColors, float var13, boolean trail, boolean flicker,
			boolean par8, boolean traversable) {
		double var9 = points[0][0];
		double var11 = points[0][1];
		this.makeSpark(this.posX, this.posY, this.posZ, var9 * par1, var11
				* par1, 0.0D, colors, fadeColors, trail, flicker);
		double var14 = par8 ? 0.034D : 0.34D;

		for (int var16 = 0; var16 < 3; ++var16) {
			double var17 = (double) var13
					+ (double) ((float) var16 * (float) Math.PI) * var14;
			double var19 = var9;
			double var21 = var11;

			for (int var23 = 1; var23 < points.length; ++var23) {
				double var24 = points[var23][0];
				double var26 = points[var23][1];

				for (double var28 = 0.25D; var28 <= 1.0D; var28 += 0.25D) {
					double var30 = (var19 + (var24 - var19) * var28) * par1;
					double var32 = (var21 + (var26 - var21) * var28) * par1;
					double var34 = var30 * Math.sin(var17);
					var30 *= Math.cos(var17);

					for (double var36 = -1.0D; var36 <= 1.0D; var36 += 2.0D) {
						this.makeSpark(this.posX, this.posY, this.posZ, var30
								* var36, var32, var34 * var36, colors,
								fadeColors, trail, flicker);
						if (traversable == false) {
							break;
						}
					}
				}

				var19 = var24;
				var21 = var26;
			}
		}
	}

	private void makeLine(double par1, double[][] points, int color,
			float var13, boolean trail, boolean flicker, boolean par8) {
		double var9 = points[0][0];
		double var11 = points[0][1];
		// int c = ItemDye.dyeColors[color];
		// this.makeSpark(this.posX, this.posY, this.posZ, var9 * par1, var11 *
		// par1, 0.0D, c, trail, flicker);
		double var14 = par8 ? 0.034D : 0.34D;

		for (int var16 = 0; var16 < 3; ++var16) {
			double angle = (double) var13
					+ (double) ((float) var16 * (float) Math.PI) * var14;
			double var19 = var9;
			double var21 = var11;

			for (int var23 = 1; var23 < points.length; ++var23) {
				double var24 = points[var23][0];
				double var26 = points[var23][1];

				for (double var28 = 0.25D; var28 <= 1.0D; var28 += 0.25D) {
					double motionX = (var19 + (var24 - var19) * var28) * par1;
					double motionY = (var21 + (var26 - var21) * var28) * par1;
					double motionZ = motionX * Math.sin(angle);
					motionX *= Math.cos(angle);

					this.makeSpark(this.posX, this.posY, this.posZ, motionX,
							motionY, motionZ, color, trail, flicker);

				}

				var19 = var24;
				var21 = var26;
			}
		}
	}

	public int getFXLayer() {
		return 0;
	}
}
