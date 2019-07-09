package com.hlh.GLPT.dm.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.hlh.GLPT.dm.domain
 * User: 黄良辉  15-6-3  下午10:08
 */
public class DevelopingParty implements Serializable {
    private int Id;
    private String Code;
    private String FullName;
    private String ContactPerson;
    private String QQ;
    private String EMail;
    private String Phone;
    private String Mobile;
    private String Address;
    private String CooperationDate;     //写入时转为Date
    private String Remarks;
    private boolean Enabled;
    private int SortCode;
    private boolean DeleteMark;
    private boolean CallMark;
    private Date CreateDate;
    private String CreateUserCode;
    private String CreateUserName;
    private Date ModifyDate;
    private String ModifyUserCode;
    private String ModifyUserName;

    public DevelopingParty() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCooperationDate() {
        return CooperationDate;
    }

    public void setCooperationDate(String cooperationDate) {
        CooperationDate = cooperationDate;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
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

    public boolean isDeleteMark() {
        return DeleteMark;
    }

    public void setDeleteMark(boolean deleteMark) {
        DeleteMark = deleteMark;
    }

    public boolean isCallMark() {
        return CallMark;
    }

    public void setCallMark(boolean callMark) {
        CallMark = callMark;
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
}
