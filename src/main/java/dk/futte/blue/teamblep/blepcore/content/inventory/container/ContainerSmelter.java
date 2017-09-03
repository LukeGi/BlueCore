package dk.futte.blue.teamblep.blepcore.content.inventory.container;

import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntitySmelter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Kelan
 */

public class ContainerSmelter extends ContainerMachine<TileEntitySmelter>
{
    public ContainerSmelter(TileEntitySmelter tileEntity, InventoryPlayer inventoryPlayer)
    {
        super(tileEntity, inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return false;
    }
}
