package com.github.tybo96789.energizedquartz;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.github.tybo96789.energizedquartz.init.ModBlocks;
import com.github.tybo96789.energizedquartz.init.ModItems;
import com.github.tybo96789.energizedquartz.proxies.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class EnergizedQuartz {
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	
	@Instance(Reference.MODID)
	public static EnergizedQuartz instance;
	
	public static CreativeTabs testerTab = new CreativeTabs("Energized Quartz"){
		public Item getTabIconItem(){
			return Reference.CREATIVETABICON;
		}
	};
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
			event.getModLog().info("Aloha");
			System.out.println(Reference.MODID);
			ModItems.registerItems();
			ModBlocks.registerBlocks();
			event.getModLog().info("Done init: " + Reference.MODID);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	
}
