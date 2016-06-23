package com.github.tybo96789.energizedquartz.item;

import com.github.tybo96789.energizedquartz.Reference;
import com.github.tybo96789.energizedquartz.EnergizedQuartz;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTester extends Item {
	
	public ItemTester(String name)
	{
		super();
		this.setCreativeTab(EnergizedQuartz.testerTab);
		this.setUnlocalizedName(name);
		this.setTextureName(Reference.MODID + ":" + name);
	}

}
