package com.absorr.morered.materials;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.absorr.morered.base.MoreRed;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;

public class BlockIronButton extends BlockButton
{
    public BlockIronButton(int par1)
    {
        super(par1, false);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
    
    static Map timeMap = new HashMap();
    public static int time;
    
    @SideOnly(Side.CLIENT)
    @Override
    public void func_94332_a(IconRegister par1IconRegister)
    {
    	this.field_94336_cN = Block.blockSteel.getBlockTextureFromSide(0);
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
                par1World.setBlockMetadataWithNotify(par2, par3, par4, var7 + var8, 1);
                par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
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
                par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 & 7, 1);
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
                par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
            }
        }
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