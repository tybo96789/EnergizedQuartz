package tybo96789.energizedquartz.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.helpers.ITexturedItem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnergizedWrench extends Item implements ITexturedItem {
	
	private static final int MAXDMG = 300;

	public EnergizedWrench(int par1) {
		super(par1);
		this.setUnlocalizedName("Energized Wrench");
		this.setCreativeTab(EQ_Core.tabCustom);
		this.setMaxStackSize(1);
		this.setMaxDamage(MAXDMG);
		this.setNoRepair();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
	itemIcon = icon.registerIcon(EQ_Core.modid + ":" + getUnlocalizedName());
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
	{
		par3List.add("Da kine wrench");
	}
	
	


}
