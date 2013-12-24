package tybo96789.energizedquartz.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.BlockHelper;
import tybo96789.energizedquartz.helpers.MaterialHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class EnergizedGlass extends BlockHelper {

	public EnergizedGlass(int par1, Material redstonelight) {
		
		super(par1, redstonelight);
		this.setCreativeTab(EQ_Core.tabCustom);
		this.setLightValue(0.9375F);
	}
	
	  @SideOnly(Side.CLIENT)
	    public void registerIcons(IconRegister par1IconRegister)
	    {
	        this.blockIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
	    }
	  @Override
	    public boolean isOpaqueCube()
	    {
	        return false;
	    }

}
