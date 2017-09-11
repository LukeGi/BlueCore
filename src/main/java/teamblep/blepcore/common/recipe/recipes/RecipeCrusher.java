package teamblep.blepcore.common.recipe.recipes;

import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.inventory.SlotData;
import teamblep.blepcore.common.recipe.MachineRecipe;
import teamblep.blepcore.common.recipe.RecipeHandler;
import teamblep.blepcore.common.recipe.inputs.RecipeItemInput;
import teamblep.blepcore.common.recipe.outputs.RecipeItemByproductOutput;

import java.util.List;

/**
 * @author Kelan
 */
public class RecipeCrusher extends MachineRecipe<RecipeItemInput, RecipeItemByproductOutput, RecipeCrusher>
{
    public RecipeCrusher(RecipeItemInput input, RecipeItemByproductOutput output)
    {
        super(input, output);
    }

    @Override
    public RecipeCrusher getRecipeFor(RecipeItemInput input)
    {
        if (input != null && input.isValid())
        {
            return (RecipeCrusher) RecipeHandler.Recipe.CRUSHER.getRecipeFor(input.getInput());
        }
        return null;
    }

    @Override
    public boolean processRecipe(ItemStack[] inventory, List<SlotData> slots, boolean simulate)
    {
        if (getInput().consumeInputs(inventory, slots, simulate))
        {
            if (getOutput().applyOutputs(inventory, slots, null, simulate))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInventoryValid(RecipeItemInput recipeInput, RecipeItemByproductOutput recipeOutput)
    {
        if (recipeInput != null && recipeInput.isValid())
        {
            if (recipeOutput != null)
            {
                ItemStack currentOutputStack = recipeOutput.getOutput();
                ItemStack currentByproductStack = recipeOutput.getByproduct();

                RecipeCrusher recipe = getRecipeFor(recipeInput);

                if (recipe != null)
                {
                    ItemStack recipeResult = recipe.getOutput().getOutput();
                    ItemStack recipeByproduct = recipe.getOutput().getByproduct();

                    if (!Utils.isItemStackNull(recipeResult))
                    {
                        if (Utils.isItemStackNull(recipeByproduct) || Utils.isItemStackNull(currentByproductStack) || recipeByproduct.stackSize + currentByproductStack.stackSize <= recipeByproduct.getMaxStackSize())
                        {
                            if (Utils.isItemStackNull(currentOutputStack) || Utils.canItemStacksMerge(currentOutputStack, recipeResult))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
