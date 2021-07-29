package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author 咲蛍
 * @date 2021/6/21
 */
public class PassDTO {
    @NotEmpty(message = "原密码不能为空")
    private String oldPassword;

    @NotEmpty(message = "新密码不能为空")
    private String newPassword;

    @NotEmpty(message = "重复密码不能为空")
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
