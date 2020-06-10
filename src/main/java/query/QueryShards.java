package query;

import org.apache.commons.lang3.tuple.Pair;
import query.enums.QueryType;

import java.util.List;

public class QueryShards {
    private QueryType type;
    private String tableName;
    private List<Pair<String, String>> subselects;
    private List<Pair<String, String>> parameters;
    private Class returningType;

    public QueryShards(QueryType type, String tableName, List<Pair<String, String>> subselects, List<Pair<String, String>> parameters, Class returningType) {
        this.type = type;
        this.tableName = tableName;
        this.subselects = subselects;
        this.parameters = parameters;
        this.returningType = returningType;
    }

    public QueryShards() {
    }

    public QueryType getType() {
        return type;
    }

    public void setType(QueryType type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Pair<String, String>> getSubselects() {
        return subselects;
    }

    public void setSubselects(List<Pair<String, String>> subselects) {
        this.subselects = subselects;
    }

    public List<Pair<String, String>> getParameters() {
        return parameters;
    }

    public void setParameters(List<Pair<String, String>> parameters) {
        this.parameters = parameters;
    }

    public Class getReturningType() {
        return returningType;
    }

    public void setReturningType(Class returningType) {
        this.returningType = returningType;
    }
}
