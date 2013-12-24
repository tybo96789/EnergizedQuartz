package tybo96789.energizedquartz.tiles;

/*
 * TILEVOLTAGE SERVES AS THE FOUNDATION OF HOW ENERGY IS MOVED THOUGHTOUT THE ENERGY NET, AND ALSO IS USED TO MAKE CONNECTIONS FOR SOURCES AND RECEPTORS
 */

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import tybo96789.energizedquartz.EQ_Core;
import tybo96789.energizedquartz.blocks.Cable;
import tybo96789.energizedquartz.blocks.ControllerExtender;
import tybo96789.energizedquartz.blocks.CrystalOscillator;
import tybo96789.energizedquartz.blocks.Generator;
import tybo96789.energizedquartz.blocks.Machine;
import tybo96789.energizedquartz.energy.EQ_Energy;
import tybo96789.energizedquartz.energy.EnergyNet;
import tybo96789.energizedquartz.energy.EnergyPacket;
import tybo96789.energizedquartz.energy.TileEnergyChecker;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

//TODO Code Cleanup 

public class TileEnergy extends TileEntity
{
    private TileEnergy up, down, north, south, east, west, source, receptor;

    public boolean upExists, downExists, northExists, southExists, eastExists, westExists, isSource, isReceptor, isLine, foundSource = false, eNetChange =true, isController, isExtender;

    private EQ_Energy eQ_Energy;
    
    private int debug = 0, updateTimer = 0;
    
    private Block blk;
    
    private TileEnergy lastUsed = null; //TODO Remove Statement
    
    private ForgeDirection lastChecked;
    
    private TileEnergy[] tileSides;  //Stores all the sides of the block that are TileEnergy type if not they are null
    
    private EnergyNet eNetData;
    
    private EnergyPacket packet;
    
    private static boolean forcedUpdate = false;
    
    public TileEnergy(Block block)
    {
    	this.blk =block;
    	this.eQ_Energy = new EQ_Energy(this.blk);
    	init();
    }

    @Override
    public void updateEntity()
    {
    	
    	
		this.up = TileEnergyChecker.getTileVoltageUp(this);

		this.down = TileEnergyChecker.getTileVoltageDown(this);

    	this.north = TileEnergyChecker.getTileVoltageNorth(this);

    	this.south = TileEnergyChecker.getTileVoltageSouth(this);

    	this.east = TileEnergyChecker.getTileVoltageEast(this);

    	this.west = TileEnergyChecker.getTileVoltageWest(this);
        
        this.updateSidesArray(up, down, north, south, east, west);

        if (updateTimer <= 0 || forcedUpdate)
        {
        	//updateSides();
        	/*
        	if(isSource) 
        	{
        		eNet = EnergyNet.mapNetwork(this);
        		this.eNetData = new EnergyNet(eNet);
        	}
        	*/

        	if((isExtender || isController) && this.eNetData == null) this.eNetData = new EnergyNet(this);
        	if((isExtender || isController) && this.eNetData != null) eNetData.update();
            //updateSrc(this); //Updates the receptors sources
           //System.out.println("Updated Sources");
        	//eNetChange = false;
            updateTimer = 80;
            if(forcedUpdate) forcedUpdate = false;
            //Add finds path to source
        }
        
        if(debug <= 0)
        {
        	//debug();
        
            //System.out.println(this.blk.getUnlocalizedName() +": "+ upExists +" "+ downExists +" "+ northExists +" "+ southExists +" "+ eastExists +" "+ westExists);
            //boolean sourceNull = this.source == null;
            //System.out.println("Source Null? "+ sourceNull );
            //boolean receptorNull = this.receptor == null;
        	//System.out.println("Receptor Null? " + receptorNull);
        	//System.out.println("Source: " + isSource);
        	//System.out.println("Receptor: " + isReceptor);
        	//System.out.println("isLine: " + isLine);
        	//System.out.println("isController: " + isController);
        	//System.out.println("ENet Size: " + this.eNetData.getENetSize()); //Calling this causes null pointer exceptions :P
        }
        debug++;
        updateTimer--;
        if(debug >= 120)
        {
        	debug = 0;
        }
    }
    
    private boolean init()
    {
    	isSource = this.blk instanceof Generator;
    	
    	isReceptor = this.blk instanceof Machine;

    	isLine = this.blk instanceof Cable;
    	
    	isController = this.blk instanceof CrystalOscillator;
    	
    	isExtender = this.blk instanceof ControllerExtender;
    	
    	return true;
    }
    
    public void tickTile()
    {
		this.up = TileEnergyChecker.getTileVoltageUp(this);

		this.down = TileEnergyChecker.getTileVoltageDown(this);

    	this.north = TileEnergyChecker.getTileVoltageNorth(this);

    	this.south = TileEnergyChecker.getTileVoltageSouth(this);

    	this.east = TileEnergyChecker.getTileVoltageEast(this);

    	this.west = TileEnergyChecker.getTileVoltageWest(this);
        
        this.updateSidesArray(up, down, north, south, east, west);
    }
    

    
    /*
     * This updates the SidesArray 
     */
    public void updateSidesArray(TileEnergy up, TileEnergy down ,TileEnergy north ,TileEnergy south ,TileEnergy east ,TileEnergy west)
    {
    	tileSides = new TileEnergy[] {up,down,north,south,east,west};
    }
    
    public TileEnergy[] getTileSides()
    {
    	return this.tileSides;
    }
    
    public EnergyNet getEnergyNet()
    {
    	return this.eNetData;
    }
    
    public TileEnergy getIntanceTile()
    {
    	return this;
    }
    
    public TileEnergy getUp() {
		return this.up;
	}

	public TileEnergy getDown() {
		return this.down;
	}

	public TileEnergy getNorth() {
		return this.north;
	}

	public TileEnergy getSouth() {
		return this.south;
	}

	public TileEnergy getEast() {
		return this.east;
	}

	public TileEnergy getWest() {
		return this.west;
	}
	
	public void packet(EnergyPacket ep)
	{
		this.packet = ep;
	}
	
	public EnergyPacket getEnergyPacket()
	{
		return this.packet;
	}
	public static void forceUpdate()
	{
		System.out.println("Forcing Update");
		forcedUpdate = true;
	}

	@Deprecated
	private void debug()
    {
        if (up != null)
        {
            upExists = true;
        }
        else
        {
            upExists = false;
        }

        if (down != null)
        {
            downExists = true;
        }
        else
        {
            downExists = false;
        }

        if (north != null)
        {
            northExists = true;
        }
        else
        {
            northExists = false;
        }

        if (south != null)
        {
            southExists = true;
        }
        else
        {
            southExists = false;
        }

        if (east != null)
        {
            eastExists = true;
        }
        else
        {
            eastExists = false;
        }

        if (west != null)
        {
            westExists = true;
        }
        else
        {
            westExists = false;
        }

        //System.out.println(upExists +" "+ downExists +" "+ northExists +" "+ southExists +" "+ eastExists +" "+ westExists);
    }
}

