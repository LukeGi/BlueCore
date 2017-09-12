package teamblep.blepcore.common.block.material;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamblep.blepcore.common.BlepCore;
import teamblep.blepcore.common.Names;
import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.block.BlockHandler;
import teamblep.blepcore.common.block.core.BlockBase;
import teamblep.blepcore.common.creativetab.CreativeTab;
import teamblep.blepcore.common.item.materials.Substance;

import java.util.List;

/**
 * @author Blue
 */

public class BlockMetal extends BlockBase
{
    public static final IProperty<BlockMetal.Variants> VARIANT = PropertyEnum.create("variant", BlockMetal.Variants.class);

    public BlockMetal()
    {
        super(Material.IRON, Names.Blocks.METAL_BLOCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Variants.COPPER));
    }

    public void registerItemBlockModels()
    {
        ImmutableList<IBlockState> list = this.blockState.getValidStates().asList();
        for (int i = 0; i < list.size(); i++)
        {
            BlepCore.proxy.registerModel(new ItemStack(this, 1, i), getRegistryName(), "variant=" + list.get(i).getValue(VARIANT).getName());
        }
    }

    @Override
    public ItemBlock getItemBlock()
    {
        return new ItemBlockMetal(this);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack)
    {
        return getStateFromMeta(stack.getMetadata());
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer.Builder(this).add(VARIANT).build();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.blockState.getBaseState().withProperty(VARIANT, Variants.byMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        if (CreativeTab.MAIN_TAB.equals(tab))
        {
            for (Variants metals : Variants.values())
            {
                list.add(metals.getItemStack());
            }
        }
    }

    public enum Variants implements IStringSerializable
    {
        COPPER(Substance.COPPER),
        TIN(Substance.TIN),
        LEAD(Substance.LEAD),
        SILVER(Substance.SILVER);

        private Substance substance;
        private ItemStack stack;

        Variants(Substance substance)
        {
            this.substance = substance;
        }

        public static Variants byMeta(int meta)
        {
            return values()[meta % values().length];
        }

        public ItemStack getItemStack()
        {
            return getItemStack(1);
        }

        public ItemStack getItemStack(int amount)
        {
            if (Utils.isItemStackNull(stack))
            {
                stack = new ItemStack(BlockHandler.BLOCK_METAL, 1, getMetadata());
            }
            return amount > 1 ? Utils.copyStackWithSize(stack, amount) : stack;
        }

        public Substance getSubstance()
        {
            return substance;
        }

        public int getColor()
        {
            return substance.getColor();
        }

        public int getMetadata()
        {
            return ordinal();
        }

        @Override
        public String getName()
        {
            return name().toLowerCase();
        }
    }
}
