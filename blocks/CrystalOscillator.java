package tybo96789.energizedquartz.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.energy.EnergyNet;
import tybo96789.energizedquartz.helpers.BlockHelper;
import tybo96789.energizedquartz.helpers.ITextured;
import tybo96789.energizedquartz.tiles.TileCrystalOscillator;
import tybo96789.energizedquartz.tiles.TileEnergy;

public class CrystalOscillator extends BlockContainer implements ITextured{

	public CrystalOscillator(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(EQ_Core.tabCustom);
		this.setUnlocalizedName("Crystal Oscillator");
		this.setHardness(3.5F);
		this.setResistance(10F);
	}
	
    @SideOnly(Side.CLIENT) //TODO Add sided textures
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEnergy(this);
	}
    @Override
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
    {
    	//EnergyNet.forceUpdate();
    	EnergyNet.numOfControl--;
    }
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
    	//EnergyNet.forceUpdate();
    	EnergyNet.numOfControl++;
    }
    @Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if (tileEntity instanceof TileEnergy)
		{
			EnergyNet.forceUpdate();
		}
	}
	

}
