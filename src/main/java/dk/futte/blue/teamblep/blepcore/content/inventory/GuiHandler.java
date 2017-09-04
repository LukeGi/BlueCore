package dk.futte.blue.teamblep.blepcore.content.inventory;

import dk.futte.blue.teamblep.blepcore.BlepCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kelan
 */

public class GuiHandler implements IGuiHandler
{
    private static GuiHandler instance;
    private static List<IGuiHandler> guis = new ArrayList<>();

    private GuiHandler()
    {

    }

    public static void init()
    {
        GuiHandler.instance = new GuiHandler();
        NetworkRegistry.INSTANCE.registerGuiHandler(BlepCore.instance, instance);
    }

    public static void addGuiHandler(IGuiHandler guiHandler)
    {
        if (guiHandler != null)
        {
            guis.add(guiHandler);
        }
    }

    public static IGuiHandler getHandlerFromID(int id)
    {
        return guis.get(id);
    }


    public static int getIDFromHandler(IGuiHandler guiHandler)
    {
        return guis.indexOf(guiHandler);
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        IGuiHandler guiHandler = getHandlerFromID(id);
        if (guiHandler != null)
        {
            return guiHandler.getServerGuiElement(id, player, world, x, y, z);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        IGuiHandler guiHandler = getHandlerFromID(id);
        if (guiHandler != null)
        {
            return guiHandler.getClientGuiElement(id, player, world, x, y, z);
        }
        return null;
    }
}
