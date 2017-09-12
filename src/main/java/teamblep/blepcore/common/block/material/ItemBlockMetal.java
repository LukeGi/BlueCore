package teamblep.blepcore.common.block.material;

import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.ModInfo;
import teamblep.blepcore.common.item.core.ItemBlockBase;

/**
 * @author Blue
 */

public class ItemBlockMetal extends ItemBlockBase
{
    public ItemBlockMetal(BlockMetal block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "tile." + ModInfo.RESOURCE_PREFIX + BlockMetal.Variants.byMeta(stack.getMetadata()).getName() + "_block";
    }
}
