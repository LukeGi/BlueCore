package teamblep.blepcore.common.inventory.gui;

import net.minecraft.client.renderer.GlStateManager;
import teamblep.blepcore.common.inventory.container.ContainerCrusher;
import teamblep.blepcore.common.tileentity.ProgressBar;
import teamblep.blepcore.common.tileentity.machine.TileEntityCrusher;

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
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        String tileEntityName = this.getTileEntity().getDisplayName().getUnformattedText();
        String inventoryPlayerName = this.getContainer().getInventoryPlayer().getDisplayName().getUnformattedText();

        this.renderText(tileEntityName, this.xSize / 2, 6, 4210752, true);
        this.renderText(inventoryPlayerName, 8, this.ySize - 96 + 2, 4210752);
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