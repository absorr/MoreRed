package absorr.morered.materials;

import absorr.morered.base.CommonProxy;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.*;

public class ItemRsHoe extends ItemHoe
{

	public ItemRsHoe(int par1, EnumToolMaterial par2EnumToolMaterial) 
	{
		super(par1, par2EnumToolMaterial);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getTextureFile() 
	{
		return CommonProxy.itemPic;
	}
}
