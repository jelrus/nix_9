package ua.com.alevel.entity.impl;

import ua.com.alevel.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Objects;

public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private BigDecimal salary;

    public Employee() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return getAge() == employee.getAge() && Objects.equals(getFirstName(), employee.getFirstName())
                && Objects.equals(getLastName(), employee.getLastName())
                && Objects.equals(getEmail(), employee.getEmail())
                && Objects.equals(getSalary(), employee.getSalary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getAge(), getEmail(), getSalary());
    }

    @Override
    public String toString() {
        return super.getId() + ", " + firstName + ", " + lastName + ", " + age + ", " + email + ", " + salary;
    }
}