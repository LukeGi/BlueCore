package teamblep.blepcore.common.item.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.ModInfo;
import teamblep.blepcore.common.creativetab.CreativeTab;

import java.util.List;

/**
 * @author Blue
 */
public class ItemBase extends Item
{
    public ItemBase(String name)
    {
        setRegistryName(ModInfo.MOD_ID, name);
        setUnlocalizedName(getRegistryName().toString());
        setCreativeTab(CreativeTab.MAIN_TAB);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        if (CreativeTab.MAIN_TAB.equals(tab))
        {
            list.add(getItemStack());
        }
    }

    public ItemStack getItemStack()
    {
        return new ItemStack(this, 1);
    }
}
