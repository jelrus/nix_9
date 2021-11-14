package ua.com.alevel.controller.utils.input;

public class InputChecks {

    public static boolean checkIfInputEmpty(String input) {
        return !input.isEmpty();
    }

    public static boolean checkIfInputBlank(String input) {
        return !input.isBlank();
    }

    public static boolean checkIfAlphabeticApostrophe(String input) {
        return input.matches("^[A-Za-zА-Яа-я']+$");
    }

    public static boolean checkIfIntegerNumber(String input) {
        if (input == null) {
            return false;
        }
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static Boolean checkRange(String input) {
        return Integer.parseInt(input) > 0 && Integer.parseInt(input) <= 120;
    }

    public static Boolean checkEmailPattern(String input) {
        return input.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+$");
    }

    public static boolean checkIfDoubleNumber(String input) {
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

    public static boolean checkIfPositiveIntegerNumber(String input) {
        if (input == null) {
            return false;
        }
        try {
            Integer.parseInt(input);
            if (Integer.parseInt(input) < 0) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}