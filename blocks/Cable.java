package tybo96789.energizedquartz.blocks;
/*
 * CABLE EXTENDS BLOCKCONTAINER BECAUSE IT HAS A METHOD TO CREATE A NEW TILE ENTITY FROM THERE THAT ENTITY IS USED TO ADD ITSELF TO THE ENERGY NET
 * PURPOSE: FOR THE BLOCK CABLE, AND IT IS ALSO USED TO DECLARE A NEW INSTANCE OF TILEVOLTAGE
 */

import java.util.List;
import java.util.Random;

import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.energy.EnergyNet;
import tybo96789.energizedquartz.helpers.ITextured;
import tybo96789.energizedquartz.tiles.TileCable;
import tybo96789.energizedquartz.tiles.TileEnergy;
import tybo96789_Cobble_Gen.cobble_gen;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class Cable extends BlockContainer implements ITextured
{
    protected static final double maxVoltage = 120; //Not implemented yet :P

    protected static double currentVoltage = 0;
    
    private static final float minXC = 0.25F, minYC= 0.25F, minZC= 0.25F, maxXC = 0.75F,  maxYC = 0.75F,  maxZC = 0.75F;
    
    private boolean connected = false;

    public Cable(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.setCreativeTab(EQ_Core.tabCustom);
        this.setBlockBounds(minXC, minYC, minZC, maxXC, maxYC, maxZC);
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileCable(this);
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
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
        //TODO Make it drop a item equivalent
    }
    
    
    //TODO Add light change when packet of energy is going through the cable.
    
    /*
    // * The Following methods need to have fail safes otherwise they crash because they are ticking a null pointer
    // * TODO Fix texturing
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

    @Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(tileEntity != null){
			if (tileEntity instanceof TileCable)
			{
				TileEntity[] connectable = ((TileCable) tileEntity).getTileSides();

				if (connectable != null)
				{
					float minX = (float) this.minX;
					float minY = (float) this.minY;
					float minZ = (float) this.minZ;
					float maxX = (float) this.maxX;
					float maxY = (float) this.maxY;
					float maxZ = (float) this.maxZ;

					if (connectable[0] != null)
					{
						minY = 0.0F;
					}

					if (connectable[1] != null)
					{
						maxY = 1.0F;
					}

					if (connectable[2] != null)
					{
						minZ = 0.0F;
					}

					if (connectable[3] != null)
					{
						maxZ = 1.0F;
					}

					if (connectable[4] != null)
					{
						minX = 0.0F;
					}

					if (connectable[5] != null)
					{
						maxX = 1.0F;
					}

					this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
				}
			}
		}
	}
    
    @Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisalignedbb, List list, Entity entity)
	{
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			
			if(tileEntity !=null){

			if (tileEntity instanceof TileCable)
			{
				TileEntity[] connectable = ((TileCable) tileEntity).getTileSides();

				this.setBlockBounds((float) this.minX, (float) this.minY, (float) this.minZ, (float) this.maxX, (float) this.maxY, (float) this.maxZ);
				super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);

				if (connectable[4] != null)
				{
					this.setBlockBounds(0, (float) this.minY, (float) this.minZ, (float) this.maxX, (float) this.maxY, (float) this.maxZ);
					super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);
				}

				if (connectable[5] != null)
				{
					this.setBlockBounds((float) this.minX, (float) this.minY, (float) this.minZ, 1, (float) this.maxY, (float) this.maxZ);
					super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);
				}

				if (connectable[0] != null)
				{
					this.setBlockBounds((float) this.minX, 0, (float) this.minZ, (float) this.maxX, (float) this.maxY, (float) this.maxZ);
					super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);
				}

				if (connectable[1] != null)
				{
					this.setBlockBounds((float) this.minX, (float) this.minY, (float) this.minZ, (float) this.maxX, 1, (float) this.maxZ);
					super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);
				}

				if (connectable[2] != null)
				{
					this.setBlockBounds((float) this.minX, (float) this.minY, 0, (float) this.maxX, (float) this.maxY, (float) this.maxZ);
					super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);
				}

				if (connectable[3] != null)
				{
					this.setBlockBounds((float) this.minX, (float) this.minY, (float) this.minZ, (float) this.maxX, (float) this.maxY, 1);
					super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);
				}

				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				}
				
			}
			
	}
*/
}
