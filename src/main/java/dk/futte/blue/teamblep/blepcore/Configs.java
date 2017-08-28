package dk.futte.blue.teamblep.blepcore;

import dk.futte.blue.teamblep.blepcore.refs.ModInfo;
import net.minecraftforge.common.config.Config;

/**
 * @author Blue
 */
@Config(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME)
public class Configs
{
    @Config.Comment("This is a test config")
    public boolean shouldDoThing = true;
}
