package dk.futte.blue.teamblep.blepcore.content.recipe.outputs;

import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.inventory.EnumSlotType;
import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;
import net.minecraft.item.ItemStack;

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
