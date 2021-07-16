package com.example.demo.dto;

/**
 * @author 咲蛍
 * @date 2021/6/22
 */
public class ItemDTO {
    private Integer performanceId;

    private String performanceItemDemand;

    private String performanceItemStandard;

    private String performanceItemSituation;

    private String performanceItemDirectorGrade;

    private String performanceItemDepartmentManagerGrade;

    public Integer getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Integer performanceId) {
        this.performanceId = performanceId;
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

    public String getPerformanceItemDirectorGrade() {
        return performanceItemDirectorGrade;
    }

    public void setPerformanceItemDirectorGrade(String performanceItemDirectorGrade) {
        this.performanceItemDirectorGrade = performanceItemDirectorGrade;
    }

    public String getPerformanceItemDepartmentManagerGrade() {
        return performanceItemDepartmentManagerGrade;
    }

    public void setPerformanceItemDepartmentManagerGrade(String performanceItemDepartmentManagerGrade) {
        this.performanceItemDepartmentManagerGrade = performanceItemDepartmentManagerGrade;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "performanceId=" + performanceId +
                ", performanceItemDemand='" + performanceItemDemand + '\'' +
                ", performanceItemStandard='" + performanceItemStandard + '\'' +
                ", performanceItemSituation='" + performanceItemSituation + '\'' +
                ", performanceItemDirectorGrade='" + performanceItemDirectorGrade + '\'' +
                ", performanceItemDepartmentManagerGrade='" + performanceItemDepartmentManagerGrade + '\'' +
                '}';
    }
}
