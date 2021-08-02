package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author 咲蛍
 * @date 2021/05/27
 */
public class UserDTO {
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    @NotEmpty(message = "密码不能为空")
    private String password;

    private Integer positionId;

    @NotEmpty(message = "入职时间不能为空")
    private String joinDate;

    private Integer directorId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", positionId=" + positionId +
                ", joinDate='" + joinDate + '\'' +
                ", directorId=" + directorId +
                '}';
    }
}
