package teamblep.blepcore;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;
import teamblep.blepcore.common.ProxyCommon;

@Mod(modid = BlepCore.MOD_ID, name = BlepCore.MOD_NAME, version = BlepCore.VERSION)
public class BlepCore {

  public static final String MOD_ID = "blepcore";
  public static final String MOD_NAME = "Blep Core";
  public static final String VERSION = "1.0.0";

  @Instance
  public static BlepCore instance;

  @SidedProxy(serverSide = "teamblep.blepcore.client.ProxyCommon", clientSide = "teamblep.blepcore.client.ProxyClient")
  public static ProxyCommon proxy;

  /**
   * Mod netty wrapper
   */
  public static SimpleNetworkWrapper net = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);

  /**
   * Private mod logger
   */
  private static Logger logger;

  /**
   * Static encapsulation of mod logger
   *
   * @return mod logger
   */
  public static Logger log() {
    return logger;
  }

  /**
   * Uses mod logger on info level to write a message to console
   *
   * @param message the message you would like to write to console
   */
  public static void log(String message) {
    logger.info("[Blep Core] " + message);
  }

  /**
   * Uses mod logger and String.format to write a message to the console
   *
   * @param message the message string to be formatted and then written to the console
   * @param args the args for formatting the message
   */
  public static void logf(String message, String... args) {
    log(String.format(message, args));
  }

  /**
   * Uses mod logger on error level to write a message to console
   *
   * @param message the message you would like to write to console
   */
  public static void err(String message) {
    logger.error("[Blep Core Error] " + message);
  }

  @EventHandler
  public void onPreInitialization(FMLPreInitializationEvent event) {
    logger = event.getModLog();
    proxy.initPre(event);
  }

  @EventHandler
  public void onInitialization(FMLInitializationEvent event) {
    proxy.init(event);
  }

  @EventHandler
  public void onPostInitialization(FMLPostInitializationEvent event) {
    proxy.initPost(event);
  }
}
