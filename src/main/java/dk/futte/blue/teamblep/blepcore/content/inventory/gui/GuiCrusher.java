package dk.futte.blue.teamblep.blepcore.content.inventory.gui;

import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerCrusher;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.ProgressBar;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityCrusher;
import net.minecraft.client.renderer.GlStateManager;

/**
 * @author Kelan
 */

public class GuiCrusher extends GuiMachine<TileEntityCrusher, ContainerCrusher>
{
    public GuiCrusher(TileEntityCrusher tileEntity, ContainerCrusher container)
    {
        super(tileEntity, container, 176, 186);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = getTileEntity().getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.getContainer().getInventoryPlayer().getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI_BACKGROUND_TEXTURE);
        int screenX = (this.width - this.xSize) / 2;
        int screenY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(screenX, screenY, 0, 0, this.xSize, this.ySize);

        ProgressBar processBar = getTileEntity().getProgressTracker().getProgressBar("process_time");
        int processProgressScaled = processBar.getProgressScaled(26);
        this.drawTexturedModalRect(screenX + 48, screenY + 35, 176, 0, processProgressScaled, 19);
    }
}