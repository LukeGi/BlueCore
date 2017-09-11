package teamblep.blepcore.common.block.material;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.ModInfo;

/**
 * @author Blue
 */

public class ItemBlockOre extends ItemBlock
{
    public ItemBlockOre(BlockOre block)
    {
        super(block);
        setHasSubtypes(true);
        setRegistryName(block.getRegistryName());
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "tile." + ModInfo.RESOURCE_PREFIX + BlockOre.Variants.byMeta(stack.getMetadata()).getName() + "_ore";
    }
}
