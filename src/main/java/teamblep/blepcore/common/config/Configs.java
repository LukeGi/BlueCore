package teamblep.blepcore.common.config;

import net.minecraftforge.common.config.Config;
import teamblep.blepcore.common.ModInfo;

/**
 * @author Blue
 */
@Config(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME)
public class Configs
{
    @Config.Comment("This is isInventoryValid test config")
    public static boolean shouldDoThing = true;
}
