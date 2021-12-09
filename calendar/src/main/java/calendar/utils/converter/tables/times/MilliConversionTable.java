package calendar.utils.converter.tables.times;

public enum MilliConversionTable {

    SECOND_TO_MILLI(1000D),
    MINUTE_TO_MILLI(60D * 1000D),
    HOUR_TO_MILLI(60D * 60D * 1000D),
    DAY_TO_MILLI(24D * 60L * 60D * 1000D),
    COMMON_YEAR_TO_MILLI(365D * 24D * 60D * 60D * 1000D),
    LEAP_YEAR_TO_MILLI(366D * 24D * 60D * 60D * 1000D);

    private final double value;

    MilliConversionTable(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}