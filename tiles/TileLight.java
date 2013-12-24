package tybo96789.energizedquartz.tiles;

import tybo96789.energizedquartz.blocks.Light;
import net.minecraft.block.Block;
/*
 * TITELIGHT IS A PLACEHOLDER FOR OVERRIDED METHODS NEEDED TO RUN TILELIGHT AKA LIGHTID BLOCK
 */

public class TileLight extends TileEnergy
{

	private Light blk;
	
	private int nextUpdate = 0;
	
	public TileLight(Light block) {
		super(block);
		this.blk = block;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if(this.nextUpdate <= 0){
			if(super.getEnergyPacket() != null)
			{
				if(super.getEnergyPacket().getEnergy() == Light.NEEDED_ENERGY) 
				{
					this.blk.setCanLight(true);
					System.out.println("Has enough Energy");
					this.nextUpdate = 40;
				}
				else 
				{
					this.blk.setCanLight(false);
					System.out.println("Does not have enough Energy!");
					this.nextUpdate  = 40;
				}
			}
		}
		else nextUpdate--;
		//else System.out.println("Packet is null");
		
	}
	
}
