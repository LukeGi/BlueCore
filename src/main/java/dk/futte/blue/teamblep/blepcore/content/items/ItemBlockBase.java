package dk.futte.blue.teamblep.blepcore.content.items;

import dk.futte.blue.teamblep.blepcore.content.blocks.BlockBase;
import net.minecraft.item.ItemBlock;

public class ItemBlockBase extends ItemBlock
{
    public ItemBlockBase(BlockBase block)
    {
        super(block);
        super.setRegistryName(block.getRegistryName());
    }
}
