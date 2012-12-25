package absorr.morered.materials;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import absorr.morecrafts.base.MoreCrafts;
import absorr.morered.base.CommonProxy;
import absorr.morered.base.MoreRed;


public class BlockToolCharger extends Block
{
	public BlockToolCharger(int i, int j) 
    { 
        super(i, j, Material.iron);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        setLightOpacity(255);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    } 
	@Override
	public String getTextureFile() 
	{
		return CommonProxy.blockPic;
	}
    public int idDropped(int i, Random random) 
    { 
       return MoreRed.recharger.blockID; 
    } 
    public int quantityDropped(Random random) 
    { 
            return 1; 
    } 
    public boolean isOpaqueCube()
    {
            return false;
    }
    public boolean renderAsNormalBlock()
    {
            return false;
    }
    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
            if (j == 1)
            {
            	if (i == 0)
                {
                        return 2;
                }
                if (i == 1)
                {
                        return 16;
                }
                else
                {
                        return 1;
                }
            }
            if (j == 2)
            {
            	if (i == 0)
                {
                        return 2;
                }
                if (i == 1)
                {
                        return 32;
                }
                else
                {
                        return 1;
                }
            }
            if (j == 3)
            {
            	if (i == 0)
                {
                        return 2;
                }
                if (i == 1)
                {
                        return 48;
                }
                else
                {
                        return 1;
                }
            }
            if (j == 4)
            {
            	if (i == 0)
                {
                        return 2;
                }
                if (i == 1)
                {
                        return 64;
                }
                else
                {
                        return 1;
                }
            }
            if (j == 5)
            {
            	if (i == 0)
                {
                        return 2;
                }
                if (i == 1)
                {
                        return 80;
                }
                else
                {
                        return 1;
                }
            }
            if (j == 6)
            {
            	if (i == 0)
                {
                        return 2;
                }
                if (i == 1)
                {
                        return 96;
                }
                else
                {
                        return 1;
                }
            }
            else return getBlockTextureFromSide(i);
    }

    public int getBlockTextureFromSide(int i)
    {
            if (i == 0)
            {
                    return 2;
            }
            if (i == 1)
            {
                    return 0;
            }
            else
            {
                    return 1;
            }
    }
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
            if (l == 1)
            {
                    return true;
            }
            if (!super.shouldSideBeRendered(iblockaccess, i, j, k, l))
            {
                    return false;
            }
            if (l == 0)
            {
                    return true;
            }
            else
            {
                    return iblockaccess.getBlockId(i, j, k) != blockID;
            }
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	ItemStack heldItem = player.inventory.getCurrentItem();//This is to get the item that the player is currently holding. (if any)
    	int meta = world.getBlockMetadata(x, y, z);
    	if (heldItem == null)
    	{
    		player.performHurtAnimation();
    		return false;
    	}
    	if (heldItem.getItem().equals(MoreRed.rsPick)) //|| World.getBlockMetadata(x, y, z).equals(1)) If the held item is a book then we execute this piece of code
    	{
    		if (meta == 1)
    		{
    			//world.setBlockWithNotify(x, y, z, Block.sand.blockID);//This places a block at the location of the block you rightclicked on.
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsPick));//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.rsSpade)) //If the held item is a book then we execute this piece of code
    	{
    		if (meta == 2)
    		{
    			//world.setBlockWithNotify(x, y, z, Block.sand.blockID);//This places a block at the location of the block you rightclicked on.
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsSpade));//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.rsHoe)) //If the held item is a book then we execute this piece of code
    	{
    		if (meta == 3)
    		{
    			//world.setBlockWithNotify(x, y, z, Block.sand.blockID);//This places a block at the location of the block you rightclicked on.
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsHoe));//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.rsAxe)) //If the held item is a book then we execute this piece of code
    	{
    		if (meta == 4)
    		{
    			//world.setBlockWithNotify(x, y, z, Block.sand.blockID);//This places a block at the location of the block you rightclicked on.
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsAxe));//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.rsSword)) //If the held item is a book then we execute this piece of code
    	{
    		if (meta == 5)
    		{
    			//world.setBlockWithNotify(x, y, z, Block.sand.blockID);//This places a block at the location of the block you rightclicked on.
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsSword));//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.rsMulti)) //If the held item is a book then we execute this piece of code
    	{
    		if (meta == 6)
    		{
    			//world.setBlockWithNotify(x, y, z, Block.sand.blockID);//This places a block at the location of the block you rightclicked on.
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsMulti));//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(Item.pickaxeStone)) // If the held item is a book then we execute this piece of code
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 1);//This places a block at the location of the block you rightclicked on.
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Item.stick));//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(Item.shovelStone)) // If the held item is a book then we execute this piece of code
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 2);//This places a block at the location of the block you rightclicked on.
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Item.stick));//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(Item.hoeStone))
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 3);
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Item.stick));
    			return true;
    		}
    		
    	}
    	if (heldItem.getItem().equals(Item.axeStone))
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 4);
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Item.stick));
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(Item.swordStone))
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 5);
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Item.stick));
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreCrafts.stoneMulti))
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 6);
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Item.stick));
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.crowbar))
    	{
    		world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 0);
    		if (meta == 1)
    		{
    			player.inventory.addItemStackToInventory(new ItemStack(Item.pickaxeStone, 1));
    		}
    		if (meta == 2)
    		{
    			player.inventory.addItemStackToInventory(new ItemStack(Item.shovelStone, 1));
    		}
    		if (meta == 3)
    		{
    			player.inventory.addItemStackToInventory(new ItemStack(Item.hoeStone, 1));
    		}
    		if (meta == 4)
    		{
    			player.inventory.addItemStackToInventory(new ItemStack(Item.axeStone, 1));
    		}
    		if (meta == 5)
    		{
    			player.inventory.addItemStackToInventory(new ItemStack(Item.swordStone, 1));
    		}
    		if (meta == 6)
    		{
    			player.inventory.addItemStackToInventory(new ItemStack(MoreCrafts.stoneMulti, 1));
    		}
    		return true;
    	}else
    		return false;
    }
    public void addCreativeItems(ArrayList itemList)
    {
            itemList.add(new ItemStack(this));
    }
}