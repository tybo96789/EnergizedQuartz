package tybo96789.energizedquartz.blocks;

import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.ITextured;
import tybo96789.energizedquartz.tiles.TileEnergyCondenser;
import tybo96789.energizedquartz.tiles.TileSolarPanel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EnergyCondenser extends Generator implements ITextured {

	public EnergyCondenser(int par1, Material par2Material) {
		super(par1, par2Material);
        this.setCreativeTab(EQ_Core.tabCustom);
		this.setHardness(5);
	}
	
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEnergyCondenser(this);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }

	

}
