package beans.factory;

import java.util.HashMap;
import java.util.Map;

public class BeanPool {
    private static Map<String, Object> stringBeanMap = new HashMap<>();

    public static Object getByKey(String key) {
        return stringBeanMap.get(key);
    }

    public static void initialize(Map<String, Object> map) {
        stringBeanMap = map;
    }

    public static void put(String key, Object value) {
        stringBeanMap.put(key, value);
    }
}
