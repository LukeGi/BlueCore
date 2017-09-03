package dk.futte.blue.teamblep.blepcore.content.inventory.gui;

import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerElectrolysisChamber;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityElectrolysisChamber;

/**
 * @author Kelan
 */

public class GuiElectrolysisChamber extends GuiMachine<TileEntityElectrolysisChamber, ContainerElectrolysisChamber>
{
    public GuiElectrolysisChamber(TileEntityElectrolysisChamber tileEntity, ContainerElectrolysisChamber container)
    {
        super(tileEntity, container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

    }
}
