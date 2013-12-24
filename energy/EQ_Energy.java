package tybo96789.energizedquartz.energy;
/*
 * EQ_Energy IS A FORM OF ENERGY
 * PURPOSE: USED FOR ONE TYPE OF ENERGY WITHIN THE MOD
 */

import tybo96789.energizedquartz.blocks.Generator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraft.block.Block;

public class EQ_Energy extends Energy  //TODO Recheck usage
{
    protected static final double LOSS_RATIO = 1.0 / 16;

    protected double currentEnergy;

    protected Block blk;

    protected boolean isSource;
    
    protected boolean isActive = false;

    public EQ_Energy(Block block)
    {
        this.blk = block;
        this.currentEnergy = 0;
        this.isSource = checkIfGenerator();
    }
    
    public boolean hasEnoughPower()
    {
    	return getCurrentEnergy() >= 10;
    }
    
    public void activate()
    {
    		this.isActive = true;
    		this.blk.setLightValue(1.0f);
    }
    
    public void deactivate()
    {
    	this.isActive = false;
    	this.blk.setLightValue(0.0f);
    }

    public double getRatioLoss()
    {
        return LOSS_RATIO;
    }

    public boolean isGenerator()
    {
        return isSource;
    }
    private boolean checkIfGenerator()
    {
        return  this.blk instanceof Generator;
    }
    
    public void setPower(double amt)
    {
    	this.currentEnergy = amt;
    }
}
