package absorr.morered.materials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import absorr.morered.base.CommonProxy;

public class MoreItems extends Item
{
	public MoreItems(int i, int stack, CreativeTabs tab)
    {
         super(i);
         maxStackSize = stack;
         this.setCreativeTab(CreativeTabs.tabMisc);
    }

	@Override
	public String getTextureFile() 
	{
		return CommonProxy.itemPic;
	}
}