package teamblep.blepcore.common.item.materials;

import net.minecraft.util.IStringSerializable;

import java.awt.*;

/**
 * This enum has the colour and potentially more information about the substance of a PROPERTY_MATERIAL.
 *
 * @author Blue
 */
public enum EnumSubstance implements IStringSerializable
{
    IRON(new Color(164, 164, 164)),
    GOLD(new Color(223, 203, 62)),
    COPPER(new Color(230, 130, 10)),
    TIN(new Color(204, 194, 184)),
    LEAD(new Color(120, 140, 180)),
    SILVER(new Color(210, 220, 230)),
    STONE(null);

    private Color color;

    EnumSubstance(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }


    @Override
    public String getName()
    {
        return name().toLowerCase();
    }
}
