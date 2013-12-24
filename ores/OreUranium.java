package tybo96789.energizedquartz.ores;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class OreUranium extends BlockHelper {

	/*
	 * Ore lead that is used for future implementation of nuclear energy
	 */
	public OreUranium(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setUnlocalizedName("Uranium Ore");
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
