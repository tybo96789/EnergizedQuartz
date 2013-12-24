package tybo96789.energizedquartz.energy;
/*
 * VOLTAGEHANDER IS TO DEFINE A SET OF RULES FOR A PARTICULAR TYPE
 * PURPOSE: NONE YET... WILL LIKELY DELETE LATER
 */

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;

@Deprecated //MARKED FOR POSSIBLE DELETION
public final class EnergyHandler
{
    public static enum Type
    {
        MACHINE, LINE, STORAGE, GENERATOR;

        public boolean canTakeEnergyFromLine()
        {
            switch (this)
            {
                case MACHINE:
                case STORAGE:
                    return true;

                default:
                    return false;
            }
        }

        public boolean canStoreEnergyFromLine()
        {
            switch (this)
            {
                case STORAGE:
                    return true;

                default:
                    return false;
            }
        }
        public boolean canGenerateEnergy()
        {
            switch (this)
            {
                case GENERATOR:
                    return true;

                default:
                    return false;
            }
        }
    }
}