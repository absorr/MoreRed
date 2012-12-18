package absorr.morered.base;

import java.io.File;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;

public class Config 
{
		static Configuration configuration = new Configuration(new File(new File(".").getAbsolutePath(), "config/MoreRed.cfg"));
		public static int rechargeID = configurationProperties();
		public static int scannerID;
		public static int ironButtonID;
		public static int rsChestID;
		public static int rsChunkID;
		public static int rsIngotID;
		public static int rsPickID;
		public static int rsSpadeID;
		public static int rsHoeID;
		public static int rsAxeID;
	    public static int rsSwordID;
	    public static int rsMultiID;
	    public static int crowbarID;
	    public static int screwdriverID;
	    public static int detecID;
	    public static int playPlateID;
	    public static int configurationProperties()
	    {
	            configuration.load();
	            rechargeID = Integer.parseInt(configuration.getBlock(Configuration.CATEGORY_BLOCK, "Tool_Recharging_Station", 190).value);
	            scannerID = Integer.parseInt(configuration.getBlock(Configuration.CATEGORY_BLOCK, "Inventory_Scanner", 193).value);
	            ironButtonID = Integer.parseInt(configuration.getBlock(Configuration.CATEGORY_BLOCK, "Iron_Button", 194).value);
	            rsChestID = Integer.parseInt(configuration.getBlock(Configuration.CATEGORY_BLOCK, "Redstone_Chest", 195).value);
	            detecID = Integer.parseInt(configuration.getBlock(Configuration.CATEGORY_BLOCK, "Detection_Plate", 196).value);
	            playPlateID = Integer.parseInt(configuration.getBlock(Configuration.CATEGORY_BLOCK, "Player_Plate", 197).value);
	            rsChunkID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Chunk", 5980).value);
	            rsIngotID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Ingot", 5981).value);
	            rsPickID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Pickaxe", 5983).value);
	            rsSpadeID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Shovel", 5984).value);
	            rsHoeID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Hoe", 5985).value);
	            rsAxeID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Axe", 5986).value);
	            rsSwordID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Sword", 5987).value);
	            rsMultiID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Multi_Tool", 5988).value);
	            crowbarID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Crowbar", 5999).value);
	            screwdriverID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Screwdriver", 6000).value);
	            configuration.save();
	            return rechargeID;
	    }
}
