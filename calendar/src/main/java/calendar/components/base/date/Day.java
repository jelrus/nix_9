package calendar.components.base.date;

public class Day implements Comparable<Day> {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE_ODD = 31;
    public static final int MAX_VALUE_EVEN = 30;
    public static final int MAX_VALUE_COMMON_YEAR = 28;
    public static final int MAX_VALUE_LEAP_YEAR = 29;
    private int value;

    public Day() {
        this.value = MIN_VALUE;
    }

    public Day(int value) {
        if (value < MIN_VALUE) {
            new Day();
        } else {
            this.value = value;
        }
    }

    public void addDays(int days) {
        value = days + getValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Day target) {
        return Integer.compare(this.getValue(), target.getValue());
    }

    @Override
    public String toString() {
        String dayString;
        if (value >= 0 && value <= 9) {
            dayString = "0" + value;
        } else {
            dayString = String.valueOf(value);
        }
        return dayString;
    }
}