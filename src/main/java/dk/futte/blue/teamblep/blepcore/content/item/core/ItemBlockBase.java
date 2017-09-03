package dk.futte.blue.teamblep.blepcore.content.item.core;

import dk.futte.blue.teamblep.blepcore.content.block.core.BlockBase;
import net.minecraft.item.ItemBlock;

public class ItemBlockBase extends ItemBlock
{
    public ItemBlockBase(BlockBase block)
    {
        super(block);
        super.setRegistryName(block.getRegistryName());
    }
}
