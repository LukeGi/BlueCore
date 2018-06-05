package teamblep.blepcore.common;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamblep.blepcore.BlepCore;

@Config(modid = BlepCore.MOD_ID, name = BlepCore.MOD_NAME)
public class Configs {

  public static ExampleConfigCategory exampleConfigs = new ExampleConfigCategory(5);

  @SubscribeEvent
  public void onConfigsChanged(ConfigChangedEvent event) {
    if (BlepCore.MOD_ID.equals(event.getModID())) {
      ConfigManager.sync(BlepCore.MOD_ID, Type.INSTANCE);
    }
  }

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
}
