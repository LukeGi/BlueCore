package io.github.bluemonster122.bluecore.common;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import io.github.bluemonster122.bluecore.BlueCore;

@Config(modid = BlueCore.MOD_ID, name = BlueCore.MOD_NAME)
public class Configs {

  public static ExampleConfigCategory exampleConfigs = new ExampleConfigCategory(5);

  public static class ExampleConfigCategory {

    @Comment("Config example of a boolean")
    public boolean exampleBool = true;
    @Comment("Config example of an integer")
    public int exampleInt;
    @Comment("Config example of a ranged integer")
    @RangeInt(min = 0, max = 1000)
    public int exampleRangedInt = 50;

    public ExampleConfigCategory(int exampleInt) {
      this.exampleInt = exampleInt;
    }
  }

  @EventBusSubscriber
  public static class EventHandler {

    @SubscribeEvent
    public void onConfigsChanged(ConfigChangedEvent event) {
      if (BlueCore.MOD_ID.equals(event.getModID())) {
        ConfigManager.sync(BlueCore.MOD_ID, Type.INSTANCE);
      }
    }
  }
}
