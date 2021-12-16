package ua.com.alevel.entity.impl;

import ua.com.alevel.entity.BaseEntity;

import java.util.Objects;

public class Department extends BaseEntity {

    private String name;

    public Department() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department that)) return false;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return super.getId() + ", " + name;
    }
}