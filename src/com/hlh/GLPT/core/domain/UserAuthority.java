package com.hlh.GLPT.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-7-21
 * Time: 下午8:28
 */
public class UserAuthority implements Serializable {
    private int UserAuthorityId;
    private String UserCode;
    private String AuthorityCode;
    private Date CreateDate;
    private String CreateUserCode;
    private String CreateUserName;

    public UserAuthority() {
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

    public int getUserAuthorityId() {
        return UserAuthorityId;
    }

    public void setUserAuthorityId(int roleAuthorityId) {
        UserAuthorityId = roleAuthorityId;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String roleCode) {
        UserCode = roleCode;
    }

    @Override
    public String toString() {
        return "UserAuthority{" +
                "AuthorityCode='" + AuthorityCode + '\'' +
                ", UserAuthorityId=" + UserAuthorityId +
                ", UserCode='" + UserCode + '\'' +
                ", CreateDate=" + CreateDate +
                ", CreateUserCode='" + CreateUserCode + '\'' +
                ", CreateUserName='" + CreateUserName + '\'' +
                '}';
    }
}
