package tybo96789.energizedquartz.blocks;

import java.util.logging.Level;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.Top;
import tybo96789.energizedquartz.energy.EnergyNet;
import tybo96789.energizedquartz.helpers.BlockHelper;
import tybo96789.energizedquartz.helpers.ITextured;
import tybo96789.energizedquartz.tiles.TileControllerExtender;
import tybo96789.energizedquartz.tiles.TileEnergy;

public class ControllerExtender extends BlockContainer implements ITextured {

	public ControllerExtender(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setHardness(10F);
		this.setResistance(5F);
		this.setUnlocalizedName("Controller Extender");
		this.setCreativeTab(EQ_Core.tabCustom);
		}

	@Override
	public TileEntity createNewTileEntity(World world) { //TODO Add functionality to block 
		//return new TileControllerExtender(this);
		Top.log(Level.WARNING, "This Block has No Functionailty yet");
		return null;
	}
	/*
	 * On Call it will trigger a forced update to energynet and the controller extender
	 * (non-Javadoc)
	 * @see net.minecraft.block.Block#onNeighborBlockChange(net.minecraft.world.World, int, int, int, int)
	 */
	
    @Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if (tileEntity instanceof TileEnergy)
		{
			EnergyNet.forceUpdate();
			TileControllerExtender.forceUpdate();
		}
	}
	
	

}
