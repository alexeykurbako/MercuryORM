package beans;

import query.SQLAssistent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicInvocationHandler implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        SQLAssistent sqlAssistent = new SQLAssistent();
        //sqlAssistent.transformMethodNameToQueryParts(method, Arrays.asList(args));
        return "ssss";
    }


}
