package teamblep.blepcore.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.common.item.tools.ToolBase;
import teamblep.blepcore.common.item.tools.ToolChainsaw;

public final class ModItems {

  public static final Item CHAINSAW = new ToolChainsaw().setRegistryName("chainsaw");

  private static final List<Item> ITEMS = new ArrayList<>();

  public static void registerItem(Item item) {
    if (Objects.equals(item.getUnlocalizedName(), "item.null")) {
      item.setUnlocalizedName(item.getRegistryName());
    }
    ITEMS.add(item);
  }

  @SubscribeEvent
  public static void onRegisterItems(Register<Item> event) {
    BlepCore.log("Registering Items");
    event.getRegistry().registerAll(new ToolChainsaw("chainsaw"));
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public static void onRegisterModels(ModelRegistryEvent event) {
    registerToolModel(CHAINSAW);
  }

  private static void registerToolModel(ToolBase tool) {
    ModelLoader.setCustomModelResourceLocation(
        tool, 0, new ModelResourceLocation(BlepCore.MOD_ID + ":tools", "tool=" + tool.getName()));
  }

  @SideOnly(Side.CLIENT)
  private static void setModelLocation(Item item, int meta, String variantSettings) {
    setModelLocation(item, meta, item.getRegistryName().toString(), variantSettings);
  }

  @SideOnly(Side.CLIENT)
  private static void setModelLocation(
      Item item, int meta, String location, String variantSettings) {
    if (meta == OreDictionary.WILDCARD_VALUE) {
      ModelLoader.setCustomMeshDefinition(
          item, stack -> new ModelResourceLocation(location, variantSettings));
    } else {
      ModelLoader.setCustomModelResourceLocation(
          item, meta, new ModelResourceLocation(location, variantSettings));
    }
  }

  @SideOnly(Side.CLIENT)
  public static void setInventoryModel(Item item) {
    setModelLocation(item, OreDictionary.WILDCARD_VALUE, "inventory");
  }

  public static List<Item> getItems() {
    return ITEMS;
  }
}
