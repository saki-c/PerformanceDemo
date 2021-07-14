package com.example.demo.dto;
/**
 *
 * @author 涼月
 * @date 2021/05/17
 */
public class LoginDTO {
    private String nickname;
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
