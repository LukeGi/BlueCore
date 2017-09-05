package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;
import dk.futte.blue.teamblep.blepcore.content.tileentity.ProgressBar;
import dk.futte.blue.teamblep.blepcore.content.tileentity.ProgressTracker;
import dk.futte.blue.teamblep.blepcore.content.tileentity.core.TileEntityAbstractMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * @author Blue
 * @author Kelan
 */

public class TileEntitySmelter extends TileEntityAbstractMachine
{
    public static final String PROCESS_BAR = "process_time";
    public static final String FUEL_BAR = "fuel_time";

    public TileEntitySmelter()
    {
        super(MachineData.SMELTER);
    }

    @Override
    protected ProgressTracker createProgressTracker()
    {
        ProgressTracker progressTracker = ProgressTracker.create();
        progressTracker.addProgressBar(new ProgressBar(PROCESS_BAR, 100, 0, false));
        progressTracker.addProgressBar(new ProgressBar(FUEL_BAR, 1200, 0, true));
        return progressTracker;
    }

    @Override
    public boolean updateClient()
    {
        return false;
    }

    @Override
    public boolean updateServer()
    {
        boolean updateClient = false;

        SlotData inputSlot = getMachineData().getInventoryContainer().getSlotData("inputSlot");
        SlotData outputSlot = getMachineData().getInventoryContainer().getSlotData("outputSlot");
        SlotData fuelSlot = getMachineData().getInventoryContainer().getSlotData("fuelSlot");

        ItemStack inputStack = inventory.getStackInSlot(inputSlot.getId());
        ItemStack outputStack = inventory.getStackInSlot(outputSlot.getId());
        ItemStack fuelStack = inventory.getStackInSlot(fuelSlot.getId());

        if (isBurning())
        {
            getProgressTracker().getProgressBar(FUEL_BAR).tick();
            updateClient = true;
        }

        if (isBurning() || !Utils.isItemStackNull(fuelStack) && !Utils.isItemStackNull(inputStack))
        {
            if (!isBurning() && canProcessItem(inputStack, outputStack))
            {
                getProgressTracker().getProgressBar(FUEL_BAR).setTicksRequired(TileEntityFurnace.getItemBurnTime(fuelStack));
                getProgressTracker().getProgressBar(FUEL_BAR).reset();

                if (isBurning())
                {
                    inventory.decrStackSize(fuelSlot.getId(), 1);
                }
            }

            if (isBurning() && canProcessItem(inputStack, outputStack))
            {
                getProgressTracker().getProgressBar(PROCESS_BAR).tick();
                updateClient = true;

                if (!isSmelting())
                {
                    getProgressTracker().getProgressBar(PROCESS_BAR).reset();
                    onProcessItem(inputStack, outputStack);
                }
            } else
            {
                getProgressTracker().getProgressBar(PROCESS_BAR).reset();
            }
        } else if (!isBurning() && isSmelting())
        {
            getProgressTracker().getProgressBar(PROCESS_BAR).reset();
        }

        return updateClient;
    }

    public boolean isSmelting()
    {
        return !getProgressTracker().getProgressBar(PROCESS_BAR).isDone();
    }

    public boolean isBurning()
    {
        return !getProgressTracker().getProgressBar(FUEL_BAR).isDone();
    }

    public boolean canProcessItem(ItemStack inputSlot, ItemStack outputSlot)
    {
        if (!Utils.isItemStackNull(inputSlot))
        {
            ItemStack smeltingResult = FurnaceRecipes.instance().getSmeltingResult(inputSlot);

            if (!Utils.isItemStackNull(smeltingResult))
            {
                if (Utils.isItemStackNull(outputSlot))
                {
                    return true;
                } else if (outputSlot.isItemEqual(smeltingResult))
                {
                    if (outputSlot.stackSize + smeltingResult.stackSize <= outputSlot.getMaxStackSize())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void onProcessItem(ItemStack inputSlot, ItemStack outputSlot)
    {
        if (canProcessItem(inputSlot, outputSlot))
        {
            ItemStack smeltingResult = FurnaceRecipes.instance().getSmeltingResult(inputSlot);

            if (Utils.isItemStackNull(outputSlot))
            {
                inventory.setStackInSlot(getMachineData().getInventoryContainer().getSlotData("outputSlot").getId(), smeltingResult.copy());
            } else if (outputSlot.getItem() == smeltingResult.getItem())
            {
                outputSlot.stackSize += smeltingResult.stackSize;
            }

            inputSlot.stackSize--;

            if (inputSlot.stackSize <= 0)
            {
                inventory.setStackInSlot(getMachineData().getInventoryContainer().getSlotData("inputSlot").getId(), null);
            }
        }
    }
}
