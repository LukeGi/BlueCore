package io.github.bluemonster122.bluecore.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public final class ModRegistry {

  @SubscribeEvent
  public static void onRegisterBlocks(Register<Block> event) {
    ModBlocks.getBlocks().forEach(event.getRegistry()::register);
  }

  @SubscribeEvent
  public static void onRegisterItems(Register<Item> event) {
    ModItems.getItems().forEach(event.getRegistry()::register);
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public static void onRegisterModels(ModelRegistryEvent event) {
    ModItems.getItems().forEach(ModItems::setInventoryModel);
  }

  public static void preInit(FMLPreInitializationEvent event) {
    ModBlocks.registerBlocks();
    ModItems.registerItems();
    ModBlocks.registerTileEntities();
  }
}
