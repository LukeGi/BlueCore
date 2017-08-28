package dk.futte.blue.teamblep.blepcore.content.blocks;

import dk.futte.blue.teamblep.blepcore.BlepCore;
import dk.futte.blue.teamblep.blepcore.content.blocks.tileentity.TileEntityTestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

@Mod.EventBusSubscriber
public class BlockHandler
{
    public static final BlockBase testBlock = (BlockBase)(new BlockBase(Material.ROCK, "testBlock").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));

    @SubscribeEvent
    public static void blockRegistryEvent(Register<Block> event)
    {
        registerBlocks(event.getRegistry());
        registerBlockModels();
        registerTileEntities();
    }

    @SubscribeEvent
    public static void itemRegistryEvent(Register<Item> event)
    {
        event.getRegistry().register(testBlock.getItemBlock());
    }

    private static void registerBlocks(IForgeRegistry<Block> registry)
    {
        registry.register(testBlock);
    }

    private static void registerBlockModels()
    {
        BlepCore.proxy.registerModel(testBlock.getItemStack(), testBlock.getRegistryName());
    }

    private static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityTestBlock.class, testBlock.getRegistryName().toString());
    }
}
