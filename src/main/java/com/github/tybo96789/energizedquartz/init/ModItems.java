package com.github.tybo96789.energizedquartz.init;

import com.github.tybo96789.energizedquartz.helper.RegisterHelper;
import com.github.tybo96789.energizedquartz.item.ItemTester;

import net.minecraft.item.Item;

public class ModItems {
	
	public static Item testerTool = new ItemTester("testerTool");
	
	public static void registerItems()
	{
		RegisterHelper.registerItem(testerTool);
	}

}
