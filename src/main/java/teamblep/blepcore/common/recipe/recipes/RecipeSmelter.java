package teamblep.blepcore.common.recipe.recipes;

import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.inventory.EnumSlotType;
import teamblep.blepcore.common.inventory.SlotData;
import teamblep.blepcore.common.recipe.MachineRecipe;
import teamblep.blepcore.common.recipe.RecipeHandler;
import teamblep.blepcore.common.recipe.inputs.RecipeItemInput;
import teamblep.blepcore.common.recipe.outputs.RecipeItemOutput;

import java.util.List;

/**
 * @author Kelan
 */
public class RecipeSmelter extends MachineRecipe<RecipeItemInput, RecipeItemOutput, RecipeSmelter>
{
    public RecipeSmelter(RecipeItemInput input, RecipeItemOutput output)
    {
        super(input, output);
    }

    @Override
    public RecipeSmelter getRecipeFor(RecipeItemInput input)
    {
        if (input != null && input.isValid())
        {
            return (RecipeSmelter) RecipeHandler.Recipe.SMELTER.getRecipeFor(input.getInput());
        }
        return null;
    }

    @Override
    public boolean processRecipe(ItemStack[] inventory, List<SlotData> slots, boolean simulate)
    {
        if (getInput().consumeInputs(inventory, slots, simulate))
        {
            if (getOutput().applyOutputs(inventory, slots, EnumSlotType.OUTPUT, simulate))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInventoryValid(RecipeItemInput recipeInput, RecipeItemOutput recipeOutput)
    {
        if (recipeInput != null && recipeInput.isValid())
        {
            if (recipeOutput != null)
            {
                ItemStack currentOutputStack = recipeOutput.getOutput();

                RecipeSmelter recipe = getRecipeFor(recipeInput);

                if (recipe != null)
                {
                    ItemStack recipeResult = recipe.getOutput().getOutput();

                    if (!Utils.isItemStackNull(recipeResult))
                    {
                        if (Utils.isItemStackNull(currentOutputStack) || Utils.canItemStacksMerge(currentOutputStack, recipeResult))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
