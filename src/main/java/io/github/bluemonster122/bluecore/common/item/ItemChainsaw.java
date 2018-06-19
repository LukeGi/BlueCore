package io.github.bluemonster122.bluecore.common.item;

import io.github.bluemonster122.bluecore.common.network.NetworkManager;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import io.github.bluemonster122.bluecore.client.ModCreativeTabs;
import io.github.bluemonster122.bluecore.common.network.MessageBlockBreakProgress;
import io.github.bluemonster122.bluecore.common.util.Tree;

public class ItemChainsaw extends Item {

  public ItemChainsaw() {
    this.setCreativeTab(ModCreativeTabs.TOOL_TAB);
  }

  @Override
  public float getDestroySpeed(ItemStack stack, IBlockState state) {
    if (Objects.equals(state.getMaterial(), Material.LEAVES) || Objects.equals(state.getMaterial(), Material.WOOD)) {
      return 15F;
    } else {
      return super.getDestroySpeed(stack, state);
    }
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
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
        BlockPos topLeft = tree.stream().reduce(BinaryOperator.maxBy(Comparator.comparingLong(bp -> bp.toLong()))).get();
        BlockPos bottomRight = tree.stream().reduce(BinaryOperator.maxBy(Comparator.comparingLong(bp -> -bp.toLong()))).get();
        if (bottomRight != null && topLeft != null) {
          MessageBlockBreakProgress message = new MessageBlockBreakProgress(player.getEntityId(), 10, tree.getWood().toArray(new BlockPos[0]));
          TargetPoint point = new TargetPoint(world.provider.getDimension(), player.posX, player.posY, player.posZ, 512);
          NetworkManager.INSTANCE.sendToAllAround(message, point);
          tree.getWood().forEach((pos) -> {
            IBlockState state = world.getBlockState(pos);
            state.getBlock().harvestBlock(world, player, pos, state, world.getTileEntity(pos), stack);
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

  private void scheduleBlockUpdate(World world, BlockPos blockPos) {
    world.scheduleUpdate(blockPos, world.getBlockState(blockPos).getBlock(), world.rand.nextInt(15));
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
        MessageBlockBreakProgress message = new MessageBlockBreakProgress(player.getEntityId(), 10 - (count % 10), wood.toArray(new BlockPos[0]));
        TargetPoint point = new TargetPoint(world.provider.getDimension(), player.posX, player.posY, player.posZ, 512);
        NetworkManager.INSTANCE.sendToAllAround(message, point);
      }
    }
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    tooltip.add("");
    if (GuiScreen.isShiftKeyDown()) {
      tooltip.add("To use this tool, right click on a log, and it will break the tree.");
    } else {
      tooltip.add(String.format("%sPress %sSHIFT%s for more information about use.", TextFormatting.WHITE, TextFormatting.GREEN, TextFormatting.WHITE));
    }
    tooltip.add("");
    if (GuiScreen.isAltKeyDown()) {
      tooltip.add("This tool is effective against wooden blocks and leafy blocks.");
    } else {
      tooltip.add(String.format("%sPress %sALT%s for more information about effective blocks",TextFormatting.WHITE, TextFormatting.DARK_PURPLE, TextFormatting.WHITE));
    }
  }
}
