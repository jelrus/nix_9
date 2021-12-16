package ua.com.alevel.dto.departmentemployee;

import ua.com.alevel.dto.BaseResponseDTO;
import ua.com.alevel.entity.impl.Department;
import ua.com.alevel.entity.impl.Employee;

public class DepartmentEmployeeResponseDTO extends BaseResponseDTO {

    private Department department;
    private Employee employee;

    public DepartmentEmployeeResponseDTO(String id, Department department, Employee employee) {
        setId(id);
        setDepartment(department);
        setEmployee(employee);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Relation ID: " + getId() + ", DepID: " + department.getId() + ", EmpID: " + employee.getId();
    }
}