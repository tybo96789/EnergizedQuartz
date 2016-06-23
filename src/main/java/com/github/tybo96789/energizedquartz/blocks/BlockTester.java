package com.github.tybo96789.energizedquartz.blocks;

import com.github.tybo96789.energizedquartz.Reference;
import com.github.tybo96789.energizedquartz.EnergizedQuartz;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTester extends Block {
	
	public BlockTester(String name)
	{
		super(Material.rock);
		this.setCreativeTab(EnergizedQuartz.testerTab);
		this.setBlockName(name);
		this.setBlockTextureName(Reference.MODID + ":" + name);
	}

}
