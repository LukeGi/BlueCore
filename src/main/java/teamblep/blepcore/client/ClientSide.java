package teamblep.blepcore.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamblep.blepcore.common.block.BlockHandler;
import teamblep.blepcore.common.block.material.BlockMetal;
import teamblep.blepcore.common.block.material.BlockOre;
import teamblep.blepcore.common.item.ItemHandler;
import teamblep.blepcore.common.item.materials.ItemMaterial;

/**
 * @author Blue
 */

@SideOnly(Side.CLIENT)
public class ClientSide implements IClientSide
{
    @Override
    public void preinit()
    {

    }

    @Override
    public void init()
    {
        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        itemColors.registerItemColorHandler((stack, tintIndex) -> ItemMaterial.Variants.byMeta(stack.getMetadata()).getColor(), ItemHandler.item_material);

        BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();

        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> tintIndex == 1 ? state.getValue(BlockOre.VARIANT).getColor() : -1, BlockHandler.ORE);

        itemColors.registerItemColorHandler((stack, tintIndex) -> blockColors.colorMultiplier(BlockHandler.ORE.getStateFromMeta(stack.getMetadata()), null, null, tintIndex), BlockHandler.ORE);

        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> state.getValue(BlockMetal.VARIANT).getColor(), BlockHandler.BLOCK_METAL);

        itemColors.registerItemColorHandler((stack, tintIndex) -> blockColors.colorMultiplier(BlockHandler.BLOCK_METAL.getStateFromMeta(stack.getMetadata()), null, null, tintIndex), BlockHandler.BLOCK_METAL);
    }
}
