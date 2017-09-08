package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import dk.futte.blue.teamblep.blepcore.content.recipe.MachineRecipe;
import dk.futte.blue.teamblep.blepcore.content.recipe.RecipeHandler;
import dk.futte.blue.teamblep.blepcore.content.recipe.inputs.RecipeItemInput;
import dk.futte.blue.teamblep.blepcore.content.recipe.outputs.RecipeItemByproductOutput;
import dk.futte.blue.teamblep.blepcore.content.recipe.recipes.RecipeCrusher;
import dk.futte.blue.teamblep.blepcore.content.tileentity.ProgressBar;
import dk.futte.blue.teamblep.blepcore.content.tileentity.ProgressTracker;
import dk.futte.blue.teamblep.blepcore.content.tileentity.core.TileEntityAbstractMachine;
import net.minecraft.item.ItemStack;

/**
 * @author Blue
 * @author Kelan
 */

public class TileEntityCrusher extends TileEntityAbstractMachine
{
    public static final String PROCESS_BAR = "process_time";

    public TileEntityCrusher()
    {
        super(MachineData.CRUSHER);
    }

    @Override
    protected ProgressTracker createProgressTracker()
    {
        ProgressTracker progressTracker = ProgressTracker.create();
        progressTracker.addProgressBar(new ProgressBar(PROCESS_BAR, 100));
        return progressTracker;
    }


    @Override
    public RecipeItemInput getCurrentRecipeInput()
    {
        return new RecipeItemInput(inventory.getStackInSlot(getMachineData().getInventoryContainer().getSlotData("inputSlot").getId()));
    }

    @Override
    public RecipeItemByproductOutput getCurrentRecipeOutput()
    {
        ItemStack output1 = inventory.getStackInSlot(getMachineData().getInventoryContainer().getSlotData("outputSlot1").getId());
        ItemStack output2 = inventory.getStackInSlot(getMachineData().getInventoryContainer().getSlotData("outputSlot2").getId());
        ItemStack byproduct = inventory.getStackInSlot(getMachineData().getInventoryContainer().getSlotData("byproductSlot").getId());

        if (Utils.isItemStackNull(output1) || Utils.isItemStackNull(output2))
        {
            return new RecipeItemByproductOutput(null, byproduct, 0.0F);
        } else
        {
            RecipeCrusher recipe = RecipeHandler.getCrusherRecipeFor(getCurrentRecipeInput().getInput());
            if (recipe != null)
            {
                ItemStack recipeResult = recipe.getOutput().getOutput();
                if (!Utils.isItemStackNull(recipeResult))
                {
                    if (output1.isItemEqual(recipeResult) || output2.isItemEqual(recipeResult))
                    {
                        int stackSize1 = output1.isItemEqual(recipeResult) ? output1.stackSize : 64;
                        int stackSize2 = output2.isItemEqual(recipeResult) ? output2.stackSize : 64;

                        recipeResult.stackSize = Math.min(stackSize1, stackSize2);
                        return new RecipeItemByproductOutput(recipeResult, byproduct, 0.0F);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public boolean canProcess(boolean simulate)
    {
        boolean flag = false;

        ItemStack inputStack = getCurrentRecipeInput().getInput();
        RecipeItemByproductOutput currentRecipeOutput = getCurrentRecipeOutput();

        if (currentRecipeOutput != null)
        {
            ItemStack outputStack = currentRecipeOutput.getOutput();
            ItemStack byproductStack = currentRecipeOutput.getByproduct();

            if (!Utils.isItemStackNull(inputStack))
            {
                MachineRecipe<RecipeItemInput, RecipeItemByproductOutput> recipe = RecipeHandler.Recipe.CRUSHER.getRecipeFor(inputStack);

                ItemStack recipeResult = recipe.getOutput().getOutput();
                ItemStack recipeByproduct = recipe.getOutput().getByproduct();

                if (!Utils.isItemStackNull(recipeResult))
                {
                    if (Utils.isItemStackNull(recipeByproduct) || Utils.isItemStackNull(byproductStack) || recipeByproduct.stackSize + byproductStack.stackSize <= recipeByproduct.getMaxStackSize())
                    {
                        if (Utils.isItemStackNull(outputStack))
                        {
                            flag = true;
                        } else if (outputStack.isItemEqual(recipeResult))
                        {
                            if (outputStack.stackSize + recipeResult.stackSize <= outputStack.getMaxStackSize())
                            {
                                flag = true;
                            }
                        }
                    }
                }
            }
        }

        ProgressBar processBar = getProgressTracker().getProgressBar(PROCESS_BAR);
        if (flag)
        {
            if (!simulate)
            {
                if (!processBar.isDone())
                {
                    processBar.tick();
                }

                if (processBar.isDone())
                {
                    processBar.reset();
                    return true;
                }
            }
            return processBar.isDone();
        } else
        {
            if (!simulate)
            {
                processBar.reset();
            }
            return false;
        }
    }

    @Override
    public void process()
    {
        ItemStack inputStack = getCurrentRecipeInput().getInput();
        if (!Utils.isItemStackNull(inputStack))
        {
            RecipeCrusher recipe = RecipeHandler.getCrusherRecipeFor(inputStack);
            if (recipe != null)
            {
                recipe.processRecipe(getInventory().getItemStacks(), getMachineData().getInventoryContainer().getSlotList(), false);
            }
        }
    }

    @Override
    public boolean updateClient()
    {
        return false;
    }

    @Override
    public boolean updateServer()
    {
        RecipeItemInput recipeInput = getCurrentRecipeInput();

        getProgressTracker().getProgressBar(PROCESS_BAR).setTicksRequired(20);
        if (recipeInput != null)
        {
            if (canProcess(false))
            {
                process();
            }
            return true;
        }
        return false;
    }
}
