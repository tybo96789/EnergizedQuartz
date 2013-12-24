package tybo96789.energizedquartz.blocks;
/*
 * GLASS THAT IS STRONG, BUT TO BE USED FOR NUCLEAR REACTORS
 */

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.BlockHelper;
import tybo96789.energizedquartz.helpers.ITextured;

public class ContainmentGlass extends BlockHelper implements ITextured {

	public ContainmentGlass(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setUnlocalizedName("Containment Glass");
		this.setHardness(30F);
		this.setResistance(100F);
		this.setCreativeTab(EQ_Core.tabCustom);
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
