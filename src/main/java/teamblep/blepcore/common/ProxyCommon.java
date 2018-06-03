package teamblep.blepcore.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.client.gui.handler.GuiHandlerElectricFurnace;
import teamblep.blepcore.common.item.EventHandlerTool;
import teamblep.blepcore.common.network.AirClickMessage;

public class ProxyCommon {
    public void initPre(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(EventHandlerTool.INSTANCE);
        // NOTE: register packets here.
        NetworkRegistry.INSTANCE.registerGuiHandler(BlepCore.MOD_ID, new GuiHandlerElectricFurnace());
        BlepCore.net.registerMessage(AirClickMessage.class, AirClickMessage.class, 0, Side.SERVER);
    }

    public void init(FMLInitializationEvent event) {

    }

    public void initPost(FMLPostInitializationEvent event) {

    }

    public void scheduleTask(Runnable r) {
        FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(r);
    }
}
