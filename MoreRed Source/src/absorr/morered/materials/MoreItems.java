package absorr.morered.materials;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import absorr.morered.base.CommonProxy;

public class MoreItems extends Item
{
private static String name;
	
	public MoreItems(int i, int stack, String codeName)
    {
         super(i);
         maxStackSize = stack;
         this.name = codeName;
    }
	
	@Override
	public void func_94581_a(IconRegister iconRegister)
	{
	         iconIndex = iconRegister.func_94245_a(name);
	}
}