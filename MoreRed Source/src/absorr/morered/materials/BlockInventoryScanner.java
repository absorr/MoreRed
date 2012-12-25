package absorr.morered.materials;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.EnumMobType;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import absorr.morered.base.CommonProxy;
import absorr.morered.base.MoreRed;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInventoryScanner extends BlockContainer
{
	private EnumMobType triggerMobType;
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
	@SideOnly(Side.CLIENT)
	@Override
	public String getTextureFile() 
	{
		return CommonProxy.blockPic;
	}
	@SideOnly(Side.CLIENT)
	public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
		if (j == 1 || j == 101)
        {
                return 7;
        }
        else
        {
                return 6;
        }
    }
    public int idDropped(int i, Random random) 
    { 
       return MoreRed.invScanner.blockID; 
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
            	this.scanForItem(par1World, par2, par3, par4, entity.func_92014_d());
            }
            if (par5Entity instanceof EntityLiving)
            {
            	if (par5Entity instanceof EntityPlayer)
            	{
            		this.scanInventory(par1World, par2, par3, par4, (EntityPlayer) par5Entity);
            	}
            	/**else
            	{
            		ItemStack item = EntityItemList.getEntityItem(par5Entity);
            		this.scanForItem(par1World, par2, par3, par4, item);
            	}**/
            }
        }
    }
    private void scanInventory(World par1World, int par2, int par3, int par4, EntityPlayer player)
    {
        boolean var5 = par1World.getBlockMetadata(par2, par3, par4) == 101 || par1World.getBlockMetadata(par2, par3, par4) == 1;
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
        String mode = GuiScanner.getMode(par2, par3, par4, par1World);
        
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
        	if (par1World.getBlockMetadata(par2, par3, par4) == 100)
        		par1World.setBlockMetadata(par2, par3, par4, 101);
        	else
        		par1World.setBlockMetadata(par2, par3, par4, 1);
        	par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
        	par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
        	par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
        	System.out.println("Metadata is " + par1World.getBlockMetadata(par2, par3, par4));
        }
        else 
        {
        	if (par1World.getBlockMetadata(par2, par3, par4) == 101)
        		par1World.setBlockMetadata(par2, par3, par4, 100);
        	if (par1World.getBlockMetadata(par2, par3, par4) == 1)
        		par1World.setBlockMetadata(par2, par3, par4, 0);;
    	}
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
        String mode = GuiScanner.getMode(par2, par3, par4, par1World);
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
                }
        	}
        }
        
        //Scans inventory for each item in scanner
        
        
        if (var12)
        {
        	if (par1World.getBlockMetadata(par2, par3, par4) == 100)
        		par1World.setBlockMetadataWithNotify(par2, par3, par4, 101);
        	if (par1World.getBlockMetadata(par2, par3, par4) == 0)
        		par1World.setBlockMetadataWithNotify(par2, par3, par4, 1);
        	par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
        	par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
        	par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
        	par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
        }
        else 
        {
        	if (par1World.getBlockMetadata(par2, par3, par4) == 101)
        		par1World.setBlockMetadata(par2, par3, par4, 100);
        	if (par1World.getBlockMetadata(par2, par3, par4) == 1)
        		par1World.setBlockMetadata(par2, par3, par4, 0);;
    	}
    }
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	if (par1World.getBlockMetadata(par2, par3, par4) == 101)
    		par1World.setBlockMetadata(par2, par3, par4, 100);
    	if (par1World.getBlockMetadata(par2, par3, par4) == 1)
    		par1World.setBlockMetadata(par2, par3, par4, 0);
    	else
    		par1World.setBlockMetadata(par2, par3, par4, 0);
    	par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
    }
    @Override
    
    @SideOnly(Side.CLIENT)
    public boolean onBlockActivated(World world, int x, int y, int z,EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	TileEntityScanner var6 = (TileEntityScanner)world.getBlockTileEntity(x, y, z);
        if (var6 != null)
        {
        	if(player instanceof EntityPlayerMP)
            {
        		ModLoader.serverOpenWindow((EntityPlayerMP)player, new ContainerScanner(player.inventory,var6), 0, x, y, z);
            }
            else
            {
            	ModLoader.openGUI((EntityPlayerSP)player, new GuiScanner(player.inventory, var6));
            }
        }

        return true;
    }
    
    public void addCreativeItems(ArrayList itemList)
    {
            itemList.add(new ItemStack(this));
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    @Override
    public boolean canProvidePower()
    {
        return true;
    }
    

    /**
     * Is this block powering the block on the specified side
     */
    @Override
    public boolean isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
    	return par1IBlockAccess.getBlockMetadata(par2, par3, par4) == 1 || par1IBlockAccess.getBlockMetadata(par2, par3, par4) == 101;
    }

    /**
     * Is this block indirectly powering the block on the specified side
     */
    @Override
    public boolean isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

        if ((var6 & 8) == 0 || (var6 & 8) == 100)
        {
            return false;
        }
        else
        {
            int var7 = var6 & 7;
            return var7 == 5 && par5 == 1 ? true : (var7 == 4 && par5 == 2 ? true : (var7 == 3 && par5 == 3 ? true : (var7 == 2 && par5 == 4 ? true : var7 == 1 && par5 == 5)));
        }
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
                var9 = 100;
            }

            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
            {
                var9 = 100;
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
            if (par1World.getBlockMetadata(par2, par3, par4) == 101)
            {
            	this.setBlockBounds(0F, 0F, 0.5F, 1F, 2F, 1F);
            }
            if (par1World.getBlockMetadata(par2, par3, par4) == 100)
            {
            	this.setBlockBounds(0F, 0F, 0.5F, 1F, 2F, 1F);
            }
        }
    }
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 100);
        }

        if (var6 == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0);
        }

        if (var6 == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 100);
        }

        if (var6 == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0);
        }
        if (par1World.getBlockMetadata(par2, par3, par4) == 101)
        {
        	this.setBlockBounds(0F, 0F, 0.5F, 1F, 2F, 1F);
        }
        if (par1World.getBlockMetadata(par2, par3, par4) == 100)
        {
        	this.setBlockBounds(0F, 0F, 0.5F, 1F, 2F, 1F);
        }
    }
}