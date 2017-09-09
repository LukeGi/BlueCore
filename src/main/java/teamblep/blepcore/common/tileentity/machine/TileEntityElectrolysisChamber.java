package teamblep.blepcore.common.tileentity.machine;

import teamblep.blepcore.common.block.machine.MachineData;
import teamblep.blepcore.common.recipe.MachineRecipe;
import teamblep.blepcore.common.recipe.inputs.RecipeInput;
import teamblep.blepcore.common.recipe.outputs.RecipeOutput;
import teamblep.blepcore.common.tileentity.ProgressBar;
import teamblep.blepcore.common.tileentity.ProgressTracker;
import teamblep.blepcore.common.tileentity.core.TileEntityAbstractMachine;

/**
 * @author Blue
 * @author Kelan
 */

public class TileEntityElectrolysisChamber extends TileEntityAbstractMachine
{
    public TileEntityElectrolysisChamber()
    {
        super(MachineData.ELECTROLYSIS_CHAMBER);
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
    public RecipeInput getCurrentRecipeInput()
    {
        return null;
    }

    @Override
    public RecipeOutput getCurrentRecipeOutput()
    {
        return null;
    }

    @Override
    public MachineRecipe getCurrentRecipe()
    {
        return null;
    }

    @Override
    public ProgressBar getProcessBar()
    {
        return null;
    }

    @Override
    public boolean canProcess(boolean simulate)
    {
        return false;
    }

    @Override
    public boolean isInventoryValid()
    {
        return false;
    }

    @Override
    public void process()
    {

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
        return true;
    }
}
