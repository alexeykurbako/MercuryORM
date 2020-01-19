package beans.factory;

import beans.DynamicInvocationHandler;
import scanners.BeanScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.List;

public class BeanInitializer {
    public void init() {
        List<Class> allAnnotatedClasses = BeanScanner.getAllAnnotatedClasses();
        DynamicInvocationHandler handler = new DynamicInvocationHandler();

        allAnnotatedClasses.forEach(clazz -> {
            Object instance;
            if(clazz.isInterface()) {
                instance = Proxy.newProxyInstance(
                        DynamicInvocationHandler.class.getClassLoader(), new Class[]{clazz}, handler);
            } else {
                try {
                    instance = clazz.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException
                        | InvocationTargetException | NoSuchMethodException e) {
                   throw new RuntimeException("Bean initialization crash");
                }
            }
            BeanPool.put(clazz.getName(), instance);
        });

    }
}
