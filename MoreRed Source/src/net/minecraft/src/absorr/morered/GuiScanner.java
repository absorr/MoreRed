package net.minecraft.src.absorr.morered;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import net.minecraft.src.*;
import net.minecraft.src.absorr.morecrafts.*;

import org.lwjgl.opengl.GL11;


public class GuiScanner extends GuiContainer
{
    private TileEntityScanner invFurnaceInventory;
    private GuiButton buttonName;
	private boolean mode;

    public GuiScanner(InventoryPlayer par1InventoryPlayer, TileEntityScanner par2TileEntityInversion)
    {
        super(new ContainerScanner(par1InventoryPlayer, par2TileEntityInversion));
        this.invFurnaceInventory = par2TileEntityInversion;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer()
    {
        this.fontRenderer.drawString(StatCollector.translateToLocal("Inventory Scanner"), 40, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int var4 = this.mc.renderEngine.getTexture("/morecrafts/scan.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7;
    }
    public void initGui()
    {
    	super.initGui();
        this.mc.thePlayer.craftingInventory = this.inventorySlots;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        //the 1 is the button ID, width / 2 is width location, height / 2 is height location, 98 is button size (length)
        //20 is button size (height)(don't change this), and Button Name is what is displayed on the button
        int intX = this.invFurnaceInventory.xCoord;
    	int intY = this.invFurnaceInventory.yCoord;
    	int intZ = this.invFurnaceInventory.zCoord;
        controlList.add(buttonName = new GuiButton(1, width / 2 + 17, height / 2 - 50, 20, 20, getMode(intX, intY, intZ)));
    }
    
    public static String getMode(int x, int y, int z)
    {
    	try {
			ModLoader.getMinecraftInstance().theWorld.checkSessionLock();
		} catch (MinecraftException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	boolean moder = false;
    	try {
    		File file = new File ("" + ModLoader.getMinecraftInstance().getMinecraftDir() + "/saves/" + ModLoader.getMinecraftInstance().theWorld.getSaveHandler().getSaveDirectoryName() + "","morecrafts.dat");
    	if (!file.exists()) {
    		
    	}
    	FileInputStream fileinputstream = new FileInputStream(file);
    	NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(fileinputstream);
    	if (nbttagcompound.hasKey("mode" + x + y + z)) {
    		moder= nbttagcompound.getBoolean("mode" + x + y + z);
    	}
    	fileinputstream.close();
    	}
    	catch (Exception exception) {
    	exception.printStackTrace();
    	}
    	if (moder) return "AND";
    	else return "OR";
    }


    protected void actionPerformed(GuiButton guibutton)
    {
       readNBTFromFile();
       if(mode == false)
       {
    	   buttonName.displayString = "AND";
    	   writeNBTToFile(true);
       }
       else
       {
    	   buttonName.displayString = "OR";
    	   writeNBTToFile(false);
       }
    }
    
    private void writeNBTToFile(boolean par1) {
    	try {
    	File file = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/saves/" + ModLoader.getMinecraftInstance().theWorld.getSaveHandler().getSaveDirectoryName() + "","morecrafts.dat");
    	if (!file.exists()) {
    	file.createNewFile();
    	}
    	int intX = this.invFurnaceInventory.xCoord;
    	int intY = this.invFurnaceInventory.yCoord;
    	int intZ = this.invFurnaceInventory.zCoord;
    	FileOutputStream fileoutputstream = new FileOutputStream(file);
    	NBTTagCompound nbttagcompound = new NBTTagCompound();
    	nbttagcompound.setBoolean("mode" + intX + intY + intZ, par1);

    	CompressedStreamTools.writeCompressed(nbttagcompound, fileoutputstream);
    	fileoutputstream.close();
    	}
    	catch(Exception exception) {
    	exception.printStackTrace();
    	}
    	}

    private void readNBTFromFile() {
    	try {
			ModLoader.getMinecraftInstance().theWorld.checkSessionLock();
		} catch (MinecraftException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	int intX = this.invFurnaceInventory.xCoord;
    	int intY = this.invFurnaceInventory.yCoord;
    	int intZ = this.invFurnaceInventory.zCoord;
    	try {
    		File file = new File ("" + ModLoader.getMinecraftInstance().getMinecraftDir() + "/saves/" + ModLoader.getMinecraftInstance().theWorld.getSaveHandler().getSaveDirectoryName() + "","morecrafts.dat");
    	if (!file.exists()) {
    		return;
    	}
    	FileInputStream fileinputstream = new FileInputStream(file);
    	NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(fileinputstream);
    	if (nbttagcompound.hasKey("mode" + intX + intY + intZ)) {
    		this.mode= nbttagcompound.getBoolean("mode" + intX + intY + intZ);
    	}
    	fileinputstream.close();
    	}
    	catch (Exception exception) {
    	exception.printStackTrace();
    	}
    }
}
