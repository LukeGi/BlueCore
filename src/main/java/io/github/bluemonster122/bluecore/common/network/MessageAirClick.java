package io.github.bluemonster122.bluecore.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageAirClick implements INetworkMessage<MessageAirClick> {

  private boolean m_left;
  private int m_hand;

  public MessageAirClick() {
  }

  private MessageAirClick(boolean leftClick, EnumHand hand) {
    m_left = leftClick;
    m_hand = hand.ordinal();
  }

  public static MessageAirClick rightClickMessage(EnumHand hand) {
    return new MessageAirClick(false, hand);
  }

  public static MessageAirClick leftClickMessage(EnumHand hand) {
    return new MessageAirClick(true, hand);
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    m_left = buf.readBoolean();
    if (!m_left) {
      m_hand = buf.readInt();
    }
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(m_left);
    if (!m_left) {
      buf.writeInt(m_hand);
    }
  }

  @Override
  public IMessage onMessage(MessageAirClick message, MessageContext ctx) {
    EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
    serverPlayer.getServerWorld().addScheduledTask(() -> {
      if (message.m_left) {
        ForgeHooks.onEmptyLeftClick(serverPlayer);
      } else {
        ForgeHooks.onItemRightClick(serverPlayer, EnumHand.values()[message.m_hand]);
      }
    });
    return null;
  }
}
