package tybo96789.energizedquartz.blocks;
/*
 * GENERATOR IS A BLOCKCONTAINER, WHICH IS A BLOCK, BUT BLOCKCONTAINERS CAN HAVE NBT DATA
 * PURPOSE: SERVES AS FOUNDATION FOR OTHER TYPES OF GENERATORS
 */

import java.util.logging.Logger;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.energy.EnergyNet;
import tybo96789.energizedquartz.energy.EnergyProducer;
import tybo96789.energizedquartz.helpers.ITextured;
import tybo96789.energizedquartz.helpers.MaterialHelper;
import tybo96789.energizedquartz.items.EnergizedWrench;
import tybo96789.energizedquartz.tiles.TileEnergy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class Generator extends BlockContainer implements EnergyProducer, ITextured
{
    private static final double BASE_VAL = 10;

    public Generator(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.setCreativeTab(EQ_Core.tabCustom);
        this.setHardness(3.5F);
    }
    
    @SideOnly(Side.CLIENT) //TODO Add sided textures
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEnergy(this);
    	//return null;
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
}
