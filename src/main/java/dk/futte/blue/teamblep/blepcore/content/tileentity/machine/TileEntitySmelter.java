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
    public boolean updateClient()
    {
        return false;
    }

    @Override
    public boolean updateServer()
    {
        getProgressTracker().tick();
        //need packet handling. TODO first thing tomorrow
        //TODO: only return true if something changed. Unknown performance impact but it makes sense, and is more efficient.
        return true;
    }
}
