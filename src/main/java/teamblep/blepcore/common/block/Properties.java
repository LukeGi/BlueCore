package teamblep.blepcore.common.block;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;

public class Properties {

  public static final PropertyEnum<EnumFacing> FACING6 = PropertyEnum
      .create("facing", EnumFacing.class, EnumFacing.VALUES);
  public static final PropertyEnum<EnumFacing> FACING4 = PropertyEnum
      .create("facing", EnumFacing.class, EnumFacing.HORIZONTALS);
  public static final PropertyBool CONNECTED_UP = PropertyBool.create("up");
  public static final PropertyBool CONNECTED_DOWN = PropertyBool.create("down");
  public static final PropertyBool CONNECTED_EAST = PropertyBool.create("east");
  public static final PropertyBool CONNECTED_WEST = PropertyBool.create("west");
  public static final PropertyBool CONNECTED_NORTH = PropertyBool.create("north");
  public static final PropertyBool CONNECTED_SOUTH = PropertyBool.create("south");
}
