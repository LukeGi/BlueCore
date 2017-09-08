package dk.futte.blue.teamblep.blepcore.content.inventory.slot;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

/**
 * @author Kelan
 */
public class SlotUpgrade extends SlotItemHandler
{
    public SlotUpgrade(IItemHandler inventory, int index, int xPosition, int yPosition)
    {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack)
    {
        //TODO: Check if item is isInventoryValid battery with CapabilityUpgrade.UPGRADE (maybe)
        return true;
    }
}
