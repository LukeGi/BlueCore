package teamblep.blepcore.common.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamblep.blepcore.common.ModInfo;
import teamblep.blepcore.common.Names;
import teamblep.blepcore.common.block.BlockHandler;
import teamblep.blepcore.common.item.materials.ItemMaterial;

import java.util.List;

import static teamblep.blepcore.common.block.BlockHandler.*;
import static teamblep.blepcore.common.item.ItemHandler.item_material;

/**
 * @author Blue
 */

public class CreativeTab extends CreativeTabs
{
    public static final CreativeTab MAIN_TAB = new CreativeTab(Names.CreativeTabs.MAIN);
    public static final CreativeTab MISC_TAB = new CreativeTab(Names.CreativeTabs.MISC);

    private CreativeTab(String name)
    {
        super(ModInfo.RESOURCE_PREFIX + name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(List<ItemStack> list)
    {
        item_material.getSubItems(item_material, this, list);
        ORE.getSubBlocks(ORE.getItemBlock(), this, list);
        BLOCK_METAL.getSubBlocks(BLOCK_METAL.getItemBlock(), this, list);
        MACHINE_CHASSIS.getSubBlocks(MACHINE_CHASSIS.getItemBlock(), this, list);
        MACHINE_SMELTER.getSubBlocks(MACHINE_SMELTER.getItemBlock(), this, list);
        MACHINE_CRUSHER.getSubBlocks(MACHINE_CRUSHER.getItemBlock(), this, list);
        MACHINE_CENTRIFUGE.getSubBlocks(MACHINE_CENTRIFUGE.getItemBlock(), this, list);
        MACHINE_ELECTROLYSIS_CHAMBER.getSubBlocks(MACHINE_ELECTROLYSIS_CHAMBER.getItemBlock(), this, list);
        LADDER.getSubBlocks(LADDER.getItemBlock(), this, list);
    }

    @Override
    public Item getTabIconItem()
    {
        return Item.getItemFromBlock(Blocks.COBBLESTONE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack()
    {
        switch (getTabLabel().substring(ModInfo.RESOURCE_PREFIX.length()))
        {
            case Names.CreativeTabs.MAIN:
                return ItemMaterial.Variants.COPPER_INGOT.getItemStack();
            case Names.CreativeTabs.MISC:
                return BlockHandler.LADDER.getItemStack();
            default:
                return super.getIconItemStack();
        }
    }
}
