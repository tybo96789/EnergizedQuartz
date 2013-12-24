package tybo96789.energizedquartz.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.ItemHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class EnergizedCoal extends ItemHelper {

	public EnergizedCoal(int par1) {
		super(par1);
		this.setCreativeTab(EQ_Core.tabCustom);
		// TODO Auto-generated constructor stub
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }
}
