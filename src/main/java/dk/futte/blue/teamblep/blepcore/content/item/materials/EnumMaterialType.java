package dk.futte.blue.teamblep.blepcore.content.item.materials;

import dk.futte.blue.teamblep.blepcore.content.item.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import java.awt.*;

import static dk.futte.blue.teamblep.blepcore.content.item.materials.EnumMetal.*;

/**
 * @author Blue
 */
public enum EnumMaterialType implements IStringSerializable
{
    /* INGOTS */
    INGOT_COPPER(COPPER.getColor()),
    INGOT_TIN(TIN.getColor()),
    INGOT_LEAD(LEAD.getColor()),
    INGOT_SILVER(SILVER.getColor()),

    /* DUSTS */ //TODO: maybe change this
    DUST_STONE(null),
    DUST_IRON(IRON.getColor()),
    DUST_GOLD(GOLD.getColor()),
    DUST_COPPER(COPPER.getColor()),
    DUST_TIN(TIN.getColor()),
    DUST_LEAD(LEAD.getColor()),
    DUST_SILVER(SILVER.getColor());

    public static final EnumMaterialType[] VARIANTS;

    static
    {
        VARIANTS = new EnumMaterialType[values().length];

        for (EnumMaterialType type : values())
        {
            VARIANTS[type.getMetadata()] = type;
        }
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
        return meta;
    }

    public Color getColor()
    {
        return color;
    }

    public ItemStack getItemStack(int amount)
    {
       return new ItemStack(ItemHandler.item_material, amount, this.getMetadata());
    }

    public ItemStack getItemStack()
    {
        return getItemStack(1);
    }
}
