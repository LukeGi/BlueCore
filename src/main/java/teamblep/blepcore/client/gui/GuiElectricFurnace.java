package teamblep.blepcore.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;

public class GuiElectricFurnace extends GuiMachine {

  public GuiElectricFurnace(Container inventorySlotsIn) {
    super(inventorySlotsIn);
  }

  @Override
  public void initGui() {
    super.initGui();
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
    Minecraft.getMinecraft().renderEngine.bindTexture(GUI_BASE);

    int x = /*-guiLeft +*/ (width - 18) / 2;
    int y = /*-guiTop + */(height - 18) / 2;

    GlStateManager.pushMatrix();
    GlStateManager.translate(x + 9, y + 9, 0);
    GlStateManager.rotate((float) ((mc.world.getWorldTime() + mc.getRenderPartialTicks()) * Math.PI), 0, 0, 1);
    GlStateManager.translate(-(x + 9), -(y + 9), 0);
    // Draw rotated thing in here
    drawTexturedModalRect(x, y, 0, 200, 18, 18);
    GlStateManager.popMatrix();

  }
}
