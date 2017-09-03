package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author Blue
 * @author Kelan
 */

public class TileEntitySmelter extends TileEntityMachine
{
    @Override
    protected EnergyStorage createBattery()
    {
        return null;
    }

    @Override
    protected ItemStackHandler createInventory()
    {
        return null;
    }

    @Override
    protected FluidTank createTank()
    {
        return null;
    }

    @Override
    public void updateClient()
    {

    }

    @Override
    public void updateServer()
    {

    }
}
