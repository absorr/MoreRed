package absorr.morered.materials;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.EnumMobType;
import net.minecraft.block.material.Material;
import absorr.morered.base.ClientProxy;

public class BlockPlayerPlate extends BlockPressurePlate
{

	public BlockPlayerPlate(int par1, int par2, Material par4Material) {
		super(par1, par2, EnumMobType.players, par4Material);
		this.setTextureFile(ClientProxy.blockPic);
	}

}
