package teamblep.blepcore.common.recipe.outputs;

import teamblep.blepcore.common.inventory.EnumSlotType;
import teamblep.blepcore.common.inventory.SlotData;

import java.util.List;

/**
 * @author Kelan
 */
public abstract class RecipeOutput<T>
{
    public abstract T getOutput();

    public abstract boolean isValid();

    public abstract boolean applyOutputs(T[] inventory, List<SlotData> slots, EnumSlotType slotType, boolean simulate);
}
