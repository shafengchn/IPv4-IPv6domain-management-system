package com.hlh.GLPT.dm.dao;

import com.hlh.GLPT.dm.domain.RenewRecord;
import com.hlh.common.domain.DataPage;
import com.hlh.common.service.DataPageService;
import com.hlh.common.service.DbToolsService;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import com.hlh.util.jsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 业务类型
 * Package: com.hlh.GLPT.dm.dao
 * User: 黄良辉  15-6-3  下午10:27
 */
@Repository
public class RenewRecordDao {
    @Autowired
    private DbToolsService dbToolsService;
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private DataPageService dataPageService;

    /**
     * 添加
     *
     * @param rRecord
     * @return
     */
    public String add(RenewRecord rRecord) {
        String[] fField = new String[]{"DomainNameId", "RenewDate", "RenewTime", "RenewPeopleType", "RenewCode"
                , "RenewContent", "Remarks"};
        Object[] fVal = new Object[]{rRecord.getDomainNameId(),rRecord.getRenewDate(),rRecord.getRenewTime(),rRecord.getRenewPeopleType(),rRecord.getRenewCode()
        ,rRecord.getRenewContent(),rRecord.getRemarks()};
        return dbToolsService.add("dm_RenewRecord", fField, fVal);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return dbToolsService.ThoroughDel("dm_RenewRecord", "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public RenewRecord findById(int Id) {
        String sqlStr = "SELECT * FROM dm_RenewRecord WHERE Id=?";
        return dbUtilsTemplate.findFirst(RenewRecord.class, sqlStr, Id);
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
    public String findByPage(String DomainNameId, String RenewDate,String RenewCode,String RenewPeopleType, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("dm_RenewRecord");
        dataPage1.setColumns("Id,DomainNameId,RenewDate,RenewTime,RenewPeopleType,RenewCode,RenewContent,Remarks,(SELECT RealName FROM Core_User WHERE Code=dm_RenewRecord.RenewCode) as RealName");
        String[] strings1 = new String[]{"Id", "DomainNameId", "RenewDate", "RenewTime", "RenewPeopleType", "RenewCode", "RenewContent","Remarks","RealName"};
        dataPage1.setOrder_field("RenewDate ASC");
        dataPage1.setGroup_field("");
        dataPage1.setCurrPage(start);
        dataPage1.setPageSize(number);
        StringBuilder sb1 = new StringBuilder();
        if (DomainNameId != null) {
            sb1.append(" DomainNameId='").append(DomainNameId).append("'");
        }
        if (RenewDate != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" RenewDate='").append(RenewDate).append("'");
        }
        if(RenewCode!=null){
            if(sb1.length()>0){
              sb1.append(" AND ");
            }
            sb1.append(" RenewCode='").append(RenewCode).append("'");
        }
        if(RenewPeopleType!=null){
            if(sb1.length()>0){
                sb1.append(" AND ");
            }
            sb1.append(" RenewPeopleType='").append(RenewPeopleType).append("'");
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(RenewContent LIKE '%").append(fValue).append("%'");
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
     * @param RenewDate 删除标识
     * @param RenewCode   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @return
     */
    public int GetRowCount(String DomainNameId, String RenewDate,String RenewCode,String RenewPeopleType, String fField, String fValue) {
        StringBuilder sb1 = new StringBuilder();
        if (DomainNameId != null) {
            sb1.append(" DomainNameId='").append(DomainNameId).append("'");
        }
        if (RenewDate != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" RenewDate='").append(RenewDate).append("'");
        }
        if(RenewCode!=null){
            if(sb1.length()>0){
                sb1.append(" AND ");
            }
            sb1.append(" RenewCode='").append(RenewCode).append("'");
        }
        if(RenewPeopleType!=null){
            if(sb1.length()>0){
                sb1.append(" AND ");
            }
            sb1.append(" RenewPeopleType='").append(RenewPeopleType).append("'");
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(RenewContent LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "dm_RenewRecord", sb1.toString());
    }

    /**
     * 验证添加
     *
     * @param rRecord
     * @return
     */
    public String VerifyAdd(RenewRecord rRecord) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(rRecord.getDomainNameId())) {
            msg = "请选择域名";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /**
     * 验证修改
     *
     * @param rRecord
     * @return
     */
    public String VerifyEdit(RenewRecord rRecord) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(rRecord.getDomainNameId())) {
            msg = "请选择域名";
        } else {
            msg = "OK";
        }
        return msg;
    }


}
