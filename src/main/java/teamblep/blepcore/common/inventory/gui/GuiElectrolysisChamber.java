package teamblep.blepcore.common.inventory.gui;

import teamblep.blepcore.common.inventory.container.ContainerElectrolysisChamber;
import teamblep.blepcore.common.tileentity.machine.TileEntityElectrolysisChamber;

/**
 * @author Kelan
 */

public class GuiElectrolysisChamber extends GuiMachine<TileEntityElectrolysisChamber, ContainerElectrolysisChamber>
{
    public GuiElectrolysisChamber(TileEntityElectrolysisChamber tileEntity, ContainerElectrolysisChamber container)
    {
        super(tileEntity, container, 0, 0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

    }
}
