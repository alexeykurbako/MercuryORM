package query;

import org.apache.commons.lang3.tuple.Pair;
import query.enums.QueryType;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class QueryShards {
    private QueryType type;
    private String tableName;
    private List<Pair<String, String>> subselects;
    private Map<String, Object> parameters;
    private Class returningType;

    public QueryShards(QueryType type, String tableName, List<Pair<String, String>> subselects, Map<String, Object> parameters, Class returningType) {
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

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Class getReturningType() {
        return returningType;
    }

    public void setReturningType(Class returningType) {
        this.returningType = returningType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryShards that = (QueryShards) o;
        return type == that.type &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(subselects, that.subselects) &&
                Objects.equals(parameters, that.parameters) &&
                Objects.equals(returningType, that.returningType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, tableName, subselects, parameters, returningType);
    }

    @Override
    public String toString() {
        return "QueryShards{" +
                "type=" + type +
                ", tableName='" + tableName + '\'' +
                ", subselects=" + subselects +
                ", parameters=" + parameters +
                ", returningType=" + returningType +
                '}';
    }
}
