package teamblep.blepcore.common.recipe;

import teamblep.blepcore.common.inventory.SlotData;
import teamblep.blepcore.common.recipe.inputs.RecipeInput;
import teamblep.blepcore.common.recipe.outputs.RecipeOutput;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * @author Kelan
 */
public class MachineRecipe<I extends RecipeInput, O extends RecipeOutput, R extends MachineRecipe<I, O, R>>
{
    protected I input;
    protected O output;

    public MachineRecipe(I input, O output)
    {
        this.input = input;
        this.output = output;
    }

    public I getInput()
    {
        return input;
    }

    public O getOutput()
    {
        return output;
    }

    public R getRecipeFor(I input)
    {
        return null;
    }

    public boolean processRecipe(ItemStack[] inventory, List<SlotData> slots, boolean simulate)
    {
        return false;
    }

    public boolean isInventoryValid(I recipeInput, O recipeOutput)
    {
        return false;
    }
}
