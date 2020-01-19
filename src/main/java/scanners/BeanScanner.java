package scanners;

import annotations.loader.AnnotationsScanner;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.TreeSet;

public class BeanScanner {
    public static Set<Class> getAllAnnotatedClasses() {
        Reflections ref = new Reflections("service");
        Set<Class<? extends Annotation>> annotations = AnnotationsScanner.getAnnotations();
        Set<Class> annotatedBeansSet = new TreeSet<>();
        annotations.forEach(annotation ->
            annotatedBeansSet.addAll(ref.getTypesAnnotatedWith(annotation))
        );
        return annotatedBeansSet;
    }
}
