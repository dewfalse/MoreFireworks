package morefireworks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFireworkStarterFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class EntityCustomFireworkRocket extends EntityFireworkRocket
{
    public EntityCustomFireworkRocket(World par1World)
    {
        super(par1World);
        this.setSize(0.25F, 0.25F);
    }

    public EntityCustomFireworkRocket(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack)
    {
        super(par1World, par2, par4, par6, par8ItemStack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void handleHealthUpdate(byte par1)
    {
        if (par1 == 17 && this.worldObj.isRemote)
        {
            ItemStack var2 = this.dataWatcher.getWatchableObjectItemStack(8);
            NBTTagCompound var3 = null;

            if (var2 != null && var2.hasTagCompound())
            {
                var3 = var2.getTagCompound().getCompoundTag("Fireworks");
            }

			Minecraft mc = ModLoader.getMinecraftInstance();
            mc.effectRenderer.addEffect(new EntityCustomFireworkStarterFX(mc.theWorld, posX, posY, posZ, motionX, motionY, motionZ, mc.effectRenderer, var3));
        }

    }

}
