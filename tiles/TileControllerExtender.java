package tybo96789.energizedquartz.tiles;

/*
 * THIS TILE ENEITY IS A EXTENDER BLOCK WHICH ADDS ALL INSTANCES OF TILEENERGY ON ITS AXIS TO AN EXISTING CRYSTAL OSCILLATOR
 */

import java.util.ArrayList;
import java.util.logging.Level;

import tybo96789.energizedquartz.Top;
import tybo96789.energizedquartz.blocks.ControllerExtender;
import tybo96789.energizedquartz.blocks.CrystalOscillator;
import tybo96789.energizedquartz.energy.EnergyNet;
import net.minecraft.block.Block;

public class TileControllerExtender extends TileEnergy {
	
	private boolean isValidExtender = false;
	
	private static boolean forceUpdate = false;
	
	private TileEnergy controller = null;
	
	private ArrayList<TileEnergy> enet;
	
	

	public TileControllerExtender(ControllerExtender block) {
		super(block);
		init();
		
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if(forceUpdate)
		{
			init();
			forceUpdate = false;
		}
	}
	
	private void init()
	{
		try{
			//checkIfValid();
		//if(this.isValidExtender)
			this.controller = findController(EnergyNet.heatMap(this));
			if(this.controller != null) this.isValidExtender = true;
			else this.isValidExtender = false;
		}catch(Exception e)
		{
			Top.log(Level.WARNING, "Failed to update Extender State!");
			forceUpdate = false;
		}
	}
	
	private void checkIfValid()
	{
		//TileEnergy[] tile = {super.getTileVoltageUp(this), super.getTileVoltageDown(this), super.getTileVoltageNorth(this), super.getTileVoltageSouth(this), super.getTileVoltageEast(this), super.getTileVoltageWest(this)};
		TileEnergy[] tile = super.getTileSides();
		boolean isValidTileEnergy = false;
		if(tile != null){
			for(TileEnergy i : tile)
			{
				if(i != null)
				{
					if(i.isLine || i.isSource || i.isReceptor || i.isExtender || i.isController) isValidTileEnergy = true;
					else isValidTileEnergy = false;
				}
				this.isValidExtender = this.isValidExtender && isValidTileEnergy;
				
			}
		}
	}
	
	public static TileEnergy findController(ArrayList<TileEnergy> net)
	{
		TileEnergy tile = null;
		for(TileEnergy i : net)
		{
			if(i.isController) tile = i;
			if(tile != null && i.isController)
			{
				System.out.println("Warning more then 1 Crystal Oscillator Detected");
				tile = null;
				break;
			}
		}
		return tile;
	}
	
	
	public boolean isExtending()
	{
		return this.isValidExtender;
	}
	
	public static void forceUpdate()
	{
		forceUpdate = true;
	}

}
