package teamblep.blepcore.common.network;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import teamblep.blepcore.common.ModInfo;
import teamblep.blepcore.common.network.packets.MessageTileEntity;

/**
 * @author Kelan
 */
public final class PacketHandler extends SimpleNetworkWrapper
{
    private static final PacketHandler INSTANCE = new PacketHandler(ModInfo.MOD_ID);

    private int currentID;

    private PacketHandler(String channelName)
    {
        super(channelName);
        this.currentID = 0;

        //REGISTER PACKETS
        getInstance().registerMessage(MessageTileEntity.class, MessageTileEntity.class, nextID(true), Side.CLIENT);
        getInstance().registerMessage(MessageTileEntity.class, MessageTileEntity.class, nextID(false), Side.SERVER);
        getInstance().registerMessage(MessageTileEntity.class, MessageTileEntity.class, nextID(true), Side.CLIENT);
    }

    public static PacketHandler getInstance()
    {
        return INSTANCE;
    }

    private int nextID(boolean incr)
    {
        return incr ? currentID++ : currentID;
    }
}
