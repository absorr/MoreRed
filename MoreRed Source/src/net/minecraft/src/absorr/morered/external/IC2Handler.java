package net.minecraft.src.absorr.morered.external;

import net.minecraft.src.*;
import ic2.api.*;

public class IC2Handler
{
	public static void loadRecipies()
	{
		//Redstone Ingot
    	Ic2Recipes.addCompressorRecipe(new ItemStack (Item.redstone, 1), new ItemStack (mod_MoreRed.rsIngot, 1));
	}
}