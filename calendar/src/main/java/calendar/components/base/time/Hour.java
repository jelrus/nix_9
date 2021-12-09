package calendar.components.base.time;

public class Hour implements Comparable<Hour> {

    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 24;
    private int value;

    public Hour() {
        this.value = MIN_VALUE;
    }

    public Hour(int value) {
        if (value < 0) {
            new Hour();
        } else {
            this.value = value;
        }
    }

    public void addHours(int hours) {
        value = hours + getValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Hour target) {
        return Integer.compare(this.getValue(), target.getValue());
    }

    @Override
    public String toString() {
        String hourString;
        if (value >= 0 && value <= 9) {
            hourString = "0" + value;
        } else {
            hourString = String.valueOf(value);
        }
        return hourString;
    }
}