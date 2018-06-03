package teamblep.blepcore.common.tileentity;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import teamblep.blepcore.common.energy.EnergyStorageBase;
import teamblep.blepcore.common.energy.EnergyStorageConsumer;

import javax.annotation.Nullable;

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
