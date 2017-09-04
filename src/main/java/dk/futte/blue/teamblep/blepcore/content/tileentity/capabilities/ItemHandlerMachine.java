package dk.futte.blue.teamblep.blepcore.content.tileentity.capabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Arrays;

/**
 * @author Blue
 */

public class ItemHandlerMachine extends ItemStackHandler
{
    private int[] inputs;
    private int[] outputs;
    private int battery;

    public ItemHandlerMachine(int[] inputs, int[] outputs, int battery)
    {
        super(inputs.length + outputs.length + battery);
        this.inputs = inputs;
        this.outputs = outputs;
        this.battery = battery;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
    {
        if (Arrays.asList(inputs).contains(slot))
        {
            return super.insertItem(slot, stack, simulate);
        }
        if (slot == battery && stack.hasCapability(CapabilityEnergy.ENERGY, null))
        {
            return super.insertItem(slot, stack, simulate);
        }
        return null;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        if (Arrays.asList(outputs).contains(slot))
        {
            return super.extractItem(slot, amount, simulate);
        }
        if (slot == battery)
        {
            return super.extractItem(slot, amount, simulate);
        }
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound compound = super.serializeNBT();
        compound.setIntArray("input", inputs);
        compound.setIntArray("output", outputs);
        compound.setInteger("battery", battery);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("input"))
        {
            inputs = compound.getIntArray("input");
        }
        if (compound.hasKey("output"))
        {
            outputs = compound.getIntArray("output");
        }
        if (compound.hasKey("battery"))
        {
            battery = compound.getInteger("battery");
        }
        super.deserializeNBT(compound);
    }
}
