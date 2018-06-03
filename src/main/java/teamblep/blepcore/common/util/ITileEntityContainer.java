package teamblep.blepcore.common.util;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public interface ITileEntityContainer<T extends TileEntity> extends ITileEntityProvider {
    default Class<? extends TileEntity> getTileClass() {
        return Objects.requireNonNull(this.createNewTileEntity(null, 0)).getClass();
    }

    default T getTile(World world, BlockPos pos) {
        return (T) world.getTileEntity(pos);
    }
}
