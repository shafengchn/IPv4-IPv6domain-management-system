package com.hlh.GLPT.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-9-1
 * Time: 下午8:02
 */
public class LoginLog implements Serializable {
    private int ID;
    private Date LoginDate;
    private String LoginIP;
    private String LoginUserName;
    private String UserType;

    public LoginLog() {
    }

    public LoginLog(Date loginDate, String loginIP, String loginUserName) {
        LoginDate = loginDate;
        LoginIP = loginIP;
        LoginUserName = loginUserName;
        UserType="平台用户";
    }

    public LoginLog(Date loginDate, String loginIP, String loginUserName,String userType) {
        LoginDate = loginDate;
        LoginIP = loginIP;
        LoginUserName = loginUserName;
        UserType=userType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getLoginDate() {
        return LoginDate;
    }

    public void setLoginDate(Date loginDate) {
        LoginDate = loginDate;
    }

    public String getLoginIP() {
        return LoginIP;
    }

    public void setLoginIP(String loginIP) {
        LoginIP = loginIP;
    }

    public String getLoginUserName() {
        return LoginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        LoginUserName = loginUserName;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "ID=" + ID +
                ", LoginDate=" + LoginDate +
                ", LoginIP='" + LoginIP + '\'' +
                ", LoginUserName='" + LoginUserName + '\'' +
                '}';
    }
}
