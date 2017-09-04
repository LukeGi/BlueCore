package dk.futte.blue.teamblep.blepcore.content.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * @author Kelan
 */
public class SlotOutput extends Slot
{
    public SlotOutput(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack)
    {
        return true;
    }
}
