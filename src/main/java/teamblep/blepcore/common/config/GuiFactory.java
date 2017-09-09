package teamblep.blepcore.common.config;

import teamblep.blepcore.common.Utils;
import teamblep.blepcore.common.Names;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.ConfigGuiType;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Blue
 */

public class GuiFactory implements IModGuiFactory
{
    @Override
    public void initialize(Minecraft minecraftInstance)
    {

    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass()
    {
        return BlepCoreGuiConfig.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
    {
        return null;
    }

    public static class BlepCoreGuiConfig extends GuiConfig
    {

        public BlepCoreGuiConfig(GuiScreen parent)
        {
            super(parent, getConfigElements(), "FML", false, false, I18n.format("fml.config.sample.title"));
        }

        private static List<IConfigElement> getConfigElements()
        {
            List<IConfigElement> topLevelElements = new ArrayList<>();
            List<IConfigElement> secondLevelElements = new ArrayList<>();
            List<IConfigElement> thirdLevelElements = new ArrayList<>();

            thirdLevelElements.add(new DummyConfigElement("booleanthing", Configs.shouldDoThing, ConfigGuiType.BOOLEAN, Utils.translateConfig(Names.Configs.TESTCONFIG)));

            secondLevelElements.add(new DummyCategoryElement("lvl3", Utils.translateConfig("subcategory"), thirdLevelElements));

            topLevelElements.add(new DummyCategoryElement("lvl2", Utils.translateConfig("category"), secondLevelElements));

            return topLevelElements;
        }
    }
}
