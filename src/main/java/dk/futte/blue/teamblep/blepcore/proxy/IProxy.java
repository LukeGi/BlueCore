package dk.futte.blue.teamblep.blepcore.proxy;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author Blue
 */
public interface IProxy
{
    void registerModel(ItemStack itemStack, ResourceLocation resourceLocation);

    void registerModel(ItemStack itemStack, ResourceLocation resourceLocation, String variant);
}
