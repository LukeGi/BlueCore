package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import dk.futte.blue.teamblep.blepcore.content.tileentity.ProgressBar;
import dk.futte.blue.teamblep.blepcore.content.tileentity.ProgressTracker;

/**
 * @author Blue
 * @author Kelan
 */

public class TileEntitySmelter extends TileEntityMachine
{
    public TileEntitySmelter()
    {
        super(MachineData.SMELTER);
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
        getProgressTracker().tick();
        //need packet handling. TODO first thing tomorrow
    }
}
