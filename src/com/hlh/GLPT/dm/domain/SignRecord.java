package com.hlh.GLPT.dm.domain;

import java.io.Serializable;

/**
 * Package: com.hlh.GLPT.dm.domain
 * User: 黄良辉  15-6-3  下午10:24
 */
public class SignRecord implements Serializable {
    private int Id;
    private String DomainNameId;
    private String SignDate;        //存入时转为date
    private String SignTime;         //存入时转为date
    private String SignPeopleType;
    private String SignCode;
    private String SignContent;
    private String Remarks;


    public SignRecord() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDomainNameId() {
        return DomainNameId;
    }

    public void setDomainNameId(String domainNameId) {
        DomainNameId = domainNameId;
    }

    public String getSignDate() {
        return SignDate;
    }

    public void setSignDate(String signDate) {
        SignDate = signDate;
    }

    public String getSignTime() {
        return SignTime;
    }

    public void setSignTime(String signOperTime) {
        SignTime = signOperTime;
    }

    public String getSignPeopleType() {
        return SignPeopleType;
    }

    public void setSignPeopleType(String signPeopleType) {
        SignPeopleType = signPeopleType;
    }

    public String getSignCode() {
        return SignCode;
    }

    public void setSignCode(String signCode) {
        SignCode = signCode;
    }

    public String getSignContent() {
        return SignContent;
    }

    public void setSignContent(String signContent) {
        SignContent = signContent;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
