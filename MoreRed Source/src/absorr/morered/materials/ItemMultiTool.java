package absorr.morered.materials;

import java.util.*;

import absorr.morered.base.CommonProxy;
import net.minecraft.src.*;

public class ItemMultiTool extends ItemTool
{
	public static final Block[] blocksEffectiveAgainst = combineBlockArray(ItemPickaxe.blocksEffectiveAgainst, combineBlockArray(ItemPickaxe.blocksEffectiveAgainst, ItemSpade.blocksEffectiveAgainst));
	
	public ItemMultiTool(int par1, int par2, EnumToolMaterial par3EnumToolMaterial) 
	{
		super(par1, par2, par3EnumToolMaterial, blocksEffectiveAgainst);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
	public String getTextureFile() 
	{
		return CommonProxy.itemPic;
	}
	
	/**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block par1Block)
    {
        return true;
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
    	ItemPickaxe pick = new ItemPickaxe(this.damageVsEntity, this.toolMaterial);
    	float var1 = pick.getStrVsBlock(par1ItemStack, par2Block);
    	ItemAxe axe = new ItemAxe(this.damageVsEntity, this.toolMaterial);
    	float var2 = axe.getStrVsBlock(par1ItemStack, par2Block);
    	ItemSpade shovel = new ItemSpade(this.damageVsEntity, this.toolMaterial);
    	float var3 = shovel.getStrVsBlock(par1ItemStack, par2Block);
    	if (var1 > var3 && var1 > var2)
    		return var1;
    	if (var2 > var1 && var2 > var3)
    		return var2;
    	if (var3 > var2 && var3 > var1)
    		return var3;
    	else
    		return var1;
    }
    
    static Block[] combineBlockArray(Block[] first, Block[] second) {
        List<Block> both = new ArrayList<Block>(first.length + second.length);
        Collections.addAll(both, first);
        Collections.addAll(both, second);
        return both.toArray(new Block[both.size()]);
    }
}
