package ua.com.alevel.util.quieries;

public final class OwnerQueries {

    private static final String NAME = "Owner";
    private static final String REF_NAME = "o";

    private OwnerQueries() {}

    public static String deleteEntityById() {
        return QueryUtil.deleteEntityById(NAME, REF_NAME);
    }

    public static String checkExistenceById() {
        return QueryUtil.checkExistenceById(NAME, REF_NAME);
    }

    public static String countEntities() {
        return QueryUtil.countEntities(NAME, REF_NAME);
    }

    public static String findAll() {
        return QueryUtil.findAll(NAME, REF_NAME);
    }
}
