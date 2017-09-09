package teamblep.blepcore.common.block.machine;

import teamblep.blepcore.common.BlepCore;
import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.inventory.EnumSlotType;
import teamblep.blepcore.common.inventory.GuiHandler;
import teamblep.blepcore.common.inventory.InventoryMachineContainer;
import teamblep.blepcore.common.inventory.container.ContainerCentrifuge;
import teamblep.blepcore.common.inventory.container.ContainerCrusher;
import teamblep.blepcore.common.inventory.container.ContainerElectrolysisChamber;
import teamblep.blepcore.common.inventory.container.ContainerSmelter;
import teamblep.blepcore.common.inventory.gui.GuiCentrifuge;
import teamblep.blepcore.common.inventory.gui.GuiCrusher;
import teamblep.blepcore.common.inventory.gui.GuiElectrolysisChamber;
import teamblep.blepcore.common.inventory.gui.GuiSmelter;
import teamblep.blepcore.common.inventory.slot.SlotBattery;
import teamblep.blepcore.common.inventory.slot.SlotInput;
import teamblep.blepcore.common.inventory.slot.SlotOutput;
import teamblep.blepcore.common.tileentity.core.TileEntityAbstractMachine;
import teamblep.blepcore.common.tileentity.machine.TileEntityCentrifuge;
import teamblep.blepcore.common.tileentity.machine.TileEntityCrusher;
import teamblep.blepcore.common.tileentity.machine.TileEntityElectrolysisChamber;
import teamblep.blepcore.common.tileentity.machine.TileEntitySmelter;
import teamblep.blepcore.common.Names;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Blue
 * @author Kelan
 */

public class MachineData<T extends TileEntityAbstractMachine>
{
    public static final MachineData<TileEntityAbstractMachine> CHASSIS = new MachineData<>(Names.Blocks.MACHINE_CHASSIS, BlockMachine.class, null, null);
    public static final MachineData<TileEntitySmelter> SMELTER = new MachineData<>(Names.Blocks.MACHINE_SMELTER, BlockMachine4.class, TileEntitySmelter.class, new InventoryMachineContainer<TileEntitySmelter>(ContainerSmelter.class, GuiSmelter.class)
    {
        @Override
        protected void init()
        {
            addSlot("inputSlot", 56, 17, EnumSlotType.INPUT, SlotInput.class);
            addSlot("outputSlot", 116, 35, EnumSlotType.OUTPUT, SlotOutput.class);
            addSlot("fuelSlot", 56, 53, EnumSlotType.INPUT, SlotInput.class);
            addSlot("batterySlot", 152, 74, EnumSlotType.BATTERY, SlotBattery.class);
        }
    });
    public static final MachineData<TileEntityCrusher> CRUSHER = new MachineData<>(Names.Blocks.MACHINE_CRUSHER, BlockMachine4.class, TileEntityCrusher.class, new InventoryMachineContainer<TileEntityCrusher>(ContainerCrusher.class, GuiCrusher.class)
    {
        @Override
        protected void init()
        {
            addSlot("inputSlot", 28, 37, EnumSlotType.INPUT, SlotInput.class);
            addSlot("outputSlot1", 82, 37, EnumSlotType.OUTPUT, SlotOutput.class);
            addSlot("outputSlot2", 100, 37, EnumSlotType.OUTPUT, SlotOutput.class);
            addSlot("byproductSlot", 78, 63, EnumSlotType.BYPRODUCT, SlotOutput.class);
            addSlot("batterySlot", 152, 74, EnumSlotType.BATTERY, SlotBattery.class);
        }
    });
    public static final MachineData<TileEntityElectrolysisChamber> ELECTROLYSIS_CHAMBER = new MachineData<>(Names.Blocks.MACHINE_ELECTROLYSIS_CHAMBER, BlockMachine4.class, TileEntityElectrolysisChamber.class, new InventoryMachineContainer<TileEntityElectrolysisChamber>(ContainerElectrolysisChamber.class, GuiElectrolysisChamber.class)
    {
        @Override
        protected void init()
        {
        }
    });
    public static final MachineData<TileEntityCentrifuge> CENTRIFUGE = new MachineData<>(Names.Blocks.MACHINE_CENTRIFUGE, BlockMachine4.class, TileEntityCentrifuge.class, new InventoryMachineContainer<TileEntityCentrifuge>(ContainerCentrifuge.class, GuiCentrifuge.class)
    {
        @Override
        protected void init()
        {
        }
    });

    private String name;
    private BlockMachine block = null;
    private InventoryMachineContainer<T> inventoryContainer;
    private Class<? extends BlockMachine> blockClass;
    private Class<T> tileClass;

    private MachineData(String name, Class<? extends BlockMachine> blockClass, Class<T> tileClass, InventoryMachineContainer<T> inventoryContainer)
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
            BlepCore.logger.error("The machine " + this.getName() + " does not have isInventoryValid block class. How have you done this?");
            return null;
        }

        this.block = Utils.initializeClassWithConstructorAndParameters(blockClass, this);
        return this.block;
    }

    public TileEntityAbstractMachine createTileEntity()
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

    public Class<? extends TileEntityAbstractMachine> getTileEntityClass()
    {
        return tileClass;
    }

    public InventoryMachineContainer<T> getInventoryContainer()
    {
        return inventoryContainer;
    }
}
