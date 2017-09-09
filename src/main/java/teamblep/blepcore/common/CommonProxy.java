package teamblep.blepcore.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * @author Blue
 * @author Kelan
 */
public class CommonProxy implements IProxy
{
    @Override
    public void registerModel(ItemStack itemStack, ResourceLocation resourceLocation)
    {
        BlepCore.logger.error("Failed to register model for " + itemStack.getDisplayName() + ". This should not be done on server-side.");
    }

    @Override
    public void registerModel(ItemStack itemStack, ResourceLocation resourceLocation, String variant)
    {
        BlepCore.logger.error("Failed to register model for " + itemStack.getDisplayName() + ". This should not be done on server-side.");
    }

    @Override
    public World getClientWorld()
    {
        Utils.crashWithException(new IllegalAccessException("Unable to get client-side world instance on server-side. This doesn't make sense"));
        return null;
    }

    @Override
    public EntityPlayer getClientPlayer()
    {
        Utils.crashWithException(new IllegalAccessException("Unable to get client-side player instance on server-side. This doesn't make sense"));
        return null;
    }
}
