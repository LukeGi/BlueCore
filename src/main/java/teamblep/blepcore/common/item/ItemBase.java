package teamblep.blepcore.common.item;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import teamblep.blepcore.BlepCore;

public abstract class ItemBase extends Item {

  public ItemBase(String name) {
    setup(name);
  }

  protected void setup(String name) {
    this.setRegistryName(BlepCore.MOD_ID, name);
    this.setUnlocalizedName(BlepCore.MOD_ID + ":" + name);
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    super.addInformation(stack, worldIn, tooltip, flagIn);
    Collections.addAll(tooltip, getBasicInformation(stack, worldIn));
    if (hasShiftInformation()) {
      if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
        Collections.addAll(tooltip, getShiftInformation(stack, worldIn));
      } else {
        tooltip.add("Press " + TextFormatting.GREEN + Keyboard.getKeyName(Keyboard.KEY_LSHIFT).toUpperCase() + TextFormatting.RESET + " to show more information");
      }
    }
    if (hasControlInformation()) {
      if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
        Collections.addAll(tooltip, getControlInformation(stack, worldIn));
      } else {
        tooltip.add("Press " + TextFormatting.AQUA + Keyboard.getKeyName(Keyboard.KEY_LCONTROL).toUpperCase() + TextFormatting.RESET + " to show more information");
      }
    }
    if (flagIn.isAdvanced()) {
      Collections.addAll(tooltip, getAdvancedInformation(stack, worldIn));
    }
  }

  /**
   * Gets the basic information about this item.
   *
   * @param stack the stack in question
   * @param world the world that the stack is in
   * @return an array of strings that represent the lines of the tooltip
   */
  protected abstract String[] getBasicInformation(ItemStack stack, World world);

  /**
   * Determines if the item has extra tooltip information to show when the shift key is pressed.
   *
   * @return true if the item has shift information
   */
  protected abstract boolean hasShiftInformation();

  /**
   * Gets the tooltip information shown by this item when the shift key is pressed
   *
   * @param stack the stack in question
   * @param world the world the stack is in
   * @return an array of strings that represent the lines of the tooltip
   */
  protected abstract String[] getShiftInformation(ItemStack stack, World world);

  /**
   * Determines if the item has extra tooltip information to show when the control key is pressed.
   *
   * @return true if the item has extra information
   */
  protected abstract boolean hasControlInformation();

  /**
   * Gets the tooltip information shown by the item when the control key is pressed.
   *
   * @param stack the stack in question
   * @param world the world the stack is in
   * @return an array of strings that represent the lines of the tooltip
   */
  protected abstract String[] getControlInformation(ItemStack stack, World world);

  /**
   * Gets the advanced information about this item (F3 menu advanced information toggled on).
   *
   * @param stack the stack in question
   * @param world the world the stack is in
   * @return an array of strings that represent the lines of the tooltip
   */
  protected abstract String[] getAdvancedInformation(ItemStack stack, World world);
}
