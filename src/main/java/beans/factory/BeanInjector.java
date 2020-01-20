package beans.factory;

import annotations.Injected;
import beans.DynamicInvocationHandler;
import scanners.AnnotatedFieldsScanner;
import scanners.AnnotationsScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class BeanInjector {
    public static void inject() {
        BeanPool.getBeans().forEach(bean -> {
            AnnotationsScanner.getAnnotations().forEach(annotation -> {
                Field[] annotatedDeclaredFields = AnnotatedFieldsScanner
                        .getAnnotatedDeclaredFields(annotation, Injected.class);
                for (Field field : annotatedDeclaredFields) {
                    try {
                        field.setAccessible(true);
                        field.set(bean, BeanPool.getByKey(field.getType().getName()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Injection in field " + field.getName()
                                + " bean " + bean.getClass() + " was failed");
                    }
                }

            });
        });
    }
}

