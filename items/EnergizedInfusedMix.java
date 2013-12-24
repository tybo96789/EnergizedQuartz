package tybo96789.energizedquartz.items;
/*
 * THIS CLASS SERVES AS A PLACEHOLDER FOR THE ITEM ENERGIZED INFUSED MIX 
 */

import java.util.List;

import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.ITextured;
import tybo96789.energizedquartz.helpers.ITexturedItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnergizedInfusedMix extends Item  implements ITexturedItem{

	public EnergizedInfusedMix(int par1) {
		super(par1);
        this.setCreativeTab(EQ_Core.tabCustom);
	}


	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(EQ_Core.modid + ":" + (this.getUnlocalizedName()));
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
	{
		par3List.add("Smelt This");
	}

}