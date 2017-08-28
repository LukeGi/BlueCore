package dk.futte.blue.teamblep.blepcore.content.items;

import dk.futte.blue.teamblep.blepcore.BlepCore;
import dk.futte.blue.teamblep.blepcore.refs.Names;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

/**
 * @author Blue
 */
@Mod.EventBusSubscriber
public class ItemHandler
{
    public static final ItemBase test_item = new ItemBase(Names.Items.TEST_ITEM);

    @SubscribeEvent
    public static void registerItemsEvent(Register<Item> event)
    {
        registerItems(event.getRegistry());
        registerItemModels();
    }

    private static void registerItems(IForgeRegistry<Item> registry)
    {
        registry.register(test_item);
    }

    public static void registerItemModels()
    {
        BlepCore.proxy.registerModel(test_item.getItemStack(), test_item.getRegistryName());
    }
}
