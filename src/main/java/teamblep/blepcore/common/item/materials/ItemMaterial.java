package teamblep.blepcore.common.item.materials;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import teamblep.blepcore.common.BlepCore;
import teamblep.blepcore.common.ModInfo;
import teamblep.blepcore.common.Names;
import teamblep.blepcore.common.item.core.ItemBase;

import java.awt.*;
import java.util.List;

/**
 * @author Blue
 */

public class ItemMaterial extends ItemBase implements IItemColor
{
    public ItemMaterial()
    {
        super(Names.Items.MATERIAL);
        setCreativeTab(BlepCore.tabBlepCore);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        int metadata = stack.getMetadata();
        int matmeta = Math.floorDiv(metadata, 8);
        int typemeta = metadata % 8;

        return "item." + ModInfo.MOD_ID + ":" + EnumMaterial.MATERIALS[matmeta].getNameOfType(EnumMaterialType.byMeta(EnumRegistryType.ITEM, typemeta));
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (EnumMaterialType materialType : EnumMaterialType.values())
        {
            if (materialType.getRegistryType() == EnumRegistryType.ITEM)
            {
                for (EnumMaterial material : EnumMaterial.MATERIALS)
                {
                    if (material.hasType(materialType))
                    {
                        if (material.equals(EnumMaterial.STONE) && materialType.equals(EnumMaterialType.DIRTY_DUST))
                        {
                            continue;
                        }
                        subItems.add(new ItemStack(this, 1, material.getMetadata(materialType)));
                    }
                }
            }
            if (materialType.equals(EnumMaterialType.DUST))
            {
                subItems.add(new ItemStack(this, 1, EnumMaterial.STONE.getMetadata(EnumMaterialType.DIRTY_DUST)));
            }
        }
    }

    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex)
    {
        Color colour = EnumMaterial.MATERIALS[Math.floorDiv(stack.getMetadata(), 8)].getColor();

        if (colour == null)
        {
            colour = Color.WHITE;
        }

        int r = colour.getRed() << 16;
        int g = colour.getGreen() << 8;
        int b = colour.getBlue();

        return r + b + g;
    }

}
