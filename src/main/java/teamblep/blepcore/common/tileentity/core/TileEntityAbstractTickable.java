package teamblep.blepcore.common.tileentity.core;

import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityAbstractTickable extends TileEntityAbstractBase implements ITickable
{
    @Override
    public void update()
    {
        if (getWorld().isRemote)
        {
            //noinspection MethodCallSideOnly
            if (updateClient())
            {
                notifyServer();
            }
        } else
        {
            //noinspection MethodCallSideOnly
            if (updateServer())
            {
                notifyClient();
            }
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
