package query;

import java.util.Objects;

public class SqlQuery {
    private String query;
    private Class returningType;

    public SqlQuery(String query, Class returningType) {
        this.query = query;
        this.returningType = returningType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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
        SqlQuery sqlQuery = (SqlQuery) o;
        return Objects.equals(query, sqlQuery.query) &&
                Objects.equals(returningType, sqlQuery.returningType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, returningType);
    }
}
