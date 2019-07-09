package com.hlh.GLPT.dm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.hlh.GLPT.dm.domain
 * User: 黄良辉  15-6-3  下午10:16
 */
public class DomainName implements Serializable {
    private String Id;
    private String SysFLCode;
    private String SysFLChildCode;
    private boolean NoDomain;
    private String DomainName;
    private String url;
    private String IPv4Address;
    private String IPv6Address;
    private int TTL;                //TTL
    private String RecordType;     //记录类型
    private String RecordVal;      //记录值
    private String WebSiteFullName;
    private String OpenDate; //开通日期    存入数据库时要转为date
    private String BusinessTypeCode;
    private String KFFCode;
    private String GLDepartmentCode;
    private String GLYCode;
    private String GLYName;
    private String GLYMobile;
    private String GLYPhone;
    private String GLYEmail;
    private String GLYIdType;
    private String GLYIdNumber;
    private String GLYIdImgPath1;   //管理员身份证正面图片路径
    private String GLYIdImgPath2;   //管理员身份证反面图片路径
    private String GLYIdImgPath3;   //管理员身份证手持图片路径
    private Date GLYClaimTime;
    private boolean GLYClaimState;
    private String ECPCode;
    private String ECPName;
    private String ECPMobile;
    private String ECPPhone;
    private String ECPEmail;
    private String ECPIdType;
    private String ECPIdNumber;
    private String ECPIdImgPath1;
    private String ECPIdImgPath2;
    private String ECPIdImgPath3;
    private String FZRCode;
    private String FZRName;
    private String FZRMobile;
    private String FZRPhone;
    private String FZREmail;
    private String FZRIdType;
    private String FZRIdNumber;
    private String FZRIdImgPath1;   //负责人身份证正面图片路径
    private String FZRIdImgPath2;   //负责人身份证反面图片路径
    private String FZRIdImgPath3;   //负责人身份证手持图片路径
    private Date FZRClaimTime;
    private boolean FZRClaimState;
    private String ApprovalCode;
    private String ApprovalName;
    private String ApprovalMobile;
    private String ApprovalPhone;
    private String ApprovalEmail;
    private String ApprovalIdType;
    private String ApprovalIdNumber;
    private String ApprovalIdImgPath1;   //负责人身份证正面图片路径
    private String ApprovalIdImgPath2;   //负责人身份证反面图片路径
    private String ApprovalIdImgPath3;   //负责人身份证手持图片路径
    private Date ApprovalClaimTime;
    private boolean ApprovalClaimState;
    private int SignInInterval;
    private int RenewInterval;
    private String LastSignTime; //最后签到时间
    private String LastRenewTime; //最后续订时间
    private String Remarks;      //域名备注
    private String SysRemarks; //网站、系统备注
    private boolean Disable;
    private String DisableUserCode;
    private String DisableTime;     //存入数据库时转为date
    private String DisableContent;
    private int SortCode;
    private boolean DeleteMark;
    private boolean CallMark;
    private Date CreateDate;
    private String CreateUserCode;
    private String CreateUserName;
    private Date ModifyDate;
    private String ModifyUserCode;
    private String ModifyUserName;

    public DomainName() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSysFLCode() {
        return SysFLCode;
    }

    public void setSysFLCode(String sysFLCode) {
        SysFLCode = sysFLCode;
    }

    public String getSysFLChildCode() {
        return SysFLChildCode;
    }

    public void setSysFLChildCode(String sysFLChildCode) {
        SysFLChildCode = sysFLChildCode;
    }

    public boolean isNoDomain() {
        return NoDomain;
    }

    public void setNoDomain(boolean noDomain) {
        NoDomain = noDomain;
    }

    public String getDomainName() {
        return DomainName;
    }

    public void setDomainName(String domainName) {
        DomainName = domainName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIPv4Address() {
        return IPv4Address;
    }

    public void setIPv4Address(String IPv4Address) {
        this.IPv4Address = IPv4Address;
    }

    public String getIPv6Address() {
        return IPv6Address;
    }

    public void setIPv6Address(String IPv6Address) {
        this.IPv6Address = IPv6Address;
    }

    public int getTTL() {
        return TTL;
    }

    public void setTTL(int TTL) {
        this.TTL = TTL;
    }

    public String getRecordType() {
        return RecordType;
    }

    public void setRecordType(String recordType) {
        RecordType = recordType;
    }

    public String getRecordVal() {
        return RecordVal;
    }

    public void setRecordVal(String recordVal) {
        RecordVal = recordVal;
    }

    public String getWebSiteFullName() {
        return WebSiteFullName;
    }

    public void setWebSiteFullName(String webSiteFullName) {
        WebSiteFullName = webSiteFullName;
    }

    public String getOpenDate() {
        return OpenDate;
    }

    public void setOpenDate(String openDate) {
        OpenDate = openDate;
    }

    public String getBusinessTypeCode() {
        return BusinessTypeCode;
    }

    public void setBusinessTypeCode(String businessTypeCode) {
        BusinessTypeCode = businessTypeCode;
    }

    public String getKFFCode() {
        return KFFCode;
    }

    public void setKFFCode(String KFFCode) {
        this.KFFCode = KFFCode;
    }

    public String getGLDepartmentCode() {
        return GLDepartmentCode;
    }

    public void setGLDepartmentCode(String GLDepartmentCode) {
        this.GLDepartmentCode = GLDepartmentCode;
    }

    public String getGLYCode() {
        return GLYCode;
    }

    public void setGLYCode(String GLYCode) {
        this.GLYCode = GLYCode;
    }

    public String getGLYName() {
        return GLYName;
    }

    public void setGLYName(String GLYName) {
        this.GLYName = GLYName;
    }

    public String getGLYMobile() {
        return GLYMobile;
    }

    public void setGLYMobile(String GLYMobile) {
        this.GLYMobile = GLYMobile;
    }

    public String getGLYPhone() {
        return GLYPhone;
    }

    public void setGLYPhone(String GLYPhone) {
        this.GLYPhone = GLYPhone;
    }

    public String getGLYEmail() {
        return GLYEmail;
    }

    public void setGLYEmail(String GLYEmail) {
        this.GLYEmail = GLYEmail;
    }

    public String getGLYIdImgPath1() {
        return GLYIdImgPath1;
    }

    public void setGLYIdImgPath1(String GLYIdImgPath1) {
        this.GLYIdImgPath1 = GLYIdImgPath1;
    }

    public String getGLYIdImgPath2() {
        return GLYIdImgPath2;
    }

    public void setGLYIdImgPath2(String GLYIdImgPath2) {
        this.GLYIdImgPath2 = GLYIdImgPath2;
    }

    public String getGLYIdImgPath3() {
        return GLYIdImgPath3;
    }

    public void setGLYIdImgPath3(String GLYIdImgPath3) {
        this.GLYIdImgPath3 = GLYIdImgPath3;
    }

    public Date getGLYClaimTime() {
        return GLYClaimTime;
    }

    public void setGLYClaimTime(Date GLYClaimTime) {
        this.GLYClaimTime = GLYClaimTime;
    }

    public boolean isGLYClaimState() {
        return GLYClaimState;
    }

    public void setGLYClaimState(boolean GLYClaimState) {
        this.GLYClaimState = GLYClaimState;
    }

    public String getECPCode() {
        return ECPCode;
    }

    public void setECPCode(String ECPCode) {
        this.ECPCode = ECPCode;
    }

    public String getECPName() {
        return ECPName;
    }

    public void setECPName(String ECPName) {
        this.ECPName = ECPName;
    }

    public String getECPMobile() {
        return ECPMobile;
    }

    public void setECPMobile(String ECPMobile) {
        this.ECPMobile = ECPMobile;
    }

    public String getECPPhone() {
        return ECPPhone;
    }

    public void setECPPhone(String ECPPhone) {
        this.ECPPhone = ECPPhone;
    }

    public String getECPEmail() {
        return ECPEmail;
    }

    public void setECPEmail(String ECPEmail) {
        this.ECPEmail = ECPEmail;
    }

    public String getECPIdType() {
        return ECPIdType;
    }

    public void setECPIdType(String ECPIdType) {
        this.ECPIdType = ECPIdType;
    }

    public String getECPIdNumber() {
        return ECPIdNumber;
    }

    public void setECPIdNumber(String ECPIdNumber) {
        this.ECPIdNumber = ECPIdNumber;
    }

    public String getECPIdImgPath1() {
        return ECPIdImgPath1;
    }

    public void setECPIdImgPath1(String ECPIdImgPath1) {
        this.ECPIdImgPath1 = ECPIdImgPath1;
    }

    public String getECPIdImgPath2() {
        return ECPIdImgPath2;
    }

    public void setECPIdImgPath2(String ECPIdImgPath2) {
        this.ECPIdImgPath2 = ECPIdImgPath2;
    }

    public String getECPIdImgPath3() {
        return ECPIdImgPath3;
    }

    public void setECPIdImgPath3(String ECPIdImgPath3) {
        this.ECPIdImgPath3 = ECPIdImgPath3;
    }

    public String getFZRCode() {
        return FZRCode;
    }

    public void setFZRCode(String FZRCode) {
        this.FZRCode = FZRCode;
    }

    public String getFZRName() {
        return FZRName;
    }

    public void setFZRName(String FZRName) {
        this.FZRName = FZRName;
    }

    public String getFZRMobile() {
        return FZRMobile;
    }

    public void setFZRMobile(String FZRMobile) {
        this.FZRMobile = FZRMobile;
    }

    public String getFZRPhone() {
        return FZRPhone;
    }

    public void setFZRPhone(String FZRPhone) {
        this.FZRPhone = FZRPhone;
    }

    public String getFZREmail() {
        return FZREmail;
    }

    public void setFZREmail(String FZREmail) {
        this.FZREmail = FZREmail;
    }

    public String getFZRIdImgPath1() {
        return FZRIdImgPath1;
    }

    public void setFZRIdImgPath1(String FZRIdImgPath1) {
        this.FZRIdImgPath1 = FZRIdImgPath1;
    }

    public String getFZRIdImgPath2() {
        return FZRIdImgPath2;
    }

    public void setFZRIdImgPath2(String FZRIdImgPath2) {
        this.FZRIdImgPath2 = FZRIdImgPath2;
    }

    public String getFZRIdImgPath3() {
        return FZRIdImgPath3;
    }

    public void setFZRIdImgPath3(String FZRIdImgPath3) {
        this.FZRIdImgPath3 = FZRIdImgPath3;
    }

    public Date getFZRClaimTime() {
        return FZRClaimTime;
    }

    public void setFZRClaimTime(Date FZRClaimTime) {
        this.FZRClaimTime = FZRClaimTime;
    }

    public boolean isFZRClaimState() {
        return FZRClaimState;
    }

    public void setFZRClaimState(boolean FZRClaimState) {
        this.FZRClaimState = FZRClaimState;
    }

    public String getApprovalCode() {
        return ApprovalCode;
    }

    public void setApprovalCode(String approvalCode) {
        ApprovalCode = approvalCode;
    }

    public String getApprovalName() {
        return ApprovalName;
    }

    public void setApprovalName(String approvalName) {
        ApprovalName = approvalName;
    }

    public String getApprovalMobile() {
        return ApprovalMobile;
    }

    public void setApprovalMobile(String approvalMobile) {
        ApprovalMobile = approvalMobile;
    }

    public String getApprovalPhone() {
        return ApprovalPhone;
    }

    public void setApprovalPhone(String approvalPhone) {
        ApprovalPhone = approvalPhone;
    }

    public String getApprovalEmail() {
        return ApprovalEmail;
    }

    public void setApprovalEmail(String approvalEmail) {
        ApprovalEmail = approvalEmail;
    }

    public String getApprovalIdImgPath1() {
        return ApprovalIdImgPath1;
    }

    public void setApprovalIdImgPath1(String approvalIdImgPath1) {
        ApprovalIdImgPath1 = approvalIdImgPath1;
    }

    public String getApprovalIdImgPath2() {
        return ApprovalIdImgPath2;
    }

    public void setApprovalIdImgPath2(String approvalIdImgPath2) {
        ApprovalIdImgPath2 = approvalIdImgPath2;
    }

    public String getApprovalIdImgPath3() {
        return ApprovalIdImgPath3;
    }

    public void setApprovalIdImgPath3(String approvalIdImgPath3) {
        ApprovalIdImgPath3 = approvalIdImgPath3;
    }

    public Date getApprovalClaimTime() {
        return ApprovalClaimTime;
    }

    public void setApprovalClaimTime(Date approvalClaimTime) {
        ApprovalClaimTime = approvalClaimTime;
    }

    public boolean isApprovalClaimState() {
        return ApprovalClaimState;
    }

    public void setApprovalClaimState(boolean approvalClaimState) {
        ApprovalClaimState = approvalClaimState;
    }

    public int getSignInInterval() {
        return SignInInterval;
    }

    public void setSignInInterval(int signInInterval) {
        SignInInterval = signInInterval;
    }

    public int getRenewInterval() {
        return RenewInterval;
    }

    public void setRenewInterval(int renewInterval) {
        RenewInterval = renewInterval;
    }

    public String getLastSignTime() {
        return LastSignTime;
    }

    public void setLastSignTime(String lastSignTime) {
        LastSignTime = lastSignTime;
    }

    public String getLastRenewTime() {
        return LastRenewTime;
    }

    public void setLastRenewTime(String lastRenewTime) {
        LastRenewTime = lastRenewTime;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getSysRemarks() {
        return SysRemarks;
    }

    public void setSysRemarks(String sysRemarks) {
        SysRemarks = sysRemarks;
    }

    public boolean isDisable() {
        return Disable;
    }

    public void setDisable(boolean disable) {
        Disable = disable;
    }

    public String getDisableUserCode() {
        return DisableUserCode;
    }

    public void setDisableUserCode(String disableUserCode) {
        DisableUserCode = disableUserCode;
    }

    public String getDisableTime() {
        return DisableTime;
    }

    public void setDisableTime(String disableTime) {
        DisableTime = disableTime;
    }

    public String getDisableContent() {
        return DisableContent;
    }

    public void setDisableContent(String disableContent) {
        DisableContent = disableContent;
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

    public String getGLYIdType() {
        return GLYIdType;
    }

    public void setGLYIdType(String GLYIdType) {
        this.GLYIdType = GLYIdType;
    }

    public String getGLYIdNumber() {
        return GLYIdNumber;
    }

    public void setGLYIdNumber(String GLYIdNumber) {
        this.GLYIdNumber = GLYIdNumber;
    }

    public String getFZRIdType() {
        return FZRIdType;
    }

    public void setFZRIdType(String FZRIdType) {
        this.FZRIdType = FZRIdType;
    }

    public String getFZRIdNumber() {
        return FZRIdNumber;
    }

    public void setFZRIdNumber(String FZRIdNumber) {
        this.FZRIdNumber = FZRIdNumber;
    }

    public String getApprovalIdType() {
        return ApprovalIdType;
    }

    public void setApprovalIdType(String approvalIdType) {
        ApprovalIdType = approvalIdType;
    }

    public String getApprovalIdNumber() {
        return ApprovalIdNumber;
    }

    public void setApprovalIdNumber(String approvalIdNumber) {
        ApprovalIdNumber = approvalIdNumber;
    }
}
