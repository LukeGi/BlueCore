package teamblep.blepcore.common;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.common.item.tools.ToolChainsaw;

@ObjectHolder(BlepCore.MOD_ID)
@EventBusSubscriber
public class ModItems {

  //    public static final Item EXAMPLE_ITEM = null;
  public static final ToolChainsaw CHAINSAW = null;

  @SubscribeEvent
  public static void onRegisterItems(Register<Item> event) {
    BlepCore.log("Registering Items");
    event.getRegistry().registerAll(new ToolChainsaw("chainsaw"));
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public static void onRegisterModels(ModelRegistryEvent event) {
    // NOTE: register Models here.
  }
}
