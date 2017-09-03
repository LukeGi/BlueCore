package dk.futte.blue.teamblep.blepcore.content.inventory.gui;

import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerSmelter;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.ProgressBar;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntitySmelter;
import dk.futte.blue.teamblep.blepcore.refs.ModInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

/**
 * @author Kelan
 */

public class GuiSmelter extends GuiMachine<TileEntitySmelter, ContainerSmelter>
{
    public GuiSmelter(TileEntitySmelter tileEntity, ContainerSmelter container)
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

        ProgressBar fuelBar = getTileEntity().getProgressTracker().getProgressBar("fuel_time");
        ProgressBar processBar = getTileEntity().getProgressTracker().getProgressBar("process_time");
        if (fuelBar.getTicksElapsed() > 0)
        {
            int fuelProgressScaled = fuelBar.getProgressScaled(13);
            this.drawTexturedModalRect(screenX + 56, screenY + 36 + 13 - fuelProgressScaled, 176, 12 - fuelProgressScaled, 14, fuelProgressScaled);
        }

        int processProgressScaled = processBar.getProgressScaled(24);
        this.drawTexturedModalRect(screenX + 79, screenY + 34, 176, 14, processProgressScaled, 16);
    }
}
