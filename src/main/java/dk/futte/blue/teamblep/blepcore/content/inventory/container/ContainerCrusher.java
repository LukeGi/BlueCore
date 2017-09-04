package dk.futte.blue.teamblep.blepcore.content.inventory.container;

import dk.futte.blue.teamblep.blepcore.content.inventory.EnumSlotType;
import dk.futte.blue.teamblep.blepcore.content.inventory.InventoryMachineContainer;
import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;
import dk.futte.blue.teamblep.blepcore.content.inventory.SlotRange;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

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
            for (Object o : inventoryContainer.getUnmodifiableSlotList())
            {
                SlotData slotData = (SlotData) o;
                int slotId = machineInventory.getStart() + slotData.getId();

                if (slotData.getSlotType() != EnumSlotType.OUTPUT)
                {
                    list.add(new SlotRange(slotId, slotId + 1, false));
                }
            }
        }
    }
}
