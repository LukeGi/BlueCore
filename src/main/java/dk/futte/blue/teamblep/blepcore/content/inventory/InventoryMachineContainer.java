package dk.futte.blue.teamblep.blepcore.content.inventory;

import com.sun.istack.internal.NotNull;
import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.block.machine.BlockMachine;
import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerMachine;
import dk.futte.blue.teamblep.blepcore.content.inventory.gui.GuiMachine;
import dk.futte.blue.teamblep.blepcore.content.tileentity.core.TileEntityAbstractMachine;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.*;

/**
 * @author Kelan
 */

public abstract class InventoryMachineContainer<T extends TileEntityAbstractMachine> implements IGuiHandler
{
    protected Class<? extends ContainerMachine<T>> containerClass;
    protected Class<? extends GuiMachine<T, ? extends ContainerMachine<T>>> guiClass;
    private List<SlotData> inventorySlots = new ArrayList<>();
    private Map<EnumSlotType, ArrayList<SlotData>> slotsByType = new EnumMap<>(EnumSlotType.class);

    public InventoryMachineContainer(Class<? extends ContainerMachine<T>> containerClass, Class<? extends GuiMachine<T, ? extends ContainerMachine<T>>> guiClass)
    {
        this.containerClass = containerClass;
        this.guiClass = guiClass;
        init();
        Collections.sort(inventorySlots); //don't generify this. Intellij is being stupid and things will break in MachineData.class
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(pos);
        Block block = world.getBlockState(pos).getBlock();

        if (block instanceof BlockMachine)
        {
            MachineData machineData = ((BlockMachine) block).getMachineData();

            if (tile != null && machineData.getTileEntityClass() != null && machineData.getTileEntityClass().isInstance(tile))
            {
                return Utils.initializeClassWithConstructorAndParameters(getContainerClass(), tile, player.inventory);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(pos);
        Block block = world.getBlockState(pos).getBlock();

        if (block instanceof BlockMachine)
        {
            MachineData machineData = ((BlockMachine) block).getMachineData();

            if (tile != null && machineData.getTileEntityClass() != null && machineData.getTileEntityClass().isInstance(tile))
            {
                ContainerMachine<T> container = Utils.initializeClassWithConstructorAndParameters(getContainerClass(), tile, player.inventory);

                return Utils.initializeClassWithConstructorAndParameters(getGuiClass(), tile, container);
            }
        }

        return null;
    }

    protected abstract void init();

    public void addSlotsToContainer(ContainerMachine<T> container)
    {
        //this does not allow for inventory slots to be moved, this may be fine or this may need to be changed if a button opens something that shifts the inventory, or NEI shifts it.
        //TODO: allow for this to happen ^^

        for (SlotData<?> slotData : inventorySlots)
        {
            IItemHandler inventory = container.getTileEntity().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            SlotItemHandler slot = Utils.initializeClassWithConstructor(slotData.getSlotClass(), new Class<?>[]{IItemHandler.class, int.class, int.class, int.class}, new Object[]{inventory, slotData.id, slotData.x, slotData.y});
            //TODO: addRecipe support for custom slot classes that have different constructor parameters
            container.addSlotToContainer(slot);
        }
    }

    public SlotData<SlotItemHandler> getSlotData(String name)
    {
        if (name != null && name.length() > 0)
        {
            for (SlotData<SlotItemHandler> slotData : inventorySlots)
            {
                if (name.equals(slotData.getName()))
                {
                    return slotData;
                }
            }
        }
        return null;
    }

    public SlotData<SlotItemHandler> getSlotData(int id)
    {
        if (id >= 0)
        {
            for (SlotData<SlotItemHandler> slotData : inventorySlots)
            {
                if (id == slotData.getId())
                {
                    return slotData;
                }
            }
        }
        return null;
    }

    protected <S extends SlotItemHandler> void addSlot(String name, int x, int y, EnumSlotType slotType, Class<S> slotClass)
    {
        SlotData<S> slotData = new SlotData<>(name, inventorySlots.size(), x, y, slotType, slotClass);

        if (!slotsByType.containsKey(slotType) || slotsByType.get(slotType) == null)
        {
            slotsByType.put(slotType, new ArrayList<>());
        }

        slotsByType.get(slotType).add(slotData);
        inventorySlots.add(slotData);
    }

    public List<SlotData> getSlotList()
    {
        return new ArrayList<>(inventorySlots);
    }


    public Map<EnumSlotType, List<SlotData>> getSlotsByType()
    {
        return new EnumMap<>(slotsByType);
    }

    public int[] getSlotIDArrayFor(EnumSlotType slotType)
    {
//        List<SlotData> slotDataList = getSlotsWithType(slotType);
//        int[] arr = new int[slotDataList.size()];
//
//        for (int i = 0; i < arr.length; i++)
//        {
//            arr[i] = slotDataList.get(i).getId();
//        }
        return getSlotsWithType(slotType).stream().map(SlotData::getId).mapToInt(Integer::intValue).toArray();
    }

    @NotNull
    public List<SlotData> getSlotsWithType(EnumSlotType slotType)
    {
        ArrayList<SlotData> slotData = slotsByType.get(slotType);
        if (slotData != null && !slotData.isEmpty())
        {
            return new ArrayList<>(slotData);
        }
        return new ArrayList<>();
    }

    public int getNumSlots()
    {
        return inventorySlots.size();
    }

    public Class<? extends GuiMachine<T, ? extends ContainerMachine<T>>> getGuiClass()
    {
        return guiClass;
    }

    public Class<? extends ContainerMachine<T>> getContainerClass()
    {
        return containerClass;
    }
}
