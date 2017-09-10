package teamblep.blepcore.common.block.material;

import net.minecraft.item.ItemBlock;

/**
 * @author Blue
 */

public class ItemBlockOre extends ItemBlock
{
    public ItemBlockOre(BlockOre block)
    {
        super(block);
        setRegistryName(block.getRegistryName());
    }


}
