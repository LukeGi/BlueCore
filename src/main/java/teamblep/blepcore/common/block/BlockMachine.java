package teamblep.blepcore.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockStateContainer.Builder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamblep.blepcore.common.creativetabs.CreativeTabMachine;
import teamblep.blepcore.common.tileentity.TileEntityBase;
import teamblep.blepcore.common.util.ITileEntityContainer;

import java.util.ArrayList;

public abstract class BlockMachine extends BlockBase implements ITileEntityContainer<TileEntityBase> {

    public BlockMachine(String name) {
        super(Material.IRON, name);
        setDefaultState(blockState.getBaseState().withProperty(Properties.FACING4, EnumFacing.NORTH));
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(CreativeTabMachine.INSTANCE);
    }

    @Override protected BlockStateContainer createBlockState() {
        Builder m_builder = new Builder(this);
        m_builder.add(Properties.FACING4);
        return m_builder.build();
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
                                            float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        EnumFacing direction = placer.getHorizontalFacing();
        if (!placer.isSneaking()) direction = direction.getOpposite();
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(
                Properties.FACING4, direction);
    }

    @Override public IBlockState getStateFromMeta(int meta) {
        EnumFacing[] m_allowedValues = Properties.FACING4.getAllowedValues().toArray(new EnumFacing[0]);
        return getDefaultState().withProperty(Properties.FACING4, m_allowedValues[meta & m_allowedValues.length]);
    }

    @Override public int getMetaFromState(IBlockState state) {
        return new ArrayList<>(Properties.FACING4.getAllowedValues()).indexOf(state.getValue(Properties.FACING4));
    }
}
