package com.hlh.GLPT.dm.service;

import com.hlh.GLPT.dm.dao.IPv4Dao;
import com.hlh.GLPT.dm.domain.IPv4;
import com.hlh.common.domain.DataPage;
import com.hlh.util.StrUtil;
import com.hlh.util.jsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Package: com.hlh.GLPT.dm.service
 * User: 黄良辉  15-6-4  上午9:12
 */
@Service
public class IPv4Service {
    @Autowired
    private IPv4Dao iPv4Dao;

    /**
     * 添加
     *
     * @param iPv4
     * @return
     */
    public String add(IPv4 iPv4) {
        return iPv4Dao.add(iPv4);
    }

    /**
     * 批量添加
     * @param addressRang      地址集 用"|"分隔
     * @param CreateDate
     * @param CreateUserCode
     * @param CreateUserName
     * @return
     */
    public int BatchAdd(String addressRang,Date CreateDate, String CreateUserCode, String CreateUserName){
        return iPv4Dao.BatchAdd(addressRang, CreateDate, CreateUserCode, CreateUserName);
    }

    /**
     * 批量添加
     *
     * @param BeginAddress 开始地址
     * @param EndAddress   结束地址
     * @param Enabled      有效性
     * @param Remarks      备注
     * @return 添加的记录数
     */
    public int BatchAdd(String GroupCode,String GroupName, String BeginAddress, String EndAddress, Boolean Enabled, String Remarks, Date CreateDate, String CreateUserCode, String CreateUserName) {
        return iPv4Dao.BatchAdd(GroupCode,GroupName, BeginAddress, EndAddress, Enabled, Remarks, CreateDate, CreateUserCode, CreateUserName);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return iPv4Dao.ThoroughDel(Id);
    }

    /**
     * 修改
     *
     * @param iPv4
     * @return
     */
    public String edit(IPv4 iPv4) {
        return iPv4Dao.edit(iPv4);
    }

    /**
     * 标识状态
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetState(int Id, boolean MarkVal) {
      return iPv4Dao.SetState(Id, MarkVal);
    }

    /**
     * 批量设置标识状态
     *
     * @param IPAddressRang       IP地址范围
     * @param MarkVal 标识值
     * @return
     */
    public String BatchSetState(String IPAddressRang, boolean MarkVal) {
        return iPv4Dao.BatchSetState(IPAddressRang, MarkVal);
    }


    /**
     * 标识删除
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetDeleteMark(int Id, boolean MarkVal) {
        return iPv4Dao.SetDeleteMark(Id, MarkVal);
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(int Id, boolean MarkVal) {
        return iPv4Dao.SetCallMark(Id, MarkVal);
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public IPv4 findById(int Id) {
        return iPv4Dao.findById(Id);
    }

    /**
     * 按IPv4地址查询单条记录
     *
     * @param Address
     * @return
     */
    public IPv4 findByAddress(String Address) {
        return iPv4Dao.findByAddress(Address);
    }

    /**
     * 查询记录 分页
     *
     * @param State      状态
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @param start      开始记录
     * @param number     记录数
     * @return
     */
    public String findByPage(Boolean State,String GroupCode, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue, int start, int number) {
        return iPv4Dao.findByPage(State,GroupCode, Enabled, DeleteMark, CallMark, fField, fValue, start, number);
    }

    /**
     * 查询记录数
     *
     * @param State      状态
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @return
     */
    public int GetRowCount(Boolean State,String GroupCode, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) {
        return iPv4Dao.GetRowCount(State,GroupCode, Enabled, DeleteMark, CallMark, fField, fValue);
    }

    /**
     * 查询记录数
     *
     * @param GroupCode
     * @return
     */
    public int GetRowCount(String GroupCode) {
        return iPv4Dao.GetRowCount(GroupCode);
    }

    /**
     * 验证添加
     *
     * @param iPv4
     * @return
     */
    public String VerifyAdd(IPv4 iPv4) {
        return iPv4Dao.VerifyAdd(iPv4);
    }

    /**
     * 验证修改
     *
     * @param iPv4
     * @return
     */
    public String VerifyEdit(IPv4 iPv4) {
        return iPv4Dao.VerifyEdit(iPv4);
    }

    /**
     * 导出数据到Excel文件
     *
     * @param os
     * @param State
     * @param Enabled
     * @param DeleteMark
     * @param CallMark
     * @param fField
     * @param fValue
     * @throws Exception
     */
    public void ExportToExcel(OutputStream os,Boolean State, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) throws Exception {
        iPv4Dao.ExportToExcel(os, State, Enabled, DeleteMark, CallMark, fField, fValue);
    }


    //导入 fileName 源文件
    public String IPv4Import(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        return iPv4Dao.IPv4Import(fileName, CreateDate, CreateUserCode, CreateUserName);
    }

    /**
     * 批量修改分组
     *
     * @param RowId
     * @param CurrGroupCode
     * @return
     */
    public int SetGroup(String[] RowId,String CurrGroupCode) {
        return iPv4Dao.SetGroup(RowId, CurrGroupCode);
    }
}
