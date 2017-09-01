package dk.futte.blue.teamblep.blepcore.content.items;

import dk.futte.blue.teamblep.blepcore.BlepCore;
import dk.futte.blue.teamblep.blepcore.content.items.materials.EnumMaterialType;
import dk.futte.blue.teamblep.blepcore.content.items.materials.ItemMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Blue
 */
@Mod.EventBusSubscriber
public class ItemHandler
{
    public static final ItemBase item_material = new ItemMaterial();

    @SubscribeEvent
    public static void registerItemsEvent(Register<Item> event)
    {
        registerItems(event.getRegistry());
    }

    private static void registerItems(IForgeRegistry<Item> registry)
    {
        registry.register(item_material);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels()
    {
        for (int i = 0; i < EnumMaterialType.VARIANTS.length; i++)
        {
            ItemStack material = new ItemStack(item_material, 1, i);
            BlepCore.proxy.registerModel(material, item_material.getRegistryName(), "variant=" + EnumMaterialType.VARIANTS[i].getName());
        }
    }
}
