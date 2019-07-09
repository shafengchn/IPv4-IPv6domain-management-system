package com.hlh.GLPT.dm.service;

import com.hlh.GLPT.dm.dao.DevelopingPartyDao;
import com.hlh.GLPT.dm.domain.DevelopingParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;

/**
 * Package: com.hlh.GLPT.dm.service
 * User: 黄良辉  15-6-4  下午1:48
 */
@Service
public class DevelopingPartyService {
    @Autowired
    private DevelopingPartyDao dPartyDao;


    /**
     * 添加
     *
     * @param dParty
     * @return
     */
    public String add(DevelopingParty dParty) {
        return dPartyDao.add(dParty);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return dPartyDao.ThoroughDel(Id);
    }

    /**
     * 修改
     *
     * @param dParty
     * @return
     */
    public String edit(DevelopingParty dParty) {
        return dPartyDao.edit(dParty);
    }

    /**
     * 标识删除
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetDeleteMark(int Id, boolean MarkVal) {
        return dPartyDao.SetDeleteMark(Id, MarkVal);
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(int Id, boolean MarkVal) {
        return dPartyDao.SetCallMark(Id, MarkVal);
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public DevelopingParty findById(int Id) {
        return dPartyDao.findById(Id);
    }

    /**
     * 按Code查询单条记录
     *
     * @param Code
     * @return
     */
    public DevelopingParty findByCode(String Code) {
        return dPartyDao.findByCode(Code);
    }

    /**
     * 查询记录 分页
     *
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @param start      开始记录
     * @param number     记录数
     * @return
     */
    public String findByPage(Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue, int start, int number) {
        return dPartyDao.findByPage(Enabled, DeleteMark, CallMark, fField, fValue, start, number);
    }

    /**
     * 查询记录数
     *
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @return
     */
    public int GetRowCount(Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) {
        return dPartyDao.GetRowCount(Enabled, DeleteMark, CallMark, fField, fValue);
    }

    /**
     * 查询记录 分页
     *
     * @param UserCode   用户代码
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @param start      开始记录
     * @param number     记录数
     * @return
     */
    public String findCurrUserByPage(String UserCode, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue, int start, int number) {
        return dPartyDao.findCurrUserByPage(UserCode, Enabled, DeleteMark, CallMark, fField, fValue, start, number);
    }

    /**
     * 查询记录数
     *
     * @param UserCode   用户代码
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @return
     */
    public int GetCurrUserRowCount(String UserCode,Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) {
        return dPartyDao.GetCurrUserRowCount(UserCode, Enabled, DeleteMark, CallMark, fField, fValue);
    }

    /**
     * 验证添加
     *
     * @param dParty
     * @return
     */
    public String VerifyAdd(DevelopingParty dParty) {
        return dPartyDao.VerifyAdd(dParty);
    }

    /**
     * 验证修改
     *
     * @param dParty
     * @return
     */
    public String VerifyEdit(DevelopingParty dParty) {
        return dPartyDao.VerifyEdit(dParty);
    }

    /**
     * Code转fullName
     *
     * @param code
     * @return
     */
    public String CodeToFullName(String code) {
          return dPartyDao.CodeToFullName(code);
    }

    /**
     * fullName转Code
     *
     * @param fullName
     * @return
     */
    public String FullNameToCode(String fullName) {
        return dPartyDao.FullNameToCode(fullName);
    }

    /**
     * 导出数据到Excel文件
     *
     * @param os
     * @param Enabled
     * @param DeleteMark
     * @param CallMark
     * @param fField
     * @param fValue
     * @throws Exception
     */
    public void ExportToExcel(OutputStream os, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) throws Exception {
        dPartyDao.ExportToExcel(os, Enabled, DeleteMark, CallMark, fField, fValue);
    }

    //导入 fileName 源文件
    public String DevelopingPartyImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        return dPartyDao.DevelopingPartyImport(fileName, CreateDate, CreateUserCode, CreateUserName);
    }
}
