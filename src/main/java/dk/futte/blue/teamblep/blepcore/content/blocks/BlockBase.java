package dk.futte.blue.teamblep.blepcore.content.blocks;

import dk.futte.blue.teamblep.blepcore.content.items.ItemBlockBase;
import dk.futte.blue.teamblep.blepcore.refs.ModInfo;
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
