package morefireworks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

	public static void init() {
		// chalk recipe
		for(int i = 0; i < ItemDye.dyeColorNames.length; ++i) {
			GameRegistry.addRecipe(new ItemStack(Config.itemFireworkChalkId, 16, i), new Object[] {
				"  S",
				" S ",
				"D  ",
				'S', Item.stick,
				'D', new ItemStack(Item.dyePowder, 1, i)
			});
		}

		// paper recipe
		GameRegistry.addRecipe(new ItemStack(Config.itemFireworkPaperId, 3, 0), new Object[] {
			"P",
			"P",
			"P",
			'P', Item.paper
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Config.itemFireworkPaperId, 1, 0), new Object[] {
			Items.recipePaper
		});

		// custom firework recipe
		GameRegistry.addRecipe(new RecipesCustomFirework());
		GameRegistry.addRecipe(new RecipesCustomFireworkStar());
		GameRegistry.addRecipe(new RecipesPaperCloning());
	}
}
