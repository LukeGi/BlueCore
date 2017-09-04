package dk.futte.blue.teamblep.blepcore;

import dk.futte.blue.teamblep.blepcore.content.block.machine.MachineData;
import dk.futte.blue.teamblep.blepcore.content.inventory.GuiHandler;
import dk.futte.blue.teamblep.blepcore.refs.ModInfo;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Kelan
 */

public class Utils
{
    public static <T> T initializeClassWithConstructor(Class<? extends T> clazz, Class<?>[] parameterTypes, Object[] parameters)
    {
        try
        {
            return clazz.getDeclaredConstructor(parameterTypes).newInstance(parameters);
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
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
}
