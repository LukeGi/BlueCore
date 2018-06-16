package teamblep.blepcore.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.client.gui.GuiHandlerBlepCore;
import teamblep.blepcore.client.gui.handler.GuiHandlerElectricFurnace;

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

  private static void registerGuiHandler() {
    NetworkRegistry.INSTANCE.registerGuiHandler(BlepCore.MOD_ID, new GuiHandlerBlepCore());
  }

  private static void initialize() {
    INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(BlepCore.MOD_ID);
  }

  private static void registerGuiHandlers() {
    NetworkRegistry.INSTANCE.registerGuiHandler(BlepCore.MOD_ID, new GuiHandlerElectricFurnace());
  }

  private static void registerPackets() {
    INSTANCE.registerMessage(MessageAirClick.class, MessageAirClick.class, 0, Side.SERVER);
    INSTANCE.registerMessage(MessageBlockBreakProgress.class, MessageBlockBreakProgress.class, 1, Side.CLIENT);
  }
}
