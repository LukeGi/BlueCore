package io.github.bluemonster122.bluecore.common.network;

import io.github.bluemonster122.bluecore.BlueCore;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import io.github.bluemonster122.bluecore.client.gui.GuiHandlerBlueCore;
import io.github.bluemonster122.bluecore.client.gui.handler.GuiHandlerElectricFurnace;

public class NetworkManager {

  /**
   * Mod netty wrapper.
   */
  public static SimpleNetworkWrapper INSTANCE;

  public static void register() {
    initialize();
    registerPackets();
    registerGuiHandler();
  }

  private static void initialize() {
    INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(BlueCore.MOD_ID);
  }

  private static void registerPackets() {
    INSTANCE.registerMessage(MessageAirClick.class, MessageAirClick.class, 0, Side.SERVER);
    INSTANCE.registerMessage(MessageBlockBreakProgress.class, MessageBlockBreakProgress.class, 1, Side.CLIENT);
  }

  private static void registerGuiHandler() {
    NetworkRegistry.INSTANCE.registerGuiHandler(BlueCore.MOD_ID, new GuiHandlerBlueCore());
  }

  private static void registerGuiHandlers() {
    NetworkRegistry.INSTANCE.registerGuiHandler(BlueCore.MOD_ID, new GuiHandlerElectricFurnace());
  }
}
