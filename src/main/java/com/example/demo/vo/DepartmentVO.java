package com.example.demo.vo;


/**
 * @author 涼月
 * @date 2021/6/28
 */
public class DepartmentVO {
    private Integer departmentId;
    private Integer userId;
    private String departmentName;
    private String departmentStatus;
    private String userName;
    private String userStatus;

    @Override
    public String toString() {
        return "DepartmentVO{" +
                "departmentId=" + departmentId +
                ", userId=" + userId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentStatus='" + departmentStatus + '\'' +
                ", userName='" + userName + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(String departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
