package com.hlh.GLPT.dm.service;

import com.hlh.GLPT.dm.dao.BusinessTypeDao;
import com.hlh.GLPT.dm.domain.BusinessType;
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
 * User: 黄良辉  15-6-4  下午8:29
 */
@Service
public class BusinessTypeService {
    @Autowired
    private BusinessTypeDao bTypeDao;


    /**
     * 添加
     *
     * @param bType
     * @return
     */
    public String add(BusinessType bType) {
        return bTypeDao.add(bType);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
         return bTypeDao.ThoroughDel(Id);
    }

    /**
     * 修改
     *
     * @param bType
     * @return
     */
    public String edit(BusinessType bType) {
        return bTypeDao.edit(bType);
    }

    /**
     * 标识删除
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetDeleteMark(int Id, boolean MarkVal) {
       return bTypeDao.SetDeleteMark(Id, MarkVal);
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(int Id, boolean MarkVal) {
       return bTypeDao.SetCallMark(Id, MarkVal);
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public BusinessType findById(int Id) {
        return bTypeDao.findById(Id);
    }

    /**
     * 按代码查询单条记录
     *
     * @param Code
     * @return
     */
    public BusinessType findByCode(String Code) {
       return bTypeDao.findByCode(Code);
    }

    /**
     * 查询有效的业务类型信息
     * @param ShowFieldName
     * @param strings
     * @return
     */
    public String findAllEx(String ShowFieldName, String[] strings) {
        return bTypeDao.findAllEx(ShowFieldName, strings);
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
          return bTypeDao.findByPage(Enabled, DeleteMark, CallMark, fField, fValue, start, number);
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
       return bTypeDao.GetRowCount(Enabled, DeleteMark, CallMark, fField, fValue);
    }

    /**
     * 验证添加
     *
     * @param bType
     * @return
     */
    public String VerifyAdd(BusinessType bType) {
         return bTypeDao.VerifyAdd(bType);
    }

    /**
     * 验证修改
     *
     * @param bType
     * @return
     */
    public String VerifyEdit(BusinessType bType) {
      return bTypeDao.VerifyEdit(bType);
    }

    /**
     * Code转fullName
     *
     * @param code
     * @return
     */
    public String CodeToFullName(String code) {
      return bTypeDao.CodeToFullName(code);
    }

    /**
     * fullName转Code
     *
     * @param fullName
     * @return
     */
    public String FullNameToCode(String fullName) {
      return bTypeDao.FullNameToCode(fullName);
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
        bTypeDao.ExportToExcel(os, Enabled, DeleteMark, CallMark, fField, fValue);
    }

    //导入 fileName 源文件
    public String BusinessTypeImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        return bTypeDao.BusinessTypeImport(fileName, CreateDate, CreateUserCode, CreateUserName);
    }
}
