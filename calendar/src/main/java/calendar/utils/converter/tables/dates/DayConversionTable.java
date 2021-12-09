package calendar.utils.converter.tables.dates;

public enum DayConversionTable {

    MILLI_TO_DAY(1 / (1000D * 60D * 60D * 24D)),
    SECOND_TO_DAY(1 / (60D * 60D * 24D)),
    MINUTE_TO_DAY(1 / (60D * 24D)),
    HOUR_TO_DAY(1 / 24D),
    COMMON_YEAR_TO_DAY(365L),
    LEAP_YEAR_TO_DAY(366L);

    private final double value;

    DayConversionTable(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}