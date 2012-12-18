package absorr.morered.materials;

import absorr.morered.base.ClientProxy;
import net.minecraft.src.*;

public class BlockPlayerPlate extends BlockPressurePlate
{

	public BlockPlayerPlate(int par1, int par2, Material par4Material) {
		super(par1, par2, EnumMobType.players, par4Material);
		this.setTextureFile(ClientProxy.blockPic);
	}

}
