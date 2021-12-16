package ua.com.alevel.dto.departmentemployee;

import ua.com.alevel.dto.BaseRequestDTO;

public class DepartmentEmployeeRequestDTO extends BaseRequestDTO {

    private String departmentId;
    private String employeeId;

    public DepartmentEmployeeRequestDTO(String departmentId, String employeeId) {
        setDepartmentId(departmentId);
        setEmployeeId(employeeId);
    }

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
}