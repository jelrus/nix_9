package ua.com.alevel.utils.structures;

public class Arc {

    private int value;

    public Arc(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}