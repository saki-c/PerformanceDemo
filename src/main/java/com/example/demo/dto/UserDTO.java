package com.example.demo.dto;

/**
 *
 * @author 涼月
 * @date 2021/05/27
 */
public class UserDTO {
    private String name;
    private String nickname;
    private String password;
    private Integer positionId;
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
