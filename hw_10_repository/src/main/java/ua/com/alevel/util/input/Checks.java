package ua.com.alevel.util.input;

public final class Checks {

    private Checks() {}

    public static boolean isBlankNullOrEmpty(String input) {
        return input == null || input.isEmpty() || input.isBlank();
    }

    public static boolean isPositiveNumber(String input) {
        if (input == null) {
            return false;
        }
        try {
            Double.parseDouble(input);
            if (Double.parseDouble(input) < 0) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(String input) {
        return !input.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+$");
    }

    public static boolean isValidPhone(String input) {
        return !input.matches( "^\\+[1-9][0-9]{3,14}$");
    }
}
