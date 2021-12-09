package calendar.utils.converter.tables.times;

public enum SecondConversionTable {

    MILLI_TO_SECOND(1 / 1000D),
    MINUTE_TO_SECOND(60L),
    HOUR_TO_SECOND(60L * 60L),
    DAY_TO_SECOND(24L * 60L * 60L),
    COMMON_YEAR_TO_SECOND(365L * 24L * 60L * 60L),
    LEAP_YEAR_TO_SECOND(366L * 24L * 60L * 60L);

    private final double value;

    SecondConversionTable(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}