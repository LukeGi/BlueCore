package dk.futte.blue.teamblep.blepcore.content.inventory.gui;

import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerSmelter;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntitySmelter;

/**
 * @author Kelan
 */

public class GuiSmelter extends GuiMachine<TileEntitySmelter, ContainerSmelter>
{
    public GuiSmelter(TileEntitySmelter tileEntity, ContainerSmelter container)
    {
        super(tileEntity, container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

    }
}
