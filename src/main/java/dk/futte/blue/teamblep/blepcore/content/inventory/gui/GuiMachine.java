package dk.futte.blue.teamblep.blepcore.content.inventory.gui;

import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerMachine;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityMachine;
import net.minecraft.client.gui.inventory.GuiContainer;

/**
 * @author Kelan
 */
public abstract class GuiMachine<T extends TileEntityMachine, C extends ContainerMachine<T>> extends GuiContainer
{
    public T tileEntity;
    public C container;

    public GuiMachine(T tileEntity, C container)
    {
        super(container);
        this.tileEntity = tileEntity;
        this.container = container;
    }

    public T getTileEntity()
    {
        return tileEntity;
    }

    public C getContainer()
    {
        return container;
    }
}