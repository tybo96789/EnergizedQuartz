package com.github.tybo96789.energizedquartz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class EnergizedQuartzBlock extends Block {
	
	private final String BLOCK_NAME;

	protected EnergizedQuartzBlock(String name) {
		super(Material.rock);
		this.BLOCK_NAME = name;
		// TODO Auto-generated constructor stub
	}

}
