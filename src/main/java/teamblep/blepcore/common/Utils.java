package teamblep.blepcore.common;

import teamblep.blepcore.common.block.machine.MachineData;
import teamblep.blepcore.common.inventory.GuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Kelan
 */

public final class Utils
{
    private Utils()
    {
    }

    private static final Random random = new Random();

    @SideOnly(Side.CLIENT)
    public static Random worldRandom()
    {
        return Minecraft.getMinecraft().theWorld.rand;
    }

    public static Random staticRandom()
    {
        return random;
    }

    public static boolean isItemStackNull(ItemStack itemStack)
    {
        return itemStack == null || itemStack.stackSize <= 0;
    }

    public static boolean canItemStacksMerge(ItemStack stack1, ItemStack stack2)
    {
        return !isItemStackNull(stack1) && !isItemStackNull(stack2) && stack1.isItemEqual(stack2) && stack1.stackSize + stack2.stackSize < stack1.getMaxStackSize();
    }

    public static void crashWithException(Exception exception)
    {
        crashWithException(exception, 42, false);
    }

    public static void crashWithException(Exception exception, int error, boolean hardExit)
    {
        if (exception == null)
        {
            Utils.crashWithException(new NullPointerException("You crashed with isInventoryValid null exception??? U dumb :("), 69, false);
        } else
        {
            BlepCore.logger.error("Your game appears to have crashed.", exception);
            FMLCommonHandler.instance().exitJava(error, hardExit);
        }
    }

    public static <T> T initializeClassWithConstructor(Class<? extends T> clazz, Class<?>[] parameterTypes, Object[] parameters)
    {
        try
        {
            return clazz.getDeclaredConstructor(parameterTypes).newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //long method name lol
    public static <T> T initializeClassWithConstructorAndParameters(Class<? extends T> clazz, Object... parameters)
    {
        Class[] parameterTypes = Arrays.stream(parameters).map(Object::getClass).collect(Collectors.toList()).toArray(new Class[parameters.length]);

        return initializeClassWithConstructor(clazz, parameterTypes, parameters);
    }

    public static int getGuiID(MachineData machineData)
    {
        return machineData.getInventoryContainer() == null ? -1 : GuiHandler.getIDFromHandler(machineData.getInventoryContainer());
    }

    public static String translateConfig(String name)
    {
        return ModInfo.MOD_ID + ".config." + name;
    }

    public static <T> boolean instanceOf(Class<T> class1, Class<T> class2)
    {
        return class1.isInstance(class2);
    }

    public static boolean addStackSize(ItemStack itemStack, int toAdd)
    {
        if (itemStack != null)
        {
            int newStackSize = itemStack.stackSize + toAdd;
            if (newStackSize > 0 && newStackSize <= itemStack.getMaxStackSize())
            {
                itemStack.stackSize = newStackSize;
                return true;
            }
        }

        return false;
    }
}
