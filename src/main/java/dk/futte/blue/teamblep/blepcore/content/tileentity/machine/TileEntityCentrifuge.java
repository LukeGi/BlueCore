package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author Blue
 * @author Kelan
 */

public class TileEntityCentrifuge extends TileEntityMachine
{
    public TileEntityCentrifuge()
    {
        super(MachineData.CENTRIFUGE);
    }

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
    protected ProgressTracker createProgressTracker()
    {
        ProgressTracker progressTracker = ProgressTracker.create();
        progressTracker.addProgressBar(new ProgressBar("process_time", 100));
        progressTracker.addProgressBar(new ProgressBar("fuel_time", 1200));
        return progressTracker;
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
