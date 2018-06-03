package teamblep.blepcore.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.client.gui.handler.GuiHandlerElectricFurnace;

public class ProxyCommon {
    public void initPre(FMLPreInitializationEvent event) {
        // NOTE: register packets here.
        NetworkRegistry.INSTANCE.registerGuiHandler(BlepCore.MOD_ID, new GuiHandlerElectricFurnace());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void initPost(FMLPostInitializationEvent event) {

    }
}
