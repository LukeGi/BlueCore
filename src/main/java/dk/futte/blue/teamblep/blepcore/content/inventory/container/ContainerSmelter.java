package dk.futte.blue.teamblep.blepcore.content.inventory.container;

import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntitySmelter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * @author Kelan
 */

public class ContainerSmelter extends ContainerMachine<TileEntitySmelter>
{
    public ContainerSmelter(TileEntitySmelter tileEntity, InventoryPlayer inventoryPlayer)
    {
        super(tileEntity, inventoryPlayer);
        this.tileEntity.getMachineData().getInventoryContainer().addSlotsToContainer(this);
        this.addPlayerSlots(8, 104, 8, 162, 18, 18);
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        return super.transferStackInSlot(player, index);
    }
}
