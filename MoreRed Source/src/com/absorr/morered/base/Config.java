package com.absorr.morered.base;

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
	            rechargeID = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Tool_Recharging_Station", 190).getInt();
	            scannerID = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Inventory_Scanner", 193).getInt();
	            ironButtonID = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Iron_Button", 194).getInt();
	            rsChestID = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Redstone_Chest", 195).getInt();
	            detecID = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Detection_Plate", 196).getInt();
	            playPlateID = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Player_Plate", 197).getInt();
	            rsChunkID = configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Chunk", 5980).getInt();
	            rsIngotID = configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Ingot", 5981).getInt();
	            rsPickID = configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Pickaxe", 5983).getInt();
	            rsSpadeID = configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Shovel", 5984).getInt();
	            rsHoeID = configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Hoe", 5985).getInt();
	            rsAxeID = configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Axe", 5986).getInt();
	            rsSwordID = configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Sword", 5987).getInt();
	            rsMultiID = configuration.getItem(Configuration.CATEGORY_ITEM, "Redstone_Multi_Tool", 5988).getInt();
	            crowbarID = configuration.getItem(Configuration.CATEGORY_ITEM, "Crowbar", 5999).getInt();
	            screwdriverID = configuration.getItem(Configuration.CATEGORY_ITEM, "Screwdriver", 6000).getInt();
	            configuration.save();
	            return rechargeID;
	    }
}
