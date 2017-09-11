package teamblep.blepcore.common.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.block.material.BlockMetal;
import teamblep.blepcore.common.block.material.BlockOre;
import teamblep.blepcore.common.recipe.inputs.RecipeInput;
import teamblep.blepcore.common.recipe.inputs.RecipeItemInput;
import teamblep.blepcore.common.recipe.outputs.RecipeItemByproductOutput;
import teamblep.blepcore.common.recipe.outputs.RecipeItemOutput;
import teamblep.blepcore.common.recipe.outputs.RecipeOutput;
import teamblep.blepcore.common.recipe.recipes.RecipeCrusher;
import teamblep.blepcore.common.recipe.recipes.RecipeSmelter;

import java.util.HashMap;
import java.util.Map;

import static teamblep.blepcore.common.item.materials.ItemMaterial.Variants.*;

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
        for (ItemStack input : FurnaceRecipes.instance().getSmeltingList().keySet())
            addSmelterRecipe(new RecipeItemInput(input), new RecipeItemOutput(FurnaceRecipes.instance().getSmeltingList().get(input)), true);
        addSmelterRecipe(new RecipeItemInput(BlockOre.Variants.COPPER.getItemStack()), new RecipeItemOutput(COPPER_INGOT.getItemStack()));
        addSmelterRecipe(new RecipeItemInput(BlockOre.Variants.TIN.getItemStack()), new RecipeItemOutput(TIN_INGOT.getItemStack()));
        addSmelterRecipe(new RecipeItemInput(BlockOre.Variants.LEAD.getItemStack()), new RecipeItemOutput(LEAD_INGOT.getItemStack()));
        addSmelterRecipe(new RecipeItemInput(BlockOre.Variants.SILVER.getItemStack()), new RecipeItemOutput(SILVER_INGOT.getItemStack()));

        addSmelterRecipe(new RecipeItemInput(IRON_DUST.getItemStack()), new RecipeItemOutput(new ItemStack(Items.IRON_INGOT)));
        addSmelterRecipe(new RecipeItemInput(GOLD_DUST.getItemStack()), new RecipeItemOutput(new ItemStack(Items.GOLD_INGOT)));
        addSmelterRecipe(new RecipeItemInput(COPPER_DUST.getItemStack()), new RecipeItemOutput(COPPER_INGOT.getItemStack()));
        addSmelterRecipe(new RecipeItemInput(TIN_DUST.getItemStack()), new RecipeItemOutput(TIN_INGOT.getItemStack()));
        addSmelterRecipe(new RecipeItemInput(LEAD_DUST.getItemStack()), new RecipeItemOutput(LEAD_INGOT.getItemStack()));
        addSmelterRecipe(new RecipeItemInput(SILVER_DUST.getItemStack()), new RecipeItemOutput(SILVER_INGOT.getItemStack()));

        //CRUSHER
        ItemStack stoneDust = STONE_DUST.getItemStack();
        addCrusherRecipe(new RecipeItemInput(new ItemStack(Blocks.IRON_ORE)), new RecipeItemByproductOutput(IRON_DUST.getItemStack(2), stoneDust, 0.35F));
        addCrusherRecipe(new RecipeItemInput(new ItemStack(Blocks.GOLD_ORE)), new RecipeItemByproductOutput(GOLD_DUST.getItemStack(2), stoneDust, 0.35F));
        addCrusherRecipe(new RecipeItemInput(BlockOre.Variants.COPPER.getItemStack()), new RecipeItemByproductOutput(COPPER_DUST.getItemStack(2), stoneDust, 0.35F));
        addCrusherRecipe(new RecipeItemInput(BlockOre.Variants.TIN.getItemStack()), new RecipeItemByproductOutput(TIN_DUST.getItemStack(2), stoneDust, 0.35F));
        addCrusherRecipe(new RecipeItemInput(BlockOre.Variants.LEAD.getItemStack()), new RecipeItemByproductOutput(LEAD_DUST.getItemStack(2), stoneDust, 0.35F));
        addCrusherRecipe(new RecipeItemInput(BlockOre.Variants.SILVER.getItemStack()), new RecipeItemByproductOutput(SILVER_DUST.getItemStack(2), stoneDust, 0.35F));

        addCrusherRecipe(new RecipeItemInput(new ItemStack(Items.IRON_INGOT)), new RecipeItemByproductOutput(IRON_DUST.getItemStack()));
        addCrusherRecipe(new RecipeItemInput(new ItemStack(Items.GOLD_INGOT)), new RecipeItemByproductOutput(GOLD_DUST.getItemStack()));
        addCrusherRecipe(new RecipeItemInput(COPPER_INGOT.getItemStack()), new RecipeItemByproductOutput(COPPER_DUST.getItemStack()));
        addCrusherRecipe(new RecipeItemInput(TIN_INGOT.getItemStack()), new RecipeItemByproductOutput(TIN_DUST.getItemStack()));
        addCrusherRecipe(new RecipeItemInput(LEAD_INGOT.getItemStack()), new RecipeItemByproductOutput(LEAD_DUST.getItemStack()));
        addCrusherRecipe(new RecipeItemInput(SILVER_INGOT.getItemStack()), new RecipeItemByproductOutput(SILVER_DUST.getItemStack()));

        // SHAPELESS RECIPES
        GameRegistry.addShapelessRecipe(COPPER_INGOT.getItemStack(), Utils.getObjectArray(COPPER_NUGGET.getItemStack(), 9));
        GameRegistry.addShapelessRecipe(TIN_INGOT.getItemStack(), Utils.getObjectArray(TIN_NUGGET.getItemStack(), 9));
        GameRegistry.addShapelessRecipe(LEAD_INGOT.getItemStack(), Utils.getObjectArray(LEAD_NUGGET.getItemStack(), 9));
        GameRegistry.addShapelessRecipe(SILVER_INGOT.getItemStack(), Utils.getObjectArray(SILVER_NUGGET.getItemStack(), 9));

        GameRegistry.addShapelessRecipe(COPPER_NUGGET.getItemStack(9), COPPER_INGOT.getItemStack());
        GameRegistry.addShapelessRecipe(TIN_NUGGET.getItemStack(9), TIN_INGOT.getItemStack());
        GameRegistry.addShapelessRecipe(LEAD_NUGGET.getItemStack(9), LEAD_INGOT.getItemStack());
        GameRegistry.addShapelessRecipe(SILVER_NUGGET.getItemStack(9), SILVER_INGOT.getItemStack());

        GameRegistry.addShapelessRecipe(BlockMetal.Variants.COPPER.getItemStack(), Utils.getObjectArray(COPPER_INGOT.getItemStack(), 9));
        GameRegistry.addShapelessRecipe(BlockMetal.Variants.TIN.getItemStack(), Utils.getObjectArray(TIN_INGOT.getItemStack(), 9));
        GameRegistry.addShapelessRecipe(BlockMetal.Variants.LEAD.getItemStack(), Utils.getObjectArray(LEAD_INGOT.getItemStack(), 9));
        GameRegistry.addShapelessRecipe(BlockMetal.Variants.SILVER.getItemStack(), Utils.getObjectArray(SILVER_INGOT.getItemStack(), 9));

        GameRegistry.addShapelessRecipe(COPPER_INGOT.getItemStack(9), BlockMetal.Variants.COPPER.getItemStack());
        GameRegistry.addShapelessRecipe(TIN_INGOT.getItemStack(9), BlockMetal.Variants.TIN.getItemStack());
        GameRegistry.addShapelessRecipe(LEAD_INGOT.getItemStack(9), BlockMetal.Variants.LEAD.getItemStack());
        GameRegistry.addShapelessRecipe(SILVER_INGOT.getItemStack(9), BlockMetal.Variants.SILVER.getItemStack());
    }

    public static void addSmelterRecipe(RecipeItemInput input, RecipeItemOutput output)
    {
        Recipe.SMELTER.addRecipe(new RecipeSmelter(input, output));
    }

    private static void addSmelterRecipe(RecipeItemInput input, RecipeItemOutput output, boolean isVanilla)
    {
        Recipe.SMELTER.addRecipe(new RecipeSmelter(input, output));
        if (!isVanilla)
        {
            GameRegistry.addSmelting(input.getInput(), output.getOutput(), 0.2F);
        }
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
