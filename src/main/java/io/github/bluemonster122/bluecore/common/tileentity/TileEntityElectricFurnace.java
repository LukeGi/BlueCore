package io.github.bluemonster122.bluecore.common.tileentity;

import io.github.bluemonster122.bluecore.common.energy.EnergyStorageBase;
import io.github.bluemonster122.bluecore.common.energy.EnergyStorageConsumer;

public class TileEntityElectricFurnace extends TileEntityMachine {

  int requiredEnergy = 0;

  @Override
  protected EnergyStorageBase createEnergyStorage() {
    return new EnergyStorageConsumer(30000);
  }

  @Override
  public void update() {
    EnergyStorageBase m_energy = getEnergyBase(null);
    // TODO: implement properly.
    if (useEnergy(m_energy)) {
      // do work
    } else {
      // search for energy
    }
  }

  private boolean useEnergy(EnergyStorageBase energy) {
    if (energy.getEnergyStored() > requiredEnergy) {
      energy.removeEnergy(requiredEnergy);
      return true;
    } else {
      return false;
    }
  }
}
