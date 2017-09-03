package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import dk.futte.blue.teamblep.blepcore.content.tileentity.core.TileEntityTickable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author Blue
 * @author Kelan
 */

public abstract class TileEntityMachine extends TileEntityTickable
{
    private EnergyStorage battery = createBattery();
    private ItemStackHandler inventory = createInventory();
    private FluidTank tank = createTank();
    private EnumFacing facing = EnumFacing.NORTH;

    protected abstract EnergyStorage createBattery();

    protected abstract ItemStackHandler createInventory();

    protected abstract FluidTank createTank();

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

    public EnumFacing getFacing()
    {
        return facing;
    }

    public void setFacing(EnumFacing facing)
    {
        this.facing = facing;
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
        // BATTERY
        if (battery != null)
        {
            compound.setInteger("[BLEPCORE]energyStored", battery.getEnergyStored());
        }
        // INVENTORY
        if (inventory != null)
        {
            compound.setTag("[BLEPCORE]inventory", inventory.serializeNBT());
        }
        // TANK
        if (tank != null)
        {
            tank.writeToNBT(compound);
        }
        // FACING
        if (facing != null)
        {
            compound.setInteger("[BLEPCORE]facing", facing.getIndex());
        }
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        // BATTERY
        if (compound.hasKey("[BLEPCORE]energyStored"))
        {
            int energyStored = compound.getInteger("[BLEPCORE]energyStored");
            battery = createBattery();
            battery.receiveEnergy(energyStored, false);
        }
        // INVENTORY
        if (compound.hasKey("[BLEPCORE]inventory"))
        {
            NBTTagCompound inventoryNBT = (NBTTagCompound) compound.getTag("[BLEPCORE]inventory");
            inventory.deserializeNBT(inventoryNBT);
        }
        // TANK
        if (createTank() != null)
        {
            tank.readFromNBT(compound);
        }
        if (compound.hasKey("[BLEPCORE]facing"))
        {
            facing = EnumFacing.VALUES[compound.getInteger("[BLEPCORE]facing")];
        }
        super.readFromNBT(compound);
    }
}
