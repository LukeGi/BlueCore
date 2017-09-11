package teamblep.blepcore.common.inventory.container;

import net.minecraft.entity.player.InventoryPlayer;
import teamblep.blepcore.common.tileentity.machine.TileEntityElectrolysisChamber;

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
