package dk.futte.blue.teamblep.blepcore.content.inventory.gui;

import dk.futte.blue.teamblep.blepcore.BlepCore;
import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;
import dk.futte.blue.teamblep.blepcore.content.inventory.container.ContainerMachine;
import dk.futte.blue.teamblep.blepcore.content.tileentity.machine.TileEntityMachine;
import dk.futte.blue.teamblep.blepcore.refs.ModInfo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

/**
 * @author Kelan
 */
public abstract class GuiMachine<T extends TileEntityMachine, C extends ContainerMachine<T>> extends GuiContainer
{
    protected ResourceLocation GUI_BACKGROUND_TEXTURE;
    protected T tileEntity;
    protected C container;

    public GuiMachine(T tileEntity, C container, int textureWidth, int textureHeight)
    {
        super(container);
        this.tileEntity = tileEntity;
        this.container = container;
        this.setTextureDimensions(textureWidth, textureHeight);
        this.GUI_BACKGROUND_TEXTURE = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/" + tileEntity.getMachineData().getName() + ".png");
    }

    public T getTileEntity()
    {
        return tileEntity;
    }

    public C getContainer()
    {
        return container;
    }

    void setTextureDimensions(int textureWidth, int textureHeight)
    {
        this.xSize = textureWidth;
        this.ySize = textureHeight;
    }

    public void renderText(String text, int x, int y, int colour)
    {
        this.renderText(text, x, y, colour, false);
    }

    public void renderText(String text, int x, int y, int colour, boolean centered)
    {
        if (centered)
        {
            x -= this.fontRendererObj.getStringWidth(text) / 2;
        }

        this.fontRendererObj.drawString(text, x, y, colour);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        if (BlepCore.debug)
        {
            for (Object o : tileEntity.getMachineData().getInventoryContainer().getSlotList())
            {
                SlotData slotData = (SlotData) o;
                this.renderText(Integer.toString(slotData.getId()), slotData.getX(), slotData.getY(), 4210752);
            }
        }
    }
}