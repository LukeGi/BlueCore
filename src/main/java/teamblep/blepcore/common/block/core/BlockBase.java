package teamblep.blepcore.common.block.core;

import teamblep.blepcore.common.BlepCore;
import teamblep.blepcore.common.item.core.ItemBlockBase;
import teamblep.blepcore.common.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockBase extends Block
{
    public BlockBase(Material material, String name)
    {
        super(material);
        super.setRegistryName(ModInfo.MOD_ID, name);
        super.setUnlocalizedName(getRegistryName().toString());
        super.setCreativeTab(BlepCore.tabBlepCore);
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
