package teamblep.blepcore.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import teamblep.blepcore.common.tileentity.core.TileEntityAbstractTickable;

/**
 * @author Blue
 */

@Cancelable
public class EventFastTick extends Event
{
    private EntityPlayer player;
    private World world;
    private TileEntityAbstractTickable tile;
    private BlockPos pos;

    public EventFastTick(TileEntityAbstractTickable tile)
    {
        this.player = tile.player;
        this.world = tile.getWorld();
        this.tile = tile;
        this.pos = tile.getPos();
    }

    public EntityPlayer getPlayer()
    {
        return player;
    }

    public World getWorld()
    {
        return world;
    }

    public TileEntityAbstractTickable getTile()
    {
        return tile;
    }

    public BlockPos getPos()
    {
        return pos;
    }
}
