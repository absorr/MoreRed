package net.minecraft.src.absorr.morered;
import net.minecraft.src.*;

public class MoreItems extends Item
{
	public MoreItems(int i, int stack, CreativeTabs tab)
    {
         super(i);
         maxStackSize = stack;
         this.setTabToDisplayOn(tab);
    }

	@Override
	public String getTextureFile() 
	{
		return "/morecrafts/items.png";
	}
}