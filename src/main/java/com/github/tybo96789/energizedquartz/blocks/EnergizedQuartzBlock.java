package com.github.tybo96789.energizedquartz.blocks;

import com.github.tybo96789.energizedquartz.EnergizedQuartz;
import com.github.tybo96789.energizedquartz.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class EnergizedQuartzBlock extends Block {
	
	private final String BLOCK_NAME;
	

	protected EnergizedQuartzBlock(String name) {
		super(Material.rock);
		this.BLOCK_NAME = name;
		this.setCreativeTab(EnergizedQuartz.testerTab);
		this.setBlockName(this.BLOCK_NAME);
		this.setBlockTextureName(Reference.MODID + ":" + this.BLOCK_NAME);
	}

}
