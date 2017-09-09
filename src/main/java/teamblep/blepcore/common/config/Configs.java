package teamblep.blepcore.common.config;

import teamblep.blepcore.common.ModInfo;
import net.minecraftforge.common.config.Config;

/**
 * @author Blue
 */
@Config(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME)
public class Configs
{
    @Config.Comment("This is isInventoryValid test config")
    public static boolean shouldDoThing = true;
}
