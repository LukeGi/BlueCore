package teamblep.blepcore.common.block.core;

import teamblep.blepcore.common.tileentity.core.TileEntityAbstractBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author Blue
 */
public abstract class BlockTile extends BlockBase implements ITileEntityProvider
{
    public BlockTile(Material material, String name)
    {
        super(material, name);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (placer instanceof EntityPlayer)
        {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile != null && tile instanceof TileEntityAbstractBase)
            {
                ((TileEntityAbstractBase) tile).player = (EntityPlayer) placer;
            }
        }
    }
}
