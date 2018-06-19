package io.github.bluemonster122.bluecore.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBlockBreakProgress implements IMessage, IMessageHandler<MessageBlockBreakProgress, IMessage> {

  private int breakerID;
  private BlockPos[] blockPos;
  private int progress;

  public MessageBlockBreakProgress() {
  }

  public MessageBlockBreakProgress(int breakerID, int progress, BlockPos... blockPos) {
    this.breakerID = breakerID;
    this.blockPos = blockPos;
    this.progress = progress;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    breakerID = buf.readInt();
    int arrsize = buf.readInt();
    blockPos = new BlockPos[arrsize];
    for (int i = 0; i < arrsize; i++) {
      blockPos[i] = BlockPos.fromLong(buf.readLong());
    }
    progress = buf.readInt();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(breakerID);
    buf.writeInt(blockPos.length);
    for (int i = 0; i < blockPos.length; i++) {
      buf.writeLong(blockPos[i].toLong());
    }
    buf.writeInt(progress);
  }

  @Override
  public IMessage onMessage(MessageBlockBreakProgress message, MessageContext ctx) {
    Minecraft.getMinecraft().addScheduledTask(() -> {
      BlockPos[] blockPos1 = message.blockPos;
      for (int i = 0; i < blockPos1.length; i++) {
        Minecraft.getMinecraft().renderGlobal.sendBlockBreakProgress(Integer.MAX_VALUE - i, message.blockPos[i], message.progress);
      }
    });
    return null;
  }
}
