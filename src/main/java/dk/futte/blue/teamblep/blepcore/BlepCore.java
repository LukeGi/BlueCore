package dk.futte.blue.teamblep.blepcore;

import dk.futte.blue.teamblep.blepcore.proxy.IProxy;
import dk.futte.blue.teamblep.blepcore.refs.ModInfo;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author Blue
 */
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION)
public class BlepCore
{
    @Mod.Instance(ModInfo.MOD_ID)
    public static BlepCore instance;

    @SidedProxy(clientSide = ModInfo.PROXY_CLIENT, serverSide = ModInfo.PROXY_SERVER)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e)
    {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {

    }
}
