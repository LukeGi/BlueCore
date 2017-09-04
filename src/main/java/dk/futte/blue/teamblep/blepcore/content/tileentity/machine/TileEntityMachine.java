package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import com.sun.istack.internal.NotNull;
import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import dk.futte.blue.teamblep.blepcore.content.tileentity.core.TileEntityTickable;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/**
 * @author Blue
 * @author Kelan
 */

public abstract class TileEntityMachine extends TileEntityTickable implements ISidedInventory
{
    private static final EnumFacing[] facings = new EnumFacing[]{EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST, null};
    private static final Capability[] capabilities = new Capability[]{CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, CapabilityEnergy.ENERGY, CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY};

    private EnergyStorage battery = createBattery();
    private ItemStackHandler inventory = createInventory();
    private FluidTank tank = createTank();
    private ProgressTracker progressTracker = createProgressTracker();
    private MachineData machineData;
    private EnumFacing facing = EnumFacing.NORTH;

    public TileEntityMachine(MachineData machineData)
    {
        this.machineData = machineData;
    }

    protected abstract EnergyStorage createBattery();

    protected abstract ItemStackHandler createInventory();

    protected abstract FluidTank createTank();

    protected abstract ProgressTracker createProgressTracker();

    public EnergyStorage getBattery()
    {
        return battery;
    }

    public ItemStackHandler getInventory()
    {
        return inventory;
    }

    public FluidTank getTank()
    {
        return tank;
    }

    public ProgressTracker getProgressTracker()
    {
        return progressTracker;
    }

    @NotNull
    public MachineData getMachineData()
    {
        return machineData;
    }

    public EnumFacing getFacing()
    {
        return facing;
    }

    public void setFacing(EnumFacing facing)
    {
        this.facing = facing;
    }

    @Override
    public String getName()
    {
        return machineData.getName();
    }

    @Override
    public boolean hasCustomName()
    {
        return getName() != null && getName().length() > 0;
    }

    @NotNull
    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(machineData.getTileName());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (battery != null && capability.equals(CapabilityEnergy.ENERGY))
        {
            return true;
        }
        if (inventory != null && capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY))
        {
            return true;
        }
        if (tank != null && capability.equals(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY))
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (battery != null && capability.equals(CapabilityEnergy.ENERGY))
        {
            return CapabilityEnergy.ENERGY.cast(battery);
        }
        if (inventory != null && capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY))
        {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
        }
        if (tank != null && capability.equals(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY))
        {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank);
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        String key;
        for (Capability capability : capabilities)
        {
            for (EnumFacing facing : facings)
            {
                if (capability != null && hasCapability(capability, facing))
                {
                    key = "[blepcore]" + capability.getName() + "." + facing.getName();
                    compound.setTag(key, capability.writeNBT(getCapability(capability, facing), facing));
                }
            }
        }
        // FACING
        if (this.facing != null)
        {
            compound.setInteger("[BLEPCORE]facing", this.facing.getIndex());
        }
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        String key;
        for (Capability capability : capabilities)
        {
            for (EnumFacing facing : facings)
            {
                key = "[blepcore]" + capability.getName() + "." + facing.getName();
                if (hasCapability(capability, facing) && compound.hasKey(key))
                {
                    capability.readNBT(getCapability(capability, facing), facing, compound.getTag(key));
                }
            }
        }
        if (compound.hasKey("[BLEPCORE]facing"))
        {
            this.facing = EnumFacing.VALUES[compound.getInteger("[BLEPCORE]facing")];
        }
        super.readFromNBT(compound);
    }

    @Override
    public int getSizeInventory()
    {
        return getInventory().getSlots();
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index)
    {
        return getInventory().getStackInSlot(index);
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemstack = getStackInSlot(index).splitStack(count);

        if (getStackInSlot(index).stackSize == 0)
        {
            setInventorySlotContents(index, null);
        }

        return itemstack;
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack stack = getStackInSlot(index);

        if (stack != null && stack.stackSize > 0)
        {
            return getInventory().extractItem(index, stack.stackSize, false);
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack)
    {
        getInventory().setStackInSlot(index, stack);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public final int getField(int id)
    {
        return 0;
    }

    @Override
    public final void setField(int id, int value)
    {

    }

    @Override
    public final int getFieldCount()
    {
        return 0;
    }

    @Override
    public final void clear()
    {

    }
}
