package calendar.components.base.time;

public class Second implements Comparable<Second> {

    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 60;
    private int value;

    public Second() {
        this.value = MIN_VALUE;
    }

    public Second(int value) {
        if (value < 0) {
            new Second();
        } else {
            this.value = value;
        }
    }

    public void addSeconds(int seconds) {
        value = seconds + getValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Second target) {
        return Integer.compare(this.getValue(), target.getValue());
    }

    @Override
    public String toString() {
        String secondString;
        if (value >= 0 && value <= 9) {
            secondString = "0" + value;
        } else {
            secondString = String.valueOf(value);
        }
        return secondString;
    }
}