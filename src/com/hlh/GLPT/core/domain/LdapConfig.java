package com.hlh.GLPT.core.domain;

import java.io.Serializable;

/**
 * Package: com.hlh.GLPT.core.domain
 * User: 黄良辉  16-1-5  上午11:15
 */
public class LdapConfig implements Serializable {
    private int ID;
    private String HostIP;
    private String PortNum;
    private String BaseDN;
    private String UserDN;
    private String LoginPassword;
    private String UserGroupName;
    private boolean  LdapUserLogin;

    public LdapConfig() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHostIP() {
        return HostIP;
    }

    public void setHostIP(String hostIP) {
        HostIP = hostIP;
    }

    public String getPortNum() {
        return PortNum;
    }

    public void setPortNum(String portNum) {
        PortNum = portNum;
    }

    public String getBaseDN() {
        return BaseDN;
    }

    public void setBaseDN(String baseDN) {
        BaseDN = baseDN;
    }

    public String getUserDN() {
        return UserDN;
    }

    public void setUserDN(String userDN) {
        UserDN = userDN;
    }

    public String getLoginPassword() {
        return LoginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        LoginPassword = loginPassword;
    }

    public String getUserGroupName() {
        return UserGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        UserGroupName = userGroupName;
    }

    public boolean isLdapUserLogin() {
        return LdapUserLogin;
    }

    public void setLdapUserLogin(boolean ldapUserLogin) {
        LdapUserLogin = ldapUserLogin;
    }
}
