package dk.futte.blue.teamblep.blepcore.content.recipe;

import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;
import dk.futte.blue.teamblep.blepcore.content.recipe.inputs.RecipeInput;
import dk.futte.blue.teamblep.blepcore.content.recipe.outputs.RecipeOutput;
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
