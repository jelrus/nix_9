package ua.com.alevel.utils.structures;

import java.util.Objects;

public class Node {

    private String name;
    private int index;

    public Node(int index) {
        this.index = index;
    }

    public Node(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node node)) return false;
        return getIndex() == node.getIndex() && Objects.equals(getName(), node.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getIndex());
    }

    @Override
    public String toString() {
        return name + ", " + index;
    }
}