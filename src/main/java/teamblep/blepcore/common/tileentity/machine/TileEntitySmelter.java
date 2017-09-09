package teamblep.blepcore.common.tileentity.machine;

import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.block.machine.MachineData;
import teamblep.blepcore.common.inventory.SlotData;
import teamblep.blepcore.common.recipe.RecipeHandler;
import teamblep.blepcore.common.recipe.inputs.RecipeItemInput;
import teamblep.blepcore.common.recipe.outputs.RecipeItemOutput;
import teamblep.blepcore.common.recipe.outputs.RecipeOutput;
import teamblep.blepcore.common.recipe.recipes.RecipeSmelter;
import teamblep.blepcore.common.tileentity.ProgressBar;
import teamblep.blepcore.common.tileentity.ProgressTracker;
import teamblep.blepcore.common.tileentity.core.TileEntityAbstractMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * @author Blue
 * @author Kelan
 */

public class TileEntitySmelter extends TileEntityAbstractMachine
{
    public static final String PROCESS_BAR = "process_time";
    public static final String FUEL_BAR = "fuel_time";

    public TileEntitySmelter()
    {
        super(MachineData.SMELTER);
    }

    @Override
    protected ProgressTracker createProgressTracker()
    {
        ProgressTracker progressTracker = ProgressTracker.create();
        progressTracker.addProgressBar(new ProgressBar(PROCESS_BAR, 100, 0, false));
        progressTracker.addProgressBar(new ProgressBar(FUEL_BAR, 1200, 0, true));
        return progressTracker;
    }

    @Override
    public RecipeItemInput getCurrentRecipeInput()
    {
        return new RecipeItemInput(inventory.getStackInSlot(getMachineData().getInventoryContainer().getSlotData("inputSlot").getId()));
    }

    @Override
    public RecipeOutput getCurrentRecipeOutput()
    {
        return new RecipeItemOutput(inventory.getStackInSlot(getMachineData().getInventoryContainer().getSlotData("outputSlot").getId()));
    }

    @Override
    public RecipeSmelter getCurrentRecipe()
    {
        RecipeItemInput currentRecipeInput = getCurrentRecipeInput();

        if (currentRecipeInput != null)
        {
            ItemStack inputStack = currentRecipeInput.getInput();
            return (RecipeSmelter) RecipeHandler.Recipe.SMELTER.getRecipeFor(inputStack);
        }

        return null;
    }

    @Override
    public ProgressBar getProcessBar()
    {
        return getProgressTracker().getProgressBar(PROCESS_BAR);
    }

    public ProgressBar getFuelBar()
    {
        return getProgressTracker().getProgressBar(FUEL_BAR);
    }

    @Override
    public boolean canProcess(boolean simulate)
    {
        float efficiency = 1.5F; //1.5x the output of the vanilla furnace for the same fuel.

        ProgressBar fuelBar = getFuelBar();
        if (fuelBar != null)
        {
            SlotData fuelSlot = getMachineData().getInventoryContainer().getSlotData("fuelSlot");

            if (fuelSlot != null)
            {
                boolean wasBurning = !fuelBar.isDone();

                ItemStack fuelStack = inventory.getStackInSlot(fuelSlot.getId());

                if (tickProgressBar(fuelBar, !simulate, false) && !Utils.isItemStackNull(fuelStack) && TileEntityFurnace.isItemFuel(fuelStack) && isInventoryValid())
                {
                    fuelBar.setTicksRequired((int) (TileEntityFurnace.getItemBurnTime(fuelStack) * ((float) getProcessBar().getTicksRequired() / 200.0F) * efficiency));
                    fuelBar.reset();

                    if (!Utils.addStackSize(fuelStack, -1))
                    {
                        inventory.setStackInSlot(fuelSlot.getId(), null);
                    }
                }

                if (wasBurning)
                {
                    return super.canProcess(simulate);
                }
            }
        }
        return false;
    }
}
