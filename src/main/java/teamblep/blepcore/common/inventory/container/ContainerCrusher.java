package teamblep.blepcore.common.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.inventory.EnumSlotType;
import teamblep.blepcore.common.inventory.InventoryMachineContainer;
import teamblep.blepcore.common.inventory.SlotData;
import teamblep.blepcore.common.inventory.SlotRange;
import teamblep.blepcore.common.tileentity.machine.TileEntityCrusher;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Kelan
 */

public class ContainerCrusher extends ContainerMachine<TileEntityCrusher>
{
    public ContainerCrusher(TileEntityCrusher tileEntity, InventoryPlayer inventoryPlayer)
    {
        super(tileEntity, inventoryPlayer);
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        return super.transferStackInSlot(player, index);
    }

    @Override
    public void addMachineTransferSlots(List<SlotRange> list, Slot slot)
    {
        InventoryMachineContainer inventoryContainer = getTileEntity().getMachineData().getInventoryContainer();
        ItemStack stackInSlot = slot.getStack();

        if (stackInSlot != null)
        {
            for (Object o : inventoryContainer.getSlotList())
            {
                SlotData slotData = (SlotData) o;
                int slotId = machineInventory.getStart() + slotData.getId();

                if (slotData.getSlotType() != EnumSlotType.OUTPUT && slotData.getSlotType() != EnumSlotType.BATTERY)
                {
                    list.add(new SlotRange(slotId, slotId + 1, false));
                }
            }
        }
    }
}
