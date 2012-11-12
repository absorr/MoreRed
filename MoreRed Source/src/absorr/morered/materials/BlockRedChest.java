package absorr.morered.materials;

import static net.minecraftforge.common.ForgeDirection.DOWN;

import java.util.*;

import absorr.morered.base.CommonProxy;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.*;

public class BlockRedChest extends BlockChest
{
	private Random random = new Random();

    public BlockRedChest(int par1)
    {
        super(par1);
        this.blockIndexInTexture = 11;
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }
    
    @Override
	public String getTextureFile() 
	{
		return CommonProxy.blockPic;
	}

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	if (par1World.isBlockGettingPowered(par2, par3, par4))
    	{
    		Object var10 = (TileEntityChest)par1World.getBlockTileEntity(par2, par3, par4);

            if (var10 == null)
            {
                return true;
            }
            else if (par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN))
            {
                return true;
            }
            else if (isOcelotBlockingChest(par1World, par2, par3, par4))
            {
                return true;
            }
            else if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID && (par1World.isBlockSolidOnSide(par2 - 1, par3 + 1, par4, DOWN) || isOcelotBlockingChest(par1World, par2 - 1, par3, par4)))
            {
                return true;
            }
            else if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID && (par1World.isBlockSolidOnSide(par2 + 1, par3 + 1, par4, DOWN) || isOcelotBlockingChest(par1World, par2 + 1, par3, par4)))
            {
                return true;
            }
            else if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 - 1, DOWN) || isOcelotBlockingChest(par1World, par2, par3, par4 - 1)))
            {
                return true;
            }
            else if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 + 1, DOWN) || isOcelotBlockingChest(par1World, par2, par3, par4 + 1)))
            {
                return true;
            }
            else
            {
                if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID)
                {
                    var10 = new InventoryLargeChest("container.chestDouble", (TileEntityChest)par1World.getBlockTileEntity(par2 - 1, par3, par4), (IInventory)var10);
                }

                if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
                {
                    var10 = new InventoryLargeChest("container.chestDouble", (IInventory)var10, (TileEntityChest)par1World.getBlockTileEntity(par2 + 1, par3, par4));
                }

                if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID)
                {
                    var10 = new InventoryLargeChest("container.chestDouble", (TileEntityChest)par1World.getBlockTileEntity(par2, par3, par4 - 1), (IInventory)var10);
                }

                if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID)
                {
                    var10 = new InventoryLargeChest("container.chestDouble", (IInventory)var10, (TileEntityChest)par1World.getBlockTileEntity(par2, par3, par4 + 1));
                }

                if (par1World.isRemote)
                {
                    return true;
                }
                else
                {
                    par5EntityPlayer.displayGUIChest((IInventory)var10);
                    return true;
                }
            }
    	}
    	else
    		return false;
    }

    /**
     * Looks for a sitting ocelot within certain bounds. Such an ocelot is considered to be blocking access to the
     * chest.
     */
    public static boolean isOcelotBlockingChest(World par0World, int par1, int par2, int par3)
    {
        Iterator var4 = par0World.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)par1, (double)(par2 + 1), (double)par3, (double)(par1 + 1), (double)(par2 + 2), (double)(par3 + 1))).iterator();
        EntityOcelot var6;

        do
        {
            if (!var4.hasNext())
            {
                return false;
            }

            EntityOcelot var5 = (EntityOcelot)var4.next();
            var6 = (EntityOcelot)var5;
        }
        while (!var6.isSitting());

        return true;
    }
}