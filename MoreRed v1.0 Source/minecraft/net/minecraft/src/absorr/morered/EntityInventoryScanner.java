package net.minecraft.src.absorr.morered;
import net.minecraft.src.*;
import net.minecraft.src.absorr.morecrafts.*;

import java.util.Random;

public class EntityInventoryScanner extends Entity
{
    public EntityInventoryScanner(World world)
    {
        super(world);
        skinUrl = "/morecrafts/scanner.png";
    }
    
    public void onCollideWithPlayer(EntityPlayer player)
    {
    	System.out.println("Scanning...");
    }

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
		// TODO Auto-generated method stub
		
	}

	public World getWorld()
    {
        return this.worldObj;
    }
}