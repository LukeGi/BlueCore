package dk.futte.blue.teamblep.blepcore.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * @author Kelan
 */
public interface IPacketBase<REQ extends IMessage>
{
    void handleServer(REQ message, EntityPlayer player);

    void handleClient(REQ message, EntityPlayer player);
}
