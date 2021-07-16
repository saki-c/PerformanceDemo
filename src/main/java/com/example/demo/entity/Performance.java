package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author 咲蛍
 * @date 2021/05/17
 */
@TableName(value = "tb_performance")
public class Performance {
    @TableId(type = IdType.AUTO)
    private Integer performanceId;
    private Date performanceTerm;
    private Integer performanceUserId;
    private String performanceConclusion;
    private String performanceEvaluation;
    private String performanceStatus;

    public Performance() {
    }

    public Integer getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Integer performanceId) {
        this.performanceId = performanceId;
    }

    public Date getPerformanceTerm() {
        return performanceTerm;
    }

    public void setPerformanceTerm(Date performanceTerm) {
        this.performanceTerm = performanceTerm;
    }

    public Integer getPerformanceUserId() {
        return performanceUserId;
    }

    public void setPerformanceUserId(Integer performanceUserId) {
        this.performanceUserId = performanceUserId;
    }

    public String getPerformanceStatus() {
        return performanceStatus;
    }

    public void setPerformanceStatus(String performanceStatus) {
        this.performanceStatus = performanceStatus;
    }

    public String getPerformanceConclusion() {
        return performanceConclusion;
    }

    public void setPerformanceConclusion(String performanceConclusion) {
        this.performanceConclusion = performanceConclusion;
    }

    public String getPerformanceEvaluation() {
        return performanceEvaluation;
    }

    public void setPerformanceEvaluation(String performanceEvaluation) {
        this.performanceEvaluation = performanceEvaluation;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "performanceId=" + performanceId +
                ", performanceTerm=" + performanceTerm +
                ", performanceUserId=" + performanceUserId +
                ", performanceConclusion='" + performanceConclusion + '\'' +
                ", performanceEvaluation='" + performanceEvaluation + '\'' +
                ", performanceStatus='" + performanceStatus + '\'' +
                '}';
    }
}
