package teamblep.blepcore.common.energy;

public class EnergyStorageConsumer extends EnergyStorageBase {

  public EnergyStorageConsumer(int capacity) {
    super(capacity);
  }

  public EnergyStorageConsumer(int capacity, int maxReceive) {
    super(capacity, maxReceive, 0);
  }

  public EnergyStorageConsumer(int capacity, int maxReceive, int energy) {
    super(capacity, maxReceive, 0, energy);
  }
}
