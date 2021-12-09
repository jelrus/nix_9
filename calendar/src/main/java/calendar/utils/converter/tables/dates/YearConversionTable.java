package calendar.utils.converter.tables.dates;

public enum YearConversionTable {

    MILLI_TO_LEAP_YEAR(1 / (1000D * 60D * 60D * 24D * 366D)),
    MILLI_TO_COMMON_YEAR(1 / (1000D * 60D * 60D * 24D * 365D)),
    SECOND_TO_LEAP_YEAR(1 / (60D * 60D * 24D * 366D)),
    SECOND_TO_COMMON_YEAR(1 / (60D * 60D * 24D * 365D)),
    MINUTE_TO_LEAP_YEAR(1 / (60D * 24D * 366D)),
    MINUTE_TO_COMMON_YEAR(1 / (60D * 24D * 365D)),
    HOUR_TO_LEAP_YEAR(1 / (24D * 366D)),
    HOUR_TO_COMMON_YEAR(1 / (24D * 365D)),
    DAY_TO_LEAP_YEAR(1 / 366D),
    DAY_TO_COMMON_YEAR(1 / 365D);

    private final double value;

    YearConversionTable(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}