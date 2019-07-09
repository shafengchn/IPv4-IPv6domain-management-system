package com.hlh.GLPT.base.domain;

/**
 * Package: com.hlh.GLPT.base.domain
 * User: 黄良辉  16-4-24  上午10:37
 */
public class Teacher {
    private int Id;
    private String Code;
    private String FullName;
    private String Mobile;
    private String Phone;
    private String Email;
    private String IdTypeCode;
    private String IdNumber;
    private String IdImgPath1;
    private String IdImgPath2;
    private String IdImgPath3;

    public Teacher() {
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

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIdTypeCode() {
        return IdTypeCode;
    }

    public void setIdTypeCode(String idTypeCode) {
        IdTypeCode = idTypeCode;
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
}
