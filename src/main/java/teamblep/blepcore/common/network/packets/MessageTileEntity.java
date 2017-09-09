package teamblep.blepcore.common.network.packets;

import teamblep.blepcore.common.network.MessageBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author Kelan
 */
public class MessageTileEntity extends MessageBase<MessageTileEntity>
{
    protected long serializedPosition;

    public MessageTileEntity()
    {
        this(null);
    }

    public MessageTileEntity(TileEntity tileEntity)
    {
        if (tileEntity != null)
        {
            this.serializedPosition = tileEntity.getPos().toLong();
        }
    }

    @Override
    public void handleServer(MessageTileEntity message, EntityPlayer player)
    {

    }

    @Override
    public void handleClient(MessageTileEntity message, EntityPlayer player)
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.serializedPosition = buf.readLong();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(this.serializedPosition);
    }

    public TileEntity getTileEntity(World world)
    {
        if (world != null)
        {
            BlockPos pos = BlockPos.fromLong(serializedPosition);

            return world.getTileEntity(pos);
        }
        return null;
    }
}
