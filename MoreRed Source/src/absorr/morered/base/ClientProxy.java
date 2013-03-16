package absorr.morered.base;

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
