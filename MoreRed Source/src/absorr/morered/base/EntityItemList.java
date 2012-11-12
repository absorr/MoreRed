package absorr.morered.base;

import net.minecraft.src.*;

import java.util.*;
import java.util.Map.Entry;

public class EntityItemList
{
	public static Map itemMap = new HashMap();
	
	/**
	 * Adds an item association with an entity to the list
	 * @param par1
	 * @param par2
	 */
	public static void addEntityItem(Entity par1, ItemStack par2)
	{
		itemMap.put(par1, par2);
	}
	
	/**
	 * Get the associated item to the specified entity
	 * @param par1
	 * @return {@link ItemStack}
	 */
	public static ItemStack getEntityItem(Entity par1)
	{
		return (ItemStack) itemMap.get(par1);
	}
}