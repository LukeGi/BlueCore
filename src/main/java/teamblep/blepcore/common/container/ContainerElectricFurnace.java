package teamblep.blepcore.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import teamblep.blepcore.common.tileentity.TileEntityElectricFurnace;

public class ContainerElectricFurnace extends ContainerMachine<TileEntityElectricFurnace> {

  public ContainerElectricFurnace(InventoryPlayer playerInventory, TileEntityElectricFurnace tileEntity) {
    super(playerInventory, tileEntity);
//        IItemHandler m_iItemHandler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
//        this.addSlotToContainer(new SlotItemHandler(m_iItemHandler, 0, ))
  }
}
