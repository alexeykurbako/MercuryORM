package beans;

import query.SQLAssistent;
import repository.BaseRepository;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicInvocationHandler implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        Class[] interfaces = method.getDeclaringClass().getInterfaces();
        List<Class> baseRepositories = Arrays.stream(interfaces)
                .filter(inter -> inter.equals(BaseRepository.class))
                .collect(Collectors.toList());

        if (baseRepositories.size() == 0) {
            throw new RuntimeException("don't implement BaseRepository");
        }

        Class base = baseRepositories.get(0);

        Type[] genericInterfaces = method.getDeclaringClass().getGenericInterfaces();
        Type[] genericTypes = null;
        for (Type genericInterface : genericInterfaces) {
            if (genericInterface instanceof ParameterizedType) {
                genericTypes = ((ParameterizedType) genericInterface).getActualTypeArguments();
            }
        }

        assert genericTypes != null;
        Type entityType = genericTypes[0];
        Type primaryKeyType = genericTypes[1];

        SQLAssistent sqlAssistent = new SQLAssistent();
        sqlAssistent.transformMethodNameToQueryParts(method, Arrays.asList(args));
        sqlAssistent.buildSqlQuery(entityType, primaryKeyType,);
        return "ssss";
    }


}
