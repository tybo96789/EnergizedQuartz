package tybo96789.energizedquartz.ores;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.BlockHelper;
import tybo96789.energizedquartz.helpers.ITextured;

public class OreCinnabar extends BlockHelper implements ITextured {

	public OreCinnabar(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setUnlocalizedName("Cinnabar Ore");
		this.setHardness(6);
		this.setCreativeTab(EQ_Core.tabCustom);
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }

}
