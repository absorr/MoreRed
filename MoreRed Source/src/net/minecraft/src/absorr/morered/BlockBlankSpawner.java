package net.minecraft.src.absorr.morered;
import net.minecraft.src.*;

import java.util.ArrayList;
import java.util.Random;

import net.minecraftforge.*;

public class BlockBlankSpawner extends Block
{
	public BlockBlankSpawner(int i, int j) 
    { 
        super(i, j, Material.iron);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
	public boolean isOpaqueCube()
    {
        return false;
    }
    public boolean blockActivated(World world, int x, int y, int z,EntityPlayer player)
    {
    	int metad = world.getBlockMetadata(x, y, z);
    	if (metad == 0)
    	{
    		ItemStack heldItem = player.inventory.getCurrentItem();
        	if (heldItem.getItem().equals(Item.monsterPlacer))
        	{
        		int meta = heldItem.getItemDamage();
        		String mob = EntityList.getStringFromID(meta);
        		world.setBlockAndMetadataWithNotify(x, y, z, Block.mobSpawner.blockID, meta);
        		((TileEntityMobSpawner)world.getBlockTileEntity(x, y, z)).setMobID(mob);
        		player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(mod_moreCrafts.blankEgg));
        		return true;
        	}else
        		return false;
    	}else
    		return false;
    }
    public String getItemNameIS(ItemStack itemstack) {
        String name = "";
        switch(itemstack.getItemDamage()) {
        case 0: {
                name = "blankSpawn";
                break;
        }
        case 1: {
                name = "rsBlock"; 
                break;
        }
        case 2: {
            name = "rsBlockActive"; 
            break;
        }
        default: name = "blankSpawn";
        }
        return name;
    }
    public boolean canConnectRedstone(IBlockAccess iba, int i, int j, int k, int dir)
    {
    	int meta = iba.getBlockMetadata(i, j, k);
    	if (meta == 1) return true;
    	else
    	{
    		if (meta == 2) return true;
    		else return false;
    	}
    }
    public boolean canProvidePower()
    {
        return true;
    }
    public int getBlockTextureFromSideAndMetadata(int i, int j) {
        switch (j) {
        case 0:
                return 65;
        case 1:
                return 22;
        case 2:
            return 22;
        default:
                return 65;
        }
    }
    public int getRenderColor(int par1)
    {
    	if (par1 == 1) return 8388608;
    	else return 16777215;
    }
    public void onBlockPlaced(World par1World, int par2, int par3, int par4, int par5) 
    {
    	ItemStack heldItem = ModLoader.getMinecraftInstance().thePlayer.inventory.getCurrentItem();
    	int meta = heldItem.getItemDamage();
    	par1World.setBlockAndMetadataWithNotify(par2, par3, par4, mod_moreCrafts.blankSpawner.blockID, meta);
    	if (meta == 1)
    	{
    		par1World.markBlocksDirty(par2, par3, par4, par2, par3, par4);
    		par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
    	}
    }
    public int colorMultiplier(IBlockAccess iba, int i, int j, int k)
    {
    	int meta = iba.getBlockMetadata(i, j, k);
    	if (meta == 1) return 8388608;
    	else return 16777215;
    }
    public int tickRate()
    {
        return 20;
    }
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
        	if (par1World.isBlockGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        	{
        		par1World.setBlockMetadataWithNotify(par2, par3, par4, 2);
        	}
        	else par1World.setBlockMetadataWithNotify(par2, par3, par4, 1);
        	par1World.markBlocksDirty(par2, par3, par4, par2, par3, par4);
        	par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
        }
    }
    /**
     * Is this block powering the block on the specified side
     */
    public boolean isPoweringTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par1IBlockAccess.getBlockMetadata(par2, par3, par4) > 1;
    }

    /**
     * Is this block indirectly powering the block on the specified side
     */
    public boolean isIndirectlyPoweringTo(World par1World, int par2, int par3, int par4, int par5)
    {
        return par1World.getBlockMetadata(par2, par3, par4) > 1;
    }
    public void addCreativeItems(ArrayList itemList)
    {
            itemList.add(new ItemStack(this));
            itemList.add(new ItemStack(mod_moreCrafts.blankSpawner, 1, 1));
    }
}