package dk.futte.blue.teamblep.blepcore.content.recipe.recipes;

import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;
import dk.futte.blue.teamblep.blepcore.content.recipe.MachineRecipe;
import dk.futte.blue.teamblep.blepcore.content.recipe.inputs.RecipeItemInput;
import dk.futte.blue.teamblep.blepcore.content.recipe.outputs.RecipeItemByproductOutput;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * @author Kelan
 */
public class RecipeCrusher extends MachineRecipe<RecipeItemInput, RecipeItemByproductOutput>
{
    public RecipeCrusher(RecipeItemInput input, RecipeItemByproductOutput output)
    {
        super(input, output);
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
}
