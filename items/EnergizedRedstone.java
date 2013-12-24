package tybo96789.energizedquartz.items;
/*
 * ENERGIZEDREDSTONE IS A ITEM THAT IS USED TO CRAFT ENERGIZEDREDSTONEDUST 
 */

import tybo96789.energizedquartz.EQ_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class EnergizedRedstone extends Item
{
    public EnergizedRedstone(int par1)
    {
        super(par1);
        this.setCreativeTab(EQ_Core.tabCustom);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
    }
}
