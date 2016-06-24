package com.github.tybo96789.energizedquartz.init;

import com.github.tybo96789.energizedquartz.helper.RegisterHelper;
import com.github.tybo96789.energizedquartz.item.ChargedQuartz;
import com.github.tybo96789.energizedquartz.item.RedStoneDustFragment;
import com.github.tybo96789.energizedquartz.item.ItemTester;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModItems {
	
	public static Item testerTool = new ItemTester("testerTool");
	
	public static Item chargedQuartz = new ChargedQuartz();
	public static Item redstoneDustFragment = new RedStoneDustFragment();
	
	public static void registerItems()
	{
		RegisterHelper.registerItem(testerTool);
		RegisterHelper.registerItem(chargedQuartz);
		GameRegistry.addRecipe(new ItemStack(chargedQuartz,1), new Object[]{
				"AAA",
				"ABA",
				"AAA",
				'A',Items.redstone,'B',Items.quartz
		});
		RegisterHelper.registerItem(redstoneDustFragment);
		GameRegistry.addShapelessRecipe(new ItemStack(redstoneDustFragment,9), new Object[]{
				Items.redstone
		});
		GameRegistry.addShapedRecipe(new ItemStack(Items.redstone,1), new Object[]{
				"AAA",
				"AAA",
				"AAA",
				'A',redstoneDustFragment
		});
	}

}
