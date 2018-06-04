package teamblep.blepcore.common;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.common.item.tools.ToolAxe;

@ObjectHolder(BlepCore.MOD_ID) @EventBusSubscriber public class ModItems {

    //    public static final Item EXAMPLE_ITEM = null;
    public static final ToolAxe EXAMPLE_AXE = null;

    @SubscribeEvent public static void onRegisterItems(Register<Item> event) {
        BlepCore.log("Registering Items");
        event.getRegistry().registerAll(new ToolAxe("example_axe"));
    }
}
