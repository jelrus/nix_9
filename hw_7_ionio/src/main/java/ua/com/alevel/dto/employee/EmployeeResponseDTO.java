package ua.com.alevel.dto.employee;

import ua.com.alevel.dto.BaseResponseDTO;
import ua.com.alevel.entity.impl.Employee;

import java.math.BigDecimal;

public class EmployeeResponseDTO extends BaseResponseDTO {

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private BigDecimal salary;

    public EmployeeResponseDTO(Employee employee) {
        setId(employee.getId());
        setFirstName(employee.getFirstName());
        setLastName(employee.getLastName());
        setAge(employee.getAge());
        setEmail(employee.getEmail());
        setSalary(employee.getSalary());
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
    public String toString() {
        return "ID: " + getId() + ", First Name: " + firstName + ", Last Name: " + lastName + ", Age: " + age +
               ", Email: " + email + ", Salary: " + salary;
    }
}