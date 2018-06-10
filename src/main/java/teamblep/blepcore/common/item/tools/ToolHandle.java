package teamblep.blepcore.common.item.tools;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import teamblep.blepcore.common.item.ItemBase;

public class ToolHandle extends ItemBase {

  public ToolHandle(String name) {
    super(name);
    this.setMaxStackSize(4);
  }

  @Override
  protected String[] getBasicInformation(ItemStack stack, World world) {
    return new String[]{"Use me to craft tools"};
  }

  @Override
  protected boolean hasShiftInformation() {
    return false;
  }

  @Override
  protected String[] getShiftInformation(ItemStack stack, World world) {
    return new String[0];
  }

  @Override
  protected boolean hasControlInformation() {
    return false;
  }

  @Override
  protected String[] getControlInformation(ItemStack stack, World world) {
    return new String[0];
  }

  @Override
  protected String[] getAdvancedInformation(ItemStack stack, World world) {
    return new String[0];
  }
}
