package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *
 * @author 涼月
 * @date 2021/05/17
 */
@TableName(value = "tb_performance_item")
public class PerformanceItem {
    @TableId(type = IdType.AUTO)
    private Integer performanceItemId;
    private Integer performanceItemWeight;
    private String performanceItemDemand;
    private String performanceItemStandard;
    private String performanceItemSituation;
    private Integer performanceItemDirectorGrade;
    private Integer performanceItemDepartmentManagerGrade;
    private Integer performanceItemPerformanceId;

    @Override
    public String toString() {
        return "PerformanceItem{" +
                "performanceItemId=" + performanceItemId +
                ", performanceItemWeight=" + performanceItemWeight +
                ", performanceItemDemand='" + performanceItemDemand + '\'' +
                ", performanceItemStandard='" + performanceItemStandard + '\'' +
                ", performanceItemSituation='" + performanceItemSituation + '\'' +
                ", performanceItemDirectorGrade=" + performanceItemDirectorGrade +
                ", performanceItemDepartmentManagerGrade=" + performanceItemDepartmentManagerGrade +
                ", performanceItemPerformanceId=" + performanceItemPerformanceId +
                '}';
    }

    public Integer getPerformanceItemId() {
        return performanceItemId;
    }

    public void setPerformanceItemId(Integer performanceItemId) {
        this.performanceItemId = performanceItemId;
    }

    public Integer getPerformanceItemWeight() {
        return performanceItemWeight;
    }

    public void setPerformanceItemWeight(Integer performanceItemWeight) {
        this.performanceItemWeight = performanceItemWeight;
    }

    public String getPerformanceItemDemand() {
        return performanceItemDemand;
    }

    public void setPerformanceItemDemand(String performanceItemDemand) {
        this.performanceItemDemand = performanceItemDemand;
    }

    public String getPerformanceItemStandard() {
        return performanceItemStandard;
    }

    public void setPerformanceItemStandard(String performanceItemStandard) {
        this.performanceItemStandard = performanceItemStandard;
    }

    public String getPerformanceItemSituation() {
        return performanceItemSituation;
    }

    public void setPerformanceItemSituation(String performanceItemSituation) {
        this.performanceItemSituation = performanceItemSituation;
    }

    public Integer getPerformanceItemDirectorGrade() {
        return performanceItemDirectorGrade;
    }

    public void setPerformanceItemDirectorGrade(Integer performanceItemDirectorGrade) {
        this.performanceItemDirectorGrade = performanceItemDirectorGrade;
    }

    public Integer getPerformanceItemDepartmentManagerGrade() {
        return performanceItemDepartmentManagerGrade;
    }

    public void setPerformanceItemDepartmentManagerGrade(Integer performanceItemDepartmentManagerGrade) {
        this.performanceItemDepartmentManagerGrade = performanceItemDepartmentManagerGrade;
    }

    public Integer getPerformanceItemPerformanceId() {
        return performanceItemPerformanceId;
    }

    public void setPerformanceItemPerformanceId(Integer performanceItemPerformanceId) {
        this.performanceItemPerformanceId = performanceItemPerformanceId;
    }
}
