package com.hlh.GLPT.dm.dao;

import com.hlh.GLPT.base.service.DepartmentService;
import com.hlh.GLPT.dm.domain.DomainName;
import com.hlh.GLPT.dm.domain.IPv4;
import com.hlh.GLPT.dm.domain.IPv6;
import com.hlh.GLPT.dm.service.BusinessTypeService;
import com.hlh.GLPT.dm.service.DevelopingPartyService;
import com.hlh.GLPT.dm.service.IPv4Service;
import com.hlh.GLPT.dm.service.IPv6Service;
import com.hlh.common.domain.DataPage;
import com.hlh.common.service.DataPageService;
import com.hlh.common.service.DbToolsService;
import com.hlh.util.*;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Package: com.hlh.GLPT.dm.dao
 * User: 黄良辉  15-6-3  下午10:27
 */
@Repository
public class DomainNameDao {
    @Autowired
    private DbToolsService dbToolsService;
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private DataPageService dataPageService;
    @Autowired
    private BusinessTypeService btService;
    @Autowired
    private DevelopingPartyService dPartyService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private IPv4Service iPv4Service;
    @Autowired
    private IPv6Service iPv6Service;

    /**
     * 添加
     *
     * @param dm
     * @return
     */
    public String add(DomainName dm) {
        String[] fField = new String[]{"Id", "SysFLCode", "SysFLChildCode", "NoDomain", "DomainName", "url", "IPv4Address", "IPv6Address", "TTL", "RecordType", "RecordVal", "WebSiteFullName", "OpenDate"
                , "BusinessTypeCode", "KFFCode", "GLDepartmentCode", "GLYCode", "GLYName", "GLYMobile", "GLYPhone", "GLYEmail", "GLYIdType", "GLYIdNumber", "GLYIdImgPath1", "GLYIdImgPath2", "GLYIdImgPath3", "GLYClaimTime", "GLYClaimState"
                , "FZRCode", "FZRName", "FZRMobile", "FZRPhone", "FZREmail", "FZRIdType", "FZRIdNumber", "FZRIdImgPath1", "FZRIdImgPath2", "FZRIdImgPath3", "FZRClaimTime", "FZRClaimState"
                , "ApprovalCode", "ApprovalName", "ApprovalMobile", "ApprovalPhone", "ApprovalEmail", "ApprovalIdType", "ApprovalIdNumber", "ApprovalIdImgPath1", "ApprovalIdImgPath2", "ApprovalIdImgPath3", "ApprovalClaimTime", "ApprovalClaimState"
                , "SignInInterval", "RenewInterval", "Remarks", "SysRemarks"
                , "Disable", "SortCode", "DeleteMark", "CallMark", "CreateDate"
                , "CreateUserCode", "CreateUserName", "ECPCode", "ECPName", "ECPMobile", "ECPPhone", "ECPEmail", "ECPIdType", "ECPIdNumber", "ECPIdImgPath1", "ECPIdImgPath2", "ECPIdImgPath3"};
        Object[] fVal = new Object[]{dm.getId(), dm.getSysFLCode(), dm.getSysFLChildCode(), dm.isNoDomain(), dm.getDomainName(), dm.getUrl(), dm.getIPv4Address(), dm.getIPv6Address(), dm.getTTL(), dm.getRecordType(), dm.getRecordVal(), dm.getWebSiteFullName(), dm.getOpenDate()
                , dm.getBusinessTypeCode(), dm.getKFFCode(), dm.getGLDepartmentCode(), dm.getGLYCode(), dm.getGLYName(), dm.getGLYMobile(), dm.getGLYPhone(), dm.getGLYEmail(), dm.getGLYIdType(), dm.getGLYIdNumber(), dm.getGLYIdImgPath1(), dm.getGLYIdImgPath2(), dm.getGLYIdImgPath3(), dm.getGLYClaimTime(), dm.isGLYClaimState()
                , dm.getFZRCode(), dm.getFZRName(), dm.getFZRMobile(), dm.getFZRPhone(), dm.getFZREmail(), dm.getFZRIdType(), dm.getFZRIdNumber(), dm.getFZRIdImgPath1(), dm.getFZRIdImgPath2(), dm.getFZRIdImgPath3(), dm.getFZRClaimTime(), dm.isFZRClaimState()
                , dm.getApprovalCode(), dm.getApprovalName(), dm.getApprovalMobile(), dm.getApprovalPhone(), dm.getApprovalEmail(), dm.getApprovalIdType(), dm.getApprovalIdNumber(), dm.getApprovalIdImgPath1(), dm.getApprovalIdImgPath2(), dm.getApprovalIdImgPath3(), dm.getApprovalClaimTime(), dm.isApprovalClaimState()
                , dm.getSignInInterval(), dm.getRenewInterval(), dm.getRemarks(), dm.getSysRemarks()
                , dm.isDisable(), dm.getSortCode(), dm.isDeleteMark(), dm.isCallMark(), dm.getCreateDate()
                , dm.getCreateUserCode(), dm.getCreateUserName(), dm.getECPCode(), dm.getECPName(), dm.getECPMobile(), dm.getECPPhone(), dm.getECPEmail(), dm.getECPIdType(), dm.getECPIdNumber(), dm.getECPIdImgPath1(), dm.getECPIdImgPath2(), dm.getECPIdImgPath3()
        };
        return dbToolsService.add("dm_DomainName", fField, fVal);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(String Id) {
        return dbToolsService.ThoroughDel("dm_DomainName", "Id", Id, "str");
    }

    /**
     * 修改
     *
     * @param dm
     * @return
     */
    public String edit(DomainName dm) {
        String[] fField = new String[]{"SysFLCode", "SysFLChildCode", "NoDomain", "DomainName", "url", "IPv4Address", "IPv6Address", "TTL", "RecordType", "RecordVal", "WebSiteFullName", "OpenDate"
                , "BusinessTypeCode", "KFFCode", "GLDepartmentCode", "GLYCode", "GLYName", "GLYMobile", "GLYPhone", "GLYEmail", "GLYIdType", "GLYIdNumber", "GLYIdImgPath1", "GLYIdImgPath2", "GLYIdImgPath3", "GLYClaimTime", "GLYClaimState"
                , "FZRCode", "FZRName", "FZRMobile", "FZRPhone", "FZREmail", "FZRIdType", "FZRIdNumber", "FZRIdImgPath1", "FZRIdImgPath2", "FZRIdImgPath3", "FZRClaimTime", "FZRClaimState"
                , "ApprovalCode", "ApprovalName", "ApprovalMobile", "ApprovalPhone", "ApprovalEmail", "ApprovalIdType", "ApprovalIdNumber", "ApprovalIdImgPath1", "ApprovalIdImgPath2", "ApprovalIdImgPath3", "ApprovalClaimTime", "ApprovalClaimState"
                , "SignInInterval", "RenewInterval", "Remarks", "SysRemarks"
                , "Disable", "SortCode", "DeleteMark", "CallMark", "ModifyDate"
                , "ModifyUserCode", "ModifyUserName", "ECPCode", "ECPName", "ECPMobile", "ECPPhone", "ECPEmail", "ECPIdType", "ECPIdNumber", "ECPIdImgPath1", "ECPIdImgPath2", "ECPIdImgPath3"};
        Object[] fVal = new Object[]{dm.getSysFLCode(), dm.getSysFLChildCode(), dm.isNoDomain(), dm.getDomainName(), dm.getUrl(), dm.getIPv4Address(), dm.getIPv6Address(), dm.getTTL(), dm.getRecordType(), dm.getRecordVal(), dm.getWebSiteFullName(), dm.getOpenDate()
                , dm.getBusinessTypeCode(), dm.getKFFCode(), dm.getGLDepartmentCode(), dm.getGLYCode(), dm.getGLYName(), dm.getGLYMobile(), dm.getGLYPhone(), dm.getGLYEmail(), dm.getGLYIdType(), dm.getGLYIdNumber(), dm.getGLYIdImgPath1(), dm.getGLYIdImgPath2(), dm.getGLYIdImgPath3(), dm.getGLYClaimTime(), dm.isGLYClaimState()
                , dm.getFZRCode(), dm.getFZRName(), dm.getFZRMobile(), dm.getFZRPhone(), dm.getFZREmail(), dm.getFZRIdType(), dm.getFZRIdNumber(), dm.getFZRIdImgPath1(), dm.getFZRIdImgPath2(), dm.getFZRIdImgPath3(), dm.getFZRClaimTime(), dm.isFZRClaimState()
                , dm.getApprovalCode(), dm.getApprovalName(), dm.getApprovalMobile(), dm.getApprovalPhone(), dm.getApprovalEmail(), dm.getApprovalIdType(), dm.getApprovalIdNumber(), dm.getApprovalIdImgPath1(), dm.getApprovalIdImgPath2(), dm.getApprovalIdImgPath3(), dm.getApprovalClaimTime(), dm.isApprovalClaimState()
                , dm.getSignInInterval(), dm.getRenewInterval(), dm.getRemarks(), dm.getSysRemarks()
                , dm.isDisable(), dm.getSortCode(), dm.isDeleteMark(), dm.isCallMark(), dm.getModifyDate()
                , dm.getModifyUserCode(), dm.getModifyUserName(), dm.getECPCode(), dm.getECPName(), dm.getECPMobile(), dm.getECPPhone(), dm.getECPEmail(), dm.getECPIdType(), dm.getECPIdNumber(), dm.getECPIdImgPath1(), dm.getECPIdImgPath2(), dm.getECPIdImgPath3()
        };
        return dbToolsService.edit("dm_DomainName", fField, fVal, "Id", dm.getId(), "str");
    }

    /**
     * 域名认领
     *
     * @param dm
     * @return
     */
    public String ClaimOK(DomainName dm) {
        String[] fField = new String[]{"WebSiteFullName", "SysFLCode", "SysFLChildCode", "KFFCode", "OpenDate", "SysRemarks"
                , "GLYCode", "GLYName", "GLYMobile", "GLYPhone", "GLYEmail", "GLYIdType", "GLYIdNumber", "GLYIdImgPath1", "GLYIdImgPath2", "GLYIdImgPath3", "GLYClaimTime", "GLYClaimState"
                , "FZRCode", "FZRName", "FZRMobile", "FZRPhone", "FZREmail", "FZRIdType", "FZRIdNumber", "FZRIdImgPath1", "FZRIdImgPath2", "FZRIdImgPath3", "FZRClaimTime", "FZRClaimState"
                , "ApprovalCode", "ApprovalName", "ApprovalMobile", "ApprovalPhone", "ApprovalEmail", "ApprovalIdType", "ApprovalIdNumber", "ApprovalIdImgPath1", "ApprovalIdImgPath2", "ApprovalIdImgPath3", "ApprovalClaimTime", "ApprovalClaimState"
                , "ECPCode", "ECPName", "ECPMobile", "ECPPhone", "ECPEmail", "ECPIdType", "ECPIdNumber", "ECPIdImgPath1", "ECPIdImgPath2", "ECPIdImgPath3"
        };
        Object[] fVal = new Object[]{dm.getWebSiteFullName(), dm.getSysFLCode(), dm.getSysFLChildCode(), dm.getKFFCode(), dm.getOpenDate(), dm.getSysRemarks()
                , dm.getGLYCode(), dm.getGLYName(), dm.getGLYMobile(), dm.getGLYPhone(), dm.getGLYEmail(), dm.getGLYIdType(), dm.getGLYIdNumber(), dm.getGLYIdImgPath1(), dm.getGLYIdImgPath2(), dm.getGLYIdImgPath3(), dm.getGLYClaimTime(), dm.isGLYClaimState()
                , dm.getFZRCode(), dm.getFZRName(), dm.getFZRMobile(), dm.getFZRPhone(), dm.getFZREmail(), dm.getFZRIdType(), dm.getFZRIdNumber(), dm.getFZRIdImgPath1(), dm.getFZRIdImgPath2(), dm.getFZRIdImgPath3(), dm.getFZRClaimTime(), dm.isFZRClaimState()
                , dm.getApprovalCode(), dm.getApprovalName(), dm.getApprovalMobile(), dm.getApprovalPhone(), dm.getApprovalEmail(), dm.getApprovalIdType(), dm.getApprovalIdNumber(), dm.getApprovalIdImgPath1(), dm.getApprovalIdImgPath2(), dm.getApprovalIdImgPath3(), dm.getApprovalClaimTime(), dm.isApprovalClaimState()
                , dm.getECPCode(), dm.getECPName(), dm.getECPMobile(), dm.getECPPhone(), dm.getECPEmail(), dm.getECPIdType(), dm.getECPIdNumber(), dm.getECPIdImgPath1(), dm.getECPIdImgPath2(), dm.getECPIdImgPath3()
        };
        return dbToolsService.edit("dm_DomainName", fField, fVal, "Id", dm.getId(), "str");
    }

    /**
     * 域名认领 - 修改
     *
     * @param dm
     * @return
     */
    public String ClaimEditOK(DomainName dm) {
        String[] fField = new String[]{"WebSiteFullName", "SysFLCode", "SysFLChildCode", "KFFCode", "OpenDate", "SysRemarks","GLDepartmentCode"
                , "GLYCode", "GLYName", "GLYMobile", "GLYPhone", "GLYEmail", "GLYIdType", "GLYIdNumber", "GLYIdImgPath1", "GLYIdImgPath2", "GLYIdImgPath3"
                , "FZRCode", "FZRName", "FZRMobile", "FZRPhone", "FZREmail", "FZRIdType", "FZRIdNumber", "FZRIdImgPath1", "FZRIdImgPath2", "FZRIdImgPath3"
                , "ApprovalCode", "ApprovalName", "ApprovalMobile", "ApprovalPhone", "ApprovalEmail", "ApprovalIdType", "ApprovalIdNumber", "ApprovalIdImgPath1", "ApprovalIdImgPath2", "ApprovalIdImgPath3"
                , "ECPCode", "ECPName", "ECPMobile", "ECPPhone", "ECPEmail", "ECPIdType", "ECPIdNumber", "ECPIdImgPath1", "ECPIdImgPath2", "ECPIdImgPath3"
        };
        Object[] fVal = new Object[]{dm.getWebSiteFullName(), dm.getSysFLCode(), dm.getSysFLChildCode(), dm.getKFFCode(), dm.getOpenDate(), dm.getSysRemarks(),dm.getGLDepartmentCode()
                , dm.getGLYCode(), dm.getGLYName(), dm.getGLYMobile(), dm.getGLYPhone(), dm.getGLYEmail(), dm.getGLYIdType(), dm.getGLYIdNumber(), dm.getGLYIdImgPath1(), dm.getGLYIdImgPath2(), dm.getGLYIdImgPath3()
                , dm.getFZRCode(), dm.getFZRName(), dm.getFZRMobile(), dm.getFZRPhone(), dm.getFZREmail(), dm.getFZRIdType(), dm.getFZRIdNumber(), dm.getFZRIdImgPath1(), dm.getFZRIdImgPath2(), dm.getFZRIdImgPath3()
                , dm.getApprovalCode(), dm.getApprovalName(), dm.getApprovalMobile(), dm.getApprovalPhone(), dm.getApprovalEmail(), dm.getApprovalIdType(), dm.getApprovalIdNumber(), dm.getApprovalIdImgPath1(), dm.getApprovalIdImgPath2(), dm.getApprovalIdImgPath3()
                , dm.getECPCode(), dm.getECPName(), dm.getECPMobile(), dm.getECPPhone(), dm.getECPEmail(), dm.getECPIdType(), dm.getECPIdNumber(), dm.getECPIdImgPath1(), dm.getECPIdImgPath2(), dm.getECPIdImgPath3()
        };
        return dbToolsService.edit("dm_DomainName", fField, fVal, "Id", dm.getId(), "str");
    }

    /**
     * 取消域名认领
     *
     * @param Id
     * @return
     */
    public String CancelClaimOK(String Id) {
        String[] fField = new String[]{"WebSiteFullName", "SysFLCode", "SysFLChildCode", "KFFCode", "OpenDate"
                , "SysRemarks", "GLYCode", "GLYName", "GLYMobile", "GLYPhone"
                , "GLYEmail", "GLYIdType", "GLYIdNumber", "GLYIdImgPath1", "GLYIdImgPath2"
                , "GLYIdImgPath3", "GLYClaimTime", "GLYClaimState", "FZRCode", "FZRName"
                , "FZRMobile", "FZRPhone", "FZREmail", "FZRIdType", "FZRIdNumber"
                , "FZRIdImgPath1", "FZRIdImgPath2", "FZRIdImgPath3", "FZRClaimTime", "FZRClaimState"
                , "ApprovalCode", "ApprovalName", "ApprovalMobile", "ApprovalPhone", "ApprovalEmail"
                , "ApprovalIdType", "ApprovalIdNumber", "ApprovalIdImgPath1", "ApprovalIdImgPath2", "ApprovalIdImgPath3"
                , "ApprovalClaimTime", "ApprovalClaimState", "ECPCode", "ECPName", "ECPMobile"
                , "ECPPhone", "ECPEmail", "ECPIdType", "ECPIdNumber", "ECPIdImgPath1"
                , "ECPIdImgPath2", "ECPIdImgPath3","GLDepartmentCode"
        };
        Object[] fVal = new Object[]{null, null, null, null, null
                , null, null, null, null, null
                , null, null, null, null, null
                , null, null, false, null, null
                , null, null, null, null, null
                , null, null, null, null, false
                , null, null, null, null, null
                , null, null, null, null, null
                , null, false, null, null, null
                , null, null, null, null, null
                , null, null,null};
        return dbToolsService.edit("dm_DomainName", fField, fVal, "Id", Id, "str");
    }

    /**
     * 标识删除
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetDeleteMark(String Id, boolean MarkVal) {
        return dbToolsService.edit("dm_DomainName", new String[]{"DeleteMark"}, new Object[]{MarkVal}, "Id", Id, "str");
    }

    /**
     * 标识停用
     *
     * @param Id
     * @param Disable
     * @param DisableUserCode
     * @param DisableTime
     * @param DisableContent
     * @return
     */
    public String SetDisable(String Id, boolean Disable, String DisableUserCode, Date DisableTime, String DisableContent) {
        return dbToolsService.edit("dm_DomainName", new String[]{"Disable", "DisableUserCode", "DisableTime", "DisableContent"}, new Object[]{Disable, DisableUserCode, DisableTime, DisableContent}, "Id", Id, "str");
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(String Id, boolean MarkVal) {
        return dbToolsService.edit("dm_DomainName", new String[]{"CallMark"}, new Object[]{MarkVal}, "Id", Id, "str");
    }

    /**
     * 设置最后签到时间
     *
     * @param Id           ID
     * @param LastSignTime 最后签到时间
     * @return
     */
    public String SetLastSignTime(String Id, String LastSignTime) {
        return dbToolsService.edit("dm_DomainName", new String[]{"LastSignTime"}, new Object[]{LastSignTime}, "Id", Id, "str");
    }

    /**
     * 设置最后续订时间
     *
     * @param Id            ID
     * @param LastRenewTime 最后续订时间
     * @return
     */
    public String SetLastRenewTime(String Id, String LastRenewTime) {
        return dbToolsService.edit("dm_DomainName", new String[]{"LastRenewTime"}, new Object[]{LastRenewTime}, "Id", Id, "str");
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public DomainName findById(String Id) {
        String sqlStr = "SELECT * FROM dm_DomainName WHERE Id=?";
        return dbUtilsTemplate.findFirst(DomainName.class, sqlStr, Id);
    }

    /**
     * 查询记录 分页
     *
     * @param Disable    是否被停用
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @param start      开始记录
     * @param number     记录数
     * @return
     */
    public String findByPage(Boolean Disable, Boolean DeleteMark, Boolean CallMark, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("VIEW_DM_DomainName");
        dataPage1.setColumns("Id,SysFLName,SysFLChildName,NoDomain,DomainName,Url,IPv4Address,IPv6Address,TTL,RecordType,RecordVal" +
                ",WebSiteFullName,BusinessTypeName,KFFName,GLDepartmentName" +
                ",GLYName,GLYMobile,GLYPhone,GLYEmail,GLYClaimTime,GLYClaimState" +
                ", FZRName,FZRMobile,FZRPhone,FZREmail" +
                ",SignInInterval,RenewInterval" +
                ",Remarks,Disable,SortCode,DeleteMark,CallMark,LastSignTime,LastRenewTime");
        String[] strings1 = new String[]{"Id", "SysFLName", "SysFLChildName", "NoDomain", "DomainName", "Url", "IPv4Address", "IPv6Address", "TTL", "RecordType", "RecordVal"
                , "WebSiteFullName", "BusinessTypeName", "KFFName", "GLDepartmentName"
                , "GLYName", "GLYMobile", "GLYPhone", "GLYEmail", "GLYClaimTime", "GLYClaimState"
                , "FZRName", "FZRMobile", "FZRPhone", "FZREmail", "SignInInterval", "RenewInterval"
                , "Remarks", "Disable", "SortCode", "DeleteMark", "CallMark", "LastSignTime", "LastRenewTime"};
        dataPage1.setOrder_field("DomainName ASC,IPv4Address ASC");
        dataPage1.setGroup_field("");
        dataPage1.setCurrPage(start);
        dataPage1.setPageSize(number);
        StringBuilder sb1 = new StringBuilder();
        if (Disable != null) {
            sb1.append("Disable=").append(Disable);
        }
        if (CallMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("CallMark=").append(CallMark);
        }
        if (DeleteMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" DeleteMark=").append(DeleteMark);
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(DomainName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Url LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv4Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv6Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR BusinessTypeName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR KFFName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLDepartmentName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYEmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZREmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        dataPage1.setsCondition(sb1.toString());
        List tmpList1 = dataPageService.DataByPage(dataPage1);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    /**
     * 查询记录 分页
     *
     * @param GLYClaimState 是否被认领 null为全部
     * @param fField        查询字段
     * @param fValue        查询值
     * @param start         开始记录
     * @param number        记录数
     * @return
     */
    public String findByPageEx(Boolean GLYClaimState, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("VIEW_DM_DomainName");
        dataPage1.setColumns("Id,SysFLName,SysFLChildName,NoDomain,DomainName,Url,IPv4Address,IPv6Address,TTL,RecordType,RecordVal" +
                ",WebSiteFullName,BusinessTypeName,KFFName,GLDepartmentName" +
                ",GLYName,GLYMobile,GLYPhone,GLYEmail,GLYClaimTime,GLYClaimState" +
                ", FZRName,FZRMobile,FZRPhone,FZREmail" +
                ",SignInInterval,RenewInterval" +
                ",Remarks,Disable,SortCode,DeleteMark,CallMark,LastSignTime,LastRenewTime");
        String[] strings1 = new String[]{"Id", "SysFLName", "SysFLChildName", "NoDomain", "DomainName", "Url", "IPv4Address", "IPv6Address", "TTL", "RecordType", "RecordVal"
                , "WebSiteFullName", "BusinessTypeName", "KFFName", "GLDepartmentName"
                , "GLYName", "GLYMobile", "GLYPhone", "GLYEmail", "GLYClaimTime", "GLYClaimState"
                , "FZRName", "FZRMobile", "FZRPhone", "FZREmail", "SignInInterval", "RenewInterval"
                , "Remarks", "Disable", "SortCode", "DeleteMark", "CallMark", "LastSignTime", "LastRenewTime"};
        dataPage1.setOrder_field("DomainName ASC,IPv4Address ASC");
        dataPage1.setGroup_field("");
        dataPage1.setCurrPage(start);
        dataPage1.setPageSize(number);
        StringBuilder sb1 = new StringBuilder();
        if (GLYClaimState != null) {
            sb1.append("GLYClaimState=").append(GLYClaimState);
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" DeleteMark=false AND");
            sb1.append("(DomainName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Url LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv4Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv6Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR BusinessTypeName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR KFFName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLDepartmentName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYEmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZREmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        dataPage1.setsCondition(sb1.toString());
        List tmpList1 = dataPageService.DataByPage(dataPage1);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    /**
     * 查询记录数
     *
     * @param Disable    状态
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @return
     */
    public int GetRowCount(Boolean Disable, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) {
        StringBuilder sb1 = new StringBuilder();
        if (Disable != null) {
            sb1.append("Disable=").append(Disable);
        }
        if (CallMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("CallMark=").append(CallMark);
        }
        if (DeleteMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" DeleteMark=").append(DeleteMark);
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(DomainName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Url LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv4Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv6Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR BusinessTypeName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR KFFName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLDepartmentName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYEmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZREmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "VIEW_DM_DomainName", sb1.toString());
    }

    /**
     * 查询记录数
     *
     * @param GLYClaimState 是否被认领 null为全部
     * @param fField        查询字段
     * @param fValue        查询值
     * @return
     */
    public int GetRowCountEx(Boolean GLYClaimState, String fField, String fValue) {
        StringBuilder sb1 = new StringBuilder();
        if (GLYClaimState != null) {
            sb1.append("GLYClaimState=").append(GLYClaimState);
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" DeleteMark=false AND");
            sb1.append("(DomainName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Url LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv4Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv6Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR BusinessTypeName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR KFFName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLDepartmentName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYEmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZREmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "VIEW_DM_DomainName", sb1.toString());
    }


    /**
     * 查询记录(按管理员代码) 分页
     *
     * @param GLYCode 管理员代码
     * @param fField  查询字段
     * @param fValue  查询值
     * @param start   开始记录
     * @param number  记录数
     * @return
     */
    public String findGLYCodeByPage(String GLYCode, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("VIEW_DM_DomainName");
        dataPage1.setColumns("Id,SysFLName,SysFLChildName,NoDomain,DomainName,Url,IPv4Address,IPv6Address,TTL,RecordType,RecordVal" +
                ",WebSiteFullName,BusinessTypeName,KFFName,GLDepartmentName" +
                ",GLYName,GLYMobile,GLYPhone,GLYEmail" +
                ", FZRName,FZRMobile,FZRPhone,FZREmail" +
                ",SignInInterval,RenewInterval" +
                ",Remarks,Disable,SortCode,DeleteMark,CallMark,LastSignTime,LastRenewTime");
        String[] strings1 = new String[]{"Id", "SysFLName", "SysFLChildName", "NoDomain", "DomainName", "Url", "IPv4Address", "IPv6Address", "TTL", "RecordType", "RecordVal"
                , "WebSiteFullName", "BusinessTypeName", "KFFName", "GLDepartmentName"
                , "GLYName", "GLYMobile", "GLYPhone", "GLYEmail"
                , "FZRName", "FZRMobile", "FZRPhone", "FZREmail", "SignInInterval", "RenewInterval"
                , "Remarks", "Disable", "SortCode", "DeleteMark", "CallMark", "LastSignTime", "LastRenewTime"};
        dataPage1.setOrder_field("LastSignTime ASC");
        dataPage1.setGroup_field("");
        dataPage1.setCurrPage(start);
        dataPage1.setPageSize(number);
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Disable=false AND DeleteMark=false");
        //sb1.append(" AND NOW()>DATE_ADD(LastSignTime, INTERVAL SignInInterval DAY)");  //当前时间大于最后签到时间加上签到间隔天数
        if (!"".equals(GLYCode)) {  //管理员代码不为空时，代表查询单个用户，为空时，代表查询所有用户
            sb1.append(" AND GLYCode='").append(GLYCode).append("'");
        } else {
            return "[{}]";
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(DomainName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Url LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv4Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv6Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR BusinessTypeName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR KFFName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLDepartmentName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYEmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZREmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        dataPage1.setsCondition(sb1.toString());
        List tmpList1 = dataPageService.DataByPage(dataPage1);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    /**
     * 查询记录数(按管理员代码)
     *
     * @param GLYCode 管理员代码
     * @param fField  查询字段
     * @param fValue  查询值
     * @return
     */
    public int GetGLYCodeRowCount(String GLYCode, String fField, String fValue) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Disable=false AND DeleteMark=false");
        //sb1.append(" AND NOW()>DATE_ADD(LastSignTime, INTERVAL SignInInterval DAY)");  //当前时间大于最后签到时间加上签到间隔天数
        if (!"".equals(GLYCode)) {  //管理员代码不为空时，代表查询单个用户，为空时，代表查询所有用户
            sb1.append(" AND GLYCode='").append(GLYCode).append("'");
        } else {
            return 0;
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(DomainName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Url LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv4Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv6Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR BusinessTypeName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR KFFName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLDepartmentName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYEmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZREmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "VIEW_DM_DomainName", sb1.toString());
    }

    /**
     * 查询未签到记录 分页
     *
     * @param GLYCode 管理员代码
     * @param fField  查询字段
     * @param fValue  查询值
     * @param start   开始记录
     * @param number  记录数
     * @return
     */
    public String findNoSignRecordByPage(String GLYCode, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("VIEW_DM_DomainName");
        dataPage1.setColumns("Id,SysFLName,SysFLChildName,NoDomain,DomainName,Url,IPv4Address,IPv6Address,TTL,RecordType,RecordVal" +
                ",WebSiteFullName,BusinessTypeName,KFFName,GLDepartmentName" +
                ",GLYName,GLYMobile,GLYPhone,GLYEmail" +
                ", FZRName,FZRMobile,FZRPhone,FZREmail" +
                ",SignInInterval,RenewInterval" +
                ",Remarks,Disable,SortCode,DeleteMark,CallMark,LastSignTime,LastRenewTime");
        String[] strings1 = new String[]{"Id", "SysFLName", "SysFLChildName", "NoDomain", "DomainName", "Url", "IPv4Address", "IPv6Address", "TTL", "RecordType", "RecordVal"
                , "WebSiteFullName", "BusinessTypeName", "KFFName", "GLDepartmentName"
                , "GLYName", "GLYMobile", "GLYPhone", "GLYEmail"
                , "FZRName", "FZRMobile", "FZRPhone", "FZREmail", "SignInInterval", "RenewInterval"
                , "Remarks", "Disable", "SortCode", "DeleteMark", "CallMark", "LastSignTime", "LastRenewTime"};
        dataPage1.setOrder_field("LastSignTime ASC");
        dataPage1.setGroup_field("");
        dataPage1.setCurrPage(start);
        dataPage1.setPageSize(number);
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Disable=false AND DeleteMark=false");
        sb1.append(" AND NOW()>DATE_ADD(LastSignTime, INTERVAL SignInInterval DAY)");  //当前时间大于最后签到时间加上签到间隔天数
        if (!"".equals(GLYCode)) {  //管理员代码不为空时，代表查询单个用户，为空时，代表查询所有用户
            sb1.append(" AND GLYCode='").append(GLYCode).append("'");
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(DomainName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Url LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv4Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv6Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR BusinessTypeName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR KFFName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLDepartmentName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYEmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZREmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        dataPage1.setsCondition(sb1.toString());
        List tmpList1 = dataPageService.DataByPage(dataPage1);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    /**
     * 查询未签到记录数
     *
     * @param GLYCode 管理员代码
     * @param fField  查询字段
     * @param fValue  查询值
     * @return
     */
    public int GetNoSignRecordRowCount(String GLYCode, String fField, String fValue) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Disable=false AND DeleteMark=false");
        sb1.append(" AND NOW()>DATE_ADD(LastSignTime, INTERVAL SignInInterval DAY)");  //当前时间大于最后签到时间加上签到间隔天数
        if (!"".equals(GLYCode)) {  //管理员代码不为空时，代表查询单个用户，为空时，代表查询所有用户
            sb1.append(" AND GLYCode='").append(GLYCode).append("'");
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(DomainName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Url LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv4Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv6Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR BusinessTypeName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR KFFName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLDepartmentName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYEmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZREmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "VIEW_DM_DomainName", sb1.toString());
    }

    /**
     * 查询未续订记录 分页
     *
     * @param GLYCode 管理员代码
     * @param fField  查询字段
     * @param fValue  查询值
     * @param start   开始记录
     * @param number  记录数
     * @return
     */
    public String findNoRenewRecordByPage(String GLYCode, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("VIEW_DM_DomainName");
        dataPage1.setColumns("Id,SysFLName,SysFLChildName,NoDomain,DomainName,Url,IPv4Address,IPv6Address,TTL,RecordType,RecordVal" +
                ",WebSiteFullName,BusinessTypeName,KFFName,GLDepartmentName" +
                ",GLYName,GLYMobile,GLYPhone,GLYEmail" +
                ", FZRName,FZRMobile,FZRPhone,FZREmail" +
                ",SignInInterval,RenewInterval" +
                ",Remarks,Disable,SortCode,DeleteMark,CallMark,LastSignTime,LastRenewTime");
        String[] strings1 = new String[]{"Id", "SysFLName", "SysFLChildName", "NoDomain", "DomainName", "Url", "IPv4Address", "IPv6Address", "TTL", "RecordType", "RecordVal"
                , "WebSiteFullName", "BusinessTypeName", "KFFName", "GLDepartmentName"
                , "GLYName", "GLYMobile", "GLYPhone", "GLYEmail"
                , "FZRName", "FZRMobile", "FZRPhone", "FZREmail", "SignInInterval", "RenewInterval"
                , "Remarks", "Disable", "SortCode", "DeleteMark", "CallMark", "LastSignTime", "LastRenewTime"};
        dataPage1.setOrder_field("LastRenewTime ASC");
        dataPage1.setGroup_field("");
        dataPage1.setCurrPage(start);
        dataPage1.setPageSize(number);
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Disable=false AND DeleteMark=false");
        sb1.append(" AND NOW()>DATE_ADD(LastRenewTime, INTERVAL RenewInterval DAY)");  //当前时间大于最后签到时间加上签到间隔天数
        if (!"".equals(GLYCode)) {  //管理员代码不为空时，代表查询单个用户，为空时，代表查询所有用户
            sb1.append(" AND GLYCode='").append(GLYCode).append("'");
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(DomainName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Url LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv4Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv6Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR BusinessTypeName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR KFFName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLDepartmentName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYEmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZREmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        dataPage1.setsCondition(sb1.toString());
        List tmpList1 = dataPageService.DataByPage(dataPage1);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    /**
     * 查询未续订记录数
     *
     * @param GLYCode 管理员代码
     * @param fField  查询字段
     * @param fValue  查询值
     * @return
     */
    public int GetNoRenewRecordRowCount(String GLYCode, String fField, String fValue) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Disable=false AND DeleteMark=false");
        sb1.append(" AND NOW()>DATE_ADD(LastRenewTime, INTERVAL RenewInterval DAY)");  //当前时间大于最后签到时间加上签到间隔天数
        if (!"".equals(GLYCode)) {  //管理员代码不为空时，代表查询单个用户，为空时，代表查询所有用户
            sb1.append(" AND GLYCode='").append(GLYCode).append("'");
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(DomainName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Url LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv4Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv6Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR BusinessTypeName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR KFFName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLDepartmentName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYEmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZREmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "VIEW_DM_DomainName", sb1.toString());
    }

    /**
     * 验证添加
     *
     * @param dm
     * @return
     */
    public String VerifyAdd(DomainName dm) {
        String msg = "OK";
        String[] tmpIPv4Address1 = dm.getIPv4Address().split("\\|");
        boolean BV_IPv4 = true;
        for (int i1 = 0; i1 < tmpIPv4Address1.length; i1++) {
            if (!IPAddressUtil.isIPv4Address(tmpIPv4Address1[i1])) {
                BV_IPv4 = false;
                break;
            }
        }
        String[] tmpIPv6Address1 = dm.getIPv6Address().split("\\|");
        boolean BV_IPv6 = true;
        for (int i2 = 0; i2 < tmpIPv6Address1.length; i2++) {
            if (!IPAddressUtil.isIPv6Address(tmpIPv6Address1[i2])) {
                BV_IPv6 = false;
                break;
            }
        }
        if (!StrUtil.trimNotEmpty(dm.getDomainName()) && !dm.isNoDomain()) {
            msg = "域名不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getIPv4Address()) && !StrUtil.trimNotEmpty(dm.getIPv6Address())) {
            msg = "IPv4或IPv6地址不能为空";
        } else if (StrUtil.trimNotEmpty(dm.getIPv4Address()) && BV_IPv4 == false) {
            msg = "IPv4地址格式不正确";
        } else if (StrUtil.trimNotEmpty(dm.getIPv6Address()) && BV_IPv6 == false) {
            msg = "IPv6地址格式不正确";
            // } else if (!StrUtil.trimNotEmpty(dm.getBusinessTypeCode())) {
            //     msg = "请选择正确的业务类型";
        } else if (dbToolsService.IsRepeat("dm_DomainName", "DomainName", "WHERE DomainName='" + dm.getDomainName() + "'") && !dm.isNoDomain()) {
            msg = "域名已经被占用";
       // } else if (dbToolsService.IsRepeat("dm_DomainName", "IPv4Address", "WHERE IPv4Address LIKE '%" + dm.getIPv4Address() + "%'") && StrUtil.trimNotEmpty(dm.getIPv4Address())) {
       //     msg = "IPv4地址已经被占用";
       // } else if (!"".equals(StrUtil.toStr(dm.getIPv6Address())) && dbToolsService.IsRepeat("dm_DomainName", "IPv6Address", "WHERE IPv6Address LIKE '%" + dm.getIPv6Address() + "%'") && StrUtil.trimNotEmpty(dm.getIPv6Address())) {
       //     msg = "IPv6地址已经被占用";
            // } else if (dbToolsService.IsRepeat("dm_DomainName", "IPv4Address", "WHERE IPv4Address='" + dm.getIPv4Address() + "'")) {
            //     msg = "IPv4地址已经存在";
            // } else if (!"".equals(StrUtil.toStr(dm.getIPv6Address())) && dbToolsService.IsRepeat("dm_DomainName", "IPv6Address", "WHERE IPv6Address='" + dm.getIPv6Address() + "'")) {
            //     msg = "IPv6地址已经存在";
        }
        return msg;
    }

    /**
     * 验证导入
     *
     * @param dm
     * @return
     */
    public String VerifyImport(DomainName dm) {
        String msg = "";
        if (!dm.isNoDomain() && !StrUtil.trimNotEmpty(dm.getDomainName())) {
            msg = "域名不能为空";
        } else if (StrUtil.trimNotEmpty(dm.getIPv4Address()) && !IPAddressUtil.isIPv4Address(dm.getIPv4Address())) {
            msg = "IPv4地址格式不正确";
        } else if (StrUtil.trimNotEmpty(dm.getIPv6Address()) && !IPAddressUtil.isIPv6Address(dm.getIPv6Address())) {
            msg = "IPv6地址格式不正确";
       /* } else if (dbToolsService.IsRepeat("dm_DomainName", "DomainName", "WHERE DomainName='" + dm.getDomainName() + "'") && !dm.isNoDomain()) {
            msg = "域名已经被占用";
        } else if (dbToolsService.IsRepeat("dm_DomainName", "IPv4Address", "WHERE IPv4Address LIKE '%" + dm.getIPv4Address() + "%'") && StrUtil.trimNotEmpty(dm.getIPv4Address())) {
            msg = "IPv4地址已经被占用";
        } else if (!"".equals(StrUtil.toStr(dm.getIPv6Address())) && dbToolsService.IsRepeat("dm_DomainName", "IPv6Address", "WHERE IPv6Address LIKE '%" + dm.getIPv6Address() + "%'") && StrUtil.trimNotEmpty(dm.getIPv6Address())) {
            msg = "IPv6地址已经被占用";*/
        } else {
            msg = "OK";
        }
        return msg;
    }

    /**
     * 验证修改
     *
     * @param dm
     * @return
     */
    public String VerifyEdit(DomainName dm) {
        String msg = "";
        String[] tmpIPv4Address1 = dm.getIPv4Address().split("\\|");
        boolean BV_IPv4 = true;
        for (int i1 = 0; i1 < tmpIPv4Address1.length; i1++) {
            if (!IPAddressUtil.isIPv4Address(tmpIPv4Address1[i1])) {
                BV_IPv4 = false;
                break;
            }
        }
        String[] tmpIPv6Address1 = dm.getIPv6Address().split("\\|");
        boolean BV_IPv6 = true;
        for (int i2 = 0; i2 < tmpIPv6Address1.length; i2++) {
            if (!IPAddressUtil.isIPv6Address(tmpIPv6Address1[i2])) {
                BV_IPv6 = false;
                break;
            }
        }
        if (!StrUtil.trimNotEmpty(dm.getDomainName()) && !dm.isNoDomain()) {
            msg = "域名不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getIPv4Address()) && !StrUtil.trimNotEmpty(dm.getIPv6Address())) {
            msg = "IPv4或IPv6地址不能为空";
        } else if (StrUtil.trimNotEmpty(dm.getIPv4Address()) && BV_IPv4 == false) {
            msg = "IPv4地址格式不正确";
        } else if (StrUtil.trimNotEmpty(dm.getIPv6Address()) && BV_IPv6 == false) {
            msg = "IPv6地址格式不正确";
            // } else if (!StrUtil.trimNotEmpty(dm.getBusinessTypeCode())) {
            //    msg = "请选择正确的业务类型";
      /*  } else if (dbToolsService.IsRepeat("dm_DomainName", "DomainName", "WHERE DomainName LIKE '%" + dm.getDomainName() + "%' AND Id<>'" + dm.getId() + "'") && !dm.isNoDomain()) {
            msg = "域名已经被占用";
        } else if (dbToolsService.IsRepeat("dm_DomainName", "IPv4Address", "WHERE IPv4Address LIKE '%" + dm.getIPv4Address() + "%' AND Id<>'" + dm.getId() + "'") && StrUtil.trimNotEmpty(dm.getIPv4Address())) {
            msg = "IPv4地址已经被占用";
        } else if (!"".equals(StrUtil.toStr(dm.getIPv6Address())) && dbToolsService.IsRepeat("dm_DomainName", "IPv6Address", "WHERE IPv6Address LIKE '%" + dm.getIPv6Address() + "%' AND Id<>'" + dm.getId() + "'") && StrUtil.trimNotEmpty(dm.getIPv6Address())) {
            msg = "IPv6地址已经被占用";*/
        } else {
            msg = "OK";
        }
        return msg;
    }

    /**
     * 验证域名认领
     *
     * @param dm
     * @return
     */
    public String VerifyClaim(DomainName dm) {
        String msg = "OK";
        DomainName dm1 = findById(dm.getId());
        if (dm1 != null) {
            if (dm1.isGLYClaimState()) {
                msg = "域名已被认领,不能重复认领";
            }
        }
        if (!StrUtil.trimNotEmpty(dm.getId())) {
            msg = "请选择要操作的记录";
        } else if (!StrUtil.trimNotEmpty(dm.getWebSiteFullName())) {
            msg = "网站、系统名称不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getSysFLCode()) || !StrUtil.trimNotEmpty(dm.getSysFLChildCode())) {
            msg = "请选择正确的系统分类";
        } else if (!StrUtil.trimNotEmpty(dm.getOpenDate()) || !VeDate.isDate(dm.getOpenDate())) {
            msg = "请填写或选择正确的开通日期";
        } else if (!StrUtil.trimNotEmpty(dm.getKFFCode())) {
            msg = "请选择开发方";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYCode()) || !StrUtil.trimNotEmpty(dm.getGLYName())) {
            msg = "系统读取不到用户的登录信息,请重新登录";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYMobile())) {
            msg = "管理员手机号码不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYIdType())) {
            msg = "请选择管理员证件类型";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYIdNumber())) {
            msg = "管理员证件号不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYIdImgPath1())) {
            msg = "请上传管理员证件（正面）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYIdImgPath2())) {
            msg = "请上传管理员证件（反面）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYIdImgPath3())) {
            msg = "请上传管理员证件（手持）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getECPCode()) || !StrUtil.trimNotEmpty(dm.getECPName())) {
            msg = "请选择正确的应急联络人";
        } else if (!StrUtil.trimNotEmpty(dm.getECPMobile())) {
            msg = "应急联络人手机号码不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getECPIdType())) {
            msg = "请选择应急联络人证件类型";
        } else if (!StrUtil.trimNotEmpty(dm.getECPIdNumber())) {
            msg = "应急联络人证件号不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getECPIdImgPath1())) {
            msg = "请上传应急联络人证件（正面）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getECPIdImgPath2())) {
            msg = "请上传应急联络人证件（反面）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getECPIdImgPath3())) {
            msg = "请上传应急联络人证件（手持）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getApprovalCode()) || !StrUtil.trimNotEmpty(dm.getApprovalName())) {
            msg = "请选择信息审批员";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYMobile())) {
            msg = "信息审批员手机号码不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getFZRCode()) || !StrUtil.trimNotEmpty(dm.getFZRName())) {
            msg = "请选择部门领导";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYMobile())) {
            msg = "部门领导手机号码不能为空";
        }
        return msg;
    }

    /**
     * 验证域名认领-修改
     *
     * @param dm
     * @return
     */
    public String VerifyClaimEdit(DomainName dm) {
        String msg = "OK";
        if (!StrUtil.trimNotEmpty(dm.getId())) {
            msg = "请选择要操作的记录";
        } else if (!StrUtil.trimNotEmpty(dm.getWebSiteFullName())) {
            msg = "网站、系统名称不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getSysFLCode()) || !StrUtil.trimNotEmpty(dm.getSysFLChildCode())) {
            msg = "请选择正确的系统分类";
        } else if (!StrUtil.trimNotEmpty(dm.getOpenDate()) || !VeDate.isDate(dm.getOpenDate())) {
            msg = "请填写或选择正确的开通日期";
        } else if (!StrUtil.trimNotEmpty(dm.getKFFCode())) {
            msg = "请选择开发方";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYCode()) || !StrUtil.trimNotEmpty(dm.getGLYName())) {
            msg = "系统读取不到用户的登录信息,请重新登录";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYMobile())) {
            msg = "管理员手机号码不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYIdType())) {
            msg = "请选择管理员证件类型";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYIdNumber())) {
            msg = "管理员证件号不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYIdImgPath1())) {
            msg = "请上传管理员证件（正面）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYIdImgPath2())) {
            msg = "请上传管理员证件（反面）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYIdImgPath3())) {
            msg = "请上传管理员证件（手持）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getECPCode()) || !StrUtil.trimNotEmpty(dm.getECPName())) {
            msg = "请选择正确的应急联络人";
        } else if (!StrUtil.trimNotEmpty(dm.getECPMobile())) {
            msg = "应急联络人手机号码不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getECPIdType())) {
            msg = "请选择应急联络人证件类型";
        } else if (!StrUtil.trimNotEmpty(dm.getECPIdNumber())) {
            msg = "应急联络人证件号不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getECPIdImgPath1())) {
            msg = "请上传应急联络人证件（正面）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getECPIdImgPath2())) {
            msg = "请上传应急联络人证件（反面）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getECPIdImgPath3())) {
            msg = "请上传应急联络人证件（手持）图片";
        } else if (!StrUtil.trimNotEmpty(dm.getApprovalCode()) || !StrUtil.trimNotEmpty(dm.getApprovalName())) {
            msg = "请选择信息审批员";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYMobile())) {
            msg = "信息审批员手机号码不能为空";
        } else if (!StrUtil.trimNotEmpty(dm.getFZRCode()) || !StrUtil.trimNotEmpty(dm.getFZRName())) {
            msg = "请选择部门领导";
        } else if (!StrUtil.trimNotEmpty(dm.getGLYMobile())) {
            msg = "部门领导手机号码不能为空";
        }
        return msg;
    }


    /**
     * 导出数据到Excel文件
     *
     * @param os
     * @param Disable
     * @param DeleteMark
     * @param CallMark
     * @param fField
     * @param fValue
     * @throws Exception
     */
    public void ExportToExcel(OutputStream os, Boolean Disable, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) throws Exception {
        String titleName = "VIEW_DM_DomainName";
        String[] fFieldStr = new String[]{"DomainName", "Url", "IPv4Address", "IPv6Address", "WebSiteFullName"
                , "BusinessTypeName", "KFFName", "GLDepartmentName", "GLYName"
                , "GLYMobile", "GLYPhone", "GLYEmail", "FZRName"
                , "FZRMobile", "FZRPhone", "FZREmail", "SignInInterval", "RenewInterval"
                , "Remarks", "disable"};
        String[] fFieldFullName = new String[]{"域名名称", "访问地址", "IPv4地址", "IPv6地址", "网站、业务系统名称"
                , "业务类型", "开发方", "管理部门", "管理员姓名"
                , "管理员手机号码", "管理员电话号码", "管理员Email", "负责人姓名"
                , "负责人手机号码", "负责人电话号码", "负责人Email", "签到间隔天数", "续订间隔天数"
                , "备注", "停用状态"};
        int[] ColumnWidth = new int[]{30, 30, 30, 30, 30
                , 10, 10, 10, 10
                , 10, 10, 10, 10
                , 10, 10, 10, 10, 10
                , 10, 10};
        StringBuilder sb1 = new StringBuilder();
        if (Disable != null) {
            sb1.append("Disable=").append(Disable);
        }
        if (CallMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("CallMark=").append(CallMark);
        }
        if (DeleteMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" DeleteMark=").append(DeleteMark);
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(DomainName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Url LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv4Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IPv6Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR BusinessTypeName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR KFFName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLDepartmentName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR GLYEmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRMobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZRPhone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FZREmail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        String orderByStr = "SortCode ASC";
        String ExcelHeaderName = "域名记录";
        dbToolsService.ExportToExcel(os, titleName, fFieldStr, fFieldFullName, ColumnWidth, sb1.toString(), orderByStr, ExcelHeaderName);
    }

    /**
     * JSCode转JSFullName
     *
     * @param code
     * @return
     */
    public String JSCodeToJSFullName(String code) {
        return dbToolsService.GetFieldValue("VIEW_DM_JSData", "FullName", "Code='" + code + "'");
    }

    /**
     * JSFullName转JSCode
     *
     * @param fullName
     * @return
     */
    public String JSFullNameToJSCode(String fullName) {
        return dbToolsService.GetFieldValue("VIEW_DM_JSData", "Code", "FullName='" + fullName + "'");
    }

    /**
     * 获取排序号最大值加1
     *
     * @return
     */
    public int GetSortCodeMaxAdd1() {
        String sSql1 = "SELECT MAX(SortCode) as RowCount FROM dm_DomainName";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if (objects != null) {
            if (objects.length > 0) {
                if (objects[0] != null) {
                    return Integer.parseInt(objects[0].toString()) + 1;
                }
            }
        }
        return 0;
    }

    public int StrToTTL(String val) {
        String tmpVal1 = StrUtil.toStr(val);
        if ("".equals(tmpVal1)) {
            return 3600;
        } else {
            return Integer.parseInt(tmpVal1);
        }
    }

    //教师导入 fileName 源文件
    public String DomainNameImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        int RowCount = 0;
        int YesCount = 0;
        int ErrCount = 0;
        StringBuilder sb_ipv4 = new StringBuilder();
        StringBuilder sb_ipv6 = new StringBuilder();
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = workbook.getSheet(0);

            System.out.println("记录数：" + sheet.getRows());
            for (int i1 = 2; i1 < sheet.getRows(); i1++) {
                RowCount++;
                Cell cell1 = sheet.getCell(1, i1); //域名
                Cell cell2 = sheet.getCell(2, i1); //访问地址
                Cell cell3 = sheet.getCell(3, i1); //IPv4地址
                Cell cell4 = sheet.getCell(4, i1); //IPv6地址

                Cell cell_TTL = sheet.getCell(5, i1); //TTL
                Cell cell_RecordType = sheet.getCell(6, i1); //记录类型
                Cell cell_RecordVal = sheet.getCell(7, i1); //记录值

                Cell cell5 = sheet.getCell(8, i1); //网站、业务系统名称
                Cell cell6 = sheet.getCell(9, i1); //签到间隔天数
                Cell cell7 = sheet.getCell(10, i1); //续订间隔天数
                Cell cell8 = sheet.getCell(11, i1); //备注
                if (!"".equals(cell1.getContents()) || !"".equals(cell2.getContents()) || !"".equals(cell3.getContents())) {
                    DomainName dm1 = new DomainName();
                    dm1.setId(StrUtil.GetUUID());
                    dm1.setDomainName(StrUtil.toStr(cell1.getContents()));
                    dm1.setUrl(StrUtil.toStr(cell2.getContents()));
                    dm1.setIPv4Address(StrUtil.toStr(cell3.getContents()));
                    dm1.setIPv6Address(StrUtil.toStr(cell4.getContents()));
                    dm1.setTTL(StrToTTL(cell_TTL.getContents()));
                    dm1.setRecordType(StrUtil.toStr(cell_RecordType.getContents()));
                    dm1.setRecordVal(StrUtil.toStr(cell_RecordVal.getContents()));
                    dm1.setWebSiteFullName(StrUtil.toStr(cell5.getContents()));
                    int SignInInterval1 = Integer.parseInt(StrUtil.toStr(cell6.getContents()));
                    if (SignInInterval1 > 0) {
                        dm1.setSignInInterval(SignInInterval1);
                    } else {
                        dm1.setSignInInterval(90);
                    }
                    int RenewInterval1 = Integer.parseInt(StrUtil.toStr(cell7.getContents()));
                    if (RenewInterval1 > 0) {
                        dm1.setRenewInterval(RenewInterval1);
                    } else {
                        dm1.setRenewInterval(90);
                    }
                    dm1.setRemarks(StrUtil.toStr(cell8.getContents()));
                    dm1.setSortCode(dbToolsService.GetFieldMaxVal("dm_DomainName", "SortCode") + 1);
                    dm1.setCreateDate(CreateDate);
                    dm1.setCreateUserCode(CreateUserCode);
                    dm1.setCreateUserName(CreateUserName);
                    String msg = VerifyImport(dm1);
                    if ("OK".equals(msg)) {
                        String RValue = add(dm1);
                        if ("OK".equals(RValue)) {
                            if (!"".equals(dm1.getIPv4Address())) {
                                iPv4Service.BatchAdd(dm1.getIPv4Address(), new Date(), CreateUserCode, CreateUserName);
                                iPv4Service.BatchSetState(dm1.getIPv4Address(), true);
                            }
                            if (!"".equals(dm1.getIPv6Address())) {
                                iPv6Service.BatchAdd(dm1.getIPv6Address(), new Date(), CreateUserCode, CreateUserName);
                                iPv6Service.BatchSetState(dm1.getIPv6Address(), true);
                            }
                            YesCount++;
                        } else {
                            ErrCount++;
                        }
                    } else {
                        ErrCount++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "导入失败,源文件不正确";
        }
        if (YesCount == RowCount) {
            return "OK";
        } else {
            return "导入结束,共导入" + RowCount + "条,有" + YesCount + "条导入成功,有" + ErrCount + "条导入失败.";
        }

    }
}
