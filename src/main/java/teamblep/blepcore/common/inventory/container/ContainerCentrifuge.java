package teamblep.blepcore.common.inventory.container;

import teamblep.blepcore.common.tileentity.machine.TileEntityCentrifuge;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Kelan
 */

public class ContainerCentrifuge extends ContainerMachine<TileEntityCentrifuge>
{
    public ContainerCentrifuge(TileEntityCentrifuge tileEntity, InventoryPlayer inventoryPlayer)
    {
        super(tileEntity, inventoryPlayer);
    }
}
