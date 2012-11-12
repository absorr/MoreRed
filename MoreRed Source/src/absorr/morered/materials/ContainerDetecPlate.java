package absorr.morered.materials;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class ContainerDetecPlate extends Container
{
    private TileEntityDetecPlate plate;
    
    public ContainerDetecPlate(IInventory par1IInventory, TileEntityDetecPlate par2TileEntity)
    {
    	this.plate = par2TileEntity;
        int var3;
        int var4;
        int var5;

        for (var3 = 0; var3 < 1; ++var3)
        {
            for (var4 = 0; var4 < 3; ++var4)
            {
                this.addSlotToContainer(new Slot(par2TileEntity, var4 + var3 * 3, 44 + var4 * 18, 17 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(par1IInventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(par1IInventory, var3, 8 + var3 * 18, 142));
        }
    }
    
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.plate.isUseableByPlayer(par1EntityPlayer);
    }
    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 < 9)
            {
                if (!this.mergeItemStack(var5, 9, 45, true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 0, 9, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.func_82870_a(par1EntityPlayer, var5);
        }

        return var3;
    }
}
