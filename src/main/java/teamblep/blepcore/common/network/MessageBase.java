package teamblep.blepcore.common.network;

import teamblep.blepcore.common.BlepCore;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author Kelan
 */
public abstract class MessageBase<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ>, IPacketBase<REQ>
{
    public REQ onMessage(REQ message, MessageContext context)
    {
        if (context.side == Side.SERVER)
        {
            handleServer(message, context.getServerHandler().playerEntity);
        } else
        {
            handleClient(message, BlepCore.proxy.getClientPlayer());
        }

        return null;
    }
}
