package teamblep.blepcore.common.network;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageAirClick implements IBlepMessage<MessageAirClick> {

  private UUID m_uuid;
  private boolean m_left;
  private int m_hand;

  public MessageAirClick() {
  }

  public MessageAirClick(UUID uuid) {
    m_uuid = uuid;
    m_left = true;
  }

  public MessageAirClick(UUID uuid, EnumHand hand) {
    m_uuid = uuid;
    m_left = false;
    m_hand = hand.ordinal();
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    m_uuid = NBTUtil.getUUIDFromTag(ByteBufUtils.readTag(buf));
    m_left = buf.readBoolean();
    if (!m_left) {
      m_hand = buf.readInt();
    }
  }

  @Override
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeTag(buf, NBTUtil.createUUIDTag(m_uuid));
    buf.writeBoolean(m_left);
    if (!m_left) {
      buf.writeInt(m_hand);
    }
  }

  @Override
  public IMessage onMessage(MessageAirClick message, MessageContext ctx) {
    MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
    server.addScheduledTask(() -> {
      EntityPlayerMP player = server.getPlayerList().getPlayerByUUID(message.m_uuid);
      if (message.m_left) {
        ForgeHooks.onEmptyLeftClick(player);
      } else {
        ForgeHooks.onItemRightClick(player, EnumHand.values()[message.m_hand]);
      }
    });
    return null;
  }
}
