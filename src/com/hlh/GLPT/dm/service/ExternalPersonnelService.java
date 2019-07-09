package com.hlh.GLPT.dm.service;

import com.hlh.GLPT.dm.dao.ExternalPersonnelDao;
import com.hlh.GLPT.dm.domain.ExternalPersonnel;
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
 * User: 黄良辉  15-6-4  下午7:53
 */
@Service
public class ExternalPersonnelService {
    @Autowired
    private ExternalPersonnelDao ePersonnelDao;

    /**
     * 添加
     *
     * @param ePersonnel
     * @return
     */
    public String add(ExternalPersonnel ePersonnel) {
        return ePersonnelDao.add(ePersonnel);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
     return ePersonnelDao.ThoroughDel(Id);
    }

    /**
     * 修改
     *
     * @param ePersonnel
     * @return
     */
    public String edit(ExternalPersonnel ePersonnel) {
        return ePersonnelDao.edit(ePersonnel);
    }

    /**
     * 标识删除
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetDeleteMark(int Id, boolean MarkVal) {
          return ePersonnelDao.SetDeleteMark(Id, MarkVal);
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(int Id, boolean MarkVal) {
       return ePersonnelDao.SetCallMark(Id, MarkVal);
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public ExternalPersonnel findById(int Id) {
        return ePersonnelDao.findById(Id);
    }

    /**
     * 按Code查询单条记录
     *
     * @param Code
     * @return
     */
    public ExternalPersonnel findByCode(String Code) {
       return ePersonnelDao.findByCode(Code);
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
       return ePersonnelDao.findByPage(Enabled, DeleteMark, CallMark, fField, fValue, start, number);
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
       return ePersonnelDao.GetRowCount(Enabled, DeleteMark, CallMark, fField, fValue);
    }

    /**
     * 验证添加
     *
     * @param ePersonnel
     * @return
     */
    public String VerifyAdd(ExternalPersonnel ePersonnel) {
         return ePersonnelDao.VerifyAdd(ePersonnel);
    }

    /**
     * 验证修改
     *
     * @param ePersonnel
     * @return
     */
    public String VerifyEdit(ExternalPersonnel ePersonnel) {
       return ePersonnelDao.VerifyEdit(ePersonnel);
    }

    /**
     * Code转fullName
     *
     * @param code
     * @return
     */
    public String CodeToFullName(String code) {
         return ePersonnelDao.CodeToFullName(code);
    }

    /**
     * fullName转Code
     *
     * @param fullName
     * @return
     */
    public String FullNameToCode(String fullName) {
        return ePersonnelDao.FullNameToCode(fullName);
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
        ePersonnelDao.ExportToExcel(os, Enabled, DeleteMark, CallMark, fField, fValue);
    }

    //导入 fileName 源文件
    public String ExternalPersonnelImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        return ePersonnelDao.ExternalPersonnelImport(fileName, CreateDate, CreateUserCode, CreateUserName);
    }
}
