package dk.futte.blue.teamblep.blepcore.content.block.machine;

import dk.futte.blue.teamblep.blepcore.content.inventory.GuiHandler;
import dk.futte.blue.teamblep.blepcore.content.inventory.InventoryMachineContainer;
import dk.futte.blue.teamblep.blepcore.content.inventory.container.*;
import dk.futte.blue.teamblep.blepcore.content.inventory.gui.*;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.*;
import dk.futte.blue.teamblep.blepcore.refs.Names;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Blue
 * @author Kelan
 */

public class MachineData
{
    public static final MachineData CHASSIS = new MachineData(Names.Blocks.MACHINE_CHASSIS, BlockMachine.class, null, null, null);
    public static final MachineData SMELTER = new MachineData(Names.Blocks.MACHINE_SMELTER, BlockMachineNSWE.class, TileEntitySmelter.class, GuiSmelter.class, ContainerSmelter.class);
    public static final MachineData CRUSHER = new MachineData(Names.Blocks.MACHINE_CRUSHER, BlockMachineNSWE.class, TileEntityCrusher.class, GuiCrusher.class, ContainerCrusher.class);
    public static final MachineData ELECTROLYSIS_CHAMBER = new MachineData(Names.Blocks.MACHINE_ELECTROLYSIS_CHAMBER, BlockMachineNSWE.class, TileEntityElectrolysisChamber.class, GuiElectrolysisChamber.class, ContainerElectrolysisChamber.class);
    public static final MachineData CENTRIFUGE = new MachineData(Names.Blocks.MACHINE_CENTRIFUGE, BlockMachineNSWE.class, TileEntityCentrifuge.class, GuiContrifuge.class, ContainerCentrifuge.class);

    private String name;
    private Class<? extends BlockMachine> blockClass;
    private BlockMachine block = null;
    private Class<? extends TileEntityMachine> tileClass;
    private InventoryMachineContainer inventoryContainer;

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
        try
        {
            block = this.getBlockClass().getDeclaredConstructor(MachineData.class).newInstance(this);
            return block;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new IllegalStateException("Failed to instantiate " + this.getName() + "'s block.");
        }
    }

    public TileEntityMachine createTileEntity()
    {
        if (this.getTileEntityClass() == null) return null;

        try
        {
            return this.getTileEntityClass().newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new IllegalStateException("Failed to instantiate " + this.getName() + "'s tile entitty.");
        }
    }

    public void registerTileEntity()
    {
        GameRegistry.registerTileEntity(this.getTileEntityClass(), this.block.getRegistryName().toString());
    }

    public String getName()
    {
        return name;
    }

    public Class<? extends BlockMachine> getBlockClass()
    {
        return blockClass;
    }

    public Class<? extends TileEntityMachine> getTileEntityClass()
    {
        return tileClass;
    }
}
