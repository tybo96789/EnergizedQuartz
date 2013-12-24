package tybo96789.energizedquartz.helpers;
/*
 * THIS CLASS IS A HELPER WHERE IT ALLOWS CUSTOM METHOD OVERRIDES TO THE MATERIAL CLASS
 * NOTE: BLOCKHELPER CLASS IS REQUIRED FOR THIS TO WORK PROPERLY!
 */

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public abstract class MaterialHelper extends Material {
	
	private static boolean toolNotRequired;

	public MaterialHelper(MapColor par1MapColor) {
		super(par1MapColor);
	}
	
	@Override
    public abstract boolean getCanBurn();
	
    @Override
    public abstract boolean isToolNotRequired();
    
    public MaterialHelper getMaterial()
    {
    	return this;
    }
	
	
	

}
