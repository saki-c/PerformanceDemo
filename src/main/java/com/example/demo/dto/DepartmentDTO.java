package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author 咲蛍
 * @date 2021/6/28
 */
public class DepartmentDTO {
    @NotEmpty(message = "部门名称不能为空")
    private String name;

    @NotNull(message = "部门主管不能为空")
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
