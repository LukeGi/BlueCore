package teamblep.blepcore.common.creativetabs;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CreativeTabMachine extends CreativeTabBase {

  public static final CreativeTabMachine INSTANCE = new CreativeTabMachine();

  private CreativeTabMachine() {
    super("blep:machines");
  }

  @Override
  public ItemStack getTabIconItem() {
    return new ItemStack(Blocks.LIT_FURNACE);
  }
}
