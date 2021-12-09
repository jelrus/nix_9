package calendar.components.base.time;

public class Minute implements Comparable<Minute> {

    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 60;
    private int value;

    public Minute() {
        this.value = MIN_VALUE;
    }

    public Minute(int value) {
        if (value < 0) {
            new Minute();
        } else {
            this.value = value;
        }
    }

    public void addMinutes(int minutes) {
        value = minutes + getValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Minute target) {
        return Integer.compare(this.getValue(), target.getValue());
    }

    @Override
    public String toString() {
        String minuteString;
        if (value >= 0 && value <= 9) {
            minuteString = "0" + value;
        } else {
            minuteString = String.valueOf(value);
        }
        return minuteString;
    }
}