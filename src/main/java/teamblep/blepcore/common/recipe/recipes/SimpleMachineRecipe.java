package teamblep.blepcore.common.recipe.recipes;

import teamblep.blepcore.common.recipe.MachineRecipe;
import teamblep.blepcore.common.recipe.inputs.RecipeItemInput;
import teamblep.blepcore.common.recipe.outputs.RecipeItemOutput;

/**
 * @author Kelan
 */
public class SimpleMachineRecipe extends MachineRecipe<RecipeItemInput, RecipeItemOutput, SimpleMachineRecipe>
{
    public SimpleMachineRecipe(RecipeItemInput input, RecipeItemOutput output)
    {
        super(input, output);
    }
}
