package teamblep.blepcore.common.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.common.ModItems;

public class CreativeTabTool extends CreativeTabBase {

  public static final CreativeTabs INSTANCE = new CreativeTabTool();

  private CreativeTabTool() {
    super(BlepCore.MOD_ID + ":tools");
  }

  @Override
  public ItemStack getTabIconItem() {
    return new ItemStack(ModItems.CHAINSAW);
  }
}
