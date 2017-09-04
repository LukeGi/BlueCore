package dk.futte.blue.teamblep.blepcore.content.inventory.gui;

import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerCentrifuge;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityCentrifuge;

/**
 * @author Kelan
 */

public class GuiContrifuge extends GuiMachine<TileEntityCentrifuge, ContainerCentrifuge>
{
    public GuiContrifuge(TileEntityCentrifuge tileEntity, ContainerCentrifuge container)
    {
        super(tileEntity, container, 0, 0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

    }
}
