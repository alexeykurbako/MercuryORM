import beans.factory.BeanInitializer;
import beans.factory.BeanInjector;
import beans.factory.BeanPool;
import scanners.BeanScanner;
import service.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BeanInitializer beanInitializer = new BeanInitializer();
        beanInitializer.init();
        BeanInjector.inject();
        UserService sss = (UserService) BeanPool.getByKey("UserService");
        sss.sssss();
//BeanPool.getStringBeanMap().forEach((key, value)->{
//    System.out.println(key);
//
//});
    }
}
