package dk.futte.blue.teamblep.blepcore.content.items.materials;

import net.minecraft.util.IStringSerializable;

/**
 * @author Blue
 */
public enum EnumMaterialType implements IStringSerializable
{
    INGOT_COPPER,
    INGOT_TIN,
    INGOT_LEAD,
    INGOT_SILVER;

    public static final EnumMaterialType[] VARIANTS;

    static
    {
        VARIANTS = new EnumMaterialType[values().length];
        for (EnumMaterialType type : values())
            VARIANTS[type.getMetadata()] = type;
    }

    private int meta;

    EnumMaterialType()
    {
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
}
