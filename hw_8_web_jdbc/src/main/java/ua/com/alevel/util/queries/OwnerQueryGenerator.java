package ua.com.alevel.util.queries;

import ua.com.alevel.persistence.datatable.DataTableRequest;

public final class OwnerQueryGenerator {

    private static final String MASTER_NAME = "owners";
    private static final String MASTER_COLUMN = "owner_id";
    private static final String SLAVE = "house";
    private static final String SLAVE_COLUMN = "house_id";
    private static final String RESULT_TABLE = "property";
    private static final String RESULT_TABLE_REF = String.valueOf(RESULT_TABLE.charAt(0));
    private static final Integer COLUMNS_NUMBER = 6;
    private static final String[] COLUMNS_FULL = {"owners.id", "owners.created", "owners.updated",
                                                  "first_name", "last_name", "email", "phone"};
    private static final String[] COLUMNS_UPDATE = {"updated",
                                                    "first_name", "last_name", "email", "phone"};

    public static String create() {
        return AbstractQueryGenerator.create(MASTER_NAME, COLUMNS_NUMBER);
    }

    public static String update(Long id) {
        return AbstractQueryGenerator.update(MASTER_NAME, COLUMNS_UPDATE, id);
    }

    public static String delete(Long id) {
        return AbstractQueryGenerator.delete(MASTER_NAME, id);
    }

    public static String exist(Long id) {
        return AbstractQueryGenerator.exist(MASTER_NAME, id);
    }

    public static String count() {
        return AbstractQueryGenerator.count(MASTER_NAME);
    }

    public static String findById(Long id) {
        return AbstractQueryGenerator.findById(MASTER_NAME, id);
    }

    public static String countByEntity(Long slaveId) {
        return AbstractQueryGenerator.countByEntity(RESULT_TABLE, SLAVE_COLUMN, slaveId);
    }

    public static String findAll() {
        return AbstractQueryGenerator.findAll(MASTER_NAME);
    }

    public static String findAllOwners(DataTableRequest request) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        return "SELECT * FROM " + MASTER_NAME + " " +
                "ORDER BY " + request.getSort() + " " + request.getOrder() + " " +
                "LIMIT " + limit + ", " + request.getPageSize() + ";";
    }

    public static String findAllOwnersByHouse(DataTableRequest request, Long houseId) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        return "SELECT " + COLUMNS_FULL[0] + " AS id, " +
                           COLUMNS_FULL[1] + " AS created, " +
                           COLUMNS_FULL[2] + " AS updated, " +
                           COLUMNS_FULL[3] + ", " +
                           COLUMNS_FULL[4] + ", " +
                           COLUMNS_FULL[5] + ", " +
                           COLUMNS_FULL[6] + " " +
                "FROM " + MASTER_NAME + " " +
                "JOIN " + RESULT_TABLE + " " + RESULT_TABLE_REF + " " +
                "ON " + MASTER_NAME + "." + "id" + " = " + RESULT_TABLE_REF + "." + MASTER_COLUMN + " " +
                "WHERE " + SLAVE_COLUMN + " = " + houseId + " " +
                "ORDER BY " + request.getSort() + " " + request.getOrder() + " " +
                "LIMIT " + limit + ", " + request.getPageSize() + ";";
    }
}
