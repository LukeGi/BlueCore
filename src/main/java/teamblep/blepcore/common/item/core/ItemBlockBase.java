package teamblep.blepcore.common.item.core;

import teamblep.blepcore.common.block.core.BlockBase;
import net.minecraft.item.ItemBlock;

public class ItemBlockBase extends ItemBlock
{
    public ItemBlockBase(BlockBase block)
    {
        super(block);
        super.setRegistryName(block.getRegistryName());
    }
}
