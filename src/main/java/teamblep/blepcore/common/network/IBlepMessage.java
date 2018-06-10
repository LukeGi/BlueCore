package teamblep.blepcore.common.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public interface IBlepMessage<T extends IBlepMessage> extends IMessage, IMessageHandler<T, IMessage> {

}
