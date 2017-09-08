package dk.futte.blue.teamblep.blepcore.content.recipe.recipes;

import dk.futte.blue.teamblep.blepcore.content.recipe.MachineRecipe;
import dk.futte.blue.teamblep.blepcore.content.recipe.inputs.RecipeItemInput;
import dk.futte.blue.teamblep.blepcore.content.recipe.outputs.RecipeItemOutput;
import net.minecraft.item.ItemStack;

/**
 * @author Kelan
 */
public class SimpleMachineRecipe extends MachineRecipe<RecipeItemInput, RecipeItemOutput>
{
    public SimpleMachineRecipe(RecipeItemInput input, RecipeItemOutput output)
    {
        super(input, output);
    }
}
