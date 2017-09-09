package teamblep.blepcore.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamblep.blepcore.common.block.BlockHandler;
import teamblep.blepcore.common.item.ItemHandler;
import teamblep.blepcore.common.CommonProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Blue
 * @author Kelan
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy
{
    private static Map<ItemStack, ModelResourceLocation> modelRegistry = new HashMap<>();

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void modelRegistryEvent(ModelRegistryEvent event)
    {
        BlockHandler.registerBlockModels();
        ItemHandler.registerItemModels();
        for (Map.Entry<ItemStack, ModelResourceLocation> model : modelRegistry.entrySet())
        {
            ModelLoader.setCustomModelResourceLocation(model.getKey().getItem(), model.getKey().getItemDamage(), model.getValue());
        }
    }

    @Override
    public void registerModel(ItemStack itemStack, ResourceLocation resourceLocation)
    {
        registerModel(itemStack, resourceLocation, "inventory");
    }

    @Override
    public void registerModel(ItemStack itemStack, ResourceLocation resourceLocation, String variant)
    {
        modelRegistry.put(itemStack, new ModelResourceLocation(resourceLocation, variant));
    }

    @Override
    public World getClientWorld()
    {
        return Minecraft.getMinecraft().theWorld;
    }

    @Override
    public EntityPlayer getClientPlayer()
    {
        return Minecraft.getMinecraft().thePlayer;
    }
}
