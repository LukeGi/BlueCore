package teamblep.blepcore.common.creativetabs;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import teamblep.blepcore.BlepCore;

public class CreativeTabMachine extends CreativeTabBase {

  public static final CreativeTabMachine INSTANCE = new CreativeTabMachine();

  private CreativeTabMachine() {
    super(BlepCore.MOD_ID + ":machines");
  }

  @Override
  public ItemStack getTabIconItem() {
    return new ItemStack(Blocks.FURNACE);
  }
}
