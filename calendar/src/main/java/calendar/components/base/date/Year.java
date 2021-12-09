package calendar.components.base.date;

public class Year implements Comparable<Year> {

    public static final int MIN_VALUE = 0;
    private int value;

    public Year() {
        this.value = MIN_VALUE;
    }

    public Year(int value) {
        if (value < 0) {
            new Year();
        } else {
            this.value = value;
        }
    }

    public void addYears(int years) {
        value = years + getValue();
    }

    public boolean isLeap() {
        if (getValue() % 100 == 0) {
            return getValue() % 400 == 0;
        } else {
            return getValue() % 4 == 0;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Year target) {
        return Integer.compare(this.getValue(), target.getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}