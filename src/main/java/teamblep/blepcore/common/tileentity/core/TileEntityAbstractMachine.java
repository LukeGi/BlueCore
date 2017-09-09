package teamblep.blepcore.common.tileentity.core;

import com.sun.istack.internal.NotNull;
import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.block.machine.MachineData;
import teamblep.blepcore.common.inventory.InventoryMachineContainer;
import teamblep.blepcore.common.recipe.MachineRecipe;
import teamblep.blepcore.common.recipe.inputs.RecipeInput;
import teamblep.blepcore.common.recipe.outputs.RecipeOutput;
import teamblep.blepcore.common.tileentity.ProgressBar;
import teamblep.blepcore.common.tileentity.ProgressTracker;
import teamblep.blepcore.common.tileentity.capabilities.ItemHandlerMachine;
import teamblep.blepcore.common.Names;
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

public abstract class TileEntityAbstractMachine<I extends RecipeInput, O extends RecipeOutput, R extends MachineRecipe<I, O, R>> extends TileEntityAbstractTickable
{
    protected ItemHandlerMachine inventory;
    protected ProgressTracker progressTracker = createProgressTracker();
    protected MachineData<? extends TileEntityAbstractMachine> machineData;
    protected EnumFacing facing = EnumFacing.NORTH;

    public TileEntityAbstractMachine(MachineData<? extends TileEntityAbstractMachine> machineData)
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
    public MachineData<? extends TileEntityAbstractMachine> getMachineData()
    {
        return machineData;
    }

    @NotNull
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
    public abstract I getCurrentRecipeInput();

    /**
     * Returns a RecipeOutput that contains the state of the machines current output slots. This should return a null RecipeOutput if
     * the machine cannot process items given it's current state, otherwise it should return a RecipeOutput containing the ItemStack/FluidStack
     * that the machine has in its output slot. If this machine has multiple output slots, this function should check against the recipe result
     * for the current input slot and return the ItemStack in the slot that contains the recipe result, if any do.
     *
     * @return A RecipeOutput that contains the current items/fluids in the output slots for this machine.
     */
    public abstract O getCurrentRecipeOutput();

    public abstract R getCurrentRecipe();

    /**
     * @return The process bar that ticks for this machine to output items.
     */
    public abstract ProgressBar getProcessBar();

    public boolean tickProgressBar(ProgressBar progressBar, boolean doTick, boolean doReset)
    {
        if (!progressBar.isDone() && doTick)
        {
            progressBar.tick();
        }

        if (progressBar.isDone())
        {
            if (doReset)
            {
                progressBar.reset();
            }
            return true;
        }
        return false;
    }

    /**
     * Returns true if this machine can start processing based on its current state, including the energy it has stored
     * the input item or fluid, and the item(s) or fluid(s) in the recipeItemOutput slots.
     *
     * @param simulate True if this should not actually change anything such as progress bars or itemstacks.
     * @return true if this machine can start processing.
     */
    public boolean canProcess(boolean simulate)
    {
        ProgressBar processBar = getProcessBar();
        if (processBar != null)
        {
            if (isInventoryValid())
            {
                return tickProgressBar(processBar, !simulate, !simulate);
            } else
            {
                if (!simulate)
                {
                    processBar.reset();
                }
                return false;
            }
        }

        return true;
    }

    /**
     * @return True if the items/fluids in the machines inventory are valid for processing.
     */
    public boolean isInventoryValid()
    {
        I currentInputObject = getCurrentRecipeInput();
        O currentRecipeOutput = getCurrentRecipeOutput();

        R currentRecipe = getCurrentRecipe();

        if (currentInputObject != null && currentRecipeOutput != null && currentRecipe != null)
        {
            return currentRecipe.isInventoryValid(currentInputObject, currentRecipeOutput);
        }
        return false;
    }

    /**
     * Called whenever the machine should process the items/fluids inside it.
     */
    public void process()
    {
        Object inputObject = getCurrentRecipeInput().getInput();

        if (inputObject instanceof ItemStack)
        {
            ItemStack inputStack = (ItemStack) inputObject;
            if (!Utils.isItemStackNull(inputStack))
            {
                R recipe = getCurrentRecipe();
                if (recipe != null)
                {
                    InventoryMachineContainer<? extends TileEntityAbstractMachine> inventoryContainer = getMachineData().getInventoryContainer();
                    recipe.processRecipe(getInventory().getItemStacks(), inventoryContainer.getSlotList(), false);
                }
            }
        }
    }

    @Override
    public boolean updateClient()
    {
        return false;
    }

    @Override
    public boolean updateServer()
    {
        I recipeInput = getCurrentRecipeInput();

        if (recipeInput != null)
        {
            if (canProcess(false))
            {
                process();
            }
            return true;
        }
        return false;
    }
}
