package com.absorr.morered.materials;
import java.util.ArrayList;
import java.util.Random;


import com.absorr.morecrafts.base.MoreCrafts;
import com.absorr.morered.base.CommonProxy;
import com.absorr.morered.base.MoreRed;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockToolCharger extends Block
{
	private Icon field_94459_cP;
	private Icon field_94458_cO;
	public BlockToolCharger(int i) 
    { 
        super(i, Material.iron);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        setLightOpacity(255);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
	
	@SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return par1 == 1 ? this.field_94458_cO : (par1 == 0 ? this.field_94459_cP : (par1 != par2 ? this.field_94336_cN : this.field_94458_cO));
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94336_cN = par1IconRegister.func_94245_a("morered:chargeside");
        this.field_94459_cP = par1IconRegister.func_94245_a("morered:chargebottom");
        this.field_94458_cO = par1IconRegister.func_94245_a("morered:chargetop");
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
    	int charge = 1;
    	ItemStack heldItem = player.inventory.getCurrentItem();//This is to get the item that the player is currently holding. (if any)
    	int meta = world.getBlockMetadata(x, y, z);
    	if (heldItem == null)
    	{
    		player.performHurtAnimation();
    		return false;
    	}
    	else
    	{
    		if (world.getBlockId(x, y - 1, z) == Block.field_94341_cq.blockID) charge += 5;
    	}
    	
    	if (heldItem.getItem().equals(MoreRed.rsPick)) //|| World.getBlockMetadata(x, y, z).equals(1)) If the held item is a book then we execute this piece of code
    	{
    		if (meta == 1)
    		{
    			if (player.inventory.getCurrentItem().getItemDamage() < charge)
    				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsPick));
    			else
    				player.inventory.getCurrentItem().setItemDamage(player.inventory.getCurrentItem().getItemDamage() - charge);
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.rsSpade)) //If the held item is a book then we execute this piece of code
    	{
    		if (meta == 2)
    		{
    			if (player.inventory.getCurrentItem().getItemDamage() < charge)
    				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsSpade));
    			else
    				player.inventory.getCurrentItem().setItemDamage(player.inventory.getCurrentItem().getItemDamage() - charge);//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.rsHoe)) //If the held item is a book then we execute this piece of code
    	{
    		if (meta == 3)
    		{
    			if (player.inventory.getCurrentItem().getItemDamage() < charge)
    				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsHoe));
    			else
    				player.inventory.getCurrentItem().setItemDamage(player.inventory.getCurrentItem().getItemDamage() - charge);//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.rsAxe)) //If the held item is a book then we execute this piece of code
    	{
    		if (meta == 4)
    		{
    			if (player.inventory.getCurrentItem().getItemDamage() < charge)
    				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsAxe));
    			else
    				player.inventory.getCurrentItem().setItemDamage(player.inventory.getCurrentItem().getItemDamage() - charge);//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.rsSword)) //If the held item is a book then we execute this piece of code
    	{
    		if (meta == 5)
    		{
    			if (player.inventory.getCurrentItem().getItemDamage() < charge)
    				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsSword));
    			else
    				player.inventory.getCurrentItem().setItemDamage(player.inventory.getCurrentItem().getItemDamage() - charge);//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.rsMulti)) //If the held item is a book then we execute this piece of code
    	{
    		if (meta == 6)
    		{
    			if (player.inventory.getCurrentItem().getItemDamage() < charge)
    				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreRed.rsMulti));
    			else
    				player.inventory.getCurrentItem().setItemDamage(player.inventory.getCurrentItem().getItemDamage() - charge);//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(Item.pickaxeStone)) // If the held item is a book then we execute this piece of code
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 1, 1);//This places a block at the location of the block you rightclicked on.
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(Item.shovelStone)) // If the held item is a book then we execute this piece of code
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 2, 1);//This places a block at the location of the block you rightclicked on.
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);//replace the currentheld item with a piece of raw pork.
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(Item.hoeStone))
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 3, 1);
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
    			return true;
    		}
    		
    	}
    	if (heldItem.getItem().equals(Item.axeStone))
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 4, 1);
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(Item.swordStone))
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 5, 1);
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreCrafts.stoneMulti))
    	{
    		if (meta == 0)
    		{
    			world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 6, 1);
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
    			return true;
    		}
    	}
    	if (heldItem.getItem().equals(MoreRed.crowbar))
    	{
    		world.setBlockAndMetadataWithNotify(x, y, z, MoreRed.recharger.blockID, 0, 1);
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