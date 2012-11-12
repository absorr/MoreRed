package absorr.morered.materials;
import absorr.morered.base.CommonProxy;
import net.minecraft.src.*;

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