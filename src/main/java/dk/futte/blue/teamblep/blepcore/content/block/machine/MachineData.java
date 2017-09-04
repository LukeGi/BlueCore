package dk.futte.blue.teamblep.blepcore.content.block.machine;

import com.sun.istack.internal.NotNull;
import dk.futte.blue.teamblep.blepcore.BlepCore;
import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.inventory.GuiHandler;
import dk.futte.blue.teamblep.blepcore.content.inventory.InventoryMachineContainer;
import dk.futte.blue.teamblep.blepcore.content.inventory.container.*;
import dk.futte.blue.teamblep.blepcore.content.inventory.gui.*;
import dk.futte.blue.teamblep.blepcore.content.inventory.slot.SlotOutput;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.*;
import dk.futte.blue.teamblep.blepcore.refs.Names;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Blue
 * @author Kelan
 */

public class MachineData
{
    public static final MachineData CHASSIS = new MachineData(Names.Blocks.MACHINE_CHASSIS, BlockMachine.class, null, null);
    public static final MachineData SMELTER = new MachineData(Names.Blocks.MACHINE_SMELTER, BlockMachine4.class, TileEntitySmelter.class, new InventoryMachineContainer<TileEntitySmelter>(ContainerSmelter.class, GuiSmelter.class)
    {
        @Override
        protected void init()
        {
            inventorySlots.add(new SlotData<>("inputSlot", 0, 56, 17, Slot.class));
            inventorySlots.add(new SlotData<>("outputSlot", 1, 116, 35, SlotOutput.class));
            inventorySlots.add(new SlotData<>("fuelSlot", 2, 56, 53, Slot.class));
            inventorySlots.add(new SlotData<>("batterySlot", 3, 152, 74, Slot.class));
        }
    });
    public static final MachineData CRUSHER = new MachineData(Names.Blocks.MACHINE_CRUSHER, BlockMachine4.class, TileEntityCrusher.class, new InventoryMachineContainer<TileEntityCrusher>(ContainerCrusher.class, GuiCrusher.class)
    {
        @Override
        protected void init()
        {
            inventorySlots.add(new SlotData<>("inputSlot",0, 28, 37, Slot.class));
            inventorySlots.add(new SlotData<>("outputSlot1",1, 82, 37, SlotOutput.class));
            inventorySlots.add(new SlotData<>("outputSlot2",2, 100, 37, Slot.class));
            inventorySlots.add(new SlotData<>("byproductSlot",3, 78, 63, Slot.class));
            inventorySlots.add(new SlotData<>("batterySlot",4, 152, 74, Slot.class));
        }
    });
    public static final MachineData ELECTROLYSIS_CHAMBER = new MachineData(Names.Blocks.MACHINE_ELECTROLYSIS_CHAMBER, BlockMachine4.class, TileEntityElectrolysisChamber.class, new InventoryMachineContainer<TileEntityElectrolysisChamber>(ContainerElectrolysisChamber.class, GuiElectrolysisChamber.class)
    {
        @Override
        protected void init() { }
    });
    public static final MachineData CENTRIFUGE = new MachineData(Names.Blocks.MACHINE_CENTRIFUGE, BlockMachine4.class, TileEntityCentrifuge.class, new InventoryMachineContainer<TileEntityCentrifuge>(ContainerCentrifuge.class, GuiCentrifuge.class)
    {
        @Override
        protected void init() { }
    });

    private String name;
    private BlockMachine block = null;
    private InventoryMachineContainer inventoryContainer;
    private Class<? extends BlockMachine> blockClass;
    private Class<? extends TileEntityMachine> tileClass;

    private MachineData(String name, Class<? extends BlockMachine> blockClass, Class<? extends TileEntityMachine> tileClass, InventoryMachineContainer inventoryContainer)
    {
        this.name = name;
        this.blockClass = blockClass;
        this.tileClass = tileClass;
        this.inventoryContainer = inventoryContainer;
        GuiHandler.addGuiHandler(this.inventoryContainer);
    }

    public BlockMachine createBlock()
    {
        if (this.getBlockClass() == null)
        {
            BlepCore.logger.error("The machine " + this.getName() + " does not have a block class. How have you done this?");
            return null;
        }

        this.block = Utils.initializeClassWithConstructorAndParameters(blockClass, this);
        return this.block;
    }

    public TileEntityMachine createTileEntity()
    {
        if (this.getTileEntityClass() == null)
        {
            return null;
        }

        return Utils.initializeClassWithConstructorAndParameters(tileClass);
    }

    public void registerTileEntity()
    {
        GameRegistry.registerTileEntity(this.getTileEntityClass(), this.block.getRegistryName().toString());
    }

    public String getName()
    {
        return name;
    }

    public String getTileName()
    {
        return "tile." + getName() + ".name";
    }

    public Class<? extends BlockMachine> getBlockClass()
    {
        return blockClass;
    }

    public Class<? extends TileEntityMachine> getTileEntityClass()
    {
        return tileClass;
    }

    public InventoryMachineContainer getInventoryContainer()
    {
        return inventoryContainer;
    }
}
