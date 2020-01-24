package demo.src.DAO.query;

import java.util.List;

enum QueryType {
    SELECT, UPDATE, INSERT, DELETE
}

public class SQLAssistent<T> {
    private QueryType defineQueryType(String methodName) {
        QueryType resultType = QueryType.SELECT;
        switch(methodName.toLowerCase()) {
            case "get":
                resultType = QueryType.SELECT;
                break;
            case "update":
                resultType = QueryType.UPDATE;
                break;

            case "insert":
                resultType = QueryType.INSERT;
                break;
            case "delete":
                resultType = QueryType.DELETE;
                break;
        }
        return resultType;
    }

    public String build(String methodName, List<Object> queryParams) throws ClassNotFoundException {
        StringBuilder query = new StringBuilder();
        String[] queryParts = methodName.split("(?=[A-Z])");
        //TODO implement comparison fields from class and from method name
        QueryType type = defineQueryType(queryParts[0]);
        Class entityType = Class.forName(queryParts[1]);
        for (String component : queryParts) {

        }
        //"SELECT user_id,login,email,password,money,role,is_locked FROM totalizator.user WHERE user_id = ?";
        return query.toString();
    }

    public T exexute(QueryType type) {

    }



}
