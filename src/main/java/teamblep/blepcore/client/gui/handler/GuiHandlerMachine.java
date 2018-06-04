package teamblep.blepcore.client.gui.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import teamblep.blepcore.client.gui.GuiMachine;
import teamblep.blepcore.common.container.ContainerMachine;
import teamblep.blepcore.common.tileentity.TileEntityMachine;

import javax.annotation.Nullable;

public abstract class GuiHandlerMachine<C extends ContainerMachine<T>, G extends GuiMachine, T extends TileEntityMachine> implements IGuiHandler {
    @Nullable @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return getContainer(player, world, new BlockPos(x, y, z));
    }

    protected abstract C getContainer(EntityPlayer player, World world, BlockPos blockPos);

    @Nullable @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return getGui(player, world, new BlockPos(x, y, z));
    }

    protected abstract G getGui(EntityPlayer player, World world, BlockPos blockPos);


}
