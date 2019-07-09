package com.hlh.GLPT.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-4-7
 * Time: 下午9:19
 */
public class UserRole implements Serializable {
    private int UserRoleId;
    private String UserCode;
    private String RoleCode;
    private Date CreateDate;
    private String CreateUserCode;
    private String CreateUserName;

    public UserRole() {
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public String getCreateUserCode() {
        return CreateUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        CreateUserCode = createUserCode;
    }

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String createUserName) {
        CreateUserName = createUserName;
    }

    public String getRoleCode() {
        return RoleCode;
    }

    public void setRoleCode(String roleCode) {
        RoleCode = roleCode;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public int getUserRoleId() {
        return UserRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        UserRoleId = userRoleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "CreateDate=" + CreateDate +
                ", UserRoleId=" + UserRoleId +
                ", UserCode='" + UserCode + '\'' +
                ", RoleCode='" + RoleCode + '\'' +
                ", CreateUserCode='" + CreateUserCode + '\'' +
                ", CreateUserName='" + CreateUserName + '\'' +
                '}';
    }
}
