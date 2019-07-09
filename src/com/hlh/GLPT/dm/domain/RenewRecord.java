package com.hlh.GLPT.dm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.hlh.GLPT.dm.domain
 * User: 黄良辉  15-6-3  下午10:19
 */
public class RenewRecord implements Serializable {
    private int Id;
    private String DomainNameId;
    private String RenewDate;     //存入转为date
    private String RenewTime;    //存入转为date
    private String RenewPeopleType;
    private String RenewCode;
    private String RenewContent;
    private String Remarks;
    private String ApprovalCode;
    private String ApprovalTime;  //存入转为date
    private String ApprovalContent;
    private boolean ApprovalPass;
    private boolean DeleteMark;

    public RenewRecord() {
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

    public String getRenewDate() {
        return RenewDate;
    }

    public void setRenewDate(String renewOperDate) {
        RenewDate = renewOperDate;
    }

    public String getRenewTime() {
        return RenewTime;
    }

    public void setRenewTime(String renewOperTime) {
        RenewTime = renewOperTime;
    }

    public String getRenewPeopleType() {
        return RenewPeopleType;
    }

    public void setRenewPeopleType(String renewPeopleType) {
        RenewPeopleType = renewPeopleType;
    }

    public String getRenewCode() {
        return RenewCode;
    }

    public void setRenewCode(String renewCode) {
        RenewCode = renewCode;
    }

    public String getRenewContent() {
        return RenewContent;
    }

    public void setRenewContent(String renewContent) {
        RenewContent = renewContent;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getApprovalCode() {
        return ApprovalCode;
    }

    public void setApprovalCode(String approvalCode) {
        ApprovalCode = approvalCode;
    }

    public String getApprovalTime() {
        return ApprovalTime;
    }

    public void setApprovalTime(String approvalTime) {
        ApprovalTime = approvalTime;
    }

    public String getApprovalContent() {
        return ApprovalContent;
    }

    public void setApprovalContent(String approvalContent) {
        ApprovalContent = approvalContent;
    }

    public boolean isApprovalPass() {
        return ApprovalPass;
    }

    public void setApprovalPass(boolean approvalPass) {
        ApprovalPass = approvalPass;
    }

    public boolean isDeleteMark() {
        return DeleteMark;
    }

    public void setDeleteMark(boolean deleteMark) {
        DeleteMark = deleteMark;
    }
}
