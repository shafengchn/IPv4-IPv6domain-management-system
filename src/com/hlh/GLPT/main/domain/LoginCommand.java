package com.hlh.GLPT.main.domain;

/**
 * User1: 黄良辉
 * Date: 14-2-8
 * Time: 下午9:52
 */
public class LoginCommand {
    private String userName;  //用户名
    private String password;  //密码
    private String rand;       //验证码

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRand() {
        return rand;
    }

    public void setRand(String rand) {
        this.rand = rand;
    }
}
