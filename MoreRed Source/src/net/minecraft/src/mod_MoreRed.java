package net.minecraft.src;
import net.minecraft.client.Minecraft;
import net.minecraft.src.absorr.morered.*;
import net.minecraft.src.absorr.morered.external.IC2Handler;
import net.minecraftforge.client.*;
import net.minecraftforge.common.*;
import java.awt.List;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class mod_MoreRed extends BaseMod
{
	static EnumToolMaterial toolOBSIDIAN = EnumHelper.addToolMaterial("OBSIDIAN", 3, 1000, 13F, 3, 22);
	//Creates the configuration integers
	static Configuration configuration = new Configuration(new File(Minecraft.getMinecraftDir(), "config/MoreRed.cfg"));
	static int rechargeID = configurationProperties();
	static int scannerID;
	static int ironButtonID;
    static int rsChunkID;
    static int rsIngotID;
    static int rsPickID;
    static int rsSpadeID;
    static int rsHoeID;
    static int rsAxeID;
    static int rsSwordID;
    static int rsMultiID;
    static int crowbarID;
    static int screwdriverID;
    public static int configurationProperties()
    {
            configuration.load();
            rechargeID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Tool_Recharging_Station", 190).value);
            scannerID = Integer.parseInt(configuration.getOrCreateIntProperty("Inventory_Scanner", Configuration.CATEGORY_BLOCK, 193).value);
            ironButtonID = Integer.parseInt(configuration.getOrCreateIntProperty("Iron_Button", Configuration.CATEGORY_BLOCK, 194).value);rsChunkID = Integer.parseInt(configuration.getOrCreateIntProperty("Redstone_Chunk", Configuration.CATEGORY_ITEM, 5980).value);
            rsIngotID = Integer.parseInt(configuration.getOrCreateIntProperty("Redstone_Ingot", Configuration.CATEGORY_ITEM, 5981).value);
            rsPickID = Integer.parseInt(configuration.getOrCreateIntProperty("Redstone_Pickaxe", Configuration.CATEGORY_ITEM, 5983).value);
            rsSpadeID = Integer.parseInt(configuration.getOrCreateIntProperty("Redstone_Shovel", Configuration.CATEGORY_ITEM, 5984).value);
            rsHoeID = Integer.parseInt(configuration.getOrCreateIntProperty("Redstone_Hoe", Configuration.CATEGORY_ITEM, 5985).value);
            rsAxeID = Integer.parseInt(configuration.getOrCreateIntProperty("Redstone_Axe", Configuration.CATEGORY_ITEM, 5986).value);
            rsSwordID = Integer.parseInt(configuration.getOrCreateIntProperty("Redstone_Sword", Configuration.CATEGORY_ITEM, 5987).value);
            rsMultiID = Integer.parseInt(configuration.getOrCreateIntProperty("Redstone_Multi_Tool", Configuration.CATEGORY_ITEM, 5988).value);
            crowbarID = Integer.parseInt(configuration.getOrCreateIntProperty("Crowbar", Configuration.CATEGORY_ITEM, 5999).value);
            screwdriverID = Integer.parseInt(configuration.getOrCreateIntProperty("Screwdriver", Configuration.CATEGORY_ITEM, 6000).value);
            configuration.save();
            return rechargeID;
    }
	
	//Creates the items and blocks
    public static final Item rsChunk = new MoreItems(rsChunkID, 64, CreativeTabs.tabMaterials).setItemName("redstoneChunk").setIconIndex(2);
    public static final Item rsIngot = new MoreItems(rsIngotID, 64, CreativeTabs.tabMaterials).setItemName("redstoneIngot").setIconIndex(3);
    public static final Item rsPick = new MoreItems(rsPickID, 1, CreativeTabs.tabTools).setItemName("redstonePickaxe").setIconIndex(4);
    public static final Item rsSpade = new MoreItems(rsSpadeID, 1, CreativeTabs.tabTools).setItemName("redstoneShovel").setIconIndex(5);
    public static final Item rsHoe = new ItemHoe(rsHoeID, EnumToolMaterial.IRON).setItemName("redstoneHoe").setIconIndex(6);
    public static final Item rsAxe = new MoreItems(rsAxeID, 1, CreativeTabs.tabTools).setItemName("redstoneAxe").setIconIndex(7);
    public static final Item rsSword = new ItemRsSword(rsSwordID, EnumToolMaterial.IRON).setItemName("redstoneSword").setIconIndex(8);
    public static final Item rsMulti = new MoreItems(rsMultiID, 1, CreativeTabs.tabTools).setItemName("redstoneMultitool").setIconIndex(14);
    public static final Item crowbar = new MoreItems(crowbarID, 1, CreativeTabs.tabTools).setItemName("crowbar").setIconIndex(16);
    public static final Item screwdriver = new MoreItems(screwdriverID, 1, CreativeTabs.tabTools).setItemName("screwdriver").setIconIndex(1);
    public static Block recharger = new BlockToolCharger(rechargeID, 0).setHardness(1.0F).setResistance(6000.0F).setLightValue(1.0F).setBlockName("Tool Recharging Station"); 
    public static Block invScanner = new BlockInventoryScanner(scannerID, 6).setHardness(1.0F).setResistance(6000.0F).setLightValue(0.0F).setBlockName("Inventory Scanner"); 
    public static Block ironButton = new BlockIronButton(ironButtonID, 22).setHardness(1.0F).setResistance(6000.0F).setLightValue(0.0F).setBlockName("Iron Button"); 
    
    
    public void load()
    {
    	MinecraftForge.setToolClass(rsPick, "pickaxe", 2);
    	MinecraftForge.setToolClass(rsSpade, "shovel", 2);
    	MinecraftForge.setToolClass(rsAxe, "axe", 2);
    	MinecraftForge.setToolClass(rsMulti, "pickaxe", 2);
    	MinecraftForge.setToolClass(rsMulti, "shovel", 2);
    	MinecraftForge.setToolClass(rsMulti, "axe", 2);
    	MinecraftForgeClient.preloadTexture("/morered/items.png"); 
  		MinecraftForgeClient.preloadTexture("/morered/blocks.png");
  		ModLoader.registerTileEntity(TileEntityScanner.class, "Inventory Scanner");
  		ModLoader.registerTileEntity(TileEntityRedChest.class, "Redstone Chest");
    	EntityList.entityEggs.put(Integer.valueOf(63), new EntityEggInfo(63, 0, 9118312));
    	
    	//Villager Trading "Recipes"
    	MerchantRecipeList npcTrade = new MerchantRecipeList();
    	npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (rsMulti, 1)));
    	npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (rsIngot, 3)));
    	npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (rsIngot, 3), new ItemStack (Item.emerald, 1)));
    }
    public boolean classExists (String className)
    {
    	try {
    		Class.forName (className);
    		return true;
    	}
    	catch (ClassNotFoundException exception) {
    		return false;
    	}
    }
    public mod_MoreRed()
    {
    	//ModLoader.getMinecraftInstance().thePlayer.addChatMessage("MoreCrafts Development Build Loaded Succesfully");
    	
    	if(classExists("mod_IC2")) 
        {
            IC2Handler.loadRecipies();
        }
    	//Redstone Chunk
        ModLoader.addName(rsChunk, "Redstone Chunk");
        ModLoader.addSmelting(Item.redstone.shiftedIndex, new ItemStack(rsChunk));
        //Redstone Ingot
        ModLoader.addName(rsIngot, "Redstone Ingot");
        ModLoader.addShapelessRecipe(new ItemStack(rsIngot, 1), new Object[] {rsChunk, rsChunk});
        //Redstone Pickaxe
        ModLoader.addName(rsPick, "Redstone Pickaxe");
        ModLoader.addRecipe(new ItemStack(rsPick, 1), new Object[] {"RRR", "OSO", "OSO", 'R', rsIngot, 'S', Item.stick});
        //Tool Recharging Station
        ModLoader.registerBlock(recharger); 
        ModLoader.addName(recharger, "Tool Recharging Station");
        ModLoader.addRecipe(new ItemStack(recharger, 1), new Object[] { 
        "IRI", "STS", "SSS", Character.valueOf('S'), Block.stone, Character.valueOf('T'), Block.torchRedstoneActive, Character.valueOf('R'), Item.redstone, Character.valueOf('I'), Block.blockSteel
        }); 
        //Redstone Shovel
        ModLoader.addName(rsSpade, "Redstone Shovel");
        ModLoader.addRecipe(new ItemStack(rsSpade, 1), new Object[] {"ORO", "OSO", "OSO", 'R', rsIngot, 'S', Item.stick});
        //Redstone Hoe
        rsHoe.iconIndex = ModLoader.addOverride("/gui/items.png", "/morecrafts/rshoe.png");
        ModLoader.addName(rsHoe, "Redstone Hoe");
        ModLoader.addRecipe(new ItemStack(rsHoe, 1), new Object[] {"ORR", "OSO", "OSO", 'R', rsIngot, 'S', Item.stick});
        ModLoader.addRecipe(new ItemStack(rsHoe, 1), new Object[] {"RRO", "OSO", "OSO", 'R', rsIngot, 'S', Item.stick});
        //Redstone Axe
        ModLoader.addName(rsAxe, "Redstone Axe");
        ModLoader.addRecipe(new ItemStack(rsAxe, 1), new Object[] {"RRO", "RSO", "OSO", 'R', rsIngot, 'S', Item.stick});
        ModLoader.addRecipe(new ItemStack(rsAxe, 1), new Object[] {"ORR", "OSR", "OSO", 'R', rsIngot, 'S', Item.stick});
        //Redstone Sword
        ModLoader.addName(rsSword, "Redstone Sword");
        ModLoader.addRecipe(new ItemStack(rsSword, 1), new Object[] {"ORO", "ORO", "OSO", 'R', rsIngot, 'S', Item.stick});
        //Redstone Multi-Tool
        ModLoader.addName(rsMulti, "Redstone Multi-Tool");
        ModLoader.addRecipe(new ItemStack(rsMulti, 1), new Object[] {"AHP", "OSO", "OSO", 'S', Item.stick, 'A', rsAxe, 'H', rsSpade, 'P', rsPick});
        //Crowbar
        ModLoader.addName(crowbar, "Crowbar");
        ModLoader.addRecipe(new ItemStack(crowbar, 1), new Object[] {"IOO", "IIO", "OOI", 'I', Item.ingotIron});
        //Inventory Scanner
        ModLoader.registerBlock(invScanner); 
        ModLoader.addName(new ItemStack(invScanner, 1), "Inventory Scanner");
        ModLoader.addRecipe(new ItemStack(invScanner, 1), new Object[] {"LEL", "IRI", "LEL", 'E', Item.enderPearl, 'I', Item.ingotIron, 'R', Item.redstone, 'L', new ItemStack(Item.dyePowder, 1, 4)});
        //Iron Button
        ModLoader.registerBlock(ironButton); 
        ModLoader.addName(new ItemStack(ironButton, 1), "Iron Button");
        ModLoader.addRecipe(new ItemStack(ironButton, 1), new Object[] {"OIO", "IBI", "OIO", 'B', Block.button, 'I', Item.ingotIron});
        //Redstone Block
        //ModLoader.addLocalization("tile.blankSpawner.blankSpawn.name", "Empty Monster Spawner");
        //ModLoader.addLocalization("tile.blankSpawner.rsBlock.name", "Redstone Block");
        //ModLoader.addRecipe(new ItemStack(blankSpawner, 1, 1), new Object[] { "RR", "RR", Character.valueOf('R'), rsIngot});
        //Screwdriver
        ModLoader.addName(screwdriver, "Screwdriver");
        ModLoader.addRecipe(new ItemStack(screwdriver, 1), new Object[] {"IO", "IO", 'I', Item.ingotIron});
    }
    public String getVersion()
    {
        return "1.1 pre1";
    }
}