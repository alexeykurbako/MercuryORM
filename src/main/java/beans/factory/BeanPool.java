package beans.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BeanPool {
    private static Map<String, Object> stringBeanMap = new HashMap<>();

    public static Object getByKey(String key) {
        return stringBeanMap.get(key);
    }

    static List<Object> getBeans() {
        return stringBeanMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public static void initialize(Map<String, Object> map) {
        stringBeanMap = map;
    }

    static void put(String key, Object value) {
        stringBeanMap.put(key, value);
    }

    public static Map<String, Object> getStringBeanMap() {
        return stringBeanMap;
    }
}
