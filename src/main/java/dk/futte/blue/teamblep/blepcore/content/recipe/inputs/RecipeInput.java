package dk.futte.blue.teamblep.blepcore.content.recipe.inputs;

import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;

/**
 * @author Kelan
 */
public abstract class RecipeInput<T>
{
    public abstract T getInput();

    public abstract boolean isInputValid(T input);

    public abstract boolean consumeInputs(T[] inventory, List<SlotData> slots, boolean simulate);
}
