package teamblep.blepcore.common.item.tools;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.common.network.MessageBlockBreakProgress;
import teamblep.blepcore.common.util.Tree;

public class ToolChainsaw extends ToolBase {

  public ToolChainsaw(String name) {
    super(name);
  }

  private static boolean isLeaves(World world, BlockPos blockPos) {
    IBlockState blockState = world.getBlockState(blockPos);
    return blockState.getBlock().isLeaves(blockState, world, blockPos);
  }

  @Override
  public boolean isEffective(IBlockState block) {
    Material material = block.getMaterial();
    return material.equals(Material.LEAVES) || material.equals(Material.WOOD);
  }

  @Override
  public float getEffectiveSpeed() {
    return 3.0F;
  }

  @Override
  public float getIneffectiveSpeed() {
    return 0.5F;
  }

  @Override
  public boolean rightClickBlockAction(EntityPlayer player, EnumHand hand, World world,
      BlockPos pos, IBlockState blockState, EnumFacing side, Vec3d hit) {
    return chopTree(player, hand, world, pos, blockState);
  }

  @Override
  public boolean leftClickBlockAction(EntityPlayer player, EnumHand hand, World world, BlockPos pos,
      IBlockState blockState, EnumFacing side, Vec3d hit) {
    return chopTree(player, hand, world, pos, blockState);
  }

  @Override
  public boolean rightClickAirAction(EntityPlayer player, EnumHand hand, World world) {
    return false;
  }

  @Override
  public boolean leftClickAirAction(EntityPlayer player, EnumHand hand, World world) {
    return false;
  }

  private boolean chopTree(EntityPlayer player, EnumHand hand, World world, BlockPos pos,
      IBlockState blockState) {
    if (hand != EnumHand.MAIN_HAND
        || !blockState.getBlock().isLeaves(blockState, world, pos) && !blockState.getBlock()
        .isWood(world, pos)) {
      return false;
    }
    ItemStack inst = player.getHeldItem(hand);
    NBTTagCompound nbt = inst.getTagCompound();
    if (nbt == null) {
      nbt = new NBTTagCompound();
    }
    Tree tree = nbt.hasKey("tree") ? new Tree((NBTTagCompound) nbt.getTag("tree"), world)
        : new Tree(pos, world);
    if (!tree.contains(pos)) {
      tree = new Tree(pos, world);
    }
    BlockPos topLog = tree.getTopLog();
    if (topLog != null) {
      int itter = nbt.hasKey("itter") ? nbt.getInteger("itter") : 0;
      if (itter > 5) {
        nbt.removeTag("itter");
        tree.breakBlock(topLog);
      } else {
        nbt.setInteger("itter", ++itter);
        MessageBlockBreakProgress message = new MessageBlockBreakProgress(player.getEntityId(),
            topLog, itter * 2 - 1);
        TargetPoint point = new TargetPoint(world.provider.getDimension(), topLog.getX(),
            topLog.getY(), topLog.getZ(), 4096);
        BlepCore.net.sendToAllAround(message, point);
      }
      if (tree.getWood().isEmpty()) {
        tree.getLeaves().forEach(blockPos -> world
            .scheduleUpdate(blockPos, world.getBlockState(blockPos).getBlock(),
                world.rand.nextInt(15)));
        nbt.removeTag("tree");
      } else {
        nbt.setTag("tree", tree.serializeNBT());
      }
    }
    inst.setTagCompound(nbt);
    return true;
  }
}
