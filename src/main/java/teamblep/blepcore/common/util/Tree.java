package teamblep.blepcore.common.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

public class Tree implements INBTSerializable<NBTTagCompound> {

  private World world;
  private List<BlockPos> wood;
  private List<BlockPos> leaves;

  public Tree(BlockPos start, World world) {
    this.world = world;
    wood = new ArrayList<>();
    leaves = new ArrayList<>();
    pathfind(start);
  }

  public Tree(NBTTagCompound nbt, World world) {
    this.world = world;
    wood = new ArrayList<>();
    leaves = new ArrayList<>();
    deserializeNBT(nbt);
  }

  private void pathfind(BlockPos start) {
    Set<BlockPos> visited = new HashSet<>();
    LinkedList<BlockPos> toCheck = new LinkedList<>();
    visited.add(start);
    toCheck.add(start);
    BlockPos currentPos;
    do {
      currentPos = toCheck.removeFirst();
      IBlockState currentState = world.getBlockState(currentPos);
      boolean currentLeaves = currentState.getBlock().isLeaves(currentState, world, currentPos);
      boolean currentWood = currentState.getBlock().isWood(world, currentPos);
      if (currentLeaves) {
        leaves.add(currentPos);
      } else if (currentWood) {
        wood.add(currentPos);
      }
      if (currentLeaves || currentWood) {
        for (BlockPos iq : BlockPos
            .getAllInBox(currentPos.add(-1, -1, -1), currentPos.add(1, 1, 1))) {
          if (visited.contains(iq)) {
            continue;
          }
          visited.add(iq);
          toCheck.add(iq);
        }
      }
    } while (!toCheck.isEmpty()
        && visited.size() < 8192); // Possibly change this, depends on how well it manages big trees
  }

  public boolean contains(BlockPos pos) {
    return wood.parallelStream().anyMatch(blockPos -> blockPos.equals(pos)) || leaves
        .parallelStream().anyMatch(blockPos -> blockPos.equals(pos));
  }

  public BlockPos getTopLog() {
    return wood.stream().max(Comparator.comparingInt(Vec3i::getY)).orElse(null);
  }

  public boolean breakBlock(BlockPos pos) {
    if (wood.remove(pos) || leaves.remove(pos)) {
      world.destroyBlock(pos, true);
      return true;
    }
    return false;
  }

  public boolean isEmpty() {
    return wood.isEmpty() && leaves.isEmpty();
  }

  public List<BlockPos> getWood() {
    return wood;
  }

  public List<BlockPos> getLeaves() {
    return leaves;
  }

  @Override
  public NBTTagCompound serializeNBT() {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.setInteger("wood", wood.size());
    nbt.setInteger("leaves", leaves.size());
    for (int i = 0; i < wood.size(); i++) {
      nbt.setLong("wood" + i, wood.get(i).toLong());
    }
    for (int i = 0; i < leaves.size(); i++) {
      nbt.setLong("leaves" + i, leaves.get(i).toLong());
    }
    return nbt;
  }

  @Override
  public void deserializeNBT(NBTTagCompound nbt) {
    wood.clear();
    leaves.clear();
    int woodSize = nbt.getInteger("wood");
    int leavesSize = nbt.getInteger("leaves");
    for (int i = 0; i < woodSize; i++) {
      wood.add(BlockPos.fromLong(nbt.getLong("wood" + i)));
    }
    for (int i = 0; i < leavesSize; i++) {
      leaves.add(BlockPos.fromLong(nbt.getLong("leaves" + i)));
    }
  }
}
