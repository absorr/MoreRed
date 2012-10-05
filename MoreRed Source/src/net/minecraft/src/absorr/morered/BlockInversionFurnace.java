package net.minecraft.src.absorr.morered;
import net.minecraft.src.*;
import net.minecraft.src.absorr.morecrafts.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cpw.mods.fml.client.ITextureFX;
import net.minecraftforge.*;

public class BlockInversionFurnace extends BlockContainer
{
	public BlockInversionFurnace(int i, int j) 
    { 
        super(i, j, Material.iron);
        this.setCreativeTab(CreativeTabs.tabDeco);
    }
	@Override
	public String getTextureFile() 
	{
		return "/morecrafts/blocks.png";
	}
	private static boolean keepFurnaceInventory = false;
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
                var9 = 3;
            }

            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
            {
                var9 = 2;
            }

            if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
            {
                var9 = 5;
            }

            if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
            {
                var9 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, var9);
        }
    }
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2);
        }

        if (var6 == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5);
        }

        if (var6 == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3);
        }

        if (var6 == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4);
        }
    }
	public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
            if (j == 5)
            {
                if (i == 5)
                {
                        return 3;
                }
                else
                {
                        return 4;
                }
            }
            if (j == 2)
            {
                if (i == 2)
                {
                        return 3;
                }
                else
                {
                        return 4;
                }
            }
            if (j == 3)
            {
                if (i == 3)
                {
                        return 3;
                }
                else
                {
                        return 4;
                }
            }
            if (j == 4)
            {
                if (i == 4)
                {
                        return 3;
                }
                else
                {
                        return 4;
                }
            }
            if (j == 15)
            {
                if (i == 5)
                {
                        return 5;
                }
                else
                {
                        return 4;
                }
            }
            if (j == 12)
            {
                if (i == 2)
                {
                        return 5;
                }
                else
                {
                        return 4;
                }
            }
            if (j == 13)
            {
                if (i == 3)
                {
                        return 5;
                }
                else
                {
                        return 4;
                }
            }
            if (j == 14)
            {
                if (i == 4)
                {
                        return 5;
                }
                else
                {
                        return 4;
                }
            }
            else return getBlockTextureFromSide(i);
    }

    public int getBlockTextureFromSide(int i)
    {
            if (i == 3)
            {
                    return 3;
            }
            else
            {
                    return 4;
            }
    }
    public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityInversion var6 = (TileEntityInversion)par1World.getBlockTileEntity(par2, par3, par4);
            if (var6 != null)
            {
                ModLoader.openGUI(ModLoader.getMinecraftInstance().thePlayer, new GuiInversion(ModLoader.getMinecraftInstance().thePlayer.inventory, var6));
            }

            return true;
        }
    }
    public static void updateFurnaceBlockState(boolean par0, World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        TileEntity var6 = world.getBlockTileEntity(x, y, z);
        keepFurnaceInventory = true;

        if (par0)
        {
        	if (meta < 10)
        	{
        		world.setBlockAndMetadataWithNotify(x, y, z, mod_moreCrafts.inverseFurnace.blockID, meta + 10);
        	}
        }
        else
        {
        	if (meta > 10)
        	{
        		world.setBlockAndMetadataWithNotify(x, y, z, mod_moreCrafts.inverseFurnace.blockID, meta - 10);
        	}
        }

        keepFurnaceInventory = false;

        if (var6 != null)
        {
            var6.validate();
            world.setBlockTileEntity(x, y, z, var6);
        }
    }
	public void addCreativeItems(ArrayList itemList)
    {
            itemList.add(new ItemStack(this));
    }
	@Override
	public TileEntity createNewTileEntity(World var1) {
		// TODO Auto-generated method stub
		return new TileEntityInversion();
	}
}