package tybo96789.energizedquartz.items;
/*
 * THIS ITEM IS A RECIPE ITEM THAT WILL BE IMPLEMETED FOR SOME OF THE COMPONETS OF THE CERTAIN BLOCKS
 */

import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.ItemHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class EnergizedQuartz extends ItemHelper
{
    public EnergizedQuartz(int par1)
    {
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
