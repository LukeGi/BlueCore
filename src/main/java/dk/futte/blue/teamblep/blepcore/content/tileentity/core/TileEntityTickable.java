package dk.futte.blue.teamblep.blepcore.content.tileentity.core;

import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityTickable extends TileEntityBase implements ITickable
{
    @Override
    public void update()
    {
        if (getWorld().isRemote)
        {
            //noinspection MethodCallSideOnly
            updateClient();
        } else
        {
            updateServer();
        }
    }

    @SideOnly(Side.CLIENT)
    public abstract void updateClient();

    public abstract void updateServer();


}
