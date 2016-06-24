package com.github.tybo96789.energizedquartz.item;

import com.github.tybo96789.energizedquartz.EnergizedQuartz;
import com.github.tybo96789.energizedquartz.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import net.minecraft.item.Item;

public abstract class EnergizedQuartzItem extends Item {
	
	private final String ITEM_NAME;
	
	public EnergizedQuartzItem(String name)
	{
		super();
		this.ITEM_NAME = name;
		this.setCreativeTab(EnergizedQuartz.testerTab);
		this.setUnlocalizedName(this.ITEM_NAME);
		this.setTextureName(Reference.MODID + ":" + this.ITEM_NAME);
	}

}
