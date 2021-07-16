package com.example.demo.vo;

import java.util.Date;

/**
 * @author 咲蛍
 * @date 2021/7/1
 */
public class PerformanceVO {
    private Integer performanceId;
    private Integer performanceUserId;
    private Date performanceTerm;
    private String userName;
    private String performanceStatus;

    @Override
    public String toString() {
        return "PerformanceVO{" +
                "performanceId=" + performanceId +
                ", performanceUserId=" + performanceUserId +
                ", performanceTerm=" + performanceTerm +
                ", userName='" + userName + '\'' +
                ", performanceStatus='" + performanceStatus + '\'' +
                '}';
    }

    public Integer getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Integer performanceId) {
        this.performanceId = performanceId;
    }

    public Integer getPerformanceUserId() {
        return performanceUserId;
    }

    public void setPerformanceUserId(Integer performanceUserId) {
        this.performanceUserId = performanceUserId;
    }

    public Date getPerformanceTerm() {
        return performanceTerm;
    }

    public void setPerformanceTerm(Date performanceTerm) {
        this.performanceTerm = performanceTerm;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPerformanceStatus() {
        return performanceStatus;
    }

    public void setPerformanceStatus(String performanceStatus) {
        this.performanceStatus = performanceStatus;
    }
}
