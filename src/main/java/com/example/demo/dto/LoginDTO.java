package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author 咲蛍
 * @date 2021/05/17
 */
public class LoginDTO {
    @NotEmpty(message = "账号不能为空")
    private String nickname;

    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
