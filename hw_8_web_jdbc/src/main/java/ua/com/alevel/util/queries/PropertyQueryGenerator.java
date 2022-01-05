package ua.com.alevel.util.queries;

import ua.com.alevel.persistence.datatable.DataTableRequest;

public final class PropertyQueryGenerator {

    private static final String MASTER_NAME = "property";
    private static final String FIRST_ENTITY_NAME = "houses";
    private static final String FIRST_ENTITY_REF = String.valueOf(FIRST_ENTITY_NAME.charAt(0));
    private static final String FIRST_ENTITY_ID = "house_id";
    private static final String SECOND_ENTITY_NAME = "owners";
    private static final String SECOND_ENTITY_REF = String.valueOf(SECOND_ENTITY_NAME.charAt(0));
    private static final String SECOND_ENTITY_ID = "owner_id";
    private static final Integer COLUMNS_NUMBER = 4;
    private static final String[] COLUMNS_UPDATE = {"updated", "house_id", "owner_id"};

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
        return "SELECT * FROM " + MASTER_NAME + " " +
               "JOIN " + FIRST_ENTITY_NAME + " " + FIRST_ENTITY_REF + " " +
               "ON " + MASTER_NAME + "." + FIRST_ENTITY_ID + " = " + FIRST_ENTITY_REF + ".id" + " " +
               "JOIN " + SECOND_ENTITY_NAME + " " + SECOND_ENTITY_REF + " " +
               "ON " + MASTER_NAME + "." + SECOND_ENTITY_ID + " = " + SECOND_ENTITY_REF + ".id" + " " +
               "WHERE " + MASTER_NAME + ".id = " + id + ";";
    }

    public static String deleteHouse(Long houseId) {
        return "DELETE FROM " + MASTER_NAME + " WHERE " + FIRST_ENTITY_ID + " = " + houseId + ";";
    }

    public static String deleteOwner(Long ownerId) {
        return "DELETE FROM " + MASTER_NAME + " WHERE " + SECOND_ENTITY_ID + " = " + ownerId + ";";
    }

    public static String findAll(DataTableRequest request) {
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        return "SELECT * FROM " + MASTER_NAME + " " +
               "JOIN " + FIRST_ENTITY_NAME + " AS " + FIRST_ENTITY_REF + " " +
               "ON " + MASTER_NAME + "." + FIRST_ENTITY_ID + " = " + FIRST_ENTITY_REF + ".id " +
               "JOIN " + SECOND_ENTITY_NAME + " AS " + SECOND_ENTITY_REF + " " +
               "ON " + MASTER_NAME + "." + SECOND_ENTITY_ID + " = " + SECOND_ENTITY_REF + ".id " +
               "ORDER BY " + request.getSort() + " " + request.getOrder() + " " +
               "LIMIT " + limit + ", " + request.getPageSize() + ";";
    }
}
