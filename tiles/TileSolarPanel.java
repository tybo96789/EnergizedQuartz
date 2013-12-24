package tybo96789.energizedquartz.tiles;
/*
 * TILESOLARPANEL IS THE TILE ENEITY THAT EXTENDS TILEENERGY, BECAUSE THIS TILE ENENITY WILL HAVE TO CHECK IF THERE IS DAYLIGHT AND IF THERE IS UPDATE AND PRODUCE ENERGY
 */

import tybo96789.energizedquartz.blocks.SolarPanel;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class TileSolarPanel extends TileGenerator {
	
	private SolarPanel panel;
	
	private int updateTimer = 0;
	
	private boolean  validCon = false, isDay, canProduce;
	
	private World wld;
	
	private static boolean forceUpdate = false;
	
	public TileSolarPanel(SolarPanel tile, World world) {
		super(tile);
		this.wld = world;
		this.panel = tile;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if(!this.wld.provider.hasNoSky && this.wld.canBlockSeeTheSky(this.xCoord, this.yCoord +1, this.zCoord)){
			if(updateTimer <= 0 || forceUpdate)
			{
				this.isDay = this.wld.isDaytime();
				this.validCon = !(this.wld.getWorldInfo().isRaining() || this.wld.getWorldInfo().isThundering());
				if(forceUpdate)forceUpdate = false;
					if(this.isDay && this.validCon)
					{
						System.out.println("Conditions are Valid!");
						this.canProduce = true;
						if(super.getEnergyPacket() != null) super.getEnergyPacket().setEnergy(SolarPanel.PRODUCEAMT);
					}
					else
					{
						System.out.println("Conditions are NOT Valid!");
						this.canProduce = false;
						if(super.getEnergyPacket() != null) super.getEnergyPacket().setEnergy(0);
					}
				super.updateEntity();
				if(updateTimer <= 0) updateTimer = 600;
			}
			else
			{
				this.updateTimer--;
			}
		}
	}
	
	public boolean getState()
	{
		return this.canProduce;
	}
}
