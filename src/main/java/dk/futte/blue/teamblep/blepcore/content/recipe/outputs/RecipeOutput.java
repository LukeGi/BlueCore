package dk.futte.blue.teamblep.blepcore.content.recipe.outputs;

import dk.futte.blue.teamblep.blepcore.content.inventory.EnumSlotType;
import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;

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
