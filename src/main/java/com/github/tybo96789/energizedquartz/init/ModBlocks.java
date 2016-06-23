package com.github.tybo96789.energizedquartz.init;

import com.github.tybo96789.energizedquartz.blocks.BlockTester;
import com.github.tybo96789.energizedquartz.helper.RegisterHelper;

import net.minecraft.block.Block;

public class ModBlocks {
	
	public static Block oreAluminum = new BlockTester("oreAluminum");
	
	public static void registerBlocks()
	{
		RegisterHelper.registerBlock(oreAluminum);
	}

}
