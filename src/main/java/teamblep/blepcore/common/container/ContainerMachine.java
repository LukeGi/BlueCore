package teamblep.blepcore.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamblep.blepcore.common.tileentity.TileEntityMachine;

import javax.annotation.Nullable;

public class ContainerMachine<T extends TileEntityMachine> extends Container {
    public T tileEntity;

    public ContainerMachine(InventoryPlayer playerInventory, T tileEntity) {
        this.tileEntity = tileEntity;
        addPlayerSlots(playerInventory);
    }

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

        this.addSlotToContainer(new Slot(playerInventory, 36, -22, 78));
        this.addSlotToContainer(new Slot(playerInventory, 37, -22, 54));
        this.addSlotToContainer(new Slot(playerInventory, 38, -22, 30));
        this.addSlotToContainer(new Slot(playerInventory, 39, -22, 6));

        this.addSlotToContainer(new Slot(playerInventory, 40, 238, 78) {
            @Nullable @SideOnly(Side.CLIENT) public String getSlotTexture() {
                return "minecraft:items/empty_armor_slot_shield";
            }
        });
    }


    @Override public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.getDistanceSq(tileEntity.getPos()) < 196;
    }
}
