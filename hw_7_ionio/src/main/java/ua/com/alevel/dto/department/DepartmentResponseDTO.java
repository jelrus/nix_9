package ua.com.alevel.dto.department;

import ua.com.alevel.dto.BaseResponseDTO;
import ua.com.alevel.entity.impl.Department;

public class DepartmentResponseDTO extends BaseResponseDTO {

    private String name;

    public DepartmentResponseDTO(Department department) {
        setId(department.getId());
        setName(department.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", Name: " + name;
    }
}