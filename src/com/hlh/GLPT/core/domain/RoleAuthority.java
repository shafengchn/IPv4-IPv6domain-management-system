package com.hlh.GLPT.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-7-21
 * Time: 下午8:28
 */
public class RoleAuthority implements Serializable {
    private int RoleAuthorityId;
    private String RoleCode;
    private String AuthorityCode;
    private Date CreateDate;
    private String CreateUserCode;
    private String CreateUserName;

    public RoleAuthority() {
    }

    public String getAuthorityCode() {
        return AuthorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        AuthorityCode = authorityCode;
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

    public int getRoleAuthorityId() {
        return RoleAuthorityId;
    }

    public void setRoleAuthorityId(int roleAuthorityId) {
        RoleAuthorityId = roleAuthorityId;
    }

    public String getRoleCode() {
        return RoleCode;
    }

    public void setRoleCode(String roleCode) {
        RoleCode = roleCode;
    }

    @Override
    public String toString() {
        return "RoleAuthority{" +
                "AuthorityCode='" + AuthorityCode + '\'' +
                ", RoleAuthorityId=" + RoleAuthorityId +
                ", RoleCode='" + RoleCode + '\'' +
                ", CreateDate=" + CreateDate +
                ", CreateUserCode='" + CreateUserCode + '\'' +
                ", CreateUserName='" + CreateUserName + '\'' +
                '}';
    }
}
