package absorr.morered.base;

import net.minecraft.creativetab.CreativeTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabMoreRed extends CreativeTabs {
	public TabMoreRed(String par2Str)
	{
		super(par2Str);
	}
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return MoreRed.rsIngot.itemID;
	}

	public String getTranslatedTabLabel()
	{
		return "MoreRed";
	}
}