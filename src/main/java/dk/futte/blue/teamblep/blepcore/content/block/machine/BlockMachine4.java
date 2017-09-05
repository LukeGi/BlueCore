package dk.futte.blue.teamblep.blepcore.content.block.machine;

/**
 * @author Blue
 */

import dk.futte.blue.teamblep.blepcore.content.tileentity.core.TileEntityAbstractMachine;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Blue
 */

public class BlockMachine4 extends BlockMachine
{
    public BlockMachine4(MachineData machineData)
    {
        super(machineData);
        this.setDefaultState(this.blockState.getBaseState().withProperty(PROPERTY_FACING_4, EnumFacing.NORTH));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        TileEntityAbstractMachine tileEntityMachine = getTileEntity(worldIn, pos);

        if (tileEntityMachine != null)
        {
            tileEntityMachine.setFacing(placer.getHorizontalFacing().getOpposite());
        }
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack)
    {
        TileEntityAbstractMachine tileEntityMachine = getTileEntity(world, pos);

        if (tileEntityMachine != null)
        {
            return getDefaultState().withProperty(PROPERTY_FACING_4, tileEntityMachine.getFacing());
        }

        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, PROPERTY_FACING_4);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return getExtendedState(state, worldIn, pos);
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        TileEntityAbstractMachine tileEntityMachine = getTileEntity(world, pos);
        if (tileEntityMachine != null)
        {
            return state.withProperty(PROPERTY_FACING_4, tileEntityMachine.getFacing());
        }
        return state;
    }
}
