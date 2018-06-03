package teamblep.blepcore.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.common.block.BlockBase;
import teamblep.blepcore.common.block.BlockElectricFurnace;

@ObjectHolder(value = BlepCore.MOD_ID)
@EventBusSubscriber
public class ModBlocks {

//    public static final Block EXAMPLE_BLOCK = null;
    public static final BlockBase ELECTRIC_FURNACE = null;

//    @ObjectHolder("example_block")
//    public static final Item EXAMPLE_BLOCK_ITEM = null;
    @ObjectHolder("electric_furnace")
    public static final ItemBlock ELECTRIC_FURNACE_ITEM = null;

    @SubscribeEvent
    public static void onRegisterBlocks(Register<Block> event) {
        BlepCore.log("Registering Blocks");
        event.getRegistry().registerAll(
//            new Block(Material.CLAY).setRegistryName(BlepCore.MOD_ID, "example_block")
                new BlockElectricFurnace("electric_furnace")
        );
    }

    @SubscribeEvent
    public static void onRegisterItems(Register<Item> event) {
        BlepCore.log("Registering ItemBlocks");
        event.getRegistry().registerAll(
//                getItemBlock(EXAMPLE_BLOCK)
                getItemBlock(ELECTRIC_FURNACE)
        );
    }

    private static Item getItemBlock(Block block) {
        // TODO: Add custom behaviour for modblocks
        return new ItemBlock(block).setRegistryName(block.getRegistryName());
    }
}
