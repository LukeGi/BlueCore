package dk.futte.blue.teamblep.blepcore.content.inventory;

import dk.futte.blue.teamblep.blepcore.Utils;
import dk.futte.blue.teamblep.blepcore.content.block.machine.BlockMachine;
import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerMachine;
import dk.futte.blue.teamblep.blepcore.content.inventory.gui.GuiMachine;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityMachine;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * @author Kelan
 */

public class InventoryMachineContainer<T extends TileEntityMachine> implements IGuiHandler
{
    protected Class<? extends ContainerMachine<T>> containerClass;
    protected Class<? extends GuiMachine<T, ? extends ContainerMachine<T>>> guiClass;

    public InventoryMachineContainer(Class<? extends ContainerMachine<T>> containerClass, Class<? extends GuiMachine<T, ? extends ContainerMachine<T>>> guiClass)
    {
        this.containerClass = containerClass;
        this.guiClass = guiClass;
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(pos);
        Block block = world.getBlockState(pos).getBlock();

        if (block instanceof BlockMachine)
        {
            MachineData machineData = ((BlockMachine) block).getMachineData();

            if (tile != null && machineData.getTileEntityClass() != null && machineData.getTileEntityClass().isInstance(tile))
            {
                return Utils.initializeClassWithConstructor(getContainerClass(), tile, player.inventory);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(pos);
        Block block = world.getBlockState(pos).getBlock();

        if (block instanceof BlockMachine)
        {
            MachineData machineData = ((BlockMachine) block).getMachineData();

            if (tile != null && machineData.getTileEntityClass() != null && machineData.getTileEntityClass().isInstance(tile))
            {
                ContainerMachine container = Utils.initializeClassWithConstructor(getContainerClass(), tile, player.inventory);

                return Utils.initializeClassWithConstructor(getGuiClass(), tile, container);
            }
        }

        return null;
    }

    public Class<? extends ContainerMachine<T>> getContainerClass()
    {
        return containerClass;
    }

    public Class<? extends GuiMachine<T, ? extends ContainerMachine<T>>> getGuiClass()
    {
        return guiClass;
    }
}
