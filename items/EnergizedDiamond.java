package tybo96789.energizedquartz.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.ITexturedItem;

public class EnergizedDiamond extends Item implements ITexturedItem {

	public EnergizedDiamond(int par1) {
		super(par1);
		this.setUnlocalizedName("Energized Diamond");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
	itemIcon = icon.registerIcon(EQ_Core.modid + ":" + getUnlocalizedName());
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
	{
		par3List.add("Energized C128");
	}

}
