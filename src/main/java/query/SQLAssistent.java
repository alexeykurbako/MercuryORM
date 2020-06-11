package query;

import demo.src.DAO.query.exceptions.FieldNotFoundException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import query.enums.QueryType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SQLAssistent<T> {


    private String[] OPERATIONS = {"GET", "FIND", "UPDATE", "EDIT", "INSERT", "ADD", "DELETE", "REMOVE"};

    private static final String AND_SEPARATOR = "And";
    private static final String BY_SEPARATOR = "By";

    private QueryType defineQueryType(String methodName) {
        QueryType resultType = QueryType.SELECT;
        switch (methodName.toLowerCase()) {
            case "get":
            case "find":
                resultType = QueryType.SELECT;
                break;
            case "update":
            case "edit":
                resultType = QueryType.UPDATE;
                break;
            case "insert":
            case "add":
                resultType = QueryType.INSERT;
                break;
            case "delete":
            case "remove":
                resultType = QueryType.DELETE;
                break;
        }
        return resultType;
    }

    private boolean isFieldExist(Field[] entityFields, String field) {
        for (Field entityField : entityFields) {
            if (entityField.getName().toLowerCase().equals(field.toLowerCase())) {
                return true;
            }
        }
        throw new FieldNotFoundException("Field " + field + "isn't exist");
    }

    private void appendElement(StringBuilder query, String element) {
        query.append(element);
        query.append(" ");
    }

    private boolean isOperation(String operationForCheck) {
        return Arrays.stream(OPERATIONS)
                .anyMatch((operation) -> operationForCheck.toUpperCase().equals(operation));
    }

    private String getLastOperation(String[] words, int currentIndex) {
        for(int i = currentIndex; i >= 0; i--) {
            if(isOperation(words[i]))
                return words[i];
        }
        throw new RuntimeException("Operation doesn't exist");
    }

    public List<Pair<String, String>> transformMethodNameToQueryParts(String[] words) {
        List<Pair<String, String>> queryParts = new ArrayList<>();

//        Field[] existingEntityFields = entityType.getFields();
//        queryParts.put(type.name(), entityType.getName());
        if (words.length > 2) {
            for (int i = 1; i < words.length; i++) {
                String part = words[i];
                //TODO check are operation name and field exist
                if (part.equals(BY_SEPARATOR)) {
                    String operation = words[i - 1];
                    String field = words[i + 1];
                    queryParts.add(new ImmutablePair<>(operation, field));
                }
                if (part.equals(AND_SEPARATOR)) {
                    String operation = getLastOperation(words, i);
                    String field = words[i + 1];
                    queryParts.add(new ImmutablePair<>(operation, field));
                }
            }
        }

        return queryParts;
    }

    public String buildSelect(List<Pair<String, String>> queryParts, String table) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        appendElement(query, table);
        return query.toString();
    }

    //TODO: move to separate service
    private List<String> getParameterNames(Method method) {
        Parameter[] parameters = method.getParameters();
        List<String> parameterNames = new ArrayList<>();

        for (Parameter parameter : parameters) {
            if(!parameter.isNamePresent()) {
                throw new IllegalArgumentException("Parameter names are not present!");
            }

            String parameterName = parameter.getName();
            parameterNames.add(parameterName);
        }

        return parameterNames;
    }

    private Map<String, Object> mergeLists(List<String> keys, List<Object> values) {
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }


    public QueryShards buildSqlQuery(Method method, List<Object> queryParams) {
        String[] words = method.getName().split("(?=[A-Z])");

        Class returnType = method.getReturnType();
        QueryType queryType = defineQueryType(words[0]);
        List<Pair<String, String>> queryParts = transformMethodNameToQueryParts(words);
        Map<String, Object> queryParameters = mergeLists(getParameterNames(method), queryParams);

        String query = "";
        switch (queryType) {
            case SELECT:
                query = buildSelect(queryParts, returnType.toString());
                break;
        }
        System.out.println(query);
        return null;
    }

    //Generics: Long, User
//User find User By  Id Group By Date Sort By Age(Long Id);
//SELECT: USER
//WHERE: ID
//GROUP: DATE
//SORT: AGE
//"SELECT user_id,login,email,password,money,role,is_locked FROM totalizator.user WHERE user_id = ? GROUP BY date SORT BY age";


}
