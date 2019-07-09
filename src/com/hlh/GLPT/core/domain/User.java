package com.hlh.GLPT.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-3-11
 * Time: 下午9:27
 */
public class User implements Serializable {
    private String UserId;
    private String Code;
    private String Account;
    private String Password;
    private String RealName;
    private String Spell;
    private String Alias;
    private String Gender;
    private String Mobile;
    private String Telephone;
    private String Birthday;
    private String Email;
    private String OICQ;
    private String DutyCode;
    private String TitleCode;
    private String DepartmentCode;
    private String Description;
    private String IdType;
    private String IdNumber;
    private String IdImgPath1;
    private String IdImgPath2;
    private String IdImgPath3;
    private String ChangePasswordDate;
    private String IPAddress;
    private String MACAddress;
    private int LogOnCount;
    private Date FirstVisit;
    private Date PreviousVisit;
    private Date LastVisit;
    private boolean Enabled;
    private int SortCode;
    private boolean DeleteMark;
    private Date CreateDate;
    private String CreateUserCode;
    private String CreateUserName;
    private Date ModifyDate;
    private String ModifyUserCode;
    private String ModifyUserName;

    public User() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getSpell() {
        return Spell;
    }

    public void setSpell(String spell) {
        Spell = spell;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOICQ() {
        return OICQ;
    }

    public void setOICQ(String OICQ) {
        this.OICQ = OICQ;
    }

    public String getDepartmentCode() {
        return DepartmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        DepartmentCode = departmentCode;
    }

    public String getDutyCode() {
        return DutyCode;
    }

    public void setDutyCode(String dutyCode) {
        DutyCode = dutyCode;
    }

    public String getTitleCode() {
        return TitleCode;
    }

    public void setTitleCode(String titleCode) {
        TitleCode = titleCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getIdType() {
        return IdType;
    }

    public void setIdType(String idType) {
        IdType = idType;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getIdImgPath1() {
        return IdImgPath1;
    }

    public void setIdImgPath1(String idImgPath1) {
        IdImgPath1 = idImgPath1;
    }

    public String getIdImgPath2() {
        return IdImgPath2;
    }

    public void setIdImgPath2(String idImgPath2) {
        IdImgPath2 = idImgPath2;
    }

    public String getIdImgPath3() {
        return IdImgPath3;
    }

    public void setIdImgPath3(String idImgPath3) {
        IdImgPath3 = idImgPath3;
    }

    public String getChangePasswordDate() {
        return ChangePasswordDate;
    }

    public void setChangePasswordDate(String changePasswordDate) {
        ChangePasswordDate = changePasswordDate;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getMACAddress() {
        return MACAddress;
    }

    public void setMACAddress(String MACAddress) {
        this.MACAddress = MACAddress;
    }

    public int getLogOnCount() {
        return LogOnCount;
    }

    public void setLogOnCount(int logOnCount) {
        LogOnCount = logOnCount;
    }

    public Date getFirstVisit() {
        return FirstVisit;
    }

    public void setFirstVisit(Date firstVisit) {
        FirstVisit = firstVisit;
    }

    public Date getPreviousVisit() {
        return PreviousVisit;
    }

    public void setPreviousVisit(Date previousVisit) {
        PreviousVisit = previousVisit;
    }

    public Date getLastVisit() {
        return LastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        LastVisit = lastVisit;
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
        return "User{" +
                "Account='" + Account + '\'' +
                ", UserId='" + UserId + '\'' +
                ", Code='" + Code + '\'' +
                ", Password='" + Password + '\'' +
                ", RealName='" + RealName + '\'' +
                ", Spell='" + Spell + '\'' +
                ", Alias='" + Alias + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", Birthday='" + Birthday + '\'' +
                ", Email='" + Email + '\'' +
                ", OICQ='" + OICQ + '\'' +
                ", DutyCode='" + DutyCode + '\'' +
                ", TitleCode='" + TitleCode + '\'' +
                ", DepartmentCode='" + DepartmentCode + '\'' +
                ", Description='" + Description + '\'' +
                ", ChangePasswordDate='" + ChangePasswordDate + '\'' +
                ", IPAddress='" + IPAddress + '\'' +
                ", MACAddress='" + MACAddress + '\'' +
                ", LogOnCount=" + LogOnCount +
                ", FirstVisit=" + FirstVisit +
                ", PreviousVisit=" + PreviousVisit +
                ", LastVisit=" + LastVisit +
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
