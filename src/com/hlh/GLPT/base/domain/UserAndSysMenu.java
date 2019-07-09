package com.hlh.GLPT.base.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-6-9
 * Time: 上午11:41
 */
public class UserAndSysMenu implements Serializable {
    private int ID;
    private String UserCode;
    private String SysMenuCode;
    private boolean Enabled;
    private int SortCode;
    private boolean DeleteMark;
    private Date CreateDate;
    private String CreateUserCode;
    private String CreateUserName;
    private Date ModifyDate;
    private String ModifyUserCode;
    private String ModifyUserName;

    public UserAndSysMenu() {
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

    public boolean isDeleteMark() {
        return DeleteMark;
    }

    public void setDeleteMark(boolean deleteMark) {
        DeleteMark = deleteMark;
    }

    public String getSysMenuCode() {
        return SysMenuCode;
    }

    public void setSysMenuCode(String classCode) {
        SysMenuCode = classCode;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean enabled) {
        Enabled = enabled;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getModifyDate() {
        return ModifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        ModifyDate = modifyDate;
    }

    public String getModifyUserCode() {
        return ModifyUserCode;
    }

    public void setModifyUserCode(String modifyUserCode) {
        ModifyUserCode = modifyUserCode;
    }

    public String getModifyUserName() {
        return ModifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        ModifyUserName = modifyUserName;
    }

    public int getSortCode() {
        return SortCode;
    }

    public void setSortCode(int sortCode) {
        SortCode = sortCode;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String teacherCode) {
        UserCode = teacherCode;
    }

    @Override
    public String toString() {
        return "TeacherAndClass{" +
                "CreateDate=" + CreateDate +
                ", ID=" + ID +
                ", UserCode='" + UserCode + '\'' +
                ", SysMenuCode='" + SysMenuCode + '\'' +
                ", Enabled=" + Enabled +
                ", SortCode=" + SortCode +
                ", DeleteMark=" + DeleteMark +
                ", CreateUserCode='" + CreateUserCode + '\'' +
                ", CreateUserName='" + CreateUserName + '\'' +
                ", ModifyDate=" + ModifyDate +
                ", ModifyUserCode='" + ModifyUserCode + '\'' +
                ", ModifyUserName='" + ModifyUserName + '\'' +
                '}';
    }
}
