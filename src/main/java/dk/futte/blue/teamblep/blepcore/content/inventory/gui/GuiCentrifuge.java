package dk.futte.blue.teamblep.blepcore.content.inventory.gui;

import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerCentrifuge;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityCentrifuge;

/**
 * @author Kelan
 */
public class GuiCentrifuge extends GuiMachine<TileEntityCentrifuge, ContainerCentrifuge>
{
    public GuiCentrifuge(TileEntityCentrifuge tileEntity, ContainerCentrifuge container, int textureWidth, int textureHeight)
    {
        super(tileEntity, container, textureWidth, textureHeight);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

    }
}
