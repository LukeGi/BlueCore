package teamblep.blepcore.common.block.machine;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamblep.blepcore.common.BlepCore;
import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.block.core.BlockTile;
import teamblep.blepcore.common.tileentity.core.TileEntityAbstractMachine;

import javax.annotation.Nullable;

/**
 * @author Blue
 * @author Kelan
 */

public class BlockMachine extends BlockTile
{
    public static final IProperty<EnumFacing> PROPERTY_FACING_4 = PropertyEnum.create("facing", EnumFacing.class, EnumFacing.HORIZONTALS);
    public static final IProperty<EnumFacing> PROPERTY_FACING_6 = PropertyEnum.create("facing", EnumFacing.class, EnumFacing.VALUES);

    protected MachineData machineData;

    public BlockMachine(MachineData machineData)
    {
        super(Material.IRON, machineData.getName());
        this.machineData = machineData;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        int guiID = Utils.getGuiID(machineData);

        if (guiID != -1)
        {
            if (!world.isRemote)
            {
                player.openGui(BlepCore.instance, guiID, world, pos.getX(), pos.getY(), pos.getZ());
            }
            return true;
        }

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return machineData.createTileEntity();
    }

    @Nullable
    protected TileEntityAbstractMachine getTileEntity(IBlockAccess world, BlockPos pos)
    {
        TileEntity tile = world.getTileEntity(pos);
        return tile instanceof TileEntityAbstractMachine ? (TileEntityAbstractMachine) tile : null;
    }

    public MachineData getMachineData()
    {
        return machineData;
    }
}
