package io.github.bluemonster122.bluecore;

import io.github.bluemonster122.bluecore.common.ProxyCommon;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = BlueCore.MOD_ID, name = BlueCore.MOD_NAME, version = BlueCore.VERSION)
public class BlueCore {

  public static final String MOD_ID = "bluecore";
  public static final String MOD_NAME = "Blue Core";
  public static final String VERSION = "1.0.0";

  @Instance
  public static BlueCore instance;

  @SidedProxy(serverSide = "io.github.bluemonster122.bluecore.common.ProxyCommon", clientSide = "io.github.bluemonster122.bluecore.client.ProxyClient")
  public static ProxyCommon proxy;

  /**
   * Private mod logger.
   */
  private static Logger logger;

  /**
   * Static encapsulation of mod logger.
   *
   * @return mod logger
   */
  public static Logger log() {
    return logger;
  }

  /**
   * Uses mod logger and String.format to write a message to the console.
   *
   * @param message the message string to be formatted and then written to the console
   * @param args the args for formatting the message
   */
  public static void logf(String message, String... args) {
    log(String.format(message, args));
  }

  /**
   * Uses mod logger on info level to write a message to console.
   *
   * @param message the message you would like to write to console
   */
  public static void log(String message) {
    logger.info(String.format("[%s] %s", MOD_NAME, message));
  }

  /**
   * Uses mod logger on error level to write a message to console.
   *
   * @param message the message you would like to write to console
   */
  public static void err(String message) {
    logger.error(String.format("[%s Error] %s", MOD_NAME, message));
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
