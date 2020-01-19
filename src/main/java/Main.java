import scanners.BeanScanner;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Class> allAnnotatedClasses = BeanScanner.getAllAnnotatedClasses();
        allAnnotatedClasses.forEach(ss -> System.out.println(ss.toString()));
    }
}
