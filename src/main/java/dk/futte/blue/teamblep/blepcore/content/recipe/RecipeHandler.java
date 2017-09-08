package dk.futte.blue.teamblep.blepcore.content.recipe;

import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.recipe.inputs.RecipeInput;
import dk.futte.blue.teamblep.blepcore.content.recipe.inputs.RecipeItemInput;
import dk.futte.blue.teamblep.blepcore.content.recipe.outputs.RecipeItemByproductOutput;
import dk.futte.blue.teamblep.blepcore.content.recipe.outputs.RecipeItemOutput;
import dk.futte.blue.teamblep.blepcore.content.recipe.outputs.RecipeOutput;
import dk.futte.blue.teamblep.blepcore.content.recipe.recipes.RecipeCrusher;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static dk.futte.blue.teamblep.blepcore.content.item.materials.EnumMaterialType.DUST_GOLD;
import static dk.futte.blue.teamblep.blepcore.content.item.materials.EnumMaterialType.DUST_IRON;
import static dk.futte.blue.teamblep.blepcore.content.item.materials.EnumMaterialType.DUST_STONE;

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
        addCrusherRecipe(new RecipeItemInput(new ItemStack(Blocks.IRON_ORE)), new RecipeItemByproductOutput(DUST_IRON.getItemStack(2), DUST_STONE.getItemStack(), 0.35F));
        addCrusherRecipe(new RecipeItemInput(new ItemStack(Blocks.GOLD_ORE)), new RecipeItemByproductOutput(DUST_GOLD.getItemStack(2), DUST_STONE.getItemStack(), 0.35F));
    }

    public static void addSmelterRecipe(RecipeItemInput input, RecipeItemOutput output)
    {
        Recipe.SMELTER.addRecipe(new MachineRecipe<>(input, output));
    }

    public static void addCrusherRecipe(RecipeItemInput input, RecipeItemByproductOutput output)
    {
        Recipe.CRUSHER.addRecipe(new RecipeCrusher(input, output));
    }

    public static RecipeCrusher getCrusherRecipeFor(ItemStack inputStack)
    {
        if (!Utils.isItemStackNull(inputStack))
        {
            return (RecipeCrusher) Recipe.CRUSHER.getRecipeFor(inputStack);
        }
        return null;
    }

    public static class Recipe<T, I extends RecipeInput<T>, O extends RecipeOutput>
    {
        //TODO: allow for Recipes to be created in preInit do that other mods can add recipe objects.
        public static final Recipe<ItemStack, RecipeItemInput, RecipeItemOutput> SMELTER = new Recipe<>();
        public static final Recipe<ItemStack, RecipeItemInput, RecipeItemByproductOutput> CRUSHER = new Recipe<>();

        private Map<I, MachineRecipe<I, O>> recipes;

        private Recipe()
        {
            this.recipes = new HashMap<>();
        }

        public void addRecipe(MachineRecipe<I, O> recipe)
        {
            if (recipe != null)
            {
                recipes.put(recipe.getInput(), recipe);
            }
        }

        public Map<I, MachineRecipe<I, O>> getRecipeMap()
        {
            return recipes;
        }

        public MachineRecipe<I, O> getRecipeFor(T inputObject)
        {
            for (I recipeInput : recipes.keySet())
            {
                if (recipeInput != null)
                {
                    MachineRecipe<I, O> recipe = recipes.get(recipeInput);

                    if (recipe != null && recipeInput.isInputValid(inputObject))
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
