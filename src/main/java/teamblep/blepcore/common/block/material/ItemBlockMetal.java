package teamblep.blepcore.common.block.material;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.ModInfo;

/**
 * @author Blue
 */

public class ItemBlockMetal extends ItemBlock
{
    public ItemBlockMetal(BlockMetal block)
    {
        super(block);
        setHasSubtypes(true);
        setRegistryName(block.getRegistryName());
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "tile." + ModInfo.RESOURCE_PREFIX + BlockMetal.Variants.byMeta(stack.getMetadata()).getName() + "_block";
    }
}
