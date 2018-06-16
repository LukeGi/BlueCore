package teamblep.blepcore.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ModRegistry {

  @SubscribeEvent
  public static void onRegisterBlocks(Register<Block> event) {
    ModBlocks.getBlocks().forEach(event.getRegistry()::register);
  }

  @SubscribeEvent
  public static void onRegisterItems(Register<Item> event){
    ModItems.getItems().forEach(event.getRegistry()::register);
  }
}
