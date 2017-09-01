package dk.futte.blue.teamblep.blepcore.content.items.materials;

import net.minecraft.util.IStringSerializable;

import java.awt.*;

/**
 * @author Blue
 */
public enum EnumMaterialType implements IStringSerializable
{
    INGOT_COPPER(new Color(184,115,51)),
    INGOT_TIN(new Color(211, 212, 213)),
    INGOT_LEAD(new Color(159, 157, 153)),
    INGOT_SILVER(new Color(192,192,192));

    public static final EnumMaterialType[] VARIANTS;

    static
    {
        VARIANTS = new EnumMaterialType[values().length];
        for (EnumMaterialType type : values())
            VARIANTS[type.getMetadata()] = type;
    }

    private Color color;
    private int meta;

    EnumMaterialType(Color color)
    {
        this.color = color;
        this.meta = ordinal();
    }

    @Override
    public String getName()
    {
        return name().toLowerCase();
    }

    public int getMetadata()
    {
        return ordinal();
    }

    public Color getColor()
    {
        return color;
    }
}
