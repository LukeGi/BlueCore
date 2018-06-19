package io.github.bluemonster122.bluecore.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import io.github.bluemonster122.bluecore.common.item.ItemChainsaw;

public final class ModItems {

  public static final Item CHAINSAW = new ItemChainsaw().setRegistryName("chainsaw");

  private static final List<Item> ITEMS = new ArrayList<>();

  public static void registerItems() {
    registerItem(CHAINSAW);
  }

  public static void registerItem(Item item) {
    if (Objects.equals(item.getUnlocalizedName(), "item.null")) {
      item.setUnlocalizedName(item.getRegistryName().toString());
    }
    ITEMS.add(item);
  }

  @SideOnly(Side.CLIENT)
  public static void setInventoryModel(Item item) {
    setModelLocation(item, OreDictionary.WILDCARD_VALUE, "inventory");
  }

  @SideOnly(Side.CLIENT)
  private static void setModelLocation(Item item, int meta, String variantSettings) {
    setModelLocation(item, meta, item.getRegistryName().toString(), variantSettings);
  }

  @SideOnly(Side.CLIENT)
  private static void setModelLocation(Item item, int meta, String location, String variantSettings) {
    if (meta == OreDictionary.WILDCARD_VALUE) {
      ModelLoader.setCustomMeshDefinition(item, stack -> new ModelResourceLocation(location, variantSettings));
    } else {
      ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(location, variantSettings));
    }
  }

  public static List<Item> getItems() {
    return ITEMS;
  }
}
