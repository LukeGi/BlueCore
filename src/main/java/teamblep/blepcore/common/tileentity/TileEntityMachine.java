package teamblep.blepcore.common.tileentity;

import net.minecraft.util.ITickable;
import teamblep.blepcore.common.energy.EnergyStorageBase;

public abstract class TileEntityMachine extends TileEntityBase implements ITickable {
    private EnergyStorageBase energyStorage;

    public TileEntityMachine() {
        this.energyStorage = createEnergyStorage();
    }

    protected abstract EnergyStorageBase createEnergyStorage();

    @Override
    public void update() {

    }
}
