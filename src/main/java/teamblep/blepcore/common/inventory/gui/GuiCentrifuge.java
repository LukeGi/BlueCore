package teamblep.blepcore.common.inventory.gui;

import teamblep.blepcore.common.inventory.container.ContainerCentrifuge;
import teamblep.blepcore.common.tileentity.machine.TileEntityCentrifuge;

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
