package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author 咲蛍
 * @date 2021/6/29
 */
public class PositionDTO {
    @NotEmpty(message = "岗位名称不能为空")
    private String name;

    @NotEmpty(message = "所属部门不能为空")
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
