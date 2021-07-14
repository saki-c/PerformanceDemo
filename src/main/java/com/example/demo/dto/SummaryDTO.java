package com.example.demo.dto;

/**
 * @author 涼月
 * @date 2021/7/5
 */
public class SummaryDTO {
    private Integer performanceId;
    private String performanceConclusion;
    private String performanceEvaluation;

    @Override
    public String toString() {
        return "SummaryDTO{" +
                "performanceId=" + performanceId +
                ", performanceConclusion='" + performanceConclusion + '\'' +
                ", performanceEvaluation='" + performanceEvaluation + '\'' +
                '}';
    }

    public Integer getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Integer performanceId) {
        this.performanceId = performanceId;
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
}
