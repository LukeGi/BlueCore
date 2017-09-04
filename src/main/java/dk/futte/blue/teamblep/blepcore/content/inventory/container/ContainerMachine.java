package dk.futte.blue.teamblep.blepcore.content.inventory.container;

import dk.futte.blue.teamblep.blepcore.content.inventory.EnumSlotType;
import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;
import dk.futte.blue.teamblep.blepcore.content.inventory.SlotRange;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kelan
 */
public abstract class ContainerMachine<T extends TileEntityMachine> extends Container
{
    public T tileEntity;
    public InventoryPlayer inventoryPlayer;
    public SlotRange machineInventory = new SlotRange(0, 0, false);
    public SlotRange playerInventory = new SlotRange(0, 0, false);
    public SlotRange playerHotbar = new SlotRange(0, 0, false);

    public ContainerMachine(T tileEntity, InventoryPlayer inventoryPlayer)
    {
        this.tileEntity = tileEntity;
        this.inventoryPlayer = inventoryPlayer;
        setupAllSlots();
    }

    protected void setupAllSlots()
    {
        machineInventory.setStart(inventorySlots.size());
        this.tileEntity.getMachineData().getInventoryContainer().addSlotsToContainer(this); //this must come before the player slots are added.
        machineInventory.setEnd(inventorySlots.size());

        this.addPlayerSlots(8, 104, 8, 162, 18, 18);
    }

    /**
     * Add the players inventory slots into this container.
     * This function should be called after the machine slots are added in order to make the machine slots start from ID 0. Things might break otherwise.
     *
     * @param inventoryX   The x-position of the top left of the player's inventory
     * @param inventoryY   The y-position of the top left of the player's inventory
     * @param hotbarX      The x-position of the top left of the player's hotbar
     * @param hotbarY      The y-position of the top left of the player's hotbar
     * @param slotSpacingX The distance between two slots. Most inventories use a gap of 18 pixels.
     */
    public void addPlayerSlots(int inventoryX, int inventoryY, int hotbarX, int hotbarY, int slotSpacingX, int slotSpacingY)
    {

        playerInventory.setStart(inventorySlots.size());
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                int index = j + i * 9 + 9;
                int xPosition = inventoryX + j * slotSpacingX;
                int yPosition = inventoryY + i * slotSpacingY;

                this.addSlotToContainer(new Slot(inventoryPlayer, index, xPosition, yPosition));
            }
        }
        playerInventory.setEnd(inventorySlots.size());
        playerHotbar.setStart(inventorySlots.size());
        for (int i = 0; i < 9; ++i)
        {
            int xPosition = hotbarX + i * slotSpacingX;
            int yPosition = hotbarY;
            this.addSlotToContainer(new Slot(inventoryPlayer, i, xPosition, yPosition));
        }
        playerHotbar.setEnd(inventorySlots.size());
    }

    public T getTileEntity()
    {
        return tileEntity;
    }

    public InventoryPlayer getInventoryPlayer()
    {
        return inventoryPlayer;
    }

    @Override
    public Slot addSlotToContainer(Slot slotIn)
    {
        //public override of a protected function
        return super.addSlotToContainer(slotIn);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack slotStackCopy = null;
        Slot slot = this.inventorySlots.get(index);

//        InventoryMachineContainer inventoryContainer = getTileEntity().getMachineData().getInventoryContainer();
//        SlotData inputSlot = inventoryContainer.getSlotData("inputSlot");
//        SlotData outputSlot = inventoryContainer.getSlotData("outputSlot");
//        SlotData fuelSlot = inventoryContainer.getSlotData("fuelSlot");
//        SlotData batterySlot = inventoryContainer.getSlotData("batterySlot");
//
//        int playerInventorySize = player.inventory.mainInventory.length;
//        int playerInventoryStart = inventoryContainer.getNumSlots();
//        int playerInventoryEnd = playerInventoryStart + playerInventorySize;
//        int playerHotbarStart = playerInventoryEnd - InventoryPlayer.getHotbarSize();
//
//        if (slot != null && slot.getHasStack())
//        {
//            ItemStack slotStack = slot.getStack();
//            slotStackCopy = slotStack.copy();
//
//            if (index == outputSlot.getId())
//            {
//                //if the machine output slot was shift clicked, transfer the stack to the players inventory or hotbar
//                if (!this.mergeItemStack(slotStack, playerInventoryStart, playerInventoryEnd, true))
//                {
//                    return null;
//                }
//
//                slot.onSlotChange(slotStack, slotStackCopy);
//            } else if (index != batterySlot.getId() && index != fuelSlot.getId() && index != inputSlot.getId())
//            {
//                //if the players inventory was shift clicked (not the fuel, input or battery slots)
//                if (FurnaceRecipes.instance().getSmeltingResult(slotStack) != null && !this.mergeItemStack(slotStack, inputSlot.getId(), inputSlot.getId() + 1, false))
//                {
//                    //if this slot contains an item that this machine can process, transfer this stack to the input slot
//                    return null;
//                } else if (TileEntityFurnace.isItemFuel(slotStack) && !this.mergeItemStack(slotStack, fuelSlot.getId(), fuelSlot.getId() + 1, false))
//                {
//                    //if this slot contains an item that this machine can sue as fuel, transfer this stack to the fuel slot.
//                    return null;
//                } else if (index >= playerInventoryStart && index < playerHotbarStart && !this.mergeItemStack(slotStack, playerHotbarStart, playerInventoryEnd, false))
//                {
//                    //if none of the above statements did anything, and this slot is in the players main inventory, transfer this stack to the hotbar
//                    return null;
//                } else if (index >= playerHotbarStart && index < playerInventoryEnd && !this.mergeItemStack(slotStack, playerInventoryStart, playerHotbarStart, false))
//                {
//                    //if none of the above statements did anything, and this slot is in the players hotbar, transfer this stack to the main inventory
//                    return null;
//                }
//            } else if (!this.mergeItemStack(slotStack, playerInventoryStart, playerInventoryEnd, false))
//            {
//                //if any of the remaining slots were shift clicked, transfer the stack to the players inventory or hotbar
//                return null;
//            }
//
//            if (slotStack.stackSize == 0)
//            {
//                slot.putStack(null);
//            } else
//            {
//                slot.onSlotChanged();
//            }
//
//            if (slotStack.stackSize == slotStackCopy.stackSize)
//            {
//                return null;
//            }
//
//            slot.onPickupFromSlot(player, slotStack);
//        }
//
//        return slotStackCopy;

        if (slot != null && slot.getHasStack())
        {
            ItemStack slotStack = slot.getStack();
            slotStackCopy = slotStack.copy();
            boolean flag = false;

            for (SlotRange slotRange : getTransferSlots(slot))
            {
                if (mergeItemStack(slotStack, slotRange.getStart(), slotRange.getEnd(), slotRange.doReverse()))
                {
                    flag = true;
                    break;
                }
            }
            if (!flag)
            {
                return null;
            }

            if (slotStack.stackSize == 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
            if (slotStack.stackSize == slotStackCopy.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, slotStack);
        }

        return slotStackCopy;
    }

    /**
     * Called when a slot in the players inventory is shift clicked, and the item will try to be placed in one of the machines
     * slots. Override this for custom functionality.
     * This method should be overridden if when a certain item is shift clicked it should land in a certain slot.
     * <p>
     * Adds SlotRange(s) to the list of existing slot ranges that correspond to what slots the shift clicked item will land in.
     *
     * @param list The existing list of SlotRanges
     * @param slot The slot in the machines inventory that was just shift clicked
     */
    public void addMachineTransferSlots(List<SlotRange> list, Slot slot)
    {
        for (Object o : getTileEntity().getMachineData().getInventoryContainer().getUnmodifiableSlotList())
        {
            int slotId = machineInventory.getStart() + ((SlotData) o).getId();
            list.add(new SlotRange(slotId, slotId + 1, false));
        }
    }

    /**
     * Called when a slot in the players inventory is shift clicked, and the item will try and be placed in the players inventory
     * or hotbar. Override this for custom functionality.
     * <p>
     * Adds SlotRange(s) to the list of existing slot ranges that correspond to what slots the shift clicked item will land in.
     *
     * @param list The existing list of SlotRanges
     * @param slot The slot in the players inventory that was just shift clicked
     */
    public void addPlayerTransferSlots(List<SlotRange> list, Slot slot)
    {
        int index = slot.slotNumber;
        if (playerInventory.isSlotInRange(index)) //If the item was in the players inventory, transfer it to the players hotbar.
        {
            list.add(new SlotRange(playerHotbar.getStart(), playerHotbar.getEnd(), false));
        }
        if (playerHotbar.isSlotInRange(index)) //If the item was in the players hotbar, transfer it to the players inventory.
        {
            list.add(new SlotRange(playerInventory.getStart(), playerInventory.getEnd(), false));
        }
    }

    /**
     * Gets the list of SlotRanges that the item in the slot that was shift clicked can land in. The order of the list
     * represents the priority of the slot, i.e. The first SlotRange is the first range of slots to attempt to send the
     * stack in the clicked slot to, and the last SlotRange added will be the last one attempted.
     *
     * @param slot The slot that was shift clicked.
     * @return A list of SlotRanges to attempt to move the shift clicked stack into.
     */
    public List<SlotRange> getTransferSlots(Slot slot)
    {
        List<SlotRange> list = new ArrayList<>();

        int index = slot.slotNumber;

        if (machineInventory.isSlotInRange(index)) //The item that was shift clicked was inside the machines inventory and should be transferred to the players inventory.
        {
            SlotData slotData = getTileEntity().getMachineData().getInventoryContainer().getSlotData(machineInventory.getRelativeSlotIndex(index));
            SlotRange range = new SlotRange(playerInventory, playerHotbar, false);

            if (slotData.getSlotType() == EnumSlotType.INPUT)
            {
                range.setReverse(false);
            } else if (slotData.getSlotType() == EnumSlotType.OUTPUT)
            {
                range.setReverse(true);
            }

            list.add(range);
        } else if (playerInventory.isSlotInRange(index) || playerHotbar.isSlotInRange(index)) //The item that was shift clicked was inside the players inventory and should either be transferred to the players hotbar or to the machines inventory, depending on the implementation.
        {
            addMachineTransferSlots(list, slot);
            addPlayerTransferSlots(list, slot);
        }

        return list;
    }
}



