package dk.futte.blue.teamblep.blepcore.proxy;

import dk.futte.blue.teamblep.blepcore.BlepCore;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author Blue
 */
public class ServerProxy implements IProxy
{
    @Override
    public void registerModel(ItemStack itemStack, ResourceLocation resourceLocation)
    {
        BlepCore.logger.error("Failed to register model for " + itemStack.getDisplayName() + ". This should not be done on server-side.");
    }
}
