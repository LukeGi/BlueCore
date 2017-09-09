package dk.futte.blue.teamblep.blepcore.content.recipe.outputs;

import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.inventory.EnumSlotType;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * @author Kelan
 */
public class RecipeItemByproductOutput extends RecipeOutput<ItemStack>
{
    protected RecipeItemOutput recipeItemOutput;
    protected RecipeItemChanceOutput recipeByproductOutput;

    public RecipeItemByproductOutput(RecipeItemOutput output, RecipeItemChanceOutput byproduct)
    {
        this.recipeItemOutput = output;
        this.recipeByproductOutput = byproduct;
    }

    public RecipeItemByproductOutput(RecipeItemOutput output, RecipeItemOutput byproduct, float byproductChance)
    {
        this(output, new RecipeItemChanceOutput(byproduct, byproductChance));
    }

    public RecipeItemByproductOutput(ItemStack output, ItemStack byproduct, float byproductChance)
    {
        this(new RecipeItemOutput(output), new RecipeItemChanceOutput(byproduct, byproductChance));
    }

    public RecipeItemOutput getRecipeItemOutput()
    {
        return recipeItemOutput;
    }

    public RecipeItemChanceOutput getRecipeByproductOutput()
    {
        return recipeByproductOutput;
    }

    @Override
    public ItemStack getOutput()
    {
        return recipeItemOutput.getOutput();
    }

    public ItemStack getByproduct()
    {
        return recipeByproductOutput.getOutput();
    }

    @Override
    public boolean isValid()
    {
        return !Utils.isItemStackNull(getOutput());
    }

    @Override
    public boolean applyOutputs(ItemStack[] inventory, List slots, EnumSlotType slotType, boolean simulate)
    {
        if (!getRecipeItemOutput().applyOutputs(inventory, slots, EnumSlotType.OUTPUT, simulate))
        {
            return false;
        }

        getRecipeByproductOutput().applyOutputs(inventory, slots, EnumSlotType.BYPRODUCT, simulate);
        return true;
    }
}
