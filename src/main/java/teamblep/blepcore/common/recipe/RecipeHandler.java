package teamblep.blepcore.common.recipe;

import teamblep.blepcore.common.recipe.inputs.RecipeInput;
import teamblep.blepcore.common.recipe.inputs.RecipeItemInput;
import teamblep.blepcore.common.recipe.outputs.RecipeItemByproductOutput;
import teamblep.blepcore.common.recipe.outputs.RecipeItemOutput;
import teamblep.blepcore.common.recipe.outputs.RecipeOutput;
import teamblep.blepcore.common.recipe.recipes.RecipeCrusher;
import teamblep.blepcore.common.recipe.recipes.RecipeSmelter;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.util.HashMap;
import java.util.Map;

import static teamblep.blepcore.common.item.materials.EnumMaterialType.*;

/**
 * @author Kelan
 */
public class RecipeHandler
{
    private RecipeHandler()
    {
    }

    public static void initRecipes()
    {
        //SMELTER
        for (ItemStack input : FurnaceRecipes.instance().getSmeltingList().keySet()) addSmelterRecipe(new RecipeItemInput(input), new RecipeItemOutput(FurnaceRecipes.instance().getSmeltingList().get(input)));

        //CRUSHER
        addCrusherRecipe(new RecipeItemInput(new ItemStack(Blocks.IRON_ORE)), new RecipeItemByproductOutput(DUST_IRON.getItemStack(2), DUST_STONE.getItemStack(), 0.35F));
        addCrusherRecipe(new RecipeItemInput(new ItemStack(Blocks.GOLD_ORE)), new RecipeItemByproductOutput(DUST_GOLD.getItemStack(2), DUST_STONE.getItemStack(), 0.35F));

    }

    public static void addSmelterRecipe(RecipeItemInput input, RecipeItemOutput output)
    {
        Recipe.SMELTER.addRecipe(new RecipeSmelter(input, output));
    }

    public static void addCrusherRecipe(RecipeItemInput input, RecipeItemByproductOutput output)
    {
        Recipe.CRUSHER.addRecipe(new RecipeCrusher(input, output));
    }

    public static class Recipe<T, I extends RecipeInput<T>, O extends RecipeOutput>
    {
        //TODO: allow for Recipes to be created in preInit do that other mods can add recipe objects.
        public static final Recipe<ItemStack, RecipeItemInput, RecipeItemOutput> SMELTER = new Recipe<>();
        public static final Recipe<ItemStack, RecipeItemInput, RecipeItemByproductOutput> CRUSHER = new Recipe<>();

        private Map<I, MachineRecipe<I, O, ?>> recipes;

        private Recipe()
        {
            this.recipes = new HashMap<>();
        }

        public void addRecipe(MachineRecipe<I, O, ?> recipe)
        {
            if (recipe != null)
            {
                recipes.put(recipe.getInput(), recipe);
            }
        }

        public Map<I, MachineRecipe<I, O, ?>> getRecipeMap()
        {
            return recipes;
        }

        public MachineRecipe<I, O, ?> getRecipeFor(T inputObject)
        {
            for (I recipeInput : recipes.keySet())
            {
                if (recipeInput != null)
                {
                    MachineRecipe<I, O, ?> recipe = recipes.get(recipeInput);

                    if (recipe != null && recipeInput.isInputStackValid(inputObject))
                    {
                        return recipe;
                    }
                }
            }

            return null;
        }

        public boolean hasRecipeFor(T inputObject)
        {
            return getRecipeFor(inputObject) != null;
        }
    }
}
