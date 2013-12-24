package tybo96789.energizedquartz.helpers;

/*
 * THIS HELPER CLASS IS MENT TO HELP REGISTER THE ITEMS IN THE MOD INTO FORGE'S OREDIC
 */


import java.util.logging.Level;
import java.util.logging.Logger;

import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.Top;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class OreDicHelper {
	
	public static final boolean init()
	{
		Top.log(Level.INFO, "Adding Stuff to Ore Dictionary");
		OreDictionary.registerOre("oreUranium",EQ_Core.oreUranium);
		OreDictionary.registerOre("energizedQuartz",EQ_Core.energizedQuartz);
		OreDictionary.registerOre("energizedCoal", EQ_Core.energizedCoal);
		OreDictionary.registerOre("energizedRedStone",EQ_Core.energizedRedstone);
		OreDictionary.registerOre("oreLead", EQ_Core.oreLead);
		OreDictionary.registerOre("energizedDiamond", EQ_Core.energizedDiamond);
		OreDictionary.registerOre("wrenchEnergized",EQ_Core.energizedWrench);
		OreDictionary.registerOre("ingotLead", EQ_Core.leadIngot);
		Top.log(Level.INFO, "Done Adding stuff to Ore Dictionary");
		return true;
	}

}
