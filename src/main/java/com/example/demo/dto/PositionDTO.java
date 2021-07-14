package com.example.demo.dto;

/**
 * @author 涼月
 * @date 2021/6/29
 */
public class PositionDTO {
    private String name;
    private Integer departmentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "PositionDTO{" +
                "name='" + name + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
