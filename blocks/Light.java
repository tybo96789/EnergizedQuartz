package tybo96789.energizedquartz.blocks;
/*
 * LIGHTID IS A MACHINE THAT REQUIRES 10 UNITS OF ENERGY IN ORDER TO PRODUCE LIGHTID
 */

import java.util.Random;

import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.tiles.TileEnergy;
import tybo96789.energizedquartz.tiles.TileLight;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Light extends Machine
{
    public static final double NEEDED_ENERGY = 10;
    
    private boolean canLight = false;

    public Light(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.setCreativeTab(EQ_Core.tabCustom);
        this.setTickRandomly(true);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileLight(this);
    }
    
    public void setCanLight(boolean state)
    {
    	this.canLight = state;
    	updateState();
    }
    
    private void updateState()
    {
    	if(this.canLight)
    	{
    		this.setLightValue(1.0F);
    	}
    	else
    	{
    		this.setLightValue(0.0F);
    	}
    }
    
}
