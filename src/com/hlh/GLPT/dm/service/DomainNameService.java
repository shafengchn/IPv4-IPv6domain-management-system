package com.hlh.GLPT.dm.service;

import com.hlh.GLPT.dm.dao.DomainNameDao;
import com.hlh.GLPT.dm.domain.DomainName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Package: com.hlh.GLPT.dm.service
 * User: 黄良辉  15-6-8  上午10:15
 */
@Service
public class DomainNameService {
    @Autowired
    private DomainNameDao dmDao;

    /**
     * 添加
     *
     * @param dm
     * @return
     */
    public String add(DomainName dm) {
        return dmDao.add(dm);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(String Id) {
        return dmDao.ThoroughDel(Id);
    }

    /**
     * 修改
     *
     * @param dm
     * @return
     */
    public String edit(DomainName dm) {
        return dmDao.edit(dm);
    }

    /**
     * 域名认领
     *
     * @param dm
     * @return
     */
    public String ClaimOK(DomainName dm) {
        return dmDao.ClaimOK(dm);
    }

    /**
     * 域名认领 - 修改
     *
     * @param dm
     * @return
     */
    public String ClaimEditOK(DomainName dm) {
        return dmDao.ClaimEditOK(dm);
    }

    /**
     * 取消域名认领
     *
     * @param Id
     * @return
     */
    public String CancelClaimOK(String Id) {
        return dmDao.CancelClaimOK(Id);
    }

    /**
     * 标识删除
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetDeleteMark(String Id, boolean MarkVal) {
        return dmDao.SetDeleteMark(Id, MarkVal);
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
        return dmDao.SetDisable(Id, Disable, DisableUserCode, DisableTime, DisableContent);
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(String Id, boolean MarkVal) {
        return dmDao.SetCallMark(Id, MarkVal);
    }

    /**
     * 设置最后签到时间
     *
     * @param Id           ID
     * @param LastSignTime 最后签到时间
     * @return
     */
    public String SetLastSignTime(String Id, String LastSignTime) {
        return dmDao.SetLastSignTime(Id, LastSignTime);
    }

    /**
     * 设置最后续订时间
     *
     * @param Id            ID
     * @param LastRenewTime 最后续订时间
     * @return
     */
    public String SetLastRenewTime(String Id, String LastRenewTime) {
        return dmDao.SetLastRenewTime(Id, LastRenewTime);
    }


    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public DomainName findById(String Id) {
        return dmDao.findById(Id);
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
        return dmDao.findByPage(Disable, DeleteMark, CallMark, fField, fValue, start, number);
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
        return dmDao.findByPageEx(GLYClaimState, fField, fValue, start, number);
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
        return dmDao.GetRowCount(Disable, DeleteMark, CallMark, fField, fValue);
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
        return dmDao.GetRowCountEx(GLYClaimState, fField, fValue);
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
        return dmDao.findGLYCodeByPage(GLYCode, fField, fValue, start, number);
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
        return dmDao.GetGLYCodeRowCount(GLYCode, fField, fValue);
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
        return dmDao.findNoSignRecordByPage(GLYCode, fField, fValue, start, number);
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
        return dmDao.GetNoSignRecordRowCount(GLYCode, fField, fValue);
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
        return dmDao.findNoRenewRecordByPage(GLYCode, fField, fValue, start, number);
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
        return dmDao.GetNoRenewRecordRowCount(GLYCode, fField, fValue);
    }


    /**
     * 验证添加
     *
     * @param dm
     * @return
     */
    public String VerifyAdd(DomainName dm) {
        return dmDao.VerifyAdd(dm);
    }

    /**
     * 验证修改
     *
     * @param dm
     * @return
     */
    public String VerifyEdit(DomainName dm) {
        return dmDao.VerifyEdit(dm);
    }

    /**
     * 验证域名认领
     *
     * @param dm
     * @return
     */
    public String VerifyClaim(DomainName dm) {
        return dmDao.VerifyClaim(dm);
    }


    /**
     * 验证域名认领-修改
     *
     * @param dm
     * @return
     */
    public String VerifyClaimEdit(DomainName dm) {
        return dmDao.VerifyClaimEdit(dm);
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
        dmDao.ExportToExcel(os, Disable, DeleteMark, CallMark, fField, fValue);
    }

    /**
     * GLYCode转GLYFullName
     *
     * @param code
     * @return
     */
    public String JSCodeToJSFullName(String code) {
        return dmDao.JSCodeToJSFullName(code);
    }

    /**
     * GLYFullName转GLYCode
     *
     * @param fullName
     * @return
     */
    public String JSFullNameToJSCode(String fullName) {
        return dmDao.JSFullNameToJSCode(fullName);
    }

    /**
     * 获取排序号最大值加1
     *
     * @return
     */
    public int GetSortCodeMaxAdd1() {
        return dmDao.GetSortCodeMaxAdd1();
    }

    //教师导入 fileName 源文件
    public String DomainNameImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        return dmDao.DomainNameImport(fileName, CreateDate, CreateUserCode, CreateUserName);
    }
}
