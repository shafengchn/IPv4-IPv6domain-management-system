package com.hlh.GLPT.base.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-5-25
 * Time: 下午5:53
 */
public class StudentBorrower implements Serializable {
    private String StudentBorrowerId;
    private String UserCode;
    private String Code;
    private String FullName;
    private String Alias;
    private String Gender;
    private String FindPassword;
    private String ChangePasswordDate;
    private String IDCard;
    private String Email;
    private String Mobile;
    private String Telephone;
    private String OICQ;
    private String Age;
    private String Birthday;
    private String HomeAddress;
    private String HomePhone;
    private String PhotoImg;
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


    public StudentBorrower() {
    }

    public String getPhotoImg() {
        return PhotoImg;
    }

    public void setPhotoImg(String photoImg) {
        PhotoImg = photoImg;
    }

    public String getChangePasswordDate() {
        return ChangePasswordDate;
    }

    public void setChangePasswordDate(String changePasswordDate) {
        ChangePasswordDate = changePasswordDate;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }


    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        HomeAddress = homeAddress;
    }

    public String getHomePhone() {
        return HomePhone;
    }

    public void setHomePhone(String homePhone) {
        HomePhone = homePhone;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
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

    public String getOICQ() {
        return OICQ;
    }

    public void setOICQ(String OICQ) {
        this.OICQ = OICQ;
    }

    public int getSortCode() {
        return SortCode;
    }

    public void setSortCode(int sortCode) {
        SortCode = sortCode;
    }

    public String getStudentBorrowerId() {
        return StudentBorrowerId;
    }

    public void setStudentBorrowerId(String borrowerId) {
        StudentBorrowerId = borrowerId;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public String getFindPassword() {
        return FindPassword;
    }

    public void setFindPassword(String findPassword) {
        FindPassword = findPassword;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "Age='" + Age + '\'' +
                ", StudentBorrowerId='" + StudentBorrowerId + '\'' +
                ", UserCode='" + UserCode + '\'' +
                ", Code='" + Code + '\'' +
                ", FullName='" + FullName + '\'' +
                ", Alias='" + Alias + '\'' +
                ", Gender='" + Gender + '\'' +
                ", FindPassword='" + FindPassword + '\'' +
                ", ChangePasswordDate='" + ChangePasswordDate + '\'' +
                ", IDCard='" + IDCard + '\'' +
                ", Email='" + Email + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", OICQ='" + OICQ + '\'' +
                ", Birthday='" + Birthday + '\'' +
                ", HomeAddress='" + HomeAddress + '\'' +
                ", HomePhone='" + HomePhone + '\'' +
                ", PhotoImg='" + PhotoImg + '\'' +
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
