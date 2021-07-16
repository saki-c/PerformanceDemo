package com.example.demo.dto;

/**
 * @author 咲蛍
 * @date 2021/6/28
 */
public class DepartmentDTO {
    private String name;
    private Integer managerId;

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "name='" + name + '\'' +
                ", managerId=" + managerId +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}
