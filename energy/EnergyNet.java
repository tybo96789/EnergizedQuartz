package tybo96789.energizedquartz.energy;
/*
 * THE PURPOSE OF THIS CLASS IS TO MAP OUT THE NETWORK BY CHECKING IF ITS A VALID TILEENERGY
 * WARNING: THERE IS A CASTING CRASH ISSUE @SEE heatMap() ***FIXED**** 11/24/13
 */
import java.util.ArrayList;
import java.util.logging.Level;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import tybo96789.energizedquartz.Top;
import tybo96789.energizedquartz.tiles.TileEnergy;


//TODO Code Cleanup
public class EnergyNet {
	
	private ArrayList<TileEnergy> net, existingNet, sources = new ArrayList<TileEnergy>(), receptors = new ArrayList<TileEnergy>();
	
	private TileEnergy[] tile;
	
	private boolean isConnectedToExistingNetwork = false;
	
	private TileEnergy controller;
	
	private int nextUpdate = 600;
	
	public static int numOfControl = 0; //TODO remove Debug statement
	
	private int numID = 0; //Used for debug purposes
	
	private static boolean forcedUpdate = false; //Defined Globally to force update of the energynet 
	
	public EnergyNet(TileEnergy tile)
	{
		this.controller = tile;
		addSelfToNetwork(this.controller);
	}
	
	public void update()
	{
		if(nextUpdate <= 0 || forcedUpdate){
			if(this.net != null) this.net.clear();
			this.net = heatMap(this.controller);
			if(this.receptors != null) this.receptors.clear();
			if(this.sources != null) this.sources.clear();
			this.findReceptorsSouces();
			this.sendPackets();
			if(forcedUpdate) forcedUpdate = false;
			System.out.println("Network " + this.numID + " size: Program Reports: " + this.net.size());
			System.out.println("---Num of Sources: "+ this.sources.size()+"  Num of Receptors: " + this.receptors.size());
			System.out.println("Number of controllers: " + numOfControl);
		}
		nextUpdate--;
		System.out.println("Next update in : " + nextUpdate/20.0 + " secounds or " + nextUpdate + "ticks");
		if(nextUpdate <= 0) nextUpdate = 600;
		//debug();
	}
	
	/*
	 * This method will create a new ArrayList that will contain the TileEnergy that it was passed
	 */
	public void addSelfToNetwork(TileEnergy self)
	{
		System.out.println("Making New EnergyNet!");
		System.out.println("New Network id: " + this.numID);
		net = new ArrayList<TileEnergy>();
		this.net.add(self);
		this.isConnectedToExistingNetwork = true;
	}
	/*
	 * This method takes the two ArrayList's and merges them together
	 */
	@Deprecated
	public static ArrayList<TileEnergy> mergeENet(ArrayList<TileEnergy> controllerNet, ArrayList<TileEnergy> newNet)
	{
		if(newNet == null) newNet = new ArrayList<TileEnergy>();
		
		ArrayList<TileEnergy> temp = new ArrayList<TileEnergy>();
		
		for(TileEnergy i : controllerNet)
		{
			temp.add(i);
		}
		for(TileEnergy i : newNet)
		{
			temp.add(i);
		}
		
		return temp;
		
	}
	
	/*
	 * This method will attempt to map the network of the energy net.....
	 * NOTE: BUGGY
	 */
	@Deprecated
	public static ArrayList<TileEnergy> mapNetwork(TileEnergy tile, ForgeDirection from)
	{
		ArrayList<TileEnergy> storeWhere = new ArrayList<TileEnergy>();
		ForgeDirection lastChecked = from;
		TileEnergy temp = tile;
		
		System.out.println("Mapping Network");
		
		do{
			
			if(temp.getUp() != null && lastChecked != ForgeDirection.UP)
			{
				storeWhere.add(temp.getUp());
				lastChecked = ForgeDirection.DOWN;
				
				System.out.println("Adding Up");
				
				temp = temp.getUp();
			}
			 if(temp.getDown() != null && lastChecked != ForgeDirection.DOWN)
			{
				storeWhere.add(temp.getDown());
				lastChecked = ForgeDirection.UP;
				
				System.out.println("Adding Down");
				
				temp = temp.getDown();
			}
			 if(temp.getNorth() != null && lastChecked != ForgeDirection.NORTH)
			{
				storeWhere.add(temp.getNorth());
				lastChecked = ForgeDirection.SOUTH;
				
				System.out.println("Adding North");
				
				temp = temp.getNorth();
			}
			 if(temp.getSouth() != null && lastChecked != ForgeDirection.SOUTH)
			{
				storeWhere.add(temp.getSouth());
				lastChecked = ForgeDirection.NORTH;
				
				System.out.println("Adding South");
				
				temp = temp.getSouth();
			}
			 if(temp.getEast() != null && lastChecked != ForgeDirection.EAST)
			{
				storeWhere.add(temp.getEast());
				lastChecked = ForgeDirection.WEST;
				
				System.out.println("Adding East");
				
				temp = temp.getEast();
			}
			 if(temp.getWest() != null && lastChecked != ForgeDirection.WEST)
			{
				storeWhere.add(temp.getWest());
				lastChecked = ForgeDirection.EAST;
				
				System.out.println("Adding West");
				
				temp = temp.getWest();
			}
			if(storeWhere.contains(temp)) break;

			
		}while((tile.getUp() != null || tile.getDown() != null || tile.getNorth() != null || tile.getSouth() != null || tile.getEast() != null || tile.getWest() != null));
		
		System.out.println();
		
		return storeWhere;
	}
	/*
	 * This version of heatMap works well except they only can map straight lines
	 * NOTE: There is a offset of 6 -----FIXED------
	 * WARNING: ANYTHING THAT IS A TILEENTITY WILL CAUSE AN CRASH THAT IS IN THE DIRECT LINE OF SIGHT OF THE CONTROLLER ****FIXED**** 11/24/13 @10:26PM HST
	 * TODO Rewrite so energynet can be mapped on all sides no matter where it is at
	 */
	public static ArrayList<TileEnergy> heatMap(TileEnergy controller)
	{
		ArrayList<TileEnergy> loc = new ArrayList<TileEnergy>();
		TileEnergy workingOn = controller;
		int x = controller.xCoord, y = controller.yCoord, z = controller.zCoord;
		boolean isDone = false;
		boolean broken = false;
		int offset = 1;
		do{
			
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x + offset,y,z);
			if(temp != null)
			{
				try{
				TileEnergy op = (TileEnergy) temp;
				if(op.isController || op.isLine || op.isSource || op.isReceptor)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					offset++;
				}
				}catch(Exception e)
				{
					Top.log(Level.WARNING, "Not a valid TileEnergy! Breaking!");
					break;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		
		offset = 1;
		isDone = false;
		do{
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x,y + offset,z);
			if(temp != null)
			{
				try{
				TileEnergy op = (TileEnergy) temp;
				if(op.isController || op.isLine || op.isSource || op.isReceptor)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					offset++;
				}
				}catch(Exception e)
				{
					Top.log(Level.WARNING, "Not a valid TileEnergy! Breaking!");
					break;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		
		offset = 1;
		isDone = false;
		do{
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x,y,z + offset);
			if(temp != null)
			{
				try{
				TileEnergy op = (TileEnergy) temp;
				if(op.isController || op.isLine || op.isSource || op.isReceptor)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					offset++;
				}
				}catch(Exception e)
				{
					Top.log(Level.WARNING, "Not a valid TileEnergy! Breaking!");
					break;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		
		offset = 1;
		isDone = false;
		do{
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x - offset,y,z);
			if(temp != null)
			{
				try{
				TileEnergy op = (TileEnergy) temp;
				if(op.isController || op.isLine || op.isSource || op.isReceptor)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					offset++;
				}
				}catch(Exception e)
				{
					Top.log(Level.WARNING, "Not a valid TileEnergy! Breaking!");
					break;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		
		offset = 1;
		isDone = false;
		do{
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x,y - offset,z);
			if(temp != null)
			{
				try{
				TileEnergy op = (TileEnergy) temp;
				if(op.isController || op.isLine || op.isSource || op.isReceptor)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					offset++;
				}
				}catch(Exception e)
				{
					Top.log(Level.WARNING, "Not a valid TileEnergy! Breaking!");
					break;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		
		offset = 1;
		isDone = false;
		do{
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x,y,z - offset);
			if(temp != null)
			{
				try{
				TileEnergy op = (TileEnergy) temp;
				if(op.isController || op.isLine || op.isSource || op.isReceptor)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					offset++;
				}
				}catch(Exception e)
				{
					Top.log(Level.WARNING, "Not a valid TileEnergy! Breaking!");
					break;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		
		return loc;
	}
	
	/*
	 * This version causes overflow crashes
	 */
	@Deprecated
	public static ArrayList<TileEnergy> heatMap(TileEnergy controller, ForgeDirection lastChecked)
	{
		ArrayList<TileEnergy> loc = new ArrayList<TileEnergy>();
		ArrayList<TileEnergy> meh = null;
		TileEnergy workingOn = controller;
		int x = controller.xCoord, y = controller.yCoord, z = controller.zCoord;
		boolean isDone = false;
		int offset = 0;
		if(lastChecked != ForgeDirection.WEST){
		do{
			
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x + offset,y,z);
			if(temp != null)
			{
				if(temp instanceof TileEnergy)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					
					for(TileEnergy i : ((TileEnergy) temp).getTileSides())
					{
						if(i != null)
						{
							if(i instanceof TileEnergy)
							{
								meh = heatMap(i, ForgeDirection.WEST);
								if(meh != null)
								{
									for(TileEnergy t : meh)
									{
										if(!loc.contains(t)) loc.add(t);
									}
								}
							}
						}
					}
					offset++;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		}
		
		offset = 0;
		isDone = false;
		if(meh != null) meh.clear();
		if(lastChecked != ForgeDirection.DOWN){
		do{
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x,y + offset,z);
			if(temp != null)
			{
				if(temp instanceof TileEnergy)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					for(TileEnergy i : ((TileEnergy) temp).getTileSides())
					{
						if(i != null)
						{
							if(i instanceof TileEnergy)
							{
								meh = heatMap(i,ForgeDirection.DOWN);
								if(meh != null)
								{
									for(TileEnergy t : meh)
									{
										if(!loc.contains(t)) loc.add(t);
									}
								}
							}
						}
					}
					offset++;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		}
		
		offset = 0;
		isDone = false;
		if(meh != null) meh.clear();
		if(lastChecked != ForgeDirection.NORTH){
		do{
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x,y,z + offset);
			if(temp != null)
			{
				if(temp instanceof TileEnergy)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					for(TileEnergy i : ((TileEnergy) temp).getTileSides())
					{
						if(i != null)
						{
							if(i instanceof TileEnergy)
							{
								meh = heatMap(i,ForgeDirection.NORTH);
								if(meh != null)
								{
									for(TileEnergy t : meh)
									{
										if(!loc.contains(t)) loc.add(t);
									}
								}
							}
						}
					}
					offset++;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		}
		
		offset = 0;
		isDone = false;
		if(meh != null) meh.clear();
		if(lastChecked != ForgeDirection.EAST){
		do{
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x - offset,y,z);
			if(temp != null)
			{
				if(temp instanceof TileEnergy)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					for(TileEnergy i : ((TileEnergy) temp).getTileSides())
					{
						if(i != null)
						{
							if(i instanceof TileEnergy)
							{
								meh = heatMap(i,ForgeDirection.EAST);
								if(meh != null)
								{
									for(TileEnergy t : meh)
									{
										if(!loc.contains(t)) loc.add(t);
									}
								}
							}
						}
					}
					offset++;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		}
		
		offset = 0;
		isDone = false;
		if(meh != null) meh.clear();
		if(lastChecked != ForgeDirection.UP){
		do{
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x,y - offset,z);
			if(temp != null)
			{
				if(temp instanceof TileEnergy)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					for(TileEnergy i : ((TileEnergy) temp).getTileSides())
					{
						if(i != null)
						{
							if(i instanceof TileEnergy)
							{
								meh = heatMap(i,ForgeDirection.UP);
								if(meh != null)
								{
									for(TileEnergy t : meh)
									{
										if(!loc.contains(t)) loc.add(t);
									}
								}
							}
						}
					}
					offset++;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		}
		
		offset = 0;
		isDone = false;
		if(meh != null) meh.clear();
		if(lastChecked != ForgeDirection.SOUTH){
		do{
			TileEntity temp =  controller.worldObj.getBlockTileEntity(x,y,z - offset);
			if(temp != null)
			{
				if(temp instanceof TileEnergy)
				{
					((TileEnergy) temp).tickTile();
					loc.add((TileEnergy) temp);
					for(TileEnergy i : ((TileEnergy) temp).getTileSides())
					{
						if(i != null)
						{
							if(i instanceof TileEnergy)
							{
								meh = heatMap(i,ForgeDirection.SOUTH);
								if(meh != null)
								{
									for(TileEnergy t : meh)
									{
										if(!loc.contains(t)) loc.add(t);
									}
								}
							}
						}
					}
					offset++;
				}
			}
			else
			{
				isDone = true;
			}
		}while(!isDone);
		}
		return loc;
	}
	
	
	public boolean isConnectedToNet()
	{
		return this.isConnectedToExistingNetwork;
	}
	
	public ArrayList<TileEnergy> getEnergyNetArray()
	{
		return this.net;
	}
	
	public EnergyNet getEnergyNet()
	{
		return this;
	}
	
	public int getENetSize()
	{
		return this.net.size();
	}
	
	public static void forceUpdate()
	{
		forcedUpdate =true;
		System.out.println("Update Forced!");
	}
	
	public void findReceptorsSouces()
	{
		for(TileEnergy i : this.net)
		{
			if(i != null){
			if(i.isSource) sources.add(i);
			if(i.isReceptor) receptors.add(i);
			}
		}
	}
	
	private void debug()
	{
		for(TileEnergy i : this.net)
		{
			System.out.print(i.toString());
		}
	}
	
	public void sendPackets()
	{
		for(TileEnergy recept : this.receptors)
		{
			for(TileEnergy src : this.sources)
			{
				new EnergyPacket(src,recept);
			}
		}
	}

}
