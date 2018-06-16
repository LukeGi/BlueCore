package teamblep.blepcore.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.ModItems;

public final class ModCreativeTabs {
  public static final CreativeTabs MACHINE_TAB =
      new CreativeTabs("blepcore:machines") {
        @Override
        public ItemStack getTabIconItem() {
          return new ItemStack(Blocks.FURNACE);
        }
      };
  public static final CreativeTabs TOOL_TAB =
      new CreativeTabs("blepcore:tools") {
        @Override
        public ItemStack getTabIconItem() {
          return new ItemStack(ModItems.CHAINSAW);
        }
      };
}
