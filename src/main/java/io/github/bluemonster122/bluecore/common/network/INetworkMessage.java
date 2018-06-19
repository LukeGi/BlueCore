package io.github.bluemonster122.bluecore.common.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public interface INetworkMessage<T extends INetworkMessage> extends IMessage, IMessageHandler<T, IMessage> {

}
