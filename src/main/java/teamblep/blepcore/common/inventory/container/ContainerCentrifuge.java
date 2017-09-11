package teamblep.blepcore.common.inventory.container;

import net.minecraft.entity.player.InventoryPlayer;
import teamblep.blepcore.common.tileentity.machine.TileEntityCentrifuge;

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
