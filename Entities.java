package morefireworks;

import net.minecraft.client.renderer.entity.RenderSnowball;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Entities {

	public static void init() {
		EntityRegistry.registerModEntity(EntityCustomFireworkRocket.class, "FireworksRocketEntity", Config.entityCustomFireworkRocketId, MoreFireworks.instance, 64, 2, true);
		EntityRegistry.registerGlobalEntityID(EntityCustomFireworkRocket.class, "FireworksRocketEntity", Config.entityCustomFireworkRocketId);
	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustomFireworkRocket.class, new RenderCustomFirework());
	}
}
