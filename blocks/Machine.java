package tybo96789.energizedquartz.blocks;
/*
 * MACHINE IS A BLOCKCONTAINER WHICH IS A TYPE OF BLOCK, BUT BLOCKCONTAINER CAN STORE NBT DATA
 * PURPOSE: USED TO DEFINE MACHINE TYPE
 */

import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.energy.EnergyNet;
import tybo96789.energizedquartz.helpers.ITextured;
import tybo96789.energizedquartz.tiles.TileEnergy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class Machine extends BlockContainer implements ITextured
{
    private static double NEEDED_ENERGY;
    
    private static final boolean requiresNoTool = false;

    public Machine(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.setCreativeTab(EQ_Core.tabCustom);
        this.setHardness(3.5F);
    }
    
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
    	//EnergyNet.forceUpdate();
    }
    @Override
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
    {
    	//EnergyNet.forceUpdate();
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
    
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + "null");
    }

}
