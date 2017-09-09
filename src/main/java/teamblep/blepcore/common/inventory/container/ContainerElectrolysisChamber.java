package teamblep.blepcore.common.inventory.container;

import teamblep.blepcore.common.tileentity.machine.TileEntityElectrolysisChamber;
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
