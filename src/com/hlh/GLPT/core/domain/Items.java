package com.hlh.GLPT.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-3-14
 * Time: 下午7:19
 */
public class Items implements Serializable {
    private String ItemsId;
    private String ParentId;
    private String Code;
    private String FullName;
    private String Category;
    private boolean Enabled;
    private int SortCode;
    private boolean DeleteMark;
    private Date CreateDate;
    private String CreateUserCode;
    private String CreateUserName;
    private Date ModifyDate;
    private String ModifyUserCode;
    private String ModifyUserName;

    public Items() {
    }

    public String getItemsId() {
        return ItemsId;
    }

    public void setItemsId(String itemsId) {
        ItemsId = itemsId;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public boolean isDeleteMark() {
        return DeleteMark;
    }

    public void setDeleteMark(boolean deleteMark) {
        DeleteMark = deleteMark;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean enabled) {
        Enabled = enabled;
    }
    public int getSortCode() {
        return SortCode;
    }

    public void setSortCode(int sortCode) {
        SortCode = sortCode;
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

    @Override
    public String toString() {
        return "{" +
                "ItemsId='" + ItemsId + '\'' +
                ", ParentId='" + ParentId + '\'' +
                ", Code='" + Code + '\'' +
                ", FullName='" + FullName + '\'' +
                ", Category='" + Category + '\'' +
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
