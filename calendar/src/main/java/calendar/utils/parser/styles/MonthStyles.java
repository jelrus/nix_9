package calendar.utils.parser.styles;

public enum MonthStyles {

    JANUARY(1, "January"),
    FEBRUARY(2, "February"),
    MARCH(3, "March"),
    APRIL(4, "April"),
    MAY(5, "May"),
    JUNE(6, "June"),
    JULY(7, "July"),
    AUGUST(8, "August"),
    SEPTEMBER(9, "September"),
    OCTOBER(10, "October"),
    NOVEMBER(11, "November"),
    DECEMBER(12, "December");

    private final int number;
    private final String verbose;

    MonthStyles(int number, String verbose) {
        this.number = number;
        this.verbose = verbose;
    }

    public static String getVerbose(int decimal) {
        switch (decimal) {
            case 1 -> {
                return MonthStyles.JANUARY.verbose;
            }
            case 2 -> {
                return MonthStyles.FEBRUARY.verbose;
            }
            case 3 -> {
                return MonthStyles.MARCH.verbose;
            }
            case 4 -> {
                return MonthStyles.APRIL.verbose;
            }
            case 5 -> {
                return MonthStyles.MAY.verbose;
            }
            case 6 -> {
                return MonthStyles.JUNE.verbose;
            }
            case 7 -> {
                return MonthStyles.JULY.verbose;
            }
            case 8 -> {
                return MonthStyles.AUGUST.verbose;
            }
            case 9 -> {
                return MonthStyles.SEPTEMBER.verbose;
            }
            case 10 -> {
                return MonthStyles.OCTOBER.verbose;
            }
            case 11 -> {
                return MonthStyles.NOVEMBER.verbose;
            }
            case 12 -> {
                return MonthStyles.DECEMBER.verbose;
            }
            default -> {
                return MonthStyles.JANUARY.verbose;
            }
        }
    }

    public int getValue() {
        return number;
    }
}