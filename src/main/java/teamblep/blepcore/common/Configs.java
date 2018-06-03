package teamblep.blepcore.common;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeInt;
import teamblep.blepcore.BlepCore;

@Config(modid = BlepCore.MOD_ID, name = BlepCore.MOD_NAME)
public class Configs {
    public static ExampleConfigCategory exampleConfigs = new ExampleConfigCategory(5);

    public static class ExampleConfigCategory {
        public ExampleConfigCategory(int exampleInt) {
            this.exampleInt = exampleInt;
        }

        @Comment("Config example of a boolean")
        public boolean exampleBool = true;
        @Comment("Config example of an integer")
        public int exampleInt;
        @Comment("Config example of a ranged integer")
        @RangeInt(min = 0, max = 1000)
        public int exampleRangedInt = 50;
    }
}
