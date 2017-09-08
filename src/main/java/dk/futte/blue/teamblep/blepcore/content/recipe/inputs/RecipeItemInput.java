package dk.futte.blue.teamblep.blepcore.content.recipe.inputs;

import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.inventory.EnumSlotType;
import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

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
        return input;
    }

    @Override
    public boolean isInputValid(ItemStack input)
    {
        if (input != null && getInput() != null)
        {
            if (input.getItem() == getInput().getItem() && input.stackSize >= getInput().stackSize)
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
