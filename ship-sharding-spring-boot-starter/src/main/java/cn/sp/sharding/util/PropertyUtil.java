package cn.sp.sharding.util;

import org.springframework.core.env.Environment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: Ship
 * @Description:
 * @Date: Created in 2023/6/6
 */
public class PropertyUtil {

    public static <T> T handle(final Environment environment, final String prefix, final Class<T> targetClass) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class<?> binderClass = Class.forName("org.springframework.boot.context.properties.bind.Binder");
        Method getMethod = binderClass.getDeclaredMethod("get", Environment.class);
        Method bindMethod = binderClass.getDeclaredMethod("bind", String.class, Class.class);

        Object binderObject = getMethod.invoke(null, environment);
        String prefixParam = prefix.endsWith(".") ? prefix.substring(0, prefix.length() - 1) : prefix;
        Object bindResultObject = bindMethod.invoke(binderObject, prefixParam, targetClass);
        Method resultGetMethod = bindResultObject.getClass().getDeclaredMethod("get");
        return (T)resultGetMethod.invoke(bindResultObject);
    }
}
