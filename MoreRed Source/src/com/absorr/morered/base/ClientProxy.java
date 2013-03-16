package com.absorr.morered.base;

import com.absorr.morered.base.CommonProxy;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
	/*
	 * Preloads textures for use
	 * @see absorr.morered.base.CommonProxy#registerRenderers()
	 */
	@Override
	public void registerRenderers() 
	{
		MinecraftForgeClient.preloadTexture("mods/morered/gui/scan.png"); 
	}
}
