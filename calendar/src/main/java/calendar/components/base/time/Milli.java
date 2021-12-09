package calendar.components.base.time;

public class Milli implements Comparable<Milli> {

    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 1000;
    private int value;

    public Milli() {
        this.value = MIN_VALUE;
    }

    public Milli(int value) {
        if (value < 0) {
            new Milli();
        } else {
            this.value = value;
        }
    }

    public void addMillis(int millis) {
        value = millis + getValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Milli target) {
        return Integer.compare(this.getValue(), target.getValue());
    }

    @Override
    public String toString() {
        String milliString;
        if (value >= 0 && value <= 9) {
            milliString = "0" + "0" + value;
        } else if (value > 9 && value <= 99) {
            milliString = "0" + value;
        } else {
            milliString = String.valueOf(value);
        }
        return milliString;
    }
}