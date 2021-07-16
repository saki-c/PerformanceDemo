package com.example.demo.dto;

/**
 * @author 咲蛍
 * @date 2021/6/21
 */
public class PassDTO {
    private String oldPassword;
    private String newPassword;
    private String againPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAgainPassword() {
        return againPassword;
    }

    public void setAgainPassword(String againPassword) {
        this.againPassword = againPassword;
    }

    @Override
    public String toString() {
        return "PassDTO{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", againPassword='" + againPassword + '\'' +
                '}';
    }
}
