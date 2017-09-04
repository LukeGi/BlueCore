package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import com.sun.istack.internal.NotNull;
import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import dk.futte.blue.teamblep.blepcore.content.tileentity.ProgressTracker;
import dk.futte.blue.teamblep.blepcore.content.tileentity.core.TileEntityTickable;
import net.minecraft.util.EnumFacing;

/**
 * @author Blue
 * @author Kelan
 */

public abstract class TileEntityMachine extends TileEntityTickable
{
    private ProgressTracker progressTracker = createProgressTracker();
    private MachineData machineData;
    private EnumFacing facing = EnumFacing.NORTH;

    public TileEntityMachine(MachineData machineData)
    {
        this.machineData = machineData;
    }

    protected abstract ProgressTracker createProgressTracker();

    public ProgressTracker getProgressTracker()
    {
        return progressTracker;
    }

    @NotNull
    public MachineData getMachineData()
    {
        return machineData;
    }

    public EnumFacing getFacing()
    {
        return facing;
    }

    public void setFacing(EnumFacing facing)
    {
        this.facing = facing;
    }
}
