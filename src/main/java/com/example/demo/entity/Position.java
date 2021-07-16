package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 咲蛍
 * @date 2021/6/26
 */
@TableName(value = "tb_position")
public class Position {
    @TableId(type = IdType.AUTO)
    private Integer positionId;
    private String positionName;
    private Integer positionDepartmentId;
    private String positionStatus;

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", positionDepartmentId=" + positionDepartmentId +
                ", positionStatus='" + positionStatus + '\'' +
                '}';
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getPositionDepartmentId() {
        return positionDepartmentId;
    }

    public void setPositionDepartmentId(Integer positionDepartmentId) {
        this.positionDepartmentId = positionDepartmentId;
    }

    public String getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(String positionStatus) {
        this.positionStatus = positionStatus;
    }
}
