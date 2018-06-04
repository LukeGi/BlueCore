package teamblep.blepcore.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import teamblep.blepcore.BlepCore;

public class GuiMachine extends GuiContainer {
    public static final ResourceLocation GUI_BASE = new ResourceLocation(BlepCore.MOD_ID, "textures/gui/guibase.png");

    public GuiMachine(Container inventorySlotsIn) {
        super(inventorySlotsIn);
        xSize = 232;
        ySize = 100;
    }

    @Override public void initGui() {
        this.mc.player.openContainer = this.inventorySlots;
        guiTop = height - ySize;
        guiLeft = (width - xSize) / 2;
    }

    @Override protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawPlayerSlots();
    }

    protected void drawPlayerSlots() {
        drawGradientRect(0, 0, width, height, 0xAC29434E, 0xAC29434E);
        Minecraft.getMinecraft().renderEngine.bindTexture(GUI_BASE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, 232, 100);
        drawTexturedModalRect(guiLeft - 28, guiTop, 0, 100, 28, 100);
        drawTexturedModalRect(guiLeft + xSize, guiTop + ySize - 28, 28, 100, 28, 28);
        int stringwidth = mc.fontRenderer.getStringWidth("Inventory");
        mc.fontRenderer.drawString("Inventory", guiLeft + (xSize - stringwidth) / 2, guiTop + 4, 0xFFFFFF);
    }
}