package tybo96789.energizedquartz.energy;
/*
 * ENERGY IS A FOUNDATION CLASS THAT HAS THE BASICS OF ENERGY
 * PURPOSE: USED TO EXTEND TO DIFFERENT TYPES OF ENERGYS IN THE MOD
 */

public abstract class Energy
{
    private static double LOSS_RATIO;

    protected double currentEnergy;

    protected boolean isSource;

    public double getRatioLoss()
    {
        return LOSS_RATIO;
    }

    public double getCurrentEnergy()
    {
        return currentEnergy;
    }

    public boolean addCurrent(double amt)
    {
        currentEnergy += amt;
        return true;
    }

    public abstract boolean isGenerator();

    public boolean drawCurrent(double amt)
    {
        currentEnergy -= amt;
        return true;
    }
}
