package dk.futte.blue.teamblep.blepcore.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * @author Blue
 */
public interface IProxy
{
    void registerModel(ItemStack itemStack, ResourceLocation resourceLocation);

    void registerModel(ItemStack itemStack, ResourceLocation resourceLocation, String variant);

    World getClientWorld();

    EntityPlayer getClientPlayer();
}
