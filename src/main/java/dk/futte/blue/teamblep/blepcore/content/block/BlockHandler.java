package dk.futte.blue.teamblep.blepcore.content.block;

import dk.futte.blue.teamblep.blepcore.BlepCore;
import dk.futte.blue.teamblep.blepcore.content.block.core.BlockBase;
import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

@Mod.EventBusSubscriber
public class BlockHandler
{
    public static final BlockBase MACHINE_CHASSIS = MachineData.CHASSIS.createBlock();
    public static final BlockBase MACHINE_SMELTER = MachineData.SMELTER.createBlock();
    public static final BlockBase MACHINE_CRUSHER = MachineData.CRUSHER.createBlock();
    public static final BlockBase MACHINE_ELECTROLYSIS_CHAMBER = MachineData.ELECTROLYSIS_CHAMBER.createBlock();
    public static final BlockBase MACHINE_CENTRIFUGE = MachineData.CENTRIFUGE.createBlock();

    @SubscribeEvent
    public static void blockRegistryEvent(Register<Block> event)
    {
        registerBlocks(event.getRegistry());
        registerTileEntities();
    }

    private static void registerBlocks(IForgeRegistry<Block> registry)
    {
        registry.register(MACHINE_CHASSIS);
        registry.register(MACHINE_SMELTER);
        registry.register(MACHINE_CRUSHER);
        registry.register(MACHINE_ELECTROLYSIS_CHAMBER);
        registry.register(MACHINE_CENTRIFUGE);
    }

    private static void registerTileEntities()
    {
        MachineData.SMELTER.registerTileEntity();
        MachineData.CRUSHER.registerTileEntity();
        MachineData.ELECTROLYSIS_CHAMBER.registerTileEntity();
        MachineData.CENTRIFUGE.registerTileEntity();
    }

    @SubscribeEvent
    public static void itemRegistryEvent(Register<Item> event)
    {
        registerItemBlocks(event.getRegistry());
    }

    private static void registerItemBlocks(IForgeRegistry<Item> registry)
    {
        registry.register(MACHINE_CHASSIS.getItemBlock());
        registry.register(MACHINE_SMELTER.getItemBlock());
        registry.register(MACHINE_CRUSHER.getItemBlock());
        registry.register(MACHINE_ELECTROLYSIS_CHAMBER.getItemBlock());
        registry.register(MACHINE_CENTRIFUGE.getItemBlock());
    }

    public static void registerBlockModels()
    {
        BlepCore.proxy.registerModel(MACHINE_CHASSIS.getItemStack(), MACHINE_CHASSIS.getRegistryName());
        BlepCore.proxy.registerModel(MACHINE_SMELTER.getItemStack(), MACHINE_SMELTER.getRegistryName(), "facing=north");
        BlepCore.proxy.registerModel(MACHINE_CRUSHER.getItemStack(), MACHINE_CRUSHER.getRegistryName(), "facing=north");
        BlepCore.proxy.registerModel(MACHINE_ELECTROLYSIS_CHAMBER.getItemStack(), MACHINE_ELECTROLYSIS_CHAMBER.getRegistryName(), "facing=north");
        BlepCore.proxy.registerModel(MACHINE_CENTRIFUGE.getItemStack(), MACHINE_CENTRIFUGE.getRegistryName(), "facing=north");
    }
}
