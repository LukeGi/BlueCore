package dk.futte.blue.teamblep.blepcore.content.item.materials;

import java.awt.*;

/**
 * @author Blue
 */
public enum EnumMetal
{
    COPPER(new Color(230, 130, 10)),
    TIN(new Color(204, 194,184)),
    LEAD(new Color(120,140,180)),
    SILVER(new Color(210,220,230)),;

    private Color color;

    EnumMetal(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }
}
