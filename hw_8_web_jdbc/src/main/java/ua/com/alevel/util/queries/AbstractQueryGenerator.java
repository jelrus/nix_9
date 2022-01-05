package ua.com.alevel.util.queries;

public abstract class AbstractQueryGenerator {

    public static String create(String table, int values) {
        String create = "INSERT INTO " + table + " VALUES(default, ";
        for (int i = 0; i < values; i++) {
            if (i < values - 1) {
                create += "?, ";
            } else {
                create += "?);";
            }
        }
        return create;
    }

    public static String update(String table, String[] columns, Long id) {
        String update = "UPDATE " + table + " SET ";
        for (int i = 0; i < columns.length; i++) {
            if (i < columns.length - 1) {
                update += columns[i] + " = " + "?, ";
            } else {
                update += columns[i] + " = " + "?";
            }
        }
        update += " WHERE id = " + id + ";";
        return update;
    }

    public static String delete(String table, Long id) {
        return "DELETE FROM " + table + " WHERE id = " + id + ";";
    }

    public static String exist(String table, Long id) {
        return "SELECT COUNT(*) as count FROM " + table + " WHERE id = " + id + ";";
    }

    public static String count(String table) {
        return "SELECT COUNT(*) as count FROM " + table + ";";
    }

    public static String findById(String table, Long id) {
        return "SELECT * FROM " + table + " WHERE id = " + id + ";";
    }

    public static String countByEntity(String masterTable, String slaveColumn, Long slaveId) {
        return "SELECT COUNT(*) as count FROM " + masterTable + " WHERE " + slaveColumn + " = " + slaveId + ";";
    }

    public static String findAll(String table) {
        return "SELECT * FROM " + table + ";";
    }
}
