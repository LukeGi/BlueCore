package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author Blue
 * @author Kelan
 */

public class TileEntityCrusher extends TileEntityMachine
{
    public TileEntityCrusher()
    {
        super(MachineData.CRUSHER);
    }

    @Override
    protected EnergyStorage createBattery()
    {
        return null;
    }

    @Override
    protected ItemStackHandler createInventory()
    {
        return new ItemStackHandler(5)
        {
            @Override
            protected void onContentsChanged(int slot)
            {

            }
        };
    }

    @Override
    protected FluidTank createTank()
    {
        return null;
    }

    @Override
    protected ProgressTracker createProgressTracker()
    {
        ProgressTracker progressTracker = ProgressTracker.create();
        progressTracker.addProgressBar(new ProgressBar("process_time", 100));
        progressTracker.addProgressBar(new ProgressBar("fuel_time", 1200));
        return progressTracker;
    }

    @Override
    public void updateClient()
    {
        getProgressTracker().tick();
    }

    @Override
    public void updateServer()
    {

    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        return false;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {

    }

    @Override
    public void closeInventory(EntityPlayer player)
    {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return false;
    }
}
