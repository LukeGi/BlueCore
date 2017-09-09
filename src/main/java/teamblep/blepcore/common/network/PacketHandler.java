package teamblep.blepcore.common.network;

import teamblep.blepcore.common.network.packets.MessageTileEntity;
import teamblep.blepcore.common.ModInfo;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

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

    private int nextID(boolean incr)
    {
        return incr ? currentID++ : currentID;
    }

    public static PacketHandler getInstance()
    {
        return INSTANCE;
    }
}
