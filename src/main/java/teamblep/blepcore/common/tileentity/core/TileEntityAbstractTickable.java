package teamblep.blepcore.common.tileentity.core;

import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamblep.blepcore.common.event.EventFastTick;

public abstract class TileEntityAbstractTickable extends TileEntityAbstractBase implements ITickable
{
    private long lastTime = 0;

    @Override
    public void update()
    {
        if (!getWorld().isRemote)
        {
            long now = getWorld().getTotalWorldTime();

            if (now == lastTime && !MinecraftForge.EVENT_BUS.post(new EventFastTick(this)))
            {
                getWorld().destroyBlock(getPos(), true);
            }

            lastTime = now;
        }
        tick();
    }

    /**
     * Ticks the tile entity.
     */
    public void tick()
    {
        if (getWorld().isRemote)
        {
            //noinspection MethodCallSideOnly
            updateClient();
        }
        else if (updateServer())
        {
            notifyClient();
        }
    }

    /**
     * Update this TileEntity on the clientside. Used for things such as rendering and other client-only things.
     *
     * @return true if the server needs to be notified of isInventoryValid change.
     */
    @SideOnly(Side.CLIENT)
    public abstract boolean updateClient();

    /**
     * Update this TileEntity on the serverside. Used for all serverside block updates, such as processing items.
     *
     * @return true if the client needs to be notified of isInventoryValid change.
     */
    public abstract boolean updateServer();
}
