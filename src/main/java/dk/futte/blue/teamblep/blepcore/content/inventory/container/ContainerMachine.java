package dk.futte.blue.teamblep.blepcore.content.inventory.container;

import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityMachine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

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
     * @param inventoryX The x-position of the top left of the player's inventory
     * @param inventoryY The y-position of the top left of the player's inventory
     * @param hotbarX The x-position of the top left of the player's hotbar
     * @param hotbarY The y-position of the top left of the player's hotbar
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
}