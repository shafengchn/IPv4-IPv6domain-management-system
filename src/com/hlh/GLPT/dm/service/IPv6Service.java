package com.hlh.GLPT.dm.service;

import com.hlh.GLPT.dm.dao.IPv6Dao;
import com.hlh.GLPT.dm.domain.IPv6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;


/**
 * Package: com.hlh.GLPT.dm.service
 * User: 黄良辉  15-6-4  上午9:12
 */
@Service
public class IPv6Service {
    @Autowired
    private IPv6Dao iPv6Dao;

    /**
     * 添加
     *
     * @param iPv6
     * @return
     */
    public String add(IPv6 iPv6) {
        return iPv6Dao.add(iPv6);
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
        return iPv6Dao.BatchAdd(addressRang, CreateDate, CreateUserCode, CreateUserName);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return iPv6Dao.ThoroughDel(Id);
    }

    /**
     * 修改
     *
     * @param iPv6
     * @return
     */
    public String edit(IPv6 iPv6) {
        return iPv6Dao.edit(iPv6);
    }

    /**
     * 标识状态
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetState(int Id, boolean MarkVal) {
       return iPv6Dao.SetState(Id, MarkVal);
    }

    /**
     * 批量设置标识状态
     *
     * @param IPAddressRang       IP地址范围
     * @param MarkVal 标识值
     * @return
     */
    public String BatchSetState(String IPAddressRang, boolean MarkVal) {
        return iPv6Dao.BatchSetState(IPAddressRang, MarkVal);
    }

    /**
     * 标识删除
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetDeleteMark(int Id, boolean MarkVal) {
        return iPv6Dao.SetDeleteMark(Id, MarkVal);
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(int Id, boolean MarkVal) {
        return iPv6Dao.SetCallMark(Id, MarkVal);
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public IPv6 findById(int Id) {
        return iPv6Dao.findById(Id);
    }

    /**
     * 按IPv6地址查询单条记录
     *
     * @param Address
     * @return
     */
    public IPv6 findByAddress(String Address) {
        return iPv6Dao.findByAddress(Address);
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
    public String findByPage(Boolean State, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue, int start, int number) {
        return iPv6Dao.findByPage(State, Enabled, DeleteMark, CallMark, fField, fValue, start, number);
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
    public int GetRowCount(Boolean State, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) {
        return iPv6Dao.GetRowCount(State, Enabled, DeleteMark, CallMark, fField, fValue);
    }

    /**
     * 验证添加
     *
     * @param iPv6
     * @return
     */
    public String VerifyAdd(IPv6 iPv6) {
        return iPv6Dao.VerifyAdd(iPv6);
    }

    /**
     * 验证修改
     *
     * @param iPv6
     * @return
     */
    public String VerifyEdit(IPv6 iPv6) {
        return iPv6Dao.VerifyEdit(iPv6);
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
        iPv6Dao.ExportToExcel(os, State, Enabled, DeleteMark, CallMark, fField, fValue);
    }

    //导入 fileName 源文件
    public String IPv6Import(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
            return iPv6Dao.IPv6Import(fileName, CreateDate, CreateUserCode, CreateUserName);

    }
}
