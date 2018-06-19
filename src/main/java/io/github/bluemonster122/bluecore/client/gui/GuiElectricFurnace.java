package io.github.bluemonster122.bluecore.client.gui;

import io.github.bluemonster122.bluecore.BlueCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiElectricFurnace extends GuiMachine {

  private static final ResourceLocation GUI_FLAME = new ResourceLocation(BlueCore.MOD_ID, "textures/gui/flame.png");

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

    int progress = (int) (mc.world.getWorldTime() % 300);

    int x = (width - 18) / 2;
    int y = (height - 18) / 2;

    int offset;
    if (progress < 180) {
      offset = progress;
    } else {
      offset = 0;
    }
    float offsetPerc = offset / 100F;
    drawTexturedModalRect(x, y - 40, 0, 200, 18, 18);
    GlStateManager.pushMatrix();
    GlStateManager.translate(x + 9, y + 9, 0);
    GlStateManager.translate(0, -40, 0);
    GlStateManager.rotate(offset * offset / 2, 0, 0, 1);
    GlStateManager.translate(-(x + 9), -(y + 9), 0);
    // Draw rotated thing in here
    RenderHelper.enableGUIStandardItemLighting();
    ItemStack stack = progress < 180 ? new ItemStack(Blocks.COBBLESTONE) : new ItemStack(Blocks.STONE);
    itemRender.renderItemIntoGUI(stack, x + 1, y + 1);
//    itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, stack, x + 1, y + 1, "1");
    RenderHelper.disableStandardItemLighting();
    GlStateManager.popMatrix();

    Minecraft.getMinecraft().renderEngine.bindTexture(GUI_FLAME);
    offset = (int) mc.world.getTotalWorldTime() % 16;
    drawTexturedModalRect(x, y - 16, 0, 32, 16, 32);
    int flameSize = (int) (32 * offsetPerc);
    drawTexturedModalRect(x, y + 16 - flameSize, offset * 16, 32 - flameSize, 16, flameSize);
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
  }
}

