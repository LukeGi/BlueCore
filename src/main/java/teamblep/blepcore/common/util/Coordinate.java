package teamblep.blepcore.common.util;

import net.minecraft.util.math.BlockPos;

public class Coordinate {
  private int dim;
  private BlockPos pos;

  public Coordinate(int dim, BlockPos pos) {
    this.dim = dim;
    this.pos = pos;
  }

  public int getDim() {
    return dim;
  }

  public BlockPos getPos() {
    return pos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Coordinate that = (Coordinate) o;

    if (getDim() != that.getDim()) {
      return false;
    }
    return getPos().equals(that.getPos());
  }

  @Override
  public int hashCode() {
    int result = getDim();
    result = 31 * result + getPos().hashCode();
    return result;
  }
}
