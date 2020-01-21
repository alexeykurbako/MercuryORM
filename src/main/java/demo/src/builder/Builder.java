package demo.src.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Builder<T> {
    T build(ResultSet resultSet) throws SQLException;
}
