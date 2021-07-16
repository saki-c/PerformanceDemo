package com.example.demo.vo;

import com.example.demo.entity.Position;

import java.util.List;

/**
 * @author 咲蛍
 * @date 2021/6/30
 */
public class PositionVO {
    private Integer departmentId;
    private String departmentName;
    private List<Position> positionList;

    @Override
    public String toString() {
        return "PositionVO{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", positionList=" + positionList +
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

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
}
