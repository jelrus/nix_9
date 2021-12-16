package ua.com.alevel.dto.department;

import ua.com.alevel.dto.BaseRequestDTO;

public class DepartmentRequestDTO extends BaseRequestDTO {

    private String name;

    public DepartmentRequestDTO(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}