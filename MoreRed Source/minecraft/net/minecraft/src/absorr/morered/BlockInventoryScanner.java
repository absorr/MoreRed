package net.minecraft.src.absorr.morered;
import net.minecraft.src.*;
import net.minecraft.src.absorr.morecrafts.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockInventoryScanner extends BlockContainer
{
	private EnumMobType triggerMobType;
	private boolean isActive = false;
	public BlockInventoryScanner(int i, int j) 
    { 
        super(i, j, Material.iron);   
        this.triggerMobType = EnumMobType.players;
        float var6 = 0.0625F;
        this.setBlockBounds(0F, 0F, 0.5F, 1F, 2F, 0.1875F);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    } 
	public int getRenderBlockPass()
    {
        return 1;
    }
	@Override
	public String getTextureFile() 
	{
		return "/morered/blocks.png";
	}
	public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
		if (!isActive)
        {
                return 6;
        }
        else
        {
                return 7;
        }
    }

    public int getBlockTextureFromSide(int i)
    {
            if (!isActive)
            {
                    return 6;
            }
            else
            {
                    return 7;
            }
    }
    public int idDropped(int i, Random random) 
    { 
       return mod_MoreRed.invScanner.blockID; 
    } 
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    public boolean isOpaqueCube()
    {
        return false;
    }
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    public int quantityDropped(Random random) 
    { 
            return 1; 
    }
    public void setBlockBoundsForItemRender()
    {
        float var1 = 0.5F;
        float var2 = 0.125F;
        float var3 = 0.5F;
        this.setBlockBounds(0F, 0F, 0.5F, 1F, 2F, 0.5F);
    }
    public int tickRate()
    {
        return 20;
    }
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
    	float var7 = 0.125F;
    	List var8 = null;
    	List var9 = null;
        if (!par1World.isRemote)
        {
        	var8 = par1World.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)((float)par2 + var7), (double)par3, (double)((float)par4 + var7), (double)((float)(par2 + 1) - var7), (double)par3 + 0.25D, (double)((float)(par4 + 1) - var7)));
        	var9 = par1World.getEntitiesWithinAABBExcludingEntity(par5Entity, AxisAlignedBB.getBoundingBox((double)((float)par2 + var7), (double)par3, (double)((float)par4 + var7), (double)((float)(par2 + 1) - var7), (double)par3 + 0.25D, (double)((float)(par4 + 1) - var7)));
            if (par5Entity instanceof EntityItem)
            {
            	EntityItem entity = (EntityItem) par5Entity;
            	this.scanForItem(par1World, par2, par3, par4, entity.item);
            }
            if (par5Entity instanceof EntityLiving)
            {
            	if (par5Entity instanceof EntityPlayer)
            	{
            		this.scanInventory(par1World, par2, par3, par4, (EntityPlayer) par5Entity);
            	}
            	else
            	{
            		EntityLiving entity = (EntityLiving) par5Entity;
            		if (entity.getHeldItem() != null)
            			this.scanForItem(par1World, par2, par3, par4, entity.getHeldItem());
            	}
            }
        }
    }
    private void scanInventory(World par1World, int par2, int par3, int par4, EntityPlayer player)
    {
        boolean var5 = isActive;
        boolean var6 = false;
        float var7 = 0.125F;
        List var8 = null;
        TileEntityScanner var9 = (TileEntityScanner)par1World.getBlockTileEntity(par2, par3, par4);
        ItemStack var10s1 = var9.getStackInSlot(0);
        ItemStack var10s2 = var9.getStackInSlot(1);
        ItemStack var10s3 = var9.getStackInSlot(2);
        ItemStack var10s4 = var9.getStackInSlot(3);
        ItemStack var10s5 = var9.getStackInSlot(4);
        ItemStack var10s6 = var9.getStackInSlot(5);
        ItemStack var10s7 = var9.getStackInSlot(6);
        ItemStack var10s8 = var9.getStackInSlot(7);
        ItemStack var10s9 = var9.getStackInSlot(8);
        boolean var11s1;
        boolean var11s2;
        boolean var11s3;
        boolean var11s4;
        boolean var11s5;
        boolean var11s6;
        boolean var11s7;
        boolean var11s8;
        boolean var11s9;
        boolean var12 = false;
        String mode = GuiScanner.getMode(par2, par3, par4);
        
        if (mode=="OR")
        {
        	if (var10s1 != null)
            {
            	int content1 = var10s1.itemID;
                if (player.inventory.hasItem(content1))
                {
                	var11s1 = true;
                	//System.out.println("1: Player has " + content1);
                }
                else{var11s1 = false;}
            }
            else{var11s1 = true;}
            if (var10s2 != null)
            {
            	int content2 = var10s2.itemID;
                if (player.inventory.hasItem(content2))
                {
                	var11s2 = true;
                	//System.out.println("2: Player has " + content2);
                }
                else{var11s2 = false;}
            }
            else{var11s2 = true;}
            if (var10s3 != null)
            {
            	int content3 = var10s3.itemID;
                if (player.inventory.hasItem(content3))
                {
                	var11s3 = true;
                	//System.out.println("3: Player has " + content3);
                }
                else{var11s3 = false;}
            }
            else{var11s3 = true;}
            if (var10s4 != null)
            {
            	int content4 = var10s4.itemID;
                if (player.inventory.hasItem(content4))
                {
                	var11s4 = true;
                	//System.out.println("4: Player has " + content4);
                }
                else{var11s4 = false;}
            }
            else{var11s4 = true;}
            if (var10s5 != null)
            {
            	int content5 = var10s5.itemID;
                if (player.inventory.hasItem(content5))
                {
                	var11s5 = true;
                	//System.out.println("5: Player has " + content5);
                }
                else{var11s5 = false;}
            }
            else{var11s5 = true;}
            if (var10s6 != null)
            {
            	int content6 = var10s6.itemID;
                if (player.inventory.hasItem(content6))
                {
                	var11s6 = true;
                	//System.out.println("6: Player has " + content6);
                }
                else{var11s6 = false;}
            }
            else{var11s6 = true;}
            if (var10s7 != null)
            {
            	int content7 = var10s7.itemID;
                if (player.inventory.hasItem(content7))
                {
                	var11s7 = true;
                	//System.out.println("7: Player has " + content7);
                }
                else{var11s7 = false;}
            }
            else{var11s7 = true;}
            if (var10s8 != null)
            {
            	int content8 = var10s8.itemID;
                if (player.inventory.hasItem(content8))
                {
                	var11s8 = true;
                	//System.out.println("8: Player has " + content8);
                }
                else{var11s8 = false;}
            }
            else{var11s8 = true;}
            if (var10s9 != null)
            {
            	int content9 = var10s9.itemID;
                if (player.inventory.hasItem(content9))
                {
                	var11s9 = true;
                	//System.out.println("9: Player has " + content9);
                }
                else{var11s9 = false;}
            }
            else{var11s9 = true;}
            
            if (var11s1)
            {
            	var12 = true;
            }
            else
            {
            	if (var11s2)
                {
                	var12 = true;
                }
                else
                {
                	if (var11s3)
                    {
                    	var12 = true;
                    }
                    else
                    {
                    	if (var11s4)
                        {
                        	var12 = true;
                        }
                        else
                        {
                        	if (var11s5)
                            {
                            	var12 = true;
                            }
                            else
                            {
                            	if (var11s6)
                                {
                                	var12 = true;
                                }
                                else
                                {
                                	if (var11s7)
                                    {
                                    	var12 = true;
                                    }
                                    else
                                    {
                                    	if (var11s8)
                                        {
                                        	var12 = true;
                                        }
                                        else
                                        {
                                        	if (var11s9)
                                            {
                                            	var12 = true;
                                            }
                                            else
                                            {
                                            	var12 = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else
        {
        	if (mode=="AND")
        	{
        		if (var10s1 != null)
                {
                	int content1 = var10s1.itemID;
                    if (player.inventory.hasItem(content1))
                    {
                    	var11s1 = true;
                    	//System.out.println("1: Player has " + content1);
                    }
                    else{var11s1 = false;}
                }
                else{var11s1 = true;}
                if (var10s2 != null)
                {
                	int content2 = var10s2.itemID;
                    if (player.inventory.hasItem(content2))
                    {
                    	var11s2 = true;
                    	//System.out.println("2: Player has " + content2);
                    }
                    else{var11s2 = false;}
                }
                else{var11s2 = true;}
                if (var10s3 != null)
                {
                	int content3 = var10s3.itemID;
                    if (player.inventory.hasItem(content3))
                    {
                    	var11s3 = true;
                    	//System.out.println("3: Player has " + content3);
                    }
                    else{var11s3 = false;}
                }
                else{var11s3 = true;}
                if (var10s4 != null)
                {
                	int content4 = var10s4.itemID;
                    if (player.inventory.hasItem(content4))
                    {
                    	var11s4 = true;
                    	//System.out.println("4: Player has " + content4);
                    }
                    else{var11s4 = false;}
                }
                else{var11s4 = true;}
                if (var10s5 != null)
                {
                	int content5 = var10s5.itemID;
                    if (player.inventory.hasItem(content5))
                    {
                    	var11s5 = true;
                    	//System.out.println("5: Player has " + content5);
                    }
                    else{var11s5 = false;}
                }
                else{var11s5 = true;}
                if (var10s6 != null)
                {
                	int content6 = var10s6.itemID;
                    if (player.inventory.hasItem(content6))
                    {
                    	var11s6 = true;
                    	//System.out.println("6: Player has " + content6);
                    }
                    else{var11s6 = false;}
                }
                else{var11s6 = true;}
                if (var10s7 != null)
                {
                	int content7 = var10s7.itemID;
                    if (player.inventory.hasItem(content7))
                    {
                    	var11s7 = true;
                    	//System.out.println("7: Player has " + content7);
                    }
                    else{var11s7 = false;}
                }
                else{var11s7 = true;}
                if (var10s8 != null)
                {
                	int content8 = var10s8.itemID;
                    if (player.inventory.hasItem(content8))
                    {
                    	var11s8 = true;
                    	//System.out.println("8: Player has " + content8);
                    }
                    else{var11s8 = false;}
                }
                else{var11s8 = true;}
                if (var10s9 != null)
                {
                	int content9 = var10s9.itemID;
                    if (player.inventory.hasItem(content9))
                    {
                    	var11s9 = true;
                    	//System.out.println("9: Player has " + content9);
                    }
                    else{var11s9 = false;}
                }
                else{var11s9 = true;}
                
                //Checks that all scanning results are true
                if (var11s1 && var11s2 && var11s3 && var11s4 && var11s5 && var11s6 && var11s7 && var11s8 && var11s9)
                {
                	var12 = true;
                	//System.out.println("Scanner will activate");
                }
        	}
        }
        
        //Scans inventory for each item in scanner
        
        
        if (var12)
        {
        	this.isActive = true;
        	par1World.markBlocksDirty(par2, par3, par4, par2, par3, par4);
        	par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
        }
        else {this.isActive = false;}
    }
    public void scanForItem (World par1World, int par2, int par3, int par4, ItemStack par5)
    {
    	boolean var5 = par1World.getBlockMetadata(par2, par3, par4) == 1;
        boolean var6 = false;
        float var7 = 0.125F;
        List var8 = null;
        TileEntityScanner var9 = (TileEntityScanner)par1World.getBlockTileEntity(par2, par3, par4);
        ItemStack var10s1 = var9.getStackInSlot(0);
        ItemStack var10s2 = var9.getStackInSlot(1);
        ItemStack var10s3 = var9.getStackInSlot(2);
        ItemStack var10s4 = var9.getStackInSlot(3);
        ItemStack var10s5 = var9.getStackInSlot(4);
        ItemStack var10s6 = var9.getStackInSlot(5);
        ItemStack var10s7 = var9.getStackInSlot(6);
        ItemStack var10s8 = var9.getStackInSlot(7);
        ItemStack var10s9 = var9.getStackInSlot(8);
        boolean var11s1;
        boolean var11s2;
        boolean var11s3;
        boolean var11s4;
        boolean var11s5;
        boolean var11s6;
        boolean var11s7;
        boolean var11s8;
        boolean var11s9;
        boolean var12 = false;
        String mode = GuiScanner.getMode(par2, par3, par4);
        int inputID = par5.itemID;

        if (mode=="OR")
        {
        	if (var10s1 != null)
            {
            	int content1 = var10s1.itemID;
                if (inputID == content1)
                {
                	var11s1 = true;
                	//System.out.println("1: Player has " + content1);
                }
                else{var11s1 = false;}
            }
            else{var11s1 = true;}
            if (var10s2 != null)
            {
            	int content2 = var10s2.itemID;
                if (inputID == content2)
                {
                	var11s2 = true;
                	//System.out.println("2: Player has " + content2);
                }
                else{var11s2 = false;}
            }
            else{var11s2 = true;}
            if (var10s3 != null)
            {
            	int content3 = var10s3.itemID;
                if (inputID == content3)
                {
                	var11s3 = true;
                	//System.out.println("3: Player has " + content3);
                }
                else{var11s3 = false;}
            }
            else{var11s3 = true;}
            if (var10s4 != null)
            {
            	int content4 = var10s4.itemID;
                if (inputID == content4)
                {
                	var11s4 = true;
                	//System.out.println("4: Player has " + content4);
                }
                else{var11s4 = false;}
            }
            else{var11s4 = true;}
            if (var10s5 != null)
            {
            	int content5 = var10s5.itemID;
                if (inputID == content5)
                {
                	var11s5 = true;
                	//System.out.println("5: Player has " + content5);
                }
                else{var11s5 = false;}
            }
            else{var11s5 = true;}
            if (var10s6 != null)
            {
            	int content6 = var10s6.itemID;
                if (inputID == content6)
                {
                	var11s6 = true;
                	//System.out.println("6: Player has " + content6);
                }
                else{var11s6 = false;}
            }
            else{var11s6 = true;}
            if (var10s7 != null)
            {
            	int content7 = var10s7.itemID;
                if (inputID == content7)
                {
                	var11s7 = true;
                	//System.out.println("7: Player has " + content7);
                }
                else{var11s7 = false;}
            }
            else{var11s7 = true;}
            if (var10s8 != null)
            {
            	int content8 = var10s8.itemID;
                if (inputID == content8)
                {
                	var11s8 = true;
                	//System.out.println("8: Player has " + content8);
                }
                else{var11s8 = false;}
            }
            else{var11s8 = true;}
            if (var10s9 != null)
            {
            	int content9 = var10s9.itemID;
                if (inputID == content9)
                {
                	var11s9 = true;
                	//System.out.println("9: Player has " + content9);
                }
                else{var11s9 = false;}
            }
            else{var11s9 = true;}
            
            if (var11s1)
            {
            	var12 = true;
            }
            else
            {
            	if (var11s2)
                {
                	var12 = true;
                }
                else
                {
                	if (var11s3)
                    {
                    	var12 = true;
                    }
                    else
                    {
                    	if (var11s4)
                        {
                        	var12 = true;
                        }
                        else
                        {
                        	if (var11s5)
                            {
                            	var12 = true;
                            }
                            else
                            {
                            	if (var11s6)
                                {
                                	var12 = true;
                                }
                                else
                                {
                                	if (var11s7)
                                    {
                                    	var12 = true;
                                    }
                                    else
                                    {
                                    	if (var11s8)
                                        {
                                        	var12 = true;
                                        }
                                        else
                                        {
                                        	if (var11s9)
                                            {
                                            	var12 = true;
                                            }
                                            else
                                            {
                                            	var12 = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else
        {
        	if (mode=="AND")
        	{
        		if (var10s1 != null)
                {
                	int content1 = var10s1.itemID;
                    if (inputID == content1)
                    {
                    	var11s1 = true;
                    	//System.out.println("1: Player has " + content1);
                    }
                    else{var11s1 = false;}
                }
                else{var11s1 = true;}
                if (var10s2 != null)
                {
                	int content2 = var10s2.itemID;
                    if (inputID == content2)
                    {
                    	var11s2 = true;
                    	//System.out.println("2: Player has " + content2);
                    }
                    else{var11s2 = false;}
                }
                else{var11s2 = true;}
                if (var10s3 != null)
                {
                	int content3 = var10s3.itemID;
                    if (inputID == content3)
                    {
                    	var11s3 = true;
                    	//System.out.println("3: Player has " + content3);
                    }
                    else{var11s3 = false;}
                }
                else{var11s3 = true;}
                if (var10s4 != null)
                {
                	int content4 = var10s4.itemID;
                    if (inputID == content4)
                    {
                    	var11s4 = true;
                    	//System.out.println("4: Player has " + content4);
                    }
                    else{var11s4 = false;}
                }
                else{var11s4 = true;}
                if (var10s5 != null)
                {
                	int content5 = var10s5.itemID;
                    if (inputID == content5)
                    {
                    	var11s5 = true;
                    	//System.out.println("5: Player has " + content5);
                    }
                    else{var11s5 = false;}
                }
                else{var11s5 = true;}
                if (var10s6 != null)
                {
                	int content6 = var10s6.itemID;
                    if (inputID == content6)
                    {
                    	var11s6 = true;
                    	//System.out.println("6: Player has " + content6);
                    }
                    else{var11s6 = false;}
                }
                else{var11s6 = true;}
                if (var10s7 != null)
                {
                	int content7 = var10s7.itemID;
                    if (inputID == content7)
                    {
                    	var11s7 = true;
                    	//System.out.println("7: Player has " + content7);
                    }
                    else{var11s7 = false;}
                }
                else{var11s7 = true;}
                if (var10s8 != null)
                {
                	int content8 = var10s8.itemID;
                    if (inputID == content8)
                    {
                    	var11s8 = true;
                    	//System.out.println("8: Player has " + content8);
                    }
                    else{var11s8 = false;}
                }
                else{var11s8 = true;}
                if (var10s9 != null)
                {
                	int content9 = var10s9.itemID;
                    if (inputID == content9)
                    {
                    	var11s9 = true;
                    	//System.out.println("9: Player has " + content9);
                    }
                    else{var11s9 = false;}
                }
                else{var11s9 = true;}
                
                //Checks that all scanning results are true
                if (var11s1 && var11s2 && var11s3 && var11s4 && var11s5 && var11s6 && var11s7 && var11s8 && var11s9)
                {
                	var12 = true;
                	//System.out.println("Scanner will activate");
                }
        	}
        }
        
        //Scans inventory for each item in scanner
        
        
        if (var12)
        {
        	par1World.setBlockMetadataWithNotify(par2, par3, par4, 1);
        	par1World.markBlocksDirty(par2, par3, par4, par2, par3, par4);
        	par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
        }
        else {par1World.setBlockMetadataWithNotify(par2, par3, par4, 0);}
    }
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            if (this.isActive)
            {
            	this.isActive = false;
            }
        }
        if (par1World.getBlockMetadata(par2, par3, par4) == 1)
        {
        	this.setBlockBounds(0F, 0F, 0.5F, 0.5F, 2F, 1F);
        }
    }
    public boolean blockActivated(World world, int x, int y, int z,EntityPlayer player)
    {
    	if (world.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityScanner var6 = (TileEntityScanner)world.getBlockTileEntity(x, y, z);
            if (var6 != null)
            {
                ModLoader.openGUI(ModLoader.getMinecraftInstance().thePlayer, new GuiScanner(ModLoader.getMinecraftInstance().thePlayer.inventory, var6));
            }

            return true;
        }
    }
    
    public void addCreativeItems(ArrayList itemList)
    {
            itemList.add(new ItemStack(this));
    }
    /**
     * Is this block powering the block on the specified side
     */
    public boolean isPoweringTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return isActive;
    }

    /**
     * Is this block indirectly powering the block on the specified side
     */
    public boolean isIndirectlyPoweringTo(World par1World, int par2, int par3, int par4, int par5)
    {
        return isActive;
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return true;
    }
	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityScanner();
	}
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int var5 = par1World.getBlockId(par2, par3, par4 - 1);
            int var6 = par1World.getBlockId(par2, par3, par4 + 1);
            int var7 = par1World.getBlockId(par2 - 1, par3, par4);
            int var8 = par1World.getBlockId(par2 + 1, par3, par4);
            byte var9 = 3;

            if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
            {
                var9 = 1;
            }

            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
            {
                var9 = 1;
            }

            if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
            {
                var9 = 0;
            }

            if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
            {
                var9 = 0;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, var9);
            if (par1World.getBlockMetadata(par2, par3, par4) == 1)
            {
            	this.setBlockBounds(0F, 0F, 0.5F, 0.5F, 2F, 1F);
            }
        }
    }
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1);
        }

        if (var6 == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0);
        }

        if (var6 == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1);
        }

        if (var6 == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0);
        }
        
        if (par1World.getBlockMetadata(par2, par3, par4) == 1)
        {
        	this.setBlockBounds(0F, 0F, 0.5F, 0.5F, 2F, 1F);
        }
    }
}