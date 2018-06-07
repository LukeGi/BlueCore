package teamblep.blepcore.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamblep.blepcore.common.item.EventHandlerTool;
import teamblep.blepcore.common.network.NetworkManager;

public class ProxyCommon {

  public void initPre(FMLPreInitializationEvent event) {
    MinecraftForge.EVENT_BUS.register(EventHandlerTool.INSTANCE);
    MinecraftForge.EVENT_BUS.register(Configs.class);
    NetworkManager.register();
  }

  public void init(FMLInitializationEvent event) {

  }

  public void initPost(FMLPostInitializationEvent event) {

  }

  /**
   * Schedules a task to be ran ASAP.
   *
   * @param r the task to be scheduled
   */
  public void scheduleTask(Runnable r) {
    FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(r);
  }
}
