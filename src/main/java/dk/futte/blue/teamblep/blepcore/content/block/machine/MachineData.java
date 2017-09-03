package dk.futte.blue.teamblep.blepcore.content.block.machine;

import dk.futte.blue.teamblep.blepcore.BlepCore;
import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.inventory.GuiHandler;
import dk.futte.blue.teamblep.blepcore.content.inventory.InventoryMachineContainer;
import dk.futte.blue.teamblep.blepcore.content.inventory.container.*;
import dk.futte.blue.teamblep.blepcore.content.inventory.gui.*;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.*;
import dk.futte.blue.teamblep.blepcore.refs.Names;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Blue
 * @author Kelan
 */

public class MachineData
{
    public static final MachineData CHASSIS = new MachineData(Names.Blocks.MACHINE_CHASSIS, BlockMachine.class, null, null, null);
    public static final MachineData SMELTER = new MachineData(Names.Blocks.MACHINE_SMELTER, BlockMachine4.class, TileEntitySmelter.class, GuiSmelter.class, ContainerSmelter.class);
    public static final MachineData CRUSHER = new MachineData(Names.Blocks.MACHINE_CRUSHER, BlockMachine4.class, TileEntityCrusher.class, GuiCrusher.class, ContainerCrusher.class);
    public static final MachineData ELECTROLYSIS_CHAMBER = new MachineData(Names.Blocks.MACHINE_ELECTROLYSIS_CHAMBER, BlockMachine4.class, TileEntityElectrolysisChamber.class, GuiElectrolysisChamber.class, ContainerElectrolysisChamber.class);
    public static final MachineData CENTRIFUGE = new MachineData(Names.Blocks.MACHINE_CENTRIFUGE, BlockMachine4.class, TileEntityCentrifuge.class, GuiContrifuge.class, ContainerCentrifuge.class);

    private String name;
    private BlockMachine block = null;
    private InventoryMachineContainer inventoryContainer;
    private Class<? extends BlockMachine> blockClass;
    private Class<? extends TileEntityMachine> tileClass;

    private MachineData(String name, Class<? extends BlockMachine> blockClass, Class<? extends TileEntityMachine> tileClass, Class<? extends GuiMachine> guiClass, Class<? extends ContainerMachine> containerClass)
    {
        this.name = name;
        this.blockClass = blockClass;
        this.tileClass = tileClass;

        if (containerClass != null && guiClass != null)
        {
            this.inventoryContainer = new InventoryMachineContainer(containerClass, guiClass);
            GuiHandler.addGuiHandler(this.inventoryContainer);
        }
    }

    public BlockMachine createBlock()
    {
        if (this.getBlockClass() == null)
        {
            BlepCore.logger.error("The machine " + this.getName() + " does not have a block class. How have you done this?");
            return null;
        }

        this.block = Utils.initializeClassWithConstructor(blockClass, this);
        return this.block;
    }

    public TileEntityMachine createTileEntity()
    {
        if (this.getTileEntityClass() == null)
        {
            return null;
        }

        return Utils.initializeClassWithConstructor(tileClass);
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
