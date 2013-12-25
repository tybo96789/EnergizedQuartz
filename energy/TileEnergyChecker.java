package tybo96789.energizedquartz.energy;

import net.minecraft.tileentity.TileEntity;
import tybo96789.energizedquartz.tiles.TileEnergy;
/*
 * THIS CLASS HOLDS STATIC METHODS THAT WILL CHECK IF AN NEARBY BLOCK IS AN INSTANCE OF TILEENERGY IF IT IS RETURN THAT INSTANCE
 */

//TODO Check if getTileVoltage public modifier is needed

public class TileEnergyChecker {
	/*
	 * This method takes an instance of a tileenergy and checks adjacent blocks to see if its a instance of tile energy if it is store to TileEnergy[] and return
	 */
	public static TileEnergy[] getSides(TileEnergy tile)
	{
		return new TileEnergy[] {getTileVoltageUp(tile), getTileVoltageDown(tile), getTileVoltageNorth(tile), getTileVoltageSouth(tile), getTileVoltageEast(tile), getTileVoltageWest(tile)};
	}
	
    public static TileEnergy getTileVoltageUp(TileEntity tile)
    {
    	TileEntity up =  tile.worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord);
    	
    	TileEnergy temp = null;
    	
    	if(up != null){
	        if (up instanceof TileEnergy)
	        {
	            temp = (TileEnergy) up;
	        }
	        else
	        {
	            return null;
	        }
    	}
    	return temp;
    }
    public static TileEnergy getTileVoltageDown(TileEntity tile)
    {
    	TileEntity down =  tile.worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord);
    	
    	TileEnergy temp = null;
    	
    	if(down != null){
	        if (down instanceof TileEnergy)
	        {
	            temp = (TileEnergy) down;
	        }
	        else
	        {
	            return null;
	        }
    	}
    	return temp;
    }
    public static TileEnergy getTileVoltageNorth(TileEntity tile)
    {
    	TileEntity north = tile.worldObj.getBlockTileEntity(tile.xCoord + 1, tile.yCoord, tile.zCoord);
    	
    	TileEnergy temp = null;
    	
    	if(north != null){
	        if (north instanceof TileEnergy)
	        {
	            temp = (TileEnergy) north;
	        }
	        else
	        {
	            return null;
	        }
    	}
    	return temp;
    }
    public static TileEnergy getTileVoltageSouth(TileEntity tile)
    {
    	TileEntity south =  tile.worldObj.getBlockTileEntity(tile.xCoord - 1, tile.yCoord, tile.zCoord);
    	
    	TileEnergy temp = null;
    	
    	if(south != null){
	        if (south instanceof TileEnergy)
	        {
	            temp = (TileEnergy) south;
	        }
	        else
	        {
	            return null;
	        }
	        
    	}
    	return temp;
    }
    public static TileEnergy getTileVoltageEast(TileEntity tile)
    {
    	TileEntity east = tile.worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord, tile.zCoord + 1);
    	
    	TileEnergy temp = null;
    	
    	if(east != null){
	        if (east instanceof TileEnergy)
	        {
	            temp = (TileEnergy) east;
	        }
	        else
	        {
	            return null;
	        }
    	}
    	return temp;
    }
    public static TileEnergy getTileVoltageWest(TileEntity tile)
    {
    	TileEntity west = tile.worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord, tile.zCoord - 1);
    	
    	TileEnergy temp = null;
    	
    	if(west != null){
	        if (west instanceof TileEnergy)
	        {
	            temp = (TileEnergy) west;
	        }
	        else
	        {
	            return null;
	        }
    	}
    	return temp;
    }

}
