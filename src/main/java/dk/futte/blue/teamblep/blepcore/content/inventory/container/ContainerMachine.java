package dk.futte.blue.teamblep.blepcore.content.inventory.container;

import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityMachine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

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

    public T getTileEntity()
    {
        return tileEntity;
    }

    public InventoryPlayer getInventoryPlayer()
    {
        return inventoryPlayer;
    }
}