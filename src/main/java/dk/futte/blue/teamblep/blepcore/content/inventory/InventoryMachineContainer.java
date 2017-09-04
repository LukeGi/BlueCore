package dk.futte.blue.teamblep.blepcore.content.inventory;

import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.block.machine.BlockMachine;
import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerMachine;
import dk.futte.blue.teamblep.blepcore.content.inventory.gui.GuiMachine;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityMachine;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import java.util.*;

/**
 * @author Kelan
 */

public abstract class InventoryMachineContainer<T extends TileEntityMachine> implements IGuiHandler
{
    protected Class<? extends ContainerMachine<T>> containerClass;
    protected Class<? extends GuiMachine<T, ? extends ContainerMachine<T>>> guiClass;
    private ArrayList<SlotData> inventorySlots = new ArrayList<>();
    private HashMap<EnumSlotType, ArrayList<SlotData>> slotsByType = new HashMap<>();

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
            System.out.println("Adding slot " + slotData.getName() + " to container with ID " + slotData.getId());
            Slot slot = Utils.initializeClassWithConstructor(slotData.getSlotClass(), new Class<?>[]{IInventory.class, int.class, int.class, int.class}, new Object[]{container.getTileEntity(), slotData.id, slotData.x, slotData.y}); //TODO: add support for custom slot classes that have different constructor parameters
            container.addSlotToContainer(slot);
        }
    }

    public SlotData<Slot> getSlotData(String name)
    {
        if (name != null && name.length() > 0)
        {
            for (SlotData<Slot> slotData : inventorySlots)
            {
                if (name.equals(slotData.getName()))
                {
                    return slotData;
                }
            }
        }
        return null;
    }

    public SlotData<Slot> getSlotData(int id)
    {
        if (id >= 0)
        {
            for (SlotData<Slot> slotData : inventorySlots)
            {
                if (id == slotData.getId())
                {
                    return slotData;
                }
            }
        }
        return null;
    }

    protected <S extends Slot> void addSlot(String name, int x, int y, EnumSlotType slotType, Class<S> slotClass)
    {
        SlotData<S> slotData = new SlotData<>(name, inventorySlots.size(), x, y, slotType, slotClass);

        if (!slotsByType.containsKey(slotType) || slotsByType.get(slotType) == null)
        {
            slotsByType.put(slotType, new ArrayList<SlotData>());
        }

        slotsByType.get(slotType).add(slotData);
        inventorySlots.add(slotData);
    }

    public List<SlotData> getUnmodifiableSlotList()
    {
        return new ArrayList<>(inventorySlots);
    }

    public List<SlotData> getSlotsWithType(EnumSlotType slotType)
    {
        return new ArrayList<>(slotsByType.get(slotType));
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
