package com.example.demo.dto;
/**
 *
 * @author 涼月
 * @date 2021/05/29
 */
public class DownloadDTO {
    private Integer performanceId;
    private String userName;
    private String performanceTerm;

    public Integer getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Integer performanceId) {
        this.performanceId = performanceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPerformanceTerm() {
        return performanceTerm;
    }

    public void setPerformanceTerm(String performanceTerm) {
        this.performanceTerm = performanceTerm;
    }

    @Override
    public String toString() {
        return "DownloadDTO{" +
                "performanceId=" + performanceId +
                ", userName='" + userName + '\'' +
                ", performanceTerm='" + performanceTerm + '\'' +
                '}';
    }
}
