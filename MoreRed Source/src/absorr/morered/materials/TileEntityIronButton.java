package absorr.morered.materials;

import java.util.Random;

import net.minecraft.src.*;

public class TileEntityIronButton extends TileEntity implements IInventory
{
    private ItemStack[] buttonContents = new ItemStack[1];
    public int time = 0;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 0;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.buttonContents[par1];
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.buttonContents[par1] != null)
        {
            ItemStack var3;

            if (this.buttonContents[par1].stackSize <= par2)
            {
                var3 = this.buttonContents[par1];
                this.buttonContents[par1] = null;
                this.onInventoryChanged();
                return var3;
            }
            else
            {
                var3 = this.buttonContents[par1].splitStack(par2);

                if (this.buttonContents[par1].stackSize == 0)
                {
                    this.buttonContents[par1] = null;
                }

                this.onInventoryChanged();
                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.buttonContents[par1] != null)
        {
            ItemStack var2 = this.buttonContents[par1];
            this.buttonContents[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * gets stack of one item extracted from a stack chosen at random from the block inventory
     */

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.buttonContents[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }
    
    public void setTickTime(int var1)
    {
    	this.time = var1;
    	this.onInventoryChanged();
    }
    
    public int getTickTime()
    {
    	return this.time;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Iron Button";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.buttonContents = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.buttonContents.length)
            {
                this.buttonContents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
        this.time = par1NBTTagCompound.getShort("Time");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("Time", (short)this.time);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.buttonContents.length; ++var3)
        {
            if (this.buttonContents[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.buttonContents[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}
}