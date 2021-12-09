package calendar.utils.converter.tables.times;

public enum MinuteConversionTable {

    MILLI_TO_MINUTE(1 / (1000D * 60D)),
    SECOND_TO_MINUTE(1 / 60D),
    HOUR_TO_MINUTE(60L),
    DAY_TO_MINUTE(24L * 60L),
    COMMON_YEAR_TO_MINUTE(365L * 24L * 60L),
    LEAP_YEAR_TO_MINUTE(366L * 24L * 60L);

    private final double value;

    MinuteConversionTable(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}