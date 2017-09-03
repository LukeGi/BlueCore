package dk.futte.blue.teamblep.blepcore.content.inventory.container;

import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Kelan
 */

public class ContainerCrusher extends ContainerMachine<TileEntityCrusher>
{
    public ContainerCrusher(TileEntityCrusher tileEntity, InventoryPlayer inventoryPlayer)
    {
        super(tileEntity, inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return false;
    }
}
