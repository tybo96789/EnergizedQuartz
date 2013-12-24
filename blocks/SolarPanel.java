package tybo96789.energizedquartz.blocks;
/*
 * THIS IS THE SOLAR PANEL BLOCK.. WHEN PLACED IT WILL CREATE A NEW TILESOLARPANEL
 */

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.energy.EnergyNet;
import tybo96789.energizedquartz.energy.EnergyProducer;
import tybo96789.energizedquartz.helpers.ITextured;
import tybo96789.energizedquartz.helpers.MaterialHelper;
import tybo96789.energizedquartz.tiles.TileEnergy;
import tybo96789.energizedquartz.tiles.TileSolarPanel;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SolarPanel extends Generator implements EnergyProducer, ITextured {
	
	public static final double PRODUCEAMT = 10;

	public SolarPanel(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setHardness(10);
		this.setCreativeTab(EQ_Core.tabCustom);
	}
	
	
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileSolarPanel(this, world);
    }
    
    @Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID)
	{
    	TileSolarPanel.forceUpdate();
	}
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) //TODO Add sided textures
    {
        this.blockIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }
}
