package teamblep.blepcore.common.item.tools;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import teamblep.blepcore.common.network.MessageBlockBreakProgress;
import teamblep.blepcore.common.network.NetworkManager;
import teamblep.blepcore.common.util.Tree;

public class ToolChainsaw extends ToolBase {

  public ToolChainsaw(String name) {
    super(name);
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn,
      EnumHand handIn) {
    playerIn.setActiveHand(handIn);
    return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
  }

  @Override
  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
    if (worldIn.isRemote) {
      return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
    if (!(entityLiving instanceof EntityPlayer)) {
      return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
    EntityPlayer player = (EntityPlayer) entityLiving;
    Vec3d positionEyes = player.getPositionEyes(0);
    Vec3d lookVec = player.getLook(0).scale(14).add(positionEyes);
    RayTraceResult rtr = worldIn.rayTraceBlocks(positionEyes, lookVec, false, false, true);
    if (rtr != null && rtr.typeOfHit == Type.BLOCK) {
      World world = player.world;
      IBlockState stateHit = world.getBlockState(rtr.getBlockPos());
      if (stateHit.getBlock().isWood(world, rtr.getBlockPos())) {
        Tree tree = Tree.get(rtr.getBlockPos(), world);
        BlockPos topLeft = tree.stream()
            .reduce(BinaryOperator.maxBy(Comparator.comparingLong(bp -> bp.toLong()))).get();
        BlockPos bottomRight = tree.stream()
            .reduce(BinaryOperator.maxBy(Comparator.comparingLong(bp -> -bp.toLong()))).get();
        if (bottomRight != null && topLeft != null) {
          MessageBlockBreakProgress message = new MessageBlockBreakProgress(player.getEntityId(),
              10,
              tree.getWood().toArray(new BlockPos[0]));
          TargetPoint point = new TargetPoint(world.provider.getDimension(), player.posX,
              player.posY,
              player.posZ, 512);
          NetworkManager.INSTANCE.sendToAllAround(message, point);
          tree.getWood().forEach((pos) -> {
            IBlockState state = world.getBlockState(pos);
            state.getBlock().harvestBlock(world, player, pos, state,
                world.getTileEntity(pos), stack);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
          });
          world.markBlockRangeForRenderUpdate(topLeft, bottomRight);
          tree.getWood().clear();
          tree.getLeaves().forEach(blockPos -> scheduleBlockUpdate(world, blockPos));
          tree.getLeaves().clear();
        }
      }
    }
    return super.onItemUseFinish(stack, worldIn, entityLiving);
  }

  @Override
  public EnumAction getItemUseAction(ItemStack stack) {
    return EnumAction.NONE;
  }

  @Override
  public int getMaxItemUseDuration(ItemStack stack) {
    return 10;
  }

  @Override
  public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
    World world = player.world;
    if (world.isRemote) {
      return;
    }
    Vec3d positionEyes = player.getPositionEyes(0);
    Vec3d lookVec = player.getLook(0).scale(14).add(positionEyes);
    RayTraceResult rtr = world.rayTraceBlocks(positionEyes, lookVec, false, false, true);
    if (rtr != null && rtr.typeOfHit == Type.BLOCK) {
      IBlockState stateHit = world.getBlockState(rtr.getBlockPos());
      if (stateHit.getBlock().isWood(world, rtr.getBlockPos())) {
        Tree tree = Tree.get(rtr.getBlockPos(), world);
        List<BlockPos> wood = tree.getWood();
        MessageBlockBreakProgress message = new MessageBlockBreakProgress(player.getEntityId(),
            10 - (count % 10), wood.toArray(new BlockPos[0]));
        TargetPoint point = new TargetPoint(world.provider.getDimension(), player.posX, player.posY,
            player.posZ, 512);
        NetworkManager.INSTANCE.sendToAllAround(message, point);
      }
    }
  }

  private void scheduleBlockUpdate(World world, BlockPos blockPos) {
    world.scheduleUpdate(blockPos, world.getBlockState(blockPos).getBlock(),
        world.rand.nextInt(15));
  }

  @Override
  public boolean isEffective(IBlockState block) {
    return block.getMaterial().equals(Material.LEAVES);
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
    return false;//chopTree(player, hand, world, pos, blockState);
  }

  @Override
  public boolean leftClickBlockAction(EntityPlayer player, EnumHand hand, World world, BlockPos pos,
      IBlockState blockState, EnumFacing side, Vec3d hit) {
    return false;//chopTree(player, hand, world, pos, blockState);
  }

  @Override
  public boolean rightClickAirAction(EntityPlayer player, EnumHand hand, World world) {
    return false;
  }

  @Override
  public boolean leftClickAirAction(EntityPlayer player, EnumHand hand, World world) {
    return false;
  }
}
