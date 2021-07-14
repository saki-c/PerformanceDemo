package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 涼月
 * @date 2021/6/26
 */
@TableName(value = "tb_department")
public class Department {
    @TableId(type = IdType.AUTO)
    private Integer departmentId;
    private String departmentName;
    private Integer departmentManagerId;
    private String departmentStatus;

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentManagerId=" + departmentManagerId +
                ", departmentStatus='" + departmentStatus + '\'' +
                '}';
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDepartmentManagerId() {
        return departmentManagerId;
    }

    public void setDepartmentManagerId(Integer departmentManagerId) {
        this.departmentManagerId = departmentManagerId;
    }

    public String getDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(String departmentStatus) {
        this.departmentStatus = departmentStatus;
    }
}
