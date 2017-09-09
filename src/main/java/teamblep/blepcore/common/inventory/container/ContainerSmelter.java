package teamblep.blepcore.common.inventory.container;

import teamblep.blepcore.common.inventory.EnumSlotType;
import teamblep.blepcore.common.inventory.InventoryMachineContainer;
import teamblep.blepcore.common.inventory.SlotData;
import teamblep.blepcore.common.inventory.SlotRange;
import teamblep.blepcore.common.tileentity.machine.TileEntitySmelter;
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

public class ContainerSmelter extends ContainerMachine<TileEntitySmelter>
{
    public ContainerSmelter(TileEntitySmelter tileEntity, InventoryPlayer inventoryPlayer)
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
                if (slotData.getSlotType() != EnumSlotType.OUTPUT) //we cannot shift click something into the recipeItemOutput slot
                {
                    if (FurnaceRecipes.instance().getSmeltingResult(stackInSlot) != null && inventoryContainer.getSlotData("inputSlot").getId() == slotId)
                    {
                        //If the item being shift clicked is smeltable, and the slot is the input slot, then transfer the item.
                        list.add(new SlotRange(slotId, slotId + 1, false));
                    }
                    if (TileEntityFurnace.isItemFuel(stackInSlot) && inventoryContainer.getSlotData("fuelSlot").getId() == slotId)
                    {
                        //If the item being shift clicked is fuel, and the slot is the fuel slot, then transfer the item.
                        list.add(new SlotRange(slotId, slotId + 1, false));
                    }

                    //if the item being shift clicked is both smeltable and fuel, try to transfer it to the input slot before attempting the fuel slot.
                    // To change this, the two if-statements would be the other way round, as order matters in the list.
                }
            }
        }
    }
}