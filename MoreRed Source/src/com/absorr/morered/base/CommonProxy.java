package com.absorr.morered.base;

import com.absorr.morered.base.MoreRed;

import net.minecraft.item.*;
import net.minecraft.src.*;
import net.minecraft.village.*;

public class CommonProxy 
{
	
	/*
	 * Creates more villager trading recipes
	 */
	public static void addMerchantRecipies()
	{
		MerchantRecipeList npcTrade = new MerchantRecipeList();
		npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (MoreRed.rsMulti, 1)));
		npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (MoreRed.rsIngot, 3)));
		npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (MoreRed.rsIngot, 3), new ItemStack (Item.emerald, 1)));
	}
	
	/*
	 * Only used client side
	 */
	public void registerRenderers() {}
}
