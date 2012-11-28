package absorr.morered.materials;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraftforge.client.*;
import net.minecraftforge.common.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.event.*;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import absorr.morered.base.MoreRed;


import net.minecraftforge.common.*;

public class BlockIronButton extends Block
{
    public BlockIronButton(int par1, int par2)
    {
        super(par1, par2, Material.circuits);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
    
    static Map timeMap = new HashMap();
    public static int time;

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

	/**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
    {
        ForgeDirection dir = ForgeDirection.getOrientation(par5);
        return (dir == NORTH && par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH)) ||
               (dir == SOUTH && par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH)) ||
               (dir == WEST  && par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST)) ||
               (dir == EAST  && par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST));
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return (par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST)) ||
               (par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST)) ||
               (par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH)) ||
               (par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH));
    }

    /**
     * called before onBlockPlacedBy by ItemBlock and ItemReed
     */
    public void updateBlockMetadata(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8)
    {
        int var9 = par1World.getBlockMetadata(par2, par3, par4);
        int var10 = var9 & 8;
        var9 &= 7;
        
        ForgeDirection dir = ForgeDirection.getOrientation(par5);

        if (dir == NORTH && par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH))
        {
            var9 = 4;
        }
        else if (dir == SOUTH && par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH))
        {
            var9 = 3;
        }
        else if (dir == WEST && par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST))
        {
            var9 = 2;
        }
        else if (dir == EAST && par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST))
        {
            var9 = 1;
        }
        else
        {
            var9 = this.getOrientation(par1World, par2, par3, par4);
        }

        par1World.setBlockMetadataWithNotify(par2, par3, par4, var9 + var10);
    }

    /**
     * Get side which this button is facing.
     */
    private int getOrientation(World par1World, int par2, int par3, int par4)
    {
        if (par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST)) return 1; 
        if (par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST)) return 2; 
        if (par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH)) return 3; 
        if (par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH)) return 4;
        return 1;
    }


    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (this.redundantCanPlaceBlockAt(par1World, par2, par3, par4))
        {
            int var6 = par1World.getBlockMetadata(par2, par3, par4) & 7;
            boolean var7 = false;

            if (!par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST) && var6 == 1)
            {
                var7 = true;
            }

            if (!par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST) && var6 == 2)
            {
                var7 = true;
            }

            if (!par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH) && var6 == 3)
            {
                var7 = true;
            }

            if (!par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH) && var6 == 4)
            {
                var7 = true;
            }

            if (var7)
            {
                this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
                par1World.setBlockWithNotify(par2, par3, par4, 0);
            }
        }
    }

    /**
     * This method is redundant, check it out...
     */
    private boolean redundantCanPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockWithNotify(par2, par3, par4, 0);
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        int var6 = var5 & 7;
        boolean var7 = (var5 & 8) > 0;
        float var8 = 0.375F;
        float var9 = 0.625F;
        float var10 = 0.1875F;
        float var11 = 0.125F;

        if (var7)
        {
            var11 = 0.0625F;
        }

        if (var6 == 1)
        {
            this.setBlockBounds(0.0F, var8, 0.5F - var10, var11, var9, 0.5F + var10);
        }
        else if (var6 == 2)
        {
            this.setBlockBounds(1.0F - var11, var8, 0.5F - var10, 1.0F, var9, 0.5F + var10);
        }
        else if (var6 == 3)
        {
            this.setBlockBounds(0.5F - var10, var8, 0.0F, 0.5F + var10, var9, var11);
        }
        else if (var6 == 4)
        {
            this.setBlockBounds(0.5F - var10, var8, 1.0F - var11, 0.5F + var10, var9, 1.0F);
        }
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        this.onBlockActivated(par1World, par2, par3, par4,par5EntityPlayer, 1, 1, 1, 1);
    }
    
    boolean shift;
    
    public void keyPressed(KeyEvent e)
    {
      switch(e.getKeyCode())
      {
         case KeyEvent.VK_SHIFT: shift = true;
      }
    }

    /**
     * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the
     * block.
     */
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4,EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	ItemStack heldItem = player.inventory.getCurrentItem();
    	ItemStack tweaker = new ItemStack(MoreRed.screwdriver);
    	if (heldItem.itemID == tweaker.itemID)
    	{
    		int ticks = this.tickRate();
    		readNBTFromFile(par2, par3, par4);
    		int timing = this.time;
    		if (timing == 0)
        	{
    			writeNBTToFile(par2, par3, par4, 20);
        	}
    		else
    		{
    			if (timing > 300)
        			writeNBTToFile(par2, par3, par4, 20);
    			else{
    				int newtime = Integer.valueOf(timing) + 20;
    				writeNBTToFile(par2, par3, par4, newtime);
    				this.time = newtime;
    			}
    		}
    		readNBTFromFile(par2, par3, par4);
    		ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Tick rate set to " + time / 20 + " seconds");
    		return true;
    	}
    	else
    	{
    		readNBTFromFile(par2, par3, par4);
    		int var6 = par1World.getBlockMetadata(par2, par3, par4);
            int var7 = var6 & 7;
            int var8 = 8 - (var6 & 8);

            if (var8 == 0)
            {
                return true;
            }
            else
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, var7 + var8);
                par1World.markBlocksDirty(par2, par3, par4, par2, par3, par4);
                par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.click", 0.3F, 0.6F);
                par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);

                if (var7 == 1)
                {
                    par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
                }
                else if (var7 == 2)
                {
                    par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
                }
                else if (var7 == 3)
                {
                    par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
                }
                else if (var7 == 4)
                {
                    par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
                }
                else
                {
                    par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
                }

                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
                return true;
            }
    	}
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        if ((par6 & 8) > 0)
        {
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
            int var7 = par6 & 7;

            if (var7 == 1)
            {
                par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
            }
            else if (var7 == 2)
            {
                par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
            }
            else if (var7 == 3)
            {
                par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
            }
            else if (var7 == 4)
            {
                par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
            }
            else
            {
                par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
            }
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    /**
     * Is this block powering the block on the specified side
     */
    public boolean isPoweringTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) > 0;
    }

    /**
     * Is this block indirectly powering the block on the specified side
     */
    public boolean isIndirectlyPoweringTo(World par1World, int par2, int par3, int par4, int par5)
    {
        int var6 = par1World.getBlockMetadata(par2, par3, par4);

        if ((var6 & 8) == 0)
        {
            return false;
        }
        else
        {
            int var7 = var6 & 7;
            return var7 == 5 && par5 == 1 ? true : (var7 == 4 && par5 == 2 ? true : (var7 == 3 && par5 == 3 ? true : (var7 == 2 && par5 == 4 ? true : var7 == 1 && par5 == 5)));
        }
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return true;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);

            if ((var6 & 8) != 0)
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 & 7);
                par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
                int var7 = var6 & 7;

                if (var7 == 1)
                {
                    par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
                }
                else if (var7 == 2)
                {
                    par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
                }
                else if (var7 == 3)
                {
                    par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
                }
                else if (var7 == 4)
                {
                    par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
                }
                else
                {
                    par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
                }

                par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.click", 0.3F, 0.5F);
                par1World.markBlocksDirty(par2, par3, par4, par2, par3, par4);
            }
        }
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        float var1 = 0.1875F;
        float var2 = 0.125F;
        float var3 = 0.125F;
        this.setBlockBounds(0.5F - var1, 0.5F - var2, 0.5F - var3, 0.5F + var1, 0.5F + var2, 0.5F + var3);
    }
	public void addCreativeItems(ArrayList itemList)
    {
            itemList.add(new ItemStack(this));
    }
	private void writeNBTToFile(int intX, int intY, int intZ, int par1) {
    	try {
    	File file = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/saves/" + ModLoader.getMinecraftInstance().theWorld.getSaveHandler().getSaveDirectoryName() + "","morecrafts.dat");
    	if (!file.exists()) {
    	file.createNewFile();
    	}
    	FileOutputStream fileoutputstream = new FileOutputStream(file);
    	NBTTagCompound nbttagcompound = new NBTTagCompound();
    	nbttagcompound.setInteger("time" + intX + intY + intZ, par1);

    	CompressedStreamTools.writeCompressed(nbttagcompound, fileoutputstream);
    	fileoutputstream.close();
    	}
    	catch(Exception exception) {
    	exception.printStackTrace();
    	}
    	this.time = par1;
    	}

    private void readNBTFromFile(int intX, int intY, int intZ) {
    	try {
			ModLoader.getMinecraftInstance().theWorld.checkSessionLock();
		} catch (MinecraftException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//int timer = 20;
    	try {
    		File file = new File ("" + ModLoader.getMinecraftInstance().getMinecraftDir() + "/saves/" + ModLoader.getMinecraftInstance().theWorld.getSaveHandler().getSaveDirectoryName() + "","morecrafts.dat");
    	if (!file.exists()) {
    		return;
    	}
    	FileInputStream fileinputstream = new FileInputStream(file);
    	NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(fileinputstream);
    	if (nbttagcompound.hasKey("time" + intX + intY + intZ)) {
    		this.time = nbttagcompound.getInteger("time" + intX + intY + intZ);
    	}
    	else writeNBTToFile(intX, intY, intZ, 20);
    	fileinputstream.close();
    	}
    	catch (Exception exception) {
    	exception.printStackTrace();
    	}
    	//this.time = timer;
    }


    /**
     * How many world ticks before ticking
     */
    public int tickRate()
    {
        return this.time;
    }
}