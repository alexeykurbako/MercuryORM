package demo.src.DAO.query;

import java.util.List;

public class SQLAssistent<T> {
    public String build(String methodName, List<Object> queryParams) {
        StringBuilder query = new StringBuilder();
        String[] queryParts = methodName.split("(?=[A-Z])");
        //TODO implement comparison fields from class and from method name
        for (String component : queryParts) {

        }
        //private static final String GET_USER_BY_ID = "SELECT user_id,login,email,password,money,role,is_locked FROM totalizator.user WHERE user_id = ?";
        return query.toString();
    }

    public T exexute() {
        //TODO define query type

    }



}
