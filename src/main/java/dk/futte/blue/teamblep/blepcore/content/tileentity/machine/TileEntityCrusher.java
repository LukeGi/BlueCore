package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
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

public class TileEntityCrusher extends TileEntityAbstractMachine<RecipeItemInput, RecipeItemByproductOutput, RecipeCrusher>
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
            //If the items in either output slot is null, return a RecipeItemByproductOutput with a null output, as this means an item can be outputted to this slot.
            return new RecipeItemByproductOutput(null, byproduct, 0.0F);
        } else
        {
            //Else if neither output slots are empty get the output itemstack for the current input itemstack.
            RecipeCrusher recipe = getCurrentRecipe();
            if (recipe != null)
            {
                ItemStack recipeResult = recipe.getOutput().getOutput();
                if (!Utils.isItemStackNull(recipeResult))
                {
                    if (output1.isItemEqual(recipeResult) || output2.isItemEqual(recipeResult))
                    {
                        //If isInventoryValid recipe result exists and the itemstack in either output slot equals it, return isInventoryValid new RecipeItemByproductOutput with the recipe result, as this means an utem can be outputted to this slot.
                        int stackSize1 = output1.isItemEqual(recipeResult) ? output1.stackSize : output1.getMaxStackSize();
                        int stackSize2 = output2.isItemEqual(recipeResult) ? output2.stackSize : output2.getMaxStackSize();

                        recipeResult.stackSize = Math.min(stackSize1, stackSize2);
                        return new RecipeItemByproductOutput(recipeResult, byproduct, 0.0F);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public RecipeCrusher getCurrentRecipe()
    {
        RecipeItemInput currentRecipeInput = getCurrentRecipeInput();

        if (currentRecipeInput != null)
        {
            ItemStack inputStack = currentRecipeInput.getInput();
            return (RecipeCrusher) RecipeHandler.Recipe.CRUSHER.getRecipeFor(inputStack);
        }

        return null;
    }

    @Override
    public ProgressBar getProcessBar()
    {
        return getProgressTracker().getProgressBar(PROCESS_BAR);
    }
}
