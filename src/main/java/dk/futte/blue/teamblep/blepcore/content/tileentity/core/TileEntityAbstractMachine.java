package dk.futte.blue.teamblep.blepcore.content.tileentity.core;

import com.sun.istack.internal.NotNull;
import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import dk.futte.blue.teamblep.blepcore.content.recipe.inputs.RecipeInput;
import dk.futte.blue.teamblep.blepcore.content.recipe.outputs.RecipeOutput;
import dk.futte.blue.teamblep.blepcore.content.tileentity.ProgressTracker;
import dk.futte.blue.teamblep.blepcore.content.tileentity.capabilities.ItemHandlerMachine;
import dk.futte.blue.teamblep.blepcore.refs.Names;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

/**
 * @author Blue
 * @author Kelan
 */

public abstract class TileEntityAbstractMachine extends TileEntityAbstractTickable
{
    protected ItemHandlerMachine inventory;
    protected ProgressTracker progressTracker = createProgressTracker();
    protected MachineData machineData;
    protected EnumFacing facing = EnumFacing.NORTH;

    public TileEntityAbstractMachine(MachineData machineData)
    {
        if (machineData == null)
        {
            Utils.crashWithException(new IllegalStateException("A TileEntityAbstractMachine was created with a null MachineData object. This doesn't make any sense to do."));
        } else
        {
            this.machineData = machineData;
            if (machineData.getInventoryContainer() != null)
            {
                this.inventory = new ItemHandlerMachine(machineData.getInventoryContainer())
                {
                    @Override
                    protected void onContentsChanged(int slot)
                    {
                        notifyClient();
                    }
                };
            }
        }
    }

    protected abstract ProgressTracker createProgressTracker();

    public ItemHandlerMachine getInventory()
    {
        return inventory;
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

    @Nullable
    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(getMachineData().getTileName());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY))
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY))
        {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setTag(Names.NBT.INVENTORY, inventory.serializeNBT());
        //PROGRESS TRACKER
        if (progressTracker != null)
        {
            progressTracker.writeToNBT(compound);
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
        inventory.deserializeNBT((NBTTagCompound) compound.getTag(Names.NBT.INVENTORY));
        //PROGRESS TRACKER
        if (createProgressTracker() != null)
        {
            progressTracker.readFromNBT(compound);
        }
        if (compound.hasKey("[BLEPCORE]facing"))
        {
            facing = EnumFacing.VALUES[compound.getInteger("[BLEPCORE]facing")];
        }
        super.readFromNBT(compound);
    }

    /**
     * @return A RecipeInput that contains the current items/fluids in the input slots for this machine. Shouldn't return null.
     */
    public abstract RecipeInput getCurrentRecipeInput();

    /**
     * Returns a RecipeOutput that contains the current items in the machines output slots.
     * @return
     */
    public abstract RecipeOutput getCurrentRecipeOutput();

    /**
     * Returns true if this machine can start processing based on its current state, including the energy it has stored
     * the input item or fluid, and the item(s) or fluid(s) in the recipeItemOutput slots.
     *
     * @param simulate True if this should not tick any progress bars.
     * @return true if this machine can start processing.
     */
    public abstract boolean canProcess(boolean simulate);

    public abstract void process();
}
