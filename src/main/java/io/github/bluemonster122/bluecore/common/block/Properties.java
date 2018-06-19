package io.github.bluemonster122.bluecore.common.block;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;

public class Properties {

  public static final IProperty<EnumFacing> FACING6 = PropertyDirection.create("facing");
  public static final IProperty<EnumFacing> FACING4 = BlockHorizontal.FACING;
  public static final PropertyBool CONNECTED_UP = PropertyBool.create("up");
  public static final PropertyBool CONNECTED_DOWN = PropertyBool.create("down");
  public static final PropertyBool CONNECTED_EAST = PropertyBool.create("east");
  public static final PropertyBool CONNECTED_WEST = PropertyBool.create("west");
  public static final PropertyBool CONNECTED_NORTH = PropertyBool.create("north");
  public static final PropertyBool CONNECTED_SOUTH = PropertyBool.create("south");
}
