package tybo96789.energizedquartz.tiles;
/*
 * TILEGENERATOR IS ALSO A PLACEHOLDER FOR METHODS THAT NEED TO BE OVERRIDED FOR GENERATOR TYPE
 */

import cpw.mods.fml.common.registry.GameRegistry;
import tybo96789.energizedquartz.blocks.Generator;
import tybo96789.energizedquartz.energy.EnergyHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class TileGenerator extends TileEnergy implements IInventory
{
    
    private ItemStack itemStacks[];
    
    private boolean isActive;
    
    public int fuelBurnTime = 0;
    
    public int fuelTimeLeft = 0;
    

    public TileGenerator(Generator gen)
    {
        super(gen);
        this.itemStacks = new ItemStack[1];
        
    }

	@Override
	public int getSizeInventory() {
		return this.itemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.itemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
        if (itemStacks[i] != null)
        {
                if (itemStacks[i].stackSize <= j)
                {
                        ItemStack itemstack = itemStacks[i];
                        itemStacks[i] = null;
                        return itemstack;
                }

                ItemStack itemstack1 = itemStacks[i].splitStack(j);

                if (itemStacks[i].stackSize == 0)
                {
                	itemStacks[i] = null;
                }

                return itemstack1;
        }
        else
        {
                return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (itemStacks[i] != null)
        {
                ItemStack itemstack = itemStacks[i];
                itemStacks[i] = null;
                return itemstack;
        }
        else
        {
                return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		itemStacks[i] = itemstack;

         if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
         {
        	 itemstack.stackSize = getInventoryStackLimit();
         }
		
	}

	@Override
	public String getInvName() {
		return "container.Generator";
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return getItemBurnTime(itemstack) != 0;
	}
	
	/**
     * Reads a tile entity from NBT.
     */
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
	     super.readFromNBT(par1NBTTagCompound);
	     NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
	     itemStacks = new ItemStack[getSizeInventory()];
	
	     for (int i = 0; i < nbttaglist.tagCount(); i++)
	     {
	             NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
	             byte byte0 = nbttagcompound.getByte("Slot");
	
	             if (byte0 >= 0 && byte0 < itemStacks.length)
	             {
	            	 itemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
	             }
	     }
	    
	     fuelTimeLeft = par1NBTTagCompound.getShort("FuelTimeLeft");
	     fuelBurnTime = getItemBurnTime(itemStacks[0]);
	}
	
		/**
	     * Writes a tile entity to NBT.
	     */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
	     super.writeToNBT(par1NBTTagCompound);
	     par1NBTTagCompound.setShort("FuelTimeLeft", (short)fuelTimeLeft);
	     NBTTagList nbttaglist = new NBTTagList();
	
	     for (int i = 0; i < itemStacks.length; i++)
	     {
	             if (itemStacks[i] != null)
	             {
	                     NBTTagCompound nbttagcompound = new NBTTagCompound();
	                     nbttagcompound.setByte("Slot", (byte)i);
	                     itemStacks[i].writeToNBT(nbttagcompound);
	                     nbttaglist.appendTag(nbttagcompound);
	             }
	     }
	
	     par1NBTTagCompound.setTag("Items", nbttaglist);
	     System.out.println("burn:" + fuelTimeLeft);
	}
	public int getCookProgressScaled(int par1)
	{
	         return (fuelBurnTime * par1) / 200;
	}
	
	/**
	 * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
	 * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
	 */
	public int getBurnTimeRemainingScaled(int par1)
	{
	 if (fuelBurnTime == 0)
	 {
		 fuelBurnTime = 200;
	 }
	
	 return (fuelTimeLeft * par1) / fuelBurnTime;
	}
	
	/**
	 * Returns true if the furnace is currently burning
	 */
	public boolean isBurning()
	{
	 return fuelTimeLeft > 0;
	}
	
	public void updateEntity()
	{
	         boolean var1 = this.fuelTimeLeft > 0;
	         boolean var2 = false;
	         if (this.fuelTimeLeft > 0)
	         {
	                 --this.fuelTimeLeft;
	         }
	         if (!this.worldObj.isRemote)
	         {
	                 if (this.fuelTimeLeft == 0)
	                 {
	                         this.fuelBurnTime = this.fuelTimeLeft = getItemBurnTime(this.itemStacks[0]);
	                         if (this.fuelTimeLeft > 0)
	                         {
	                                 var2 = true;
	                                 if (this.itemStacks[0] != null)
	                                 {
	                                         --this.itemStacks[0].stackSize;
	                                         if (this.itemStacks[0].stackSize == 0)
	                                         {
	                                                 Item var3 = this.itemStacks[1].getItem().getContainerItem();
	                                                 this.itemStacks[0] = var3 == null ? null : new ItemStack(var3);
	                                         }
	                                 }
	                         }
	                 }
	                 if (this.isBurning())
	                 {
	                         //TODO Energy is created
	                 }
	                 else
	                 {
	                         this.fuelTimeLeft = 0;
	                 }
	                 if (var1 != this.fuelTimeLeft > 0)
	                 {
	                         var2 = true;
	                         this.validate();
	                 }
	         }
	         boolean check = isActive;
	         isActive = isBurning();
	         if(isActive != check)
	         {
	                 this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	         }
	         if (var2)
	         {
	                 this.onInventoryChanged();
	         }
	}
	
	public static boolean isItemFuel(ItemStack par0ItemStack)
	{
	         return getItemBurnTime(par0ItemStack) > 0;
	
	}
	
	/**
	 * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
	 * fuel
	 */
	public static int getItemBurnTime(ItemStack par1ItemStack)
	{
	 if (par1ItemStack == null)
	 {
	         return 0;
	 }
	
	 int i = par1ItemStack.getItem().itemID;
	
	 if (i < 256 && Block.blocksList[i].blockMaterial == Material.wood)
	 {
	         return 300;
	 }
	
	 if (i == Item.stick.itemID)
	 {
	         return 100;
	 }
	
	 if (i == Item.coal.itemID)
	 {
	         return 1600;
	 }
	 
	 if (i == Item.bucketLava.itemID)
	 {
	         return 20000;
	 }
	
	 if (i == Block.sapling.blockID)
	 {
	         return 100;
	 }
	
	 if (i == Item.blazeRod.itemID)
	 {
	         return 2400;
	 }
	 if (i == Block.dirt.blockID)
	 {
	         return 200;
	 }
	 else
	 {
	         return GameRegistry.getFuelValue(par1ItemStack);
	 }
	}
	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
	 if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
	 {
	         return false;
	 }
	
	 return par1EntityPlayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	}
	
	public boolean isActive()
	{
		return this.isActive;
	}

}
