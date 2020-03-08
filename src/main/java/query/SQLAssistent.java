package query;

import demo.src.DAO.query.exceptions.FieldNotFoundException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum QueryType {
    SELECT, UPDATE, INSERT, DELETE
}

enum SubselectType {
    GROUP, SORT
}


public class SQLAssistent<T> {
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

    public Map<String, String> transformMethodNameToQueryParts(Method method, List<Object> queryParams) throws ClassNotFoundException {
        Map<String, String> queryParts = new HashMap<>();

        String[] words = method.getName().split("(?=[A-Z])");

//        QueryType type = defineQueryType(words[0]);
//        Class entityType = Class.forName(words[1]);
//        Field[] existingEntityFields = entityType.getFields();
//        queryParts.put(type.name(), entityType.getName());
        queryParts.put("SELECT", "User");
        if (words.length > 2) {
            for (int i = 2; i < words.length; i++) {
                String part = words[i];
                //TODO check are operation name and field exist
                if (part.equals(AND_SEPARATOR)) {
                    String operation = SQLConstants.WHERE;
                    String field = words[i + 1];
                    queryParts.put(operation, field);
                }
                if (part.equals(BY_SEPARATOR)) {
                    String operation = words[i - 1];
                    String field = words[i + 1];
                    queryParts.put(operation, field);
                }
            }
        }

        return queryParts;
    }

    //Generics: Long, User
//User find User By Id Group By Date Sort By Age(Long Id);
//SELECT: USER
//WHERE: ID
//GROUP: DATE
//SORT: AGE
//"SELECT user_id,login,email,password,money,role,is_locked FROM totalizator.user WHERE user_id = ? GROUP BY date SORT BY age";


}
