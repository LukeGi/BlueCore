package dk.futte.blue.teamblep.blepcore.content.items.materials;

import dk.futte.blue.teamblep.blepcore.content.items.ItemBase;
import dk.futte.blue.teamblep.blepcore.refs.ModInfo;
import dk.futte.blue.teamblep.blepcore.refs.Names;
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
        setCreativeTab(CreativeTabs.TOOLS);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "items." + ModInfo.MOD_ID + ":" + EnumMaterialType.VARIANTS[stack.getMetadata()].getName();
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
        Color color = EnumMaterialType.VARIANTS[stack.getMetadata()].getColor();

        int r = color.getRed() << 16;
        int g = color.getGreen() << 8;
        int b = color.getBlue();

        return r + b + g ;
    }

}
