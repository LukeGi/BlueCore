package teamblep.blepcore.common.item.tools;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import teamblep.blepcore.common.creativetabs.CreativeTabTool;
import teamblep.blepcore.common.item.ItemBase;

public abstract class ToolBase extends ItemBase {

  private String name;

  public ToolBase(String name) {
    super(name);
    this.name = name;
    this.setCreativeTab(CreativeTabTool.INSTANCE);
    this.setMaxStackSize(1);
  }

  /**
   * This method will determine whether this tool is at all effective at breaking the given block.
   *
   * @param block The block that this tool is attempting to break currently
   * @return true if it is effective, false if not
   */
  public abstract boolean isEffective(IBlockState block);

  /**
   * The speed value used on blocks that the tool is effective at breaking.
   *
   * @return 1.0F is default speed
   */
  public abstract float getEffectiveSpeed();

  /**
   * The speed value used on blocks that the tool is not effective at breaking.
   *
   * @return 1.0F is default speed
   */
  public abstract float getIneffectiveSpeed();

  /**
   * This method will be called when a player right clicks a block.
   *
   * @param player The player right clicking the block
   * @param hand The active hand
   * @param world The world in which the block that was clicked is in
   * @param pos The position in the world the block that was clicked is at
   * @param blockState The state of the block that has just been right clicked
   * @param side The side of the block that was clicked
   * @param hit The position on the block that was clicked
   * @return true if an action is performed and false if not
   */
  public abstract boolean rightClickBlockAction(EntityPlayer player, EnumHand hand, World world,
      BlockPos pos,
      IBlockState blockState, EnumFacing side, Vec3d hit);

  /**
   * This method will be called when a player left clicks a block.
   *
   * @param player The player clicking the block
   * @param hand The active hand
   * @param world The world in which the block that was clicked is in
   * @param pos The position in the world the block that was clicked is at
   * @param blockState The state of the block that has just been clicked
   * @param side The side of the block that was clicked
   * @param hit The position on the block that was clicked
   * @return true if an action is performed and false if not
   */
  public abstract boolean leftClickBlockAction(EntityPlayer player, EnumHand hand, World world,
      BlockPos pos,
      IBlockState blockState, EnumFacing side, Vec3d hit);

  /**
   * This method will be called when a player right clicks air.
   *
   * @param player The player clicking the block
   * @param hand The active hand
   * @param world The world that the player is in
   * @return true if an action is performed and false if not
   */
  public abstract boolean rightClickAirAction(EntityPlayer player, EnumHand hand, World world);

  /**
   * This method will be called when a player left clicks air.
   *
   * @param player The player clicking the block
   * @param hand The active hand
   * @param world The world that the player is in
   * @return true if an action is performed and false if not
   */
  public abstract boolean leftClickAirAction(EntityPlayer player, EnumHand hand, World world);

  public String getName() {
    return name;
  }
}
