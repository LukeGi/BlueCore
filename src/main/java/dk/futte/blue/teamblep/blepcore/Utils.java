package dk.futte.blue.teamblep.blepcore;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Kelan
 */

public class Utils
{
    public static <T> T initializeClassWithConstructor(Class<? extends T> clazz, Object... args)
    {
        Class[] objectClasses = Arrays.stream(args).map(Object::getClass).collect(Collectors.toList()).toArray(new Class[args.length]);
        try
        {
            return clazz.getDeclaredConstructor(objectClasses).newInstance(args);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> boolean instanceOf(Class<T> class1, Class<T> class2) {
        return class1.isInstance(class2);
    }
}
