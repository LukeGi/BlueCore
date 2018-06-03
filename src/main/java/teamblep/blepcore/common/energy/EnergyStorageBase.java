package teamblep.blepcore.common.energy;

import net.minecraftforge.energy.EnergyStorage;

public class EnergyStorageBase extends EnergyStorage {
    public EnergyStorageBase(int capacity) {
        super(capacity);
    }

    public EnergyStorageBase(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public EnergyStorageBase(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public EnergyStorageBase(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public int addEnergy(int amount) {
        return addEnergy(amount, true);
    }

    public int addEnergy(int amount, boolean forced) {
        int energyReceived;
        if (forced) {
            energyReceived = Math.min(capacity - energy, amount);
        } else {
            energyReceived = Math.min(capacity - energy, Math.min(maxReceive, amount));
        }
        energy += energyReceived;
        return energyReceived;
    }

    public int removeEnergy(int amount) {
        return removeEnergy(amount, true);
    }

    public int removeEnergy(int amount, boolean forced) {
        int energyExtracted;
        if (forced) {
            energyExtracted = Math.min(energy, amount);
        } else {
            energyExtracted = Math.min(energy, Math.min(this.maxExtract, amount));
        }
        energy -= energyExtracted;
        return energyExtracted;
    }
}
