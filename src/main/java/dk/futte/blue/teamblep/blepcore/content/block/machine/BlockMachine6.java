package dk.futte.blue.teamblep.blepcore.content.block.machine;

import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityMachine;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Blue
 */

public class BlockMachine6 extends BlockMachine
{
    public BlockMachine6(MachineData machineData)
    {
        super(machineData);
        this.setDefaultState(this.blockState.getBaseState().withProperty(PROPERTY_FACING_6, EnumFacing.NORTH));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        TileEntityMachine tileEntityMachine = getTileEntity(worldIn, pos);

        if (tileEntityMachine != null)
        {
            Vec3d lookVec = placer.getLookVec();
            EnumFacing placerFacing = EnumFacing.getFacingFromVector((float)lookVec.xCoord, (float)lookVec.yCoord, (float)lookVec.zCoord);
            tileEntityMachine.setFacing(placerFacing.getOpposite());
        }
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack)
    {
        TileEntityMachine tileEntityMachine = getTileEntity(world, pos);

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
        return new BlockStateContainer(this, PROPERTY_FACING_6);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return getExtendedState(state, worldIn, pos);
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        TileEntityMachine tileEntityMachine = getTileEntity(world, pos);
        if (tileEntityMachine != null)
        {
            return state.withProperty(PROPERTY_FACING_6, tileEntityMachine.getFacing());
        }
        return state;
    }
}
