package annotations.enums;

import java.util.HashMap;
import java.util.Map;

public enum AnnotationsRepository {
    INJECTED("Injected"), REPO("Repo"), SERVICE("Service");

    private String url;

    AnnotationsRepository(String envUrl) {
        this.url = envUrl;
    }

    public String getName() {
        return url;
    }

    private static final Map<String, AnnotationsRepository> lookup = new HashMap<>();

    static
    {
        for(AnnotationsRepository env : AnnotationsRepository.values())
        {
            lookup.put(env.getName(), env);
        }
    }

    public static AnnotationsRepository get(String name)
    {
        return lookup.get(name);
    }

}
