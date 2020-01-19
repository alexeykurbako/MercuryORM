package scanners;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class AnnotationsScanner {

    private static Reflections reflections = new Reflections("annotations");

    public static Set<Class<? extends Annotation>> getAnnotations() {
        return reflections.getSubTypesOf(Annotation.class);
    }

}
