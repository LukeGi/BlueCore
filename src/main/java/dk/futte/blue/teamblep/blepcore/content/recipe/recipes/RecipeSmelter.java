package dk.futte.blue.teamblep.blepcore.content.recipe.recipes;

import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.inventory.EnumSlotType;
import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;
import dk.futte.blue.teamblep.blepcore.content.recipe.MachineRecipe;
import dk.futte.blue.teamblep.blepcore.content.recipe.RecipeHandler;
import dk.futte.blue.teamblep.blepcore.content.recipe.inputs.RecipeItemInput;
import dk.futte.blue.teamblep.blepcore.content.recipe.outputs.RecipeItemOutput;
import net.minecraft.item.ItemStack;

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
