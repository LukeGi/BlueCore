package teamblep.blepcore.common;

import teamblep.blepcore.common.inventory.GuiHandler;
import teamblep.blepcore.common.item.ItemHandler;
import teamblep.blepcore.common.item.materials.ItemMaterial;
import teamblep.blepcore.common.recipe.RecipeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

/**
 * @author Blue
 */
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION, guiFactory = ModInfo.GUI_FACTORY)
@Mod.EventBusSubscriber
public class BlepCore
{
    @Mod.Instance(ModInfo.MOD_ID)
    public static BlepCore instance;

    @SidedProxy(clientSide = ModInfo.PROXY_CLIENT, serverSide = ModInfo.PROXY_SERVER)
    public static IProxy proxy;

    public static boolean debug = false;
    public static Logger logger;
    public static CreativeTabs tabBlepCore = new CreativeTabs(ModInfo.MOD_ID)
    {
        @Override
        public Item getTabIconItem()
        {
            return ItemHandler.item_material;
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        logger = e.getModLog();
        ConfigManager.load(ModInfo.MOD_ID, Config.Type.INSTANCE);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e)
    {
        if (e.getSide() == Side.CLIENT)
            //noinspection MethodCallSideOnly
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemMaterial(), ItemHandler.item_material);
        GuiHandler.init();
        RecipeHandler.initRecipes();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {

    }

    @SubscribeEvent
    public void configsChangedEvent(ConfigChangedEvent event)
    {
        if (event.getModID().equals(ModInfo.MOD_ID))
        {
            ConfigManager.load(ModInfo.MOD_ID, Config.Type.INSTANCE);
        }
    }
}
