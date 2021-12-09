package calendar.components.base.date;

public class Month implements Comparable<Month> {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 12;
    private int value;

    public Month() {
        this.value = MIN_VALUE;
    }

    public Month(int value) {
        if (value < 1) {
            new Month();
        } else {
            this.value = value;
        }
    }

    public void addMonths(int months) {
        value = months + getValue();
    }

    public boolean isEven() {
        return value == 4 || value == 6 || value == 9 || value == 11;
    }

    public boolean isOdd() {
        return value == 1 || value == 3 || value == 5 || value == 7 || value == 8 || value == 10 || value == 12;
    }

    public boolean isFebruary() {
        return value == 2;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Month target) {
        return Integer.compare(this.getValue(), target.getValue());
    }

    @Override
    public String toString() {
        String monthString;
        if (value >= 0 && value <= 9) {
            monthString = "0" + value;
        } else {
            monthString = String.valueOf(value);
        }
        return monthString;
    }
}