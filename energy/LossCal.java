package tybo96789.energizedquartz.energy;
/*
 * A UNIMPLEMENTED FEATURE OF CALCULATING LOSS IN A LINE
 */


import net.minecraft.world.World;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;

public class LossCal
{
    public double powerRemaining(double energyType, double linePower)
    {
        double pwr = linePower - (energyType * linePower);

        if (pwr < 0)
        {
            pwr = 0;
        }

        return pwr;
    }
}
