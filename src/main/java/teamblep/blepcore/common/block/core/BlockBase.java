package teamblep.blepcore.common.block.core;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.ModInfo;
import teamblep.blepcore.common.creativetab.CreativeTab;
import teamblep.blepcore.common.item.core.ItemBlockBase;

import java.util.List;

/**
 * @author Blue
 */

public class BlockBase extends Block
{
    public BlockBase(Material material, String name)
    {
        super(material);
        super.setRegistryName(ModInfo.MOD_ID, name);
        super.setUnlocalizedName(getRegistryName().toString());
        super.setCreativeTab(CreativeTab.MAIN_TAB);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        if (CreativeTab.MAIN_TAB.equals(tab))
        {
            list.add(getItemStack());
        }
    }

    public ItemBlock getItemBlock()
    {
        return new ItemBlockBase(this);
    }

    public ItemStack getItemStack()
    {
        return new ItemStack(this, 1);
    }
}
