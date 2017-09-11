package teamblep.blepcore.common.recipe.outputs;

import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.inventory.EnumSlotType;
import teamblep.blepcore.common.inventory.SlotData;

import java.util.List;

/**
 * @author Kelan
 * <p>
 * A recipe that will recipeItemOutput an itemstack with isInventoryValid chance
 */
public class RecipeItemChanceOutput extends RecipeItemOutput
{
    protected float chance;

    public RecipeItemChanceOutput(ItemStack output, float chance)
    {
        super(output);
        this.chance = chance;
    }

    public RecipeItemChanceOutput(RecipeItemOutput output, float chance)
    {
        super(output);
        this.chance = chance;
    }

    public float getChance()
    {
        return chance;
    }

    @Override
    public boolean applyOutputs(ItemStack[] inventory, List<SlotData> slots, EnumSlotType slotType, boolean simulate)
    {
        return Utils.staticRandom().nextFloat() < getChance() && super.applyOutputs(inventory, slots, slotType, simulate);
    }
}
