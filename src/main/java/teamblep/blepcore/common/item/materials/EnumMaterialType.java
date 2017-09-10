package teamblep.blepcore.common.item.materials;

import net.minecraft.util.IStringSerializable;

/**
 * This enum is for deciding which blocks/items a material will register with when the game loads.
 *
 * @author Blue
 */
public enum EnumMaterialType implements IStringSerializable
{
    // ##### BLOCKS #####
    ORE(0, EnumRegistryType.BLOCK),
    BLOCK(1, EnumRegistryType.BLOCK),

    // ##### ITEMS #####
    INGOT(0, EnumRegistryType.ITEM),
    NUGGET(1, EnumRegistryType.ITEM),
    DIRTY_DUST(2, EnumRegistryType.ITEM),
    DUST(3, EnumRegistryType.ITEM);

    private EnumRegistryType registryType;
    private int meta;

    EnumMaterialType(int meta, EnumRegistryType registryType)
    {
        this.meta = meta;
        this.registryType = registryType;
    }

    public static EnumMaterialType byMeta(EnumRegistryType item, int typemeta)
    {
        switch (item)
        {
            case BLOCK:
                switch (typemeta)
                {
                    case 0:
                        return ORE;
                    case 1:
                        return BLOCK;
                }
                break;
            case ITEM:
                switch (typemeta)
                {
                    case 0:
                        return INGOT;
                    case 1:
                        return NUGGET;
                    case 2:
                        return DIRTY_DUST;
                    case 3:
                        return DUST;
                }
                break;
        }
        return null;
    }

    public EnumRegistryType getRegistryType()
    {
        return registryType;
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
}
