package ua.com.alevel.util.queries;

import ua.com.alevel.persistence.datatable.DataTableRequest;

public final class HouseQueryGenerator {

    private static final String MASTER_NAME = "houses";
    private static final String MASTER_COLUMN = "house_id";
    private static final String SLAVE = "owner";
    private static final String SLAVE_COLUMN = "owner_id";
    private static final String RESULT_TABLE = "property";
    private static final String RESULT_TABLE_REF = String.valueOf(RESULT_TABLE.charAt(0));
    private static final Integer COLUMNS_NUMBER = 9;
    private static final String[] COLUMNS_FULL = {"houses.id", "houses.created", "houses.updated",
                                                  "image", "country", "city", "street", "building_number",
                                                  "status", "cost"};
    private static final String[] COLUMNS_UPDATE = {"updated",
                                                    "image", "country", "city", "street", "building_number",
                                                    "status", "cost"};

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

    public static String findAllHouses(DataTableRequest request) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        return "SELECT " + parseFullColumns() + ", count(" + SLAVE_COLUMN + ") as " + SLAVE + "_count " +
               "FROM " + MASTER_NAME + " " +
               "LEFT OUTER JOIN " + RESULT_TABLE + " " + RESULT_TABLE_REF +
               " ON " + MASTER_NAME + ".id = " + RESULT_TABLE_REF +"." + MASTER_COLUMN + " " +
               "GROUP BY " + MASTER_NAME + ".id " +
               "ORDER BY " + request.getSort() + " " + request.getOrder() + " " +
               "LIMIT " + limit + ", " + request.getPageSize() + ";";
    }

    public static String findAllHousesByOwner(DataTableRequest request, Long ownerId) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        return "SELECT " + parseFullColumns() + ", " + SLAVE + "_count " +
                "FROM (SELECT " + parseFullColumns() + "FROM " + MASTER_NAME + " " +
                "LEFT OUTER JOIN " + RESULT_TABLE + " " + RESULT_TABLE_REF + " " +
                "ON " + MASTER_NAME + ".id = " + RESULT_TABLE_REF + "." + MASTER_COLUMN + " " +
                "WHERE " + SLAVE_COLUMN + " = " + ownerId + ") " +
                MASTER_NAME + " JOIN (SELECT " + MASTER_NAME + ".id, count(" + SLAVE_COLUMN + ") " +
                "AS " + SLAVE + "_count " + "FROM " + MASTER_NAME + " " +
                "LEFT OUTER JOIN " + RESULT_TABLE + " " + RESULT_TABLE_REF + " " +
                "ON " + MASTER_NAME + ".id" + " = " + RESULT_TABLE_REF + "." + MASTER_COLUMN + " " +
                "GROUP BY " + MASTER_NAME + ".id " + ") counts ON " + MASTER_NAME + ".id" + " = " + "counts.id " +
                "ORDER BY " + request.getSort() + " " + request.getOrder() + " " +
                "LIMIT " + limit + ", " + request.getPageSize() + ";";
    }

    private static String parseFullColumns() {
        String fullCols = "";
        for (int i = 0; i<COLUMNS_FULL.length; i++) {
            if (i < COLUMNS_FULL.length - 1) {
            fullCols += COLUMNS_FULL[i] + ", ";
            } else {
                fullCols += COLUMNS_FULL[i] + " ";
            }
        }
        return fullCols;
    }
}
