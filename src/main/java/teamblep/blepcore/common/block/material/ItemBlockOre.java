package teamblep.blepcore.common.block.material;

import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.ModInfo;
import teamblep.blepcore.common.item.core.ItemBlockBase;

/**
 * @author Blue
 */

public class ItemBlockOre extends ItemBlockBase
{
    public ItemBlockOre(BlockOre block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "tile." + ModInfo.RESOURCE_PREFIX + BlockOre.Variants.byMeta(stack.getMetadata()).getName() + "_ore";
    }
}
