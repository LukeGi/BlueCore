package io.github.bluemonster122.bluecore.client;

import io.github.bluemonster122.bluecore.common.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public final class ModCreativeTabs {

  public static final CreativeTabs MACHINE_TAB = new CreativeTabs("bluecore:machines") {
    @Override
    public ItemStack getTabIconItem() {
      return new ItemStack(Blocks.FURNACE);
    }
  };
  public static final CreativeTabs TOOL_TAB = new CreativeTabs("bluecore:tools") {
    @Override
    public ItemStack getTabIconItem() {
      return new ItemStack(ModItems.CHAINSAW);
    }
  };
}
