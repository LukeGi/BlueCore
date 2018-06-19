package io.github.bluemonster122.bluecore.common.container;

import javax.annotation.Nullable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import io.github.bluemonster122.bluecore.common.tileentity.TileEntityMachine;

public class ContainerMachine<T extends TileEntityMachine> extends Container {

  public T tileEntity;

  public ContainerMachine(InventoryPlayer playerInventory, T tileEntity) {
    this.tileEntity = tileEntity;
    addPlayerSlots(playerInventory);
  }

  /**
   * This adds the player's slots to a machine's container.
   */
  protected void addPlayerSlots(InventoryPlayer playerInventory) {
    // Add vanilla slots
    this.addSlotToContainer(new Slot(playerInventory, 0, 18, 78));
    this.addSlotToContainer(new Slot(playerInventory, 1, 42, 78));
    this.addSlotToContainer(new Slot(playerInventory, 2, 66, 78));
    this.addSlotToContainer(new Slot(playerInventory, 3, 90, 78));
    this.addSlotToContainer(new Slot(playerInventory, 4, 114, 78));
    this.addSlotToContainer(new Slot(playerInventory, 5, 138, 78));
    this.addSlotToContainer(new Slot(playerInventory, 6, 162, 78));
    this.addSlotToContainer(new Slot(playerInventory, 7, 186, 78));
    this.addSlotToContainer(new Slot(playerInventory, 8, 210, 78));

    this.addSlotToContainer(new Slot(playerInventory, 9, 6, 19));
    this.addSlotToContainer(new Slot(playerInventory, 10, 30, 19));
    this.addSlotToContainer(new Slot(playerInventory, 11, 54, 19));
    this.addSlotToContainer(new Slot(playerInventory, 12, 78, 19));
    this.addSlotToContainer(new Slot(playerInventory, 13, 102, 19));
    this.addSlotToContainer(new Slot(playerInventory, 14, 126, 19));
    this.addSlotToContainer(new Slot(playerInventory, 15, 150, 19));
    this.addSlotToContainer(new Slot(playerInventory, 16, 174, 19));
    this.addSlotToContainer(new Slot(playerInventory, 17, 198, 19));

    this.addSlotToContainer(new Slot(playerInventory, 18, 18, 37));
    this.addSlotToContainer(new Slot(playerInventory, 19, 42, 37));
    this.addSlotToContainer(new Slot(playerInventory, 20, 66, 37));
    this.addSlotToContainer(new Slot(playerInventory, 21, 90, 37));
    this.addSlotToContainer(new Slot(playerInventory, 22, 114, 37));
    this.addSlotToContainer(new Slot(playerInventory, 23, 138, 37));
    this.addSlotToContainer(new Slot(playerInventory, 24, 162, 37));
    this.addSlotToContainer(new Slot(playerInventory, 25, 186, 37));
    this.addSlotToContainer(new Slot(playerInventory, 26, 210, 37));

    this.addSlotToContainer(new Slot(playerInventory, 27, 6, 55));
    this.addSlotToContainer(new Slot(playerInventory, 28, 30, 55));
    this.addSlotToContainer(new Slot(playerInventory, 29, 54, 55));
    this.addSlotToContainer(new Slot(playerInventory, 30, 78, 55));
    this.addSlotToContainer(new Slot(playerInventory, 31, 102, 55));
    this.addSlotToContainer(new Slot(playerInventory, 32, 126, 55));
    this.addSlotToContainer(new Slot(playerInventory, 33, 150, 55));
    this.addSlotToContainer(new Slot(playerInventory, 34, 174, 55));
    this.addSlotToContainer(new Slot(playerInventory, 35, 198, 55));

    // BOOTS
    this.addSlotToContainer(new Slot(playerInventory, 36, -22, 78) {
      /**
       * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace
       * fuel.
       */
      public boolean isItemValid(ItemStack stack) {
        return stack.getItem().isValidArmor(stack, EntityEquipmentSlot.FEET, playerInventory.player);
      }

      /**
       * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
       * in the case of armor slots)
       */
      public int getSlotStackLimit() {
        return 1;
      }


      /**
       * Return whether this slot's stack can be taken from this slot.
       */
      public boolean canTakeStack(EntityPlayer playerIn) {
        ItemStack itemstack = this.getStack();
        return !itemstack.isEmpty() && !playerIn.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.canTakeStack(playerIn);
      }

      @Nullable
      @SideOnly(Side.CLIENT)
      public String getSlotTexture() {
        return ItemArmor.EMPTY_SLOT_NAMES[EntityEquipmentSlot.FEET.getIndex()];
      }
    });
    // LEGGINGS
    this.addSlotToContainer(new Slot(playerInventory, 37, -22, 54) {
      /**
       * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
       * in the case of armor slots)
       */
      public int getSlotStackLimit() {
        return 1;
      }

      /**
       * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace
       * fuel.
       */
      public boolean isItemValid(ItemStack stack) {
        return stack.getItem().isValidArmor(stack, EntityEquipmentSlot.LEGS, playerInventory.player);
      }

      /**
       * Return whether this slot's stack can be taken from this slot.
       */
      public boolean canTakeStack(EntityPlayer playerIn) {
        ItemStack itemstack = this.getStack();
        return !itemstack.isEmpty() && !playerIn.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.canTakeStack(playerIn);
      }

      @Nullable
      @SideOnly(Side.CLIENT)
      public String getSlotTexture() {
        return ItemArmor.EMPTY_SLOT_NAMES[EntityEquipmentSlot.LEGS.getIndex()];
      }
    });
    // CHESTPLATE
    this.addSlotToContainer(new Slot(playerInventory, 38, -22, 30) {
      /**
       * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
       * in the case of armor slots)
       */
      public int getSlotStackLimit() {
        return 1;
      }

      /**
       * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace
       * fuel.
       */
      public boolean isItemValid(ItemStack stack) {
        return stack.getItem().isValidArmor(stack, EntityEquipmentSlot.CHEST, playerInventory.player);
      }

      /**
       * Return whether this slot's stack can be taken from this slot.
       */
      public boolean canTakeStack(EntityPlayer playerIn) {
        ItemStack itemstack = this.getStack();
        return !itemstack.isEmpty() && !playerIn.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.canTakeStack(playerIn);
      }

      @Nullable
      @SideOnly(Side.CLIENT)
      public String getSlotTexture() {
        return ItemArmor.EMPTY_SLOT_NAMES[EntityEquipmentSlot.CHEST.getIndex()];
      }
    });
    // HELMET
    this.addSlotToContainer(new Slot(playerInventory, 39, -22, 6) {
      /**
       * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
       * in the case of armor slots)
       */
      public int getSlotStackLimit() {
        return 1;
      }

      /**
       * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace
       * fuel.
       */
      public boolean isItemValid(ItemStack stack) {
        return stack.getItem().isValidArmor(stack, EntityEquipmentSlot.HEAD, playerInventory.player);
      }

      /**
       * Return whether this slot's stack can be taken from this slot.
       */
      public boolean canTakeStack(EntityPlayer playerIn) {
        ItemStack itemstack = this.getStack();
        return !itemstack.isEmpty() && !playerIn.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.canTakeStack(playerIn);
      }

      @Nullable
      @SideOnly(Side.CLIENT)
      public String getSlotTexture() {
        return ItemArmor.EMPTY_SLOT_NAMES[EntityEquipmentSlot.HEAD.getIndex()];
      }
    });

    // OFF_HAND
    this.addSlotToContainer(new Slot(playerInventory, 40, 238, 78) {
      @Nullable
      @SideOnly(Side.CLIENT)
      public String getSlotTexture() {
        return "minecraft:items/empty_armor_slot_shield";
      }
    });
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return playerIn.getDistanceSq(tileEntity.getPos()) < 196;
  }
}
