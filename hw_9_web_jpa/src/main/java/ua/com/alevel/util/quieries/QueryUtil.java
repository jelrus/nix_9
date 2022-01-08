package ua.com.alevel.util.quieries;

public abstract class QueryUtil {

    public static String deleteEntityById(String tableName, String refTableName) {
        return "delete from " + tableName + " " + refTableName + " " +
               "where " + refTableName + ".id = :id";
    }

    public static String checkExistenceById(String tableName, String refTableName) {
        return "select count(" + refTableName + ".id) from " + tableName + " " + refTableName + " " +
               "where " + refTableName + ".id = :id";
    }

    public static String countEntities(String tableName, String refTableName) {
        return "select count(" + refTableName + ".id) from " + tableName + " " + refTableName;
    }

    public static String findAll(String tableName, String refTableName) {
        return "select " + refTableName + " from " + tableName + " " + refTableName;
    }
}
