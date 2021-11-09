package ua.com.alevel.console.log.entries;

import java.time.LocalTime;

public class Entry implements Comparable<Entry> {

    public LocalTime time;
    public String operation;
    public String input;
    public String output;

    public Entry(String operation, String input, String output) {
        this.time = LocalTime.now();
        this.operation = operation;
        this.input = input;
        this.output = output;
    }

    @Override
    public int compareTo(Entry comparable) {
        return this.time.compareTo(comparable.time);
    }
}