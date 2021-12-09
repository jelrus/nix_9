package calendar.utils.converter.tables.times;

public enum HourConversionTable {

    MILLI_TO_HOUR(1 / (1000D * 60D * 60D)),
    SECOND_TO_HOUR(1 / (60D * 60D)),
    MINUTE_TO_HOUR(1 / 60D),
    DAY_TO_HOUR(24L),
    COMMON_YEAR_TO_HOUR(365L * 24L),
    LEAP_YEAR_TO_HOUR(366L * 24L);

    private final double value;

    HourConversionTable(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}