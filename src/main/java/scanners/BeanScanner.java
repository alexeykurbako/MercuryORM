package scanners;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeanScanner {
    public static List<Class> getAllAnnotatedClasses() {
        Reflections ref = new Reflections("");
        Set<Class<? extends Annotation>> annotations = AnnotationsScanner.getAnnotations();
        List<Class> annotatedBeansSet = new ArrayList<>();
        //TODO implement set instead of list
        annotations.forEach(annotation ->
            annotatedBeansSet.addAll(ref.getTypesAnnotatedWith(annotation, true))
        );
        return annotatedBeansSet;
    }
}
