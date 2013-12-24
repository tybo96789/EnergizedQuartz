package tybo96789.energizedquartz.ores;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.BlockHelper;

public class OreLead extends BlockHelper {

	/*
	 * Ore lead will be used in nuclear based path of energy
	 */
	public OreLead(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setUnlocalizedName("Lead Ore");
		this.setHardness(8);
		this.setCreativeTab(EQ_Core.tabCustom);
		// TODO Auto-generated constructor stub
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }

}
