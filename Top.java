package tybo96789.energizedquartz;
/*
 * THIS CLASS PROVIDES A TASK MANAGER FUNCTIONILITY TO THE MOD
 */

import java.util.logging.Level;
import java.util.logging.Logger;

import tybo96789.energizedquartz.energy.EnergyNet;
import tybo96789.energizedquartz.tiles.TileEnergy;
import cpw.mods.fml.common.FMLLog;

public final class Top {
	
	private static final Logger logger = Logger.getLogger(EQ_Core.modid);

	public Top() {
		
		init();
	}
	
	private static final void init()
	{
    	logger.setParent(FMLLog.getLogger());
	}
	
	public static void log(Level logLevel, String message) 
	{
		logger.log(logLevel, message);
	}
	
	public final static void forceUpdateAll()
	{
		TileEnergy.forceUpdate();
		EnergyNet.forceUpdate();
	}
	


}
