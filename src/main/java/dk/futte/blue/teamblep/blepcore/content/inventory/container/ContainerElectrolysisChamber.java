package dk.futte.blue.teamblep.blepcore.content.inventory.container;

import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityElectrolysisChamber;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Kelan
 */

public class ContainerElectrolysisChamber extends ContainerMachine<TileEntityElectrolysisChamber>
{
    public ContainerElectrolysisChamber(TileEntityElectrolysisChamber tileEntity, InventoryPlayer inventoryPlayer)
    {
        super(tileEntity, inventoryPlayer);
    }
}
