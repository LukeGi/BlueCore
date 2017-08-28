package dk.futte.blue.teamblep.blepcore.content.blocks;

import dk.futte.blue.teamblep.blepcore.content.blocks.tileentity.TileEntityTestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class BlockHandler
{
    public static final BlockBase testBlock = (BlockBase)(new BlockBase(Material.ROCK, "testBlock").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));

    @SubscribeEvent
    public static void blockRegistryEvent(Register<Block> event)
    {
        registerBlocks(event);
        registerTileEntities(event);
    }

    @SubscribeEvent
    public static void itemRegistryEvent(Register<Item> event)
    {
        event.getRegistry().register(testBlock.getItemBlock());
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void modelRegistryEvent(ModelRegistryEvent event)
    {
        ModelLoader.setCustomModelResourceLocation(testBlock.getItemBlock(), 0, new ModelResourceLocation(testBlock.getRegistryName(), "inventory"));
    }

    private static void registerBlocks(Register<Block> event)
    {
        event.getRegistry().register(testBlock);
    }

    private static void registerTileEntities(Register<Block> event)
    {
        GameRegistry.registerTileEntity(TileEntityTestBlock.class, testBlock.getRegistryName().toString());
    }
}
