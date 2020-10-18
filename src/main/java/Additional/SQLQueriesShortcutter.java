package Additional;

import java.util.StringJoiner;

public class SQLQueriesShortcutter {
    private static String query;

    public static String selectAll(String tableName) {
        return "SELECT * FROM " + tableName;
    }

    public static String selectWithoutCondition(String tableName, String... columns) {
        query = "SELECT ";
        StringJoiner selectedColumns = new StringJoiner(", ");
        for (String column : columns) {
            selectedColumns.add(column);
        }
        return query.concat(selectedColumns + " FROM " + tableName);
    }

    public static String select(String tableName, String condition, String... columns) {
        query = "SELECT ";
        StringJoiner selectedColumns = new StringJoiner(", ");
        for (String column : columns) {
            selectedColumns.add(column);
        }
        return query.concat(selectedColumns + " FROM " + tableName + " WHERE " + condition);
    }

    public static String insert(String tableName, int numberOfValues) {
        query = "INSERT INTO " + tableName + " VALUES (";
        StringJoiner unknowns = new StringJoiner(",");
        for (int i = 0; i < numberOfValues; i++) {
            unknowns.add("?");
        }
        return query.concat(unknowns + ")");
    }

    public static String delete(String tableName, String condition) {
        return "DELETE FROM " + tableName + " WHERE " + condition;
    }

    public static String update(String tableName, String condition, String... columns) {
        query = "UPDATE " + tableName + " SET ";
        StringJoiner changedColumns = new StringJoiner(", ");
        for (String column : columns) {
            changedColumns.add(column + " = ?");
        }
        return query.concat(String.valueOf(changedColumns))
                .concat(" WHERE " + condition);
    }
}
