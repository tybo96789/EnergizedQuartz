package tybo96789.energizedquartz.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.energy.EnergyProducer;
import tybo96789.energizedquartz.helpers.ITextured;

public class DenceSolarPanel extends SolarPanel implements EnergyProducer,
		ITextured {
	
	public static final double PRODUCEDAMT = 50;

	public DenceSolarPanel(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
    @SideOnly(Side.CLIENT) //TODO Add sided textures
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }

}
