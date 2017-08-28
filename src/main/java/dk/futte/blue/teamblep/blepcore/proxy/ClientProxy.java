package dk.futte.blue.teamblep.blepcore.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Blue
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class ClientProxy implements IProxy
{
    private static Map<ItemStack, ModelResourceLocation> modelRegistry = new HashMap<>();
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void modelRegistryEvent(ModelRegistryEvent event)
    {
        for (Map.Entry<ItemStack, ModelResourceLocation> model : modelRegistry.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(model.getKey().getItem(), model.getKey().getItemDamage(), model.getValue());
        }
    }

    public void registerModel(ItemStack itemStack, ResourceLocation resourceLocation)
    {
        modelRegistry.put(itemStack, new ModelResourceLocation(resourceLocation, "inventory"));
    }
}
