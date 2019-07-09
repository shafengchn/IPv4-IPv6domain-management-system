package com.hlh.GLPT.dm.dao;

import com.hlh.GLPT.dm.domain.SignRecord;
import com.hlh.common.domain.DataPage;
import com.hlh.common.service.DataPageService;
import com.hlh.common.service.DbToolsService;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import com.hlh.util.jsonUtil;
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
 * 业务类型
 * Package: com.hlh.GLPT.dm.dao
 * User: 黄良辉  15-6-3  下午10:27
 */
@Repository
public class SignRecordDao {
    @Autowired
    private DbToolsService dbToolsService;
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private DataPageService dataPageService;

    /**
     * 添加
     *
     * @param sRecord
     * @return
     */
    public String add(SignRecord sRecord) {
        String[] fField = new String[]{"DomainNameId", "SignDate", "SignTime", "SignPeopleType", "SignCode"
                , "SignContent", "Remarks"};
        Object[] fVal = new Object[]{sRecord.getDomainNameId(),sRecord.getSignDate(),sRecord.getSignTime(),sRecord.getSignPeopleType(),sRecord.getSignCode()
        ,sRecord.getSignContent(),sRecord.getRemarks()};
        return dbToolsService.add("dm_SignRecord", fField, fVal);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return dbToolsService.ThoroughDel("dm_SignRecord", "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public SignRecord findById(int Id) {
        String sqlStr = "SELECT * FROM dm_SignRecord WHERE Id=?";
        return dbUtilsTemplate.findFirst(SignRecord.class, sqlStr, Id);
    }

     /**
     * 查询记录 分页
     *
     * @param fField     查询字段
     * @param fValue     查询值
     * @param start      开始记录
     * @param number     记录数
     * @return
     */
    public String findByPage(String DomainNameId, String SignDate,String SignCode,String SignPeopleType, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("dm_SignRecord");
        dataPage1.setColumns("Id,DomainNameId,SignDate,SignTime,SignPeopleType,SignCode,SignContent,Remarks,(SELECT RealName FROM Core_User WHERE Code=dm_SignRecord.SignCode) as RealName");
        String[] strings1 = new String[]{"Id", "DomainNameId", "SignDate", "SignTime", "SignPeopleType", "SignCode", "SignContent","Remarks","RealName"};
        dataPage1.setOrder_field("SignDate ASC");
        dataPage1.setGroup_field("");
        dataPage1.setCurrPage(start);
        dataPage1.setPageSize(number);
        StringBuilder sb1 = new StringBuilder();
        if (DomainNameId != null) {
            sb1.append(" DomainNameId='").append(DomainNameId).append("'");
        }
        if (SignDate != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" SignDate='").append(SignDate).append("'");
        }
        if(SignCode!=null){
            if(sb1.length()>0){
              sb1.append(" AND ");
            }
            sb1.append(" SignCode='").append(SignCode).append("'");
        }
        if(SignPeopleType!=null){
            if(sb1.length()>0){
                sb1.append(" AND ");
            }
            sb1.append(" SignPeopleType='").append(SignPeopleType).append("'");
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(SignContent LIKE '%").append(fValue).append("%'");
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
     * @param DomainNameId    有效性
     * @param SignDate 删除标识
     * @param SignCode   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @return
     */
    public int GetRowCount(String DomainNameId, String SignDate,String SignCode,String SignPeopleType, String fField, String fValue) {
        StringBuilder sb1 = new StringBuilder();
        if (DomainNameId != null) {
            sb1.append(" DomainNameId='").append(DomainNameId).append("'");
        }
        if (SignDate != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" SignDate='").append(SignDate).append("'");
        }
        if(SignCode!=null){
            if(sb1.length()>0){
                sb1.append(" AND ");
            }
            sb1.append(" SignCode='").append(SignCode).append("'");
        }
        if(SignPeopleType!=null){
            if(sb1.length()>0){
                sb1.append(" AND ");
            }
            sb1.append(" SignPeopleType='").append(SignPeopleType).append("'");
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(SignContent LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "dm_SignRecord", sb1.toString());
    }

    /**
     * 验证添加
     *
     * @param sRecord
     * @return
     */
    public String VerifyAdd(SignRecord sRecord) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(sRecord.getDomainNameId())) {
            msg = "请选择域名";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /**
     * 验证修改
     *
     * @param sRecord
     * @return
     */
    public String VerifyEdit(SignRecord sRecord) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(sRecord.getDomainNameId())) {
            msg = "请选择域名";
        } else {
            msg = "OK";
        }
        return msg;
    }


}
