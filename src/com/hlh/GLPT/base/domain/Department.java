package com.hlh.GLPT.base.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-6-8
 * Time: 上午10:38
 */
public class Department implements Serializable {
    private String DepartmentId;
    private String Code;
    private String FullName;
    private String BMLXCode;
    private String Description;
    private boolean Enabled;
    private int SortCode;
    private boolean DeleteMark;
    private Date CreateDate;
    private String CreateUserCode;
    private String CreateUserName;
    private Date ModifyDate;
    private String ModifyUserCode;
    private String ModifyUserName;

    public Department() {
    }

    public String getBMLXCode() {
        return BMLXCode;
    }

    public void setBMLXCode(String BMLXCode) {
        this.BMLXCode = BMLXCode;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
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

    public String getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(String departmentId) {
        DepartmentId = departmentId;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean enabled) {
        Enabled = enabled;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Department{" +
                "BMLXCode='" + BMLXCode + '\'' +
                ", DepartmentId='" + DepartmentId + '\'' +
                ", Code='" + Code + '\'' +
                ", FullName='" + FullName + '\'' +
                ", Description='" + Description + '\'' +
                ", Enabled=" + Enabled +
                ", SortCode=" + SortCode +
                ", DeleteMark=" + DeleteMark +
                ", CreateDate=" + CreateDate +
                ", CreateUserCode='" + CreateUserCode + '\'' +
                ", CreateUserName='" + CreateUserName + '\'' +
                ", ModifyDate=" + ModifyDate +
                ", ModifyUserCode='" + ModifyUserCode + '\'' +
                ", ModifyUserName='" + ModifyUserName + '\'' +
                '}';
    }
}
