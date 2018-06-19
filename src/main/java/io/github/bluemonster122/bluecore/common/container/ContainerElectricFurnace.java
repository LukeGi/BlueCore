package io.github.bluemonster122.bluecore.common.container;

import io.github.bluemonster122.bluecore.common.tileentity.TileEntityElectricFurnace;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerElectricFurnace extends ContainerMachine<TileEntityElectricFurnace> {

  public ContainerElectricFurnace(InventoryPlayer playerInventory, TileEntityElectricFurnace tileEntity) {
    super(playerInventory, tileEntity);
//        IItemHandler m_iItemHandler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
//        this.addSlotToContainer(new SlotItemHandler(m_iItemHandler, 0, ))
  }
}
