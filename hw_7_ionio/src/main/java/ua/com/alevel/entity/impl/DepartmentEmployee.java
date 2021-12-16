package ua.com.alevel.entity.impl;

import ua.com.alevel.entity.BaseEntity;

import java.util.Objects;

public class DepartmentEmployee extends BaseEntity {

    private String departmentId;
    private String employeeId;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentEmployee)) return false;
        DepartmentEmployee that = (DepartmentEmployee) o;
        return Objects.equals(getDepartmentId(), that.getDepartmentId()) && Objects.equals(getEmployeeId(), that.getEmployeeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartmentId(), getEmployeeId());
    }

    @Override
    public String toString() {
        return getId() + ", " + departmentId + ", " + employeeId;
    }
}