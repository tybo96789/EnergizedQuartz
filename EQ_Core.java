package tybo96789.energizedquartz;
/*
 * THIS IS THE MAIN FILE THAT FML LOADS DURING STARTUP
 * PURPOSE: TO LOAD AND ADD EACH MOD BLOCK AND ITEMS
 * NOTE: ONLY NEED TO DECLARE MODID ONCE IN THE MAIN CLASS THAT WILL LOAD THE MOD
 */

import java.util.logging.Level;
import java.util.logging.Logger;

import tybo96789.energizedquartz.blocks.Cable;
import tybo96789.energizedquartz.blocks.ContainmentGlass;
import tybo96789.energizedquartz.blocks.ControllerExtender;
import tybo96789.energizedquartz.blocks.CrystalOscillator;
import tybo96789.energizedquartz.blocks.DenceGlass;
import tybo96789.energizedquartz.blocks.EnergizedGlass;
import tybo96789.energizedquartz.blocks.Generator;
import tybo96789.energizedquartz.blocks.Light;
import tybo96789.energizedquartz.blocks.SolarPanel;
import tybo96789.energizedquartz.guis.GuiGenerator;
import tybo96789.energizedquartz.helpers.OreDicHelper;
import tybo96789.energizedquartz.items.EnergizedCoal;
import tybo96789.energizedquartz.items.EnergizedDiamond;
import tybo96789.energizedquartz.items.EnergizedInfusedMix;
import tybo96789.energizedquartz.items.EnergizedQuartz;
import tybo96789.energizedquartz.items.EnergizedRedstone;
import tybo96789.energizedquartz.items.EnergizedWrench;
import tybo96789.energizedquartz.items.LeadIngot;
import tybo96789.energizedquartz.ores.OreLead;
import tybo96789.energizedquartz.ores.OreUranium;
import tybo96789.energizedquartz.tiles.TileCable;
import tybo96789.energizedquartz.tiles.TileCrystalOscillator;
import tybo96789.energizedquartz.tiles.TileEnergy;
import tybo96789.energizedquartz.tiles.TileGenerator;
import tybo96789.energizedquartz.tiles.TileLight;
import tybo96789.energizedquartz.tiles.TileSolarPanel;
import tybo96789_Cobble_Gen.ItemCobbleGen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = EQ_Core.modid, name = "Energized Quartz", version = "0.0.1 Pre-Alpha", dependencies = "required-after:Forge@[9.10,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class EQ_Core
{
    public static final String modid = "EnergizedQuartz";

    public static Block cable;
    public static Block generator;
    public static Block admin_Gen;
    public static Block light;
    public static Block solarPanel;
    public static Block energizedGlass;
    public static Block oreUranium;
    public static Block crystalOscillator;
    public static Block oreLead;
    public static Block denceGlass;
    public static Block containmentGlass;
    public static Block controllerExtender;

    public static final int CableID = 501;
    public static final int GeneratorID = 502;
    public static final int ADMIN_GEN = 503;
    public static final int LIGHTID = 504;
    public static final int solarPanelID = 505;
    public static final int energizedGlassID = 506;
    public static final int oreUraniumID = 507;
    public static final int CrystalOscillatorID = 508;
    public static final int oreLeadID = 509;
    public static final int denceGlassID = 510;
    public static final int containmentGlassID = 511;
    public static final int controllerExtenderID = 512;
    
    
    public static Item energizedRedstone;
    public static Item energizedQuartz;
    public static Item energizedCoal;
    public static Item energizedInfusedMix;
    public static Item energizedWrench;
    public static Item energizedDiamond;
    public static Item leadIngot;
    
    public static final int energizedRedstoneID = 5000;
    public static final int energizedQuartzID = 5001;
    public static final int energizedCoalID = 5002;
    public static final int energizedInfusedMixID = 5003;
    public static final int energizedWrenchID = 5004;
    public static final int energizedDiamondID = 5005;
    public static final int leadIngotID = 5006;
    
    
    public static final CreativeTabs tabCustom = new CreativeTabs("Energized Quartz") {
        public ItemStack getIconItemStack() {
            return new ItemStack(energizedQuartz);
    }
    };;
    
    public static final EventManager eventmgr = new EventManager();
    
    public static final Top top = new Top();
    
    
    /*
     * NOTE ALL IDS WILL HAVE A SYSTEM THAT WILL SET THEIR IDS BASED FROM A CONFIG FILE... FOR NOW IT IS SET STATICLY FOR DEV PURPOSES
     */
    
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = "tybo96789.energizedquartz.ClientProxy", serverSide = "tybo96789.energizedquartz.CommonProxy")
    public static CommonProxy proxy;
    

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    	top.log(Level.INFO, "ALOHA!");
    	top.log(Level.WARNING, "Mod is Pre-Alpha!");
        LanguageRegistry.instance().addStringLocalization("itemGroup.Energized Quartz", "en_US", "Energized Quartz");
        
        top.log(Level.INFO, "Registering Ores");
        GameRegistry.registerWorldGenerator(eventmgr);
        top.log(Level.INFO, "Ores Registration Done!");
        
        addBlocks();
        addItems();
        addRecipes();
        OreDicHelper.init();
        registerTiles();
        proxy.registerRenderers();
        MinecraftForge.EVENT_BUS.register(new GuiGenerator(Minecraft.getMinecraft()));
        
        top.log(Level.INFO, "Finished loading the mod!");
    }

    private static void addBlocks()
    {
    	top.log(Level.INFO, "Preparing Blocks");
    	
        cable = new Cable(CableID, Material.cactus).setUnlocalizedName("Cable").setHardness(0.5f);
        
        GameRegistry.registerBlock(cable, modid + cable.getUnlocalizedName()); //TODO Replace with Item Cable Eqv
        LanguageRegistry.addName(cable, "Cable"); //TODO Replace with Item Cable Eqv
        
        generator = new Generator(GeneratorID, Material.rock).setUnlocalizedName("Energized Generator");
        GameRegistry.registerBlock(generator, modid + generator.getUnlocalizedName());
        LanguageRegistry.addName(generator, "Energized Generator");
        
        //admin_Gen = new Generator(ADMIN_GEN, Material.rock).setUnlocalizedName("Admin Generator").setHardness(30);
        //GameRegistry.registerBlock(admin_Gen, modid + admin_Gen.getUnlocalizedName());
        //LanguageRegistry.addName(admin_Gen, "Admin Generator");
        
        light = new Light(LIGHTID, Material.redstoneLight).setUnlocalizedName("Energized Redstone Light").setHardness(1);
        GameRegistry.registerBlock(light, modid + light.getUnlocalizedName());
        LanguageRegistry.addName(light, "Energized Redstone Light");
        
        solarPanel = new SolarPanel(solarPanelID, Material.rock).setUnlocalizedName("Solar Panel");
        GameRegistry.registerBlock(solarPanel, modid + solarPanel.getUnlocalizedName());
        LanguageRegistry.addName(solarPanel, "Solar Panel");
        
        energizedGlass = new EnergizedGlass(energizedGlassID, Material.redstoneLight).setUnlocalizedName("Energized Glass").setHardness(1);
        GameRegistry.registerBlock(energizedGlass, modid + energizedGlass.getUnlocalizedName());
        LanguageRegistry.addName(energizedGlass, "Energized Glass");
        
        oreUranium = new OreUranium(oreUraniumID,Material.rock);
        GameRegistry.registerBlock(oreUranium, modid + oreUranium.getUnlocalizedName());
        LanguageRegistry.addName(oreUranium, "Uranium Ore");
        
        crystalOscillator = new CrystalOscillator(CrystalOscillatorID,Material.rock);
        GameRegistry.registerBlock(crystalOscillator, modid + crystalOscillator.getUnlocalizedName());
        LanguageRegistry.addName(crystalOscillator, "Crystal Oscillator");
        
        oreLead = new OreLead(oreLeadID,Material.rock);
        GameRegistry.registerBlock(oreLead, modid + oreLead.getUnlocalizedName());
        LanguageRegistry.addName(oreLead, "Lead Ore");
        
        denceGlass = new DenceGlass(denceGlassID,Material.redstoneLight);
        GameRegistry.registerBlock(denceGlass, modid + denceGlass.getUnlocalizedName());
        LanguageRegistry.addName(denceGlass, "Dence Glass");
        
        containmentGlass = new ContainmentGlass(containmentGlassID, Material.rock);
        GameRegistry.registerBlock(containmentGlass, modid + containmentGlass.getUnlocalizedName());
        LanguageRegistry.addName(containmentGlass, "Containment Glass");
        
        controllerExtender = new ControllerExtender(controllerExtenderID, Material.rock);
        GameRegistry.registerBlock(controllerExtender, modid + controllerExtender.getUnlocalizedName());
        LanguageRegistry.addName(controllerExtender, "Controller Extender");
        
        top.log(Level.INFO, "Blocks Loaded");
    }

    private static void addItems()
    {
    	top.log(Level.INFO, "Preparing items");
        energizedRedstone = new EnergizedRedstone(energizedRedstoneID).setUnlocalizedName("Energized Redstone");
        GameRegistry.registerItem(energizedRedstone,energizedRedstone.getUnlocalizedName() , modid);
        LanguageRegistry.addName(energizedRedstone, "Energized Redstone");
        
        energizedQuartz = new EnergizedQuartz(energizedQuartzID).setUnlocalizedName("Energized Quartz");
        GameRegistry.registerItem(energizedQuartz,energizedQuartz.getUnlocalizedName() , modid);
        LanguageRegistry.addName(energizedQuartz, "Energized Quartz");
        
        energizedCoal = new EnergizedCoal(energizedCoalID).setUnlocalizedName("Energized Coal");
        GameRegistry.registerItem(energizedCoal,energizedCoal.getUnlocalizedName() , modid);
        LanguageRegistry.addName(energizedCoal, "Energized Coal");
        
        energizedInfusedMix = new EnergizedInfusedMix(energizedInfusedMixID).setUnlocalizedName("Energized Infused Mix");
        GameRegistry.registerItem(energizedInfusedMix,energizedInfusedMix.getUnlocalizedName() , modid);
        LanguageRegistry.addName(energizedInfusedMix, "Energized Infused Mix");
        
        energizedWrench = new EnergizedWrench(energizedWrenchID);
        GameRegistry.registerItem(energizedWrench, energizedWrench.getUnlocalizedName(), modid);
        LanguageRegistry.addName(energizedWrench, "Energized Wrench");
        
        energizedDiamond = new EnergizedDiamond(energizedDiamondID);
        GameRegistry.registerItem(energizedDiamond,energizedDiamond.getUnlocalizedName(),modid);
        LanguageRegistry.addName(energizedDiamond, "Energized Diamond");
        
        leadIngot = new LeadIngot(leadIngotID);
        GameRegistry.registerItem(leadIngot,leadIngot.getUnlocalizedName(),modid);
        LanguageRegistry.addName(leadIngot, "Lead Ingot");
        top.log(Level.INFO, "Items Loaded");
    }

    private static void addRecipes() //TODO Will likely have a method that will read the recipes from a file, rather then hard coding or hard code them into the Item/Blocks respectively
    {
    	top.log(Level.INFO, "Preparing Recipes");
    	
        GameRegistry.addRecipe(new ItemStack(cable), new Object[]
                               {
                                   "CCC",
                                   "BBB",
                                   "CCC",
                                   'C', Block.glass,'B', new ItemStack(energizedRedstone)
                               }); //TODO Replace with Item Version of Cable
        GameRegistry.addRecipe(new ItemStack(generator), new Object[]
                               {
                                   "CCC",
                                   "CDC",
                                   "CBC",
                                   'C', Block.cobblestone, 'B', new ItemStack(energizedRedstone), 'D', Block.furnaceIdle
                               });
        GameRegistry.addRecipe(new ItemStack(light), new Object[]
                               {
                                   " A ",
                                   "ABA",
                                   " A ",
                                   'A', Block.redstoneLampIdle, 'B', new ItemStack(energizedRedstone)
                               });
        GameRegistry.addRecipe(new ItemStack(energizedRedstone), new Object[]
                               {
                                   "AAA",
                                   "ABA",
                                   "AAA",
                                   'A', new ItemStack(Item.redstone), 'B', Block.blockNetherQuartz
                               }
                              );
        GameRegistry.addShapelessRecipe(new ItemStack(energizedQuartz,4), new Object[]{
        	new ItemStack(energizedRedstone)
        });
        GameRegistry.addShapelessRecipe(new ItemStack(energizedCoal), new Object[]{
        	Block.sand, new ItemStack(energizedQuartz), new ItemStack(Item.coal)
        });
        
        FurnaceRecipes.smelting().addSmelting(energizedInfusedMix.itemID, 0, new ItemStack(energizedGlass), 0.3f);
        
        GameRegistry.addRecipe(new ItemStack(solarPanel), new Object[]{
        	"AAA",
        	"BBB",
        	"CCC",
        	'A', new ItemStack(energizedGlass),'B', Block.daylightSensor,'C', new ItemStack(energizedRedstone)
        });
        GameRegistry.addRecipe(new ItemStack(denceGlass), new Object[]{
        	"AAA",
        	"AAA",
        	"AAA",
        	'A', Block.glass
        });
        GameRegistry.addRecipe(new ItemStack(containmentGlass,8), new Object[]{
        	"AAA",
        	"ABA",
        	"AAA",
        	'A', denceGlass, 'B', leadIngot
        });
        
        top.log(Level.INFO, "Recipes Loaded");
    }
    
    private static void registerTiles()
    {
    	top.log(Level.INFO, "Registering Tiles");
    	GameRegistry.registerTileEntity(TileCable.class, modid +  "TileCable");
    	GameRegistry.registerTileEntity(TileCrystalOscillator.class, modid + "TileCrystalOScillator");
    	GameRegistry.registerTileEntity(TileEnergy.class,  modid + "TileEnergy");
    	GameRegistry.registerTileEntity(TileGenerator.class,  modid + "TileGenerator");
    	GameRegistry.registerTileEntity(TileLight.class,  modid + "TileLight");
    	GameRegistry.registerTileEntity(TileSolarPanel.class, modid +  "TileSolarPanel");
    	top.log(Level.INFO, "Tiles Registered");
    }
}
