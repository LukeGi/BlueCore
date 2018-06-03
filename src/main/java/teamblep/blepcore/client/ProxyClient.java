package teamblep.blepcore.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamblep.blepcore.common.ProxyCommon;

@SideOnly(Side.CLIENT)
public class ProxyClient extends ProxyCommon {
    @Override
    public void initPre(FMLPreInitializationEvent event) {
        super.initPre(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void initPost(FMLPostInitializationEvent event) {
        super.initPost(event);
    }
}
