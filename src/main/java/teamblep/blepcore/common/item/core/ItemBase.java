package teamblep.blepcore.common.item.core;

import teamblep.blepcore.common.ModInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Blue
 */
public class ItemBase extends Item
{
    public ItemBase(String name)
    {
        setRegistryName(ModInfo.MOD_ID, name);
        setUnlocalizedName(getRegistryName().toString());
    }

    public ItemStack getItemStack()
    {
        return new ItemStack(this, 1);
    }
}
