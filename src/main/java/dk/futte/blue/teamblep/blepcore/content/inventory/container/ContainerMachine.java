package dk.futte.blue.teamblep.blepcore.content.inventory.container;

import dk.futte.blue.teamblep.blepcore.content.inventory.InventoryMachineContainer;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

import javax.annotation.Nullable;

/**
 * @author Kelan
 */
public abstract class ContainerMachine<T extends TileEntityMachine> extends Container
{
    public T tileEntity;
    public InventoryPlayer inventoryPlayer;

    public ContainerMachine(T tileEntity, InventoryPlayer inventoryPlayer)
    {

        this.tileEntity = tileEntity;
        this.inventoryPlayer = inventoryPlayer;
    }

    /**
     * Add the players inventory slots into this container.
     *
     * @param inventoryX   The x-position of the top left of the player's inventory
     * @param inventoryY   The y-position of the top left of the player's inventory
     * @param hotbarX      The x-position of the top left of the player's hotbar
     * @param hotbarY      The y-position of the top left of the player's hotbar
     * @param slotSpacingX The distance between two slots. Most inventories use a gap of 18 pixels.
     */
    public void addPlayerSlots(int inventoryX, int inventoryY, int hotbarX, int hotbarY, int slotSpacingX, int slotSpacingY)
    {
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

        for (int i = 0; i < 9; ++i)
        {
            int xPosition = hotbarX + i * slotSpacingX;
            int yPosition = hotbarY;
            this.addSlotToContainer(new Slot(inventoryPlayer, i, xPosition, yPosition));
        }
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
        InventoryMachineContainer inventoryContainer = getTileEntity().getMachineData().getInventoryContainer();
        InventoryMachineContainer.SlotData inputSlot = inventoryContainer.getSlotData("inputSlot");
        InventoryMachineContainer.SlotData outputSlot = inventoryContainer.getSlotData("outputSlot");
        InventoryMachineContainer.SlotData fuelSlot = inventoryContainer.getSlotData("fuelSlot");
        InventoryMachineContainer.SlotData batterySlot = inventoryContainer.getSlotData("batterySlot");

        int playerInventorySize = player.inventory.mainInventory.length;
        int playerInventoryStart = inventoryContainer.getNumSlots();
        int playerInventoryEnd = playerInventoryStart + playerInventorySize;
        int playerHotbarStart = playerInventoryEnd - InventoryPlayer.getHotbarSize();

        //TODO: add null checks for each inventory slot, and move this implementation to each machine while keeping as much of it as possible in ContainerMachine
        if (slot != null && slot.getHasStack())
        {
            ItemStack slotStack = slot.getStack();
            slotStackCopy = slotStack.copy();

            if (index == outputSlot.getId())
            {
                //if the machine output slot was shift clicked, transfer the stack to the players inventory
                if (!this.mergeItemStack(slotStack, playerInventoryStart, playerInventoryEnd, true))
                {
                    return null;
                }

                slot.onSlotChange(slotStack, slotStackCopy);
            } else if (index != batterySlot.getId() && index != fuelSlot.getId() && index != inputSlot.getId())
            {
                //if the players inventory was shift clicked (not the fuel, input or battery slots)
                if (FurnaceRecipes.instance().getSmeltingResult(slotStack) != null && !this.mergeItemStack(slotStack, inputSlot.getId(), inputSlot.getId() + 1, false))
                {
                    //if this slot contains an item that this machine can process, transfer this stack to the input slot
                    return null;
                } else if (TileEntityFurnace.isItemFuel(slotStack) && !this.mergeItemStack(slotStack, fuelSlot.getId(), fuelSlot.getId() + 1, false))
                {
                    //if this slot contains an item that this machine can sue as fuel, transfer this stack to the fuel slot.
                    return null;
                } else if (index >= playerInventoryStart && index < playerHotbarStart && !this.mergeItemStack(slotStack, playerHotbarStart, playerInventoryEnd, false))
                {
                    //if none of the above statements did anything, and this slot is in the players main inventory, transfer this stack to the hotbar
                    return null;
                } else if (index >= playerHotbarStart && index < playerInventoryEnd && !this.mergeItemStack(slotStack, playerInventoryStart, playerHotbarStart, false))
                {
                    //if none of the above statements did anything, and this slot is in the players hotbar, transfer this stack to the main inventory
                    return null;
                }
            } else if (!this.mergeItemStack(slotStack, playerInventoryStart, playerInventoryEnd, false))
            {
                //if any of the remaining slots were shift clicked, transfer the stack to the players inventory or hotbar
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
}