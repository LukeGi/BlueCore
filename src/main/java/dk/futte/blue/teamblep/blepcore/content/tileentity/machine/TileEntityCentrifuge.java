package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import dk.futte.blue.teamblep.blepcore.content.recipe.MachineRecipe;
import dk.futte.blue.teamblep.blepcore.content.recipe.inputs.RecipeInput;
import dk.futte.blue.teamblep.blepcore.content.recipe.outputs.RecipeOutput;
import dk.futte.blue.teamblep.blepcore.content.tileentity.ProgressBar;
import dk.futte.blue.teamblep.blepcore.content.tileentity.ProgressTracker;
import dk.futte.blue.teamblep.blepcore.content.tileentity.core.TileEntityAbstractMachine;

/**
 * @author Blue
 * @author Kelan
 */

public class TileEntityCentrifuge extends TileEntityAbstractMachine
{
    public TileEntityCentrifuge()
    {
        super(MachineData.CENTRIFUGE);
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
    public boolean updateClient()
    {
        return false;
    }

    @Override
    public boolean updateServer()
    {
        return false;
    }
}
