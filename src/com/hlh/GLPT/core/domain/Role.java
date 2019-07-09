package com.hlh.GLPT.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-3-19
 * Time: 上午11:38
 */
public class Role implements Serializable {
    private String RoleId;
    private String Code;
    private String FullName;
    private String Category;
    private String Description;
    private boolean AllowEdit;
    private boolean AllowDelete;
    private boolean Enabled;
    private int SortCode;
    private boolean DeleteMark;
    private Date CreateDate;
    private String CreateUserCode;
    private String CreateUserName;
    private Date ModifyDate;
    private String ModifyUserCode;
    private String ModifyUserName;

    public Role() {
    }

    public boolean isAllowDelete() {
        return AllowDelete;
    }

    public void setAllowDelete(boolean allowDelete) {
        AllowDelete = allowDelete;
    }

    public boolean isAllowEdit() {
        return AllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        AllowEdit = allowEdit;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public int getSortCode() {
        return SortCode;
    }

    public void setSortCode(int sortCode) {
        SortCode = sortCode;
    }

    @Override
    public String toString() {
        return "Role{" +
                "AllowDelete=" + AllowDelete +
                ", RoleId='" + RoleId + '\'' +
                ", Code='" + Code + '\'' +
                ", FullName='" + FullName + '\'' +
                ", Category='" + Category + '\'' +
                ", Description='" + Description + '\'' +
                ", AllowEdit=" + AllowEdit +
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
