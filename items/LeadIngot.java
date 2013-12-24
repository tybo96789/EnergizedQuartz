package tybo96789.energizedquartz.items;

import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.ITexturedItem;
import tybo96789.energizedquartz.helpers.ItemHelper;

public class LeadIngot extends ItemHelper implements ITexturedItem {

	public LeadIngot(int par1) {
		super(par1);
		this.setUnlocalizedName("Lead Ingot");
		this.setCreativeTab(EQ_Core.tabCustom);
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }
}
