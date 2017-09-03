package dk.futte.blue.teamblep.blepcore.content.inventory.gui;

import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerCrusher;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityCrusher;

/**
 * @author Kelan
 */

public class GuiCrusher extends GuiMachine<TileEntityCrusher, ContainerCrusher>
{
    public GuiCrusher(TileEntityCrusher tileEntity, ContainerCrusher container)
    {
        super(tileEntity, container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

    }
}
