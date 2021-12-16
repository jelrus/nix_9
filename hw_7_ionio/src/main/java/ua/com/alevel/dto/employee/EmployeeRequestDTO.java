package ua.com.alevel.dto.employee;

import ua.com.alevel.dto.BaseRequestDTO;

import java.math.BigDecimal;

public class EmployeeRequestDTO extends BaseRequestDTO {

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private BigDecimal salary;

    public EmployeeRequestDTO(String firstName, String lastName, int age, String email, BigDecimal salary) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setEmail(email);
        setSalary(salary);
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
}