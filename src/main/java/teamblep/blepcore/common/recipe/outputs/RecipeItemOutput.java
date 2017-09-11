package teamblep.blepcore.common.recipe.outputs;

import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.inventory.EnumSlotType;
import teamblep.blepcore.common.inventory.SlotData;

import java.util.List;

/**
 * @author Kelan
 * <p>
 * A recipe that will recipeItemOutput one itemstack.
 */
public class RecipeItemOutput extends RecipeOutput<ItemStack>
{
    protected ItemStack output;

    public RecipeItemOutput(ItemStack output)
    {
        this.output = output;
    }

    public RecipeItemOutput(RecipeItemOutput output)
    {
        this(output.getOutput());
    }

    @Override
    public ItemStack getOutput()
    {
        if (output != null)
        {
            //NEVER edit the actual output itemstack. Always use a copy so that you cannot edit it.
            return output.copy();
        }
        return null;
    }

    @Override
    public boolean isValid()
    {
        return !Utils.isItemStackNull(getOutput());
    }

    @Override
    public boolean applyOutputs(ItemStack[] inventory, List<SlotData> slots, EnumSlotType slotType, boolean simulate)
    {
        ItemStack outputStack = getOutput();

        if (!Utils.isItemStackNull(outputStack) && inventory != null && slots != null && !slots.isEmpty())
        {
            for (SlotData slot : slots)
            {
                if (slot != null)
                {
                    if (slotType != null && slot.getSlotType() != slotType)
                    {
                        continue;
                    }

                    if (slot.getId() >= 0 && slot.getId() < inventory.length)
                    {
                        ItemStack stack = inventory[slot.getId()];

                        if (stack == null)
                        {
                            if (!simulate)
                            {
                                inventory[slot.getId()] = outputStack;
                            }
                            return true;
                        }
                        else if (stack.stackSize + outputStack.stackSize <= stack.getMaxStackSize() && stack.isItemEqual(outputStack))
                        {
                            if (!simulate)
                            {
                                stack.stackSize += outputStack.stackSize;
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
