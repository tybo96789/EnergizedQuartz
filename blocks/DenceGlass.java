package tybo96789.energizedquartz.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.BlockHelper;
import tybo96789.energizedquartz.helpers.ITextured;

public class DenceGlass extends BlockHelper implements ITextured {

	public DenceGlass(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setHardness(3.5F);
		this.setResistance(5F);
		this.setCreativeTab(EQ_Core.tabCustom);
		this.setUnlocalizedName("Dence Glass");
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
