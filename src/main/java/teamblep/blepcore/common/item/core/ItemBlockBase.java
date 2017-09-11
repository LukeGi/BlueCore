package teamblep.blepcore.common.item.core;

import net.minecraft.item.ItemBlock;
import teamblep.blepcore.common.block.core.BlockBase;

public class ItemBlockBase extends ItemBlock
{
    public ItemBlockBase(BlockBase block)
    {
        super(block);
        super.setRegistryName(block.getRegistryName());
    }
}
