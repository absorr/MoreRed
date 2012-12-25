package absorr.morered.materials;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;
import absorr.morered.base.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRsHoe extends ItemHoe
{

	public ItemRsHoe(int par1, EnumToolMaterial par2EnumToolMaterial) 
	{
		super(par1, par2EnumToolMaterial);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
	public String getTextureFile() 
	{
		return CommonProxy.itemPic;
	}
}
