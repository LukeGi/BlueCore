package teamblep.blepcore.common.block.material;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.Names;
import teamblep.blepcore.common.block.core.BlockBase;
import teamblep.blepcore.common.item.materials.EnumMaterial;
import teamblep.blepcore.common.item.materials.EnumMaterialType;

import java.util.List;

/**
 * @author Blue
 */

public class BlockOre extends BlockBase
{
    public static final IProperty<EnumMaterial> PROPERTY_MATERIAL = PropertyEnum.create("material", EnumMaterial.class, m -> m.hasType(EnumMaterialType.ORE));

    public BlockOre()
    {
        super(Material.ROCK, Names.Blocks.ORE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(PROPERTY_MATERIAL, EnumMaterial.COPPER));
    }

    @Override
    public ItemBlock getItemBlock()
    {
        return new ItemBlockOre(this);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer.Builder(this).add(PROPERTY_MATERIAL).build();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return PROPERTY_MATERIAL.getAllowedValues().stream().filter(enumMaterial -> enumMaterial.getMeta() == meta).findFirst().map(enumMaterial -> this.blockState.getBaseState().withProperty(PROPERTY_MATERIAL, enumMaterial)).orElseGet(this::getDefaultState);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        EnumMaterial value = state.getValue(PROPERTY_MATERIAL);
        return PROPERTY_MATERIAL.getAllowedValues().stream().filter(s -> s.equals(value)).findFirst().orElse(EnumMaterial.COPPER).getMeta();
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        PROPERTY_MATERIAL.getAllowedValues().stream().map(material -> new ItemStack(this, 1, material.getMeta())).forEach(list::add);
    }
}
