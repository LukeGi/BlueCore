package teamblep.blepcore.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBlockBreakProgress implements IMessage, IMessageHandler<MessageBlockBreakProgress, IMessage> {

  private int breakerID;
  private BlockPos blockPos;
  private int progress;

  public MessageBlockBreakProgress() {
  }

  public MessageBlockBreakProgress(int breakerID, BlockPos blockPos, int progress) {
    this.breakerID = breakerID;
    this.blockPos = blockPos;
    this.progress = progress;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(breakerID);
    buf.writeLong(blockPos.toLong());
    buf.writeInt(progress);
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    breakerID = buf.readInt();
    blockPos = BlockPos.fromLong(buf.readLong());
    progress = buf.readInt();
  }

  @Override
  public IMessage onMessage(MessageBlockBreakProgress message, MessageContext ctx) {
    Minecraft.getMinecraft().renderGlobal.sendBlockBreakProgress(message.breakerID, message.blockPos, message.progress);
    return null;
  }
}
