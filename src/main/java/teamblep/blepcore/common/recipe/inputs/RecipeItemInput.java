package teamblep.blepcore.common.recipe.inputs;

import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.inventory.EnumSlotType;
import teamblep.blepcore.common.inventory.SlotData;

import java.util.List;

/**
 * @author Kelan
 */
public class RecipeItemInput extends RecipeInput<ItemStack>
{
    protected ItemStack input;

    public RecipeItemInput(ItemStack input)
    {
        this.input = input;
    }

    @Override
    public ItemStack getInput()
    {
        if (input != null)
        {
            //NEVER edit the actual input itemstack. Always use a copy so that you cannot edit it.
            return input.copy();
        }
        return null;
    }

    @Override
    public boolean isValid()
    {
        return !Utils.isItemStackNull(input);
    }

    @Override
    public boolean isInputStackValid(ItemStack input)
    {
        if (input != null && getInput() != null)
        {
            if (input.getItem() == getInput().getItem() && input.getMetadata() == getInput().getMetadata() && input.stackSize >= getInput().stackSize)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean consumeInputs(ItemStack[] inventory, List<SlotData> slots, boolean simulate)
    {
        ItemStack inputStack = getInput();

        if (!Utils.isItemStackNull(inputStack) && inventory != null && slots != null && !slots.isEmpty())
        {
            for (SlotData slot : slots)
            {
                if (slot != null && slot.getSlotType() == EnumSlotType.INPUT)
                {
                    if (slot.getId() >= 0 && slot.getId() < inventory.length)
                    {
                        ItemStack stack = inventory[slot.getId()];

                        if (stack != null && stack.stackSize >= inputStack.stackSize)
                        {
                            if (!simulate)
                            {
                                stack.stackSize -= inputStack.stackSize;
                                if (stack.stackSize <= 0)
                                {
                                    inventory[slot.getId()] = null;
                                }
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
