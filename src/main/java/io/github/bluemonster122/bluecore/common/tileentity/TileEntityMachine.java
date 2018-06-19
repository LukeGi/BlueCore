package io.github.bluemonster122.bluecore.common.tileentity;

import io.github.bluemonster122.bluecore.common.energy.EnergyStorageBase;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

public abstract class TileEntityMachine extends TileEntityBase implements ITickable {

  private EnergyStorageBase energyStorage;

  public TileEntityMachine() {
    this.energyStorage = createEnergyStorage();
  }

  /**
   * This will be used to create the energy storage on initialization.
   *
   * @return energy storage or null if machine does not have one
   */
  protected abstract EnergyStorageBase createEnergyStorage();

  @Override
  public void update() {

  }

  @Override
  public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
    if (energyStorage != null && CapabilityEnergy.ENERGY.equals(capability)) {
      return true;
    }
    return super.hasCapability(capability, facing);
  }

  @Nullable
  @Override
  public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
    if (energyStorage != null && CapabilityEnergy.ENERGY.equals(capability)) {
      return CapabilityEnergy.ENERGY.cast(energyStorage);
    }
    return super.getCapability(capability, facing);
  }
}
