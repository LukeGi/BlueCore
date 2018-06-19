package io.github.bluemonster122.bluecore.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import io.github.bluemonster122.bluecore.common.block.BlockElectricFurnace;
import io.github.bluemonster122.bluecore.common.tileentity.TileEntityElectricFurnace;

public final class ModBlocks {

  public static final Block ELECTRIC_FURNACE = new BlockElectricFurnace().setRegistryName("electric_furnace");

  private static final List<Block> BLOCKS = new ArrayList<>();

  public static void registerBlocks() {
    registerBlock(ELECTRIC_FURNACE);
  }

  public static void registerBlock(Block block) {
    registerBlock(block, new ItemBlock(block));
  }

  public static void registerBlock(Block block, @Nullable Item item) {
    if (Objects.equals(block.getUnlocalizedName(), "tile.null")) {
      block.setUnlocalizedName(block.getRegistryName().toString());
    }
    BLOCKS.add(block);
    if (item != null) {
      ModItems.registerItem(item.setRegistryName(block.getRegistryName()));
    }
  }

  public static void registerTileEntities() {
    registerTileEntity(TileEntityElectricFurnace.class, "bluecore:electric_furnace");
  }

  public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
    TileEntity.register(id, tileEntityClass);
  }

  @SideOnly(Side.CLIENT)
  public static void setInventoryModel(Block block) {
    ModItems.setInventoryModel(Item.getItemFromBlock(block));
  }

  public static List<Block> getBlocks() {
    return BLOCKS;
  }
}
