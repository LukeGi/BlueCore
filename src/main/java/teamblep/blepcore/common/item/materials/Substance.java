package teamblep.blepcore.common.item.materials;

import net.minecraft.util.IStringSerializable;

/**
 * This enum has the colour and potentially more information about the substance of a VARIANT.
 *
 * @author Blue
 */
public enum Substance implements IStringSerializable
{
    IRON(0xA4A4A4),
    GOLD(0xDFCB3E),
    COPPER(0xE6820A),
    TIN(0xCCC2B8),
    LEAD(0x788CB4),
    SILVER(0xD2DCE6),
    STONE(-1);

    private int color;

    Substance(int color)
    {
        this.color = color;
    }

    public int getColor()
    {
        return color;
    }

    @Override
    public String getName()
    {
        return name().toLowerCase();
    }
}
