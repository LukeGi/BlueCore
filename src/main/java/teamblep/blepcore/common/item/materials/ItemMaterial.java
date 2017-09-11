package teamblep.blepcore.common.item.materials;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import teamblep.blepcore.common.BlepCore;
import teamblep.blepcore.common.ModInfo;
import teamblep.blepcore.common.Names;
import teamblep.blepcore.common.item.ItemHandler;
import teamblep.blepcore.common.item.core.ItemBase;

import java.util.List;

/**
 * @author Blue
 */

public class ItemMaterial extends ItemBase
{
//    public static final IProperty<ItemMaterial.Variants> VARIANT = PropertyEnum.create("variant", ItemMaterial.Variants.class);

    public ItemMaterial()
    {
        super(Names.Items.MATERIAL);
        setCreativeTab(BlepCore.tabBlepCore);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + ModInfo.RESOURCE_PREFIX + Variants.byMeta(stack.getMetadata()).getName();
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (Variants variant : Variants.values())
        {
            subItems.add(new ItemStack(this, 1, variant.getMetadata()));
        }
    }

    public enum Variants implements IStringSerializable
    {
        COPPER_INGOT(Substance.COPPER),
        TIN_INGOT(Substance.TIN),
        LEAD_INGOT(Substance.LEAD),
        SILVER_INGOT(Substance.SILVER),

        COPPER_NUGGET(Substance.COPPER),
        TIN_NUGGET(Substance.TIN),
        LEAD_NUGGET(Substance.LEAD),
        SILVER_NUGGET(Substance.SILVER),

        COPPER_DUST(Substance.COPPER),
        TIN_DUST(Substance.TIN),
        LEAD_DUST(Substance.LEAD),
        SILVER_DUST(Substance.SILVER),
        IRON_DUST(Substance.IRON),
        GOLD_DUST(Substance.GOLD),
        STONE_DUST(Substance.STONE),

        COPPER_DIRTY_DUST(Substance.COPPER),
        TIN_DIRTY_DUST(Substance.TIN),
        LEAD_DIRTY_DUST(Substance.LEAD),
        SILVER_DIRTY_DUST(Substance.SILVER);

        private Substance substance;

        Variants(Substance substance)
        {
            this.substance = substance;
        }

        public static Variants byMeta(int meta)
        {
            return values()[meta % values().length];
        }

        public ItemStack getItemStack()
        {
            return getItemStack(1);
        }

        public ItemStack getItemStack(int amount)
        {
            return new ItemStack(ItemHandler.item_material, amount, getMetadata());
        }

        public Substance getSubstance()
        {
            return substance;
        }

        public int getColor()
        {
            return substance.getColor();
        }

        public int getMetadata()
        {
            return ordinal();
        }

        @Override
        public String getName()
        {
            return name().toLowerCase();
        }
    }
}
