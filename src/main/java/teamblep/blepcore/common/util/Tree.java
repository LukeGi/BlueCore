package teamblep.blepcore.common.util;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

public class Tree implements INBTSerializable<NBTTagCompound> {

  private static final Set<Tree> treeCache = Sets.newHashSet();
  private static final Vec3i[] CHECK_ORDER = new Vec3i[]{
      new Vec3i(0, 1, 0), new Vec3i(0, -1, 0), new Vec3i(0, 0, 1), new Vec3i(0, 0, -1),
      new Vec3i(1, 0, 0), new Vec3i(-1, 0, 0), new Vec3i(0, 1, 1), new Vec3i(0, 1, -1),
      new Vec3i(1, 1, 0), new Vec3i(-1, 1, 0), new Vec3i(0, 1, 1), new Vec3i(0, 1, -1),
      new Vec3i(1, 1, 0), new Vec3i(-1, 1, 0), new Vec3i(1, 0, 1), new Vec3i(1, 0, -1),
      new Vec3i(-1, 0, 1), new Vec3i(-1, 0, -1), new Vec3i(1, 1, 1), new Vec3i(1, 1, -1),
      new Vec3i(-1, 1, 1), new Vec3i(-1, 1, -1), new Vec3i(1, 0, 1), new Vec3i(1, 0, -1),
      new Vec3i(-1, 0, 1), new Vec3i(-1, 0, -1)
  };
  private World world;
  private List<BlockPos> wood;
  private List<BlockPos> leaves;
  private BlockPos topLog;

  public Tree(BlockPos start, World world) {
    this.world = world;
    wood = new ArrayList<>();
    leaves = new ArrayList<>();
    pathfind(start);
  }

  //TODO: write pathfind to not count other trees.
  private void pathfind(BlockPos start) {
    Set<BlockPos> visited = new HashSet<>();
    LinkedList<BlockPos> toCheck = new LinkedList<>();
    IBlockState woodState = null;
    visited.add(start);
    toCheck.add(start);
    BlockPos currentPos;
    boolean currentLeaves, currentWood;
    do {
      currentPos = toCheck.removeFirst();
      IBlockState currentState = world.getBlockState(currentPos);
      Block currentBlock = currentState.getBlock();
      if (woodState == null) {
        currentWood = currentBlock.isWood(world, currentPos);
        if (currentWood) {
          woodState = currentState;
        }
      } else {
        currentWood = woodState.equals(currentState);
      }
      currentLeaves = currentBlock.isLeaves(currentState, world, currentPos);

      if (currentLeaves) {
        leaves.add(currentPos);
      } else if (currentWood) {
        wood.add(currentPos);
      }
      if (currentLeaves || currentWood) {
        BlockPos iq;
        for (Vec3i offset : CHECK_ORDER) {
          iq = currentPos.add(offset);
          if (visited.add(iq)) {
            toCheck.add(iq);
          }
        }
      }
    } while (!toCheck.isEmpty()
        && this.size() < 512); // Possibly change this, depends on how well it manages big trees
  }

  private int size() {
    return wood.size() + leaves.size();
  }

  public Tree(NBTTagCompound nbt, World world) {
    this.world = world;
    wood = new ArrayList<>();
    leaves = new ArrayList<>();
    deserializeNBT(nbt);
  }

  public static Tree get(BlockPos pos, World world) {
    Tree tree = null;
    int dim = world.provider.getDimension();
    for (Iterator<Tree> iterator = treeCache.iterator(); iterator.hasNext(); ) {
      Tree cachee = iterator.next();
      if (cachee.isEmpty()) {
        iterator.remove();
        continue;
      }
      if (cachee.world.provider.getDimension() == dim && cachee.contains(pos)) {
        tree = cachee;
        break;
      }
    }
    if (tree == null) {
      treeCache.add(tree = new Tree(pos, world));
    }
    return tree;
  }

  public boolean isEmpty() {
    wood = wood.stream().filter(pos -> !world.isAirBlock(pos)).collect(Collectors.toList());
    leaves = leaves.stream().filter(pos -> !world.isAirBlock(pos)).collect(Collectors.toList());
    return wood.isEmpty() && leaves.isEmpty();
  }

  public boolean contains(BlockPos pos) {
    return wood.parallelStream().anyMatch(blockPos -> blockPos.equals(pos)) || leaves
        .parallelStream().anyMatch(blockPos -> blockPos.equals(pos));
  }

  public BlockPos getTopLog() {
    if (topLog == null) {
      topLog = wood.stream().max(Comparator.comparingInt(Vec3i::getY)).orElse(null);
    }
    return topLog;
  }

  public boolean breakBlock(BlockPos pos) {
    if (pos.equals(topLog)) {
      topLog = null;
    }
    if (wood.remove(pos) || leaves.remove(pos)) {
      world.destroyBlock(pos, true);
      if (isEmpty()) {
        treeCache.remove(this);
      }
      return true;
    }
    return false;
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
    if (topLog != null) {
      nbt.setLong("toplog", topLog.toLong());
    }
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
    if (nbt.hasKey("toplog")) {
      topLog = BlockPos.fromLong(nbt.getLong("toplog"));
    }
    for (int i = 0; i < woodSize; i++) {
      wood.add(BlockPos.fromLong(nbt.getLong("wood" + i)));
    }
    for (int i = 0; i < leavesSize; i++) {
      leaves.add(BlockPos.fromLong(nbt.getLong("leaves" + i)));
    }
  }

  public Stream<BlockPos> stream() {
    return Stream.concat(wood.stream(), leaves.stream());
  }
}
