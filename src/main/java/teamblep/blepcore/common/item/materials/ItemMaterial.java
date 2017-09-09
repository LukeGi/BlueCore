package teamblep.blepcore.common.item.materials;

import teamblep.blepcore.common.BlepCore;
import teamblep.blepcore.common.item.core.ItemBase;
import teamblep.blepcore.common.ModInfo;
import teamblep.blepcore.common.Names;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
        return "item." + ModInfo.MOD_ID + ":" + EnumMaterialType.VARIANTS[stack.getMetadata()].getName();
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (EnumMaterialType type : EnumMaterialType.values())
        {
            subItems.add(new ItemStack(this, 1, type.getMetadata()));
        }
    }

    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex)
    {
        Color colour = EnumMaterialType.VARIANTS[stack.getMetadata()].getColor();

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
