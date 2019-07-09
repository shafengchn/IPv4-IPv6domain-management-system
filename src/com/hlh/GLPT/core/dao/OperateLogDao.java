package com.hlh.GLPT.core.dao;

import com.hlh.GLPT.core.domain.OperateLog;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.jsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-3-14
 * Time: 下午7:44
 */
@Repository
public class OperateLogDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /*
    * 增加
    * */
    public String add(OperateLog operateLog) {
        String sqlStr = "INSERT INTO sys_OperateLog (OperateDate,OperateIP,OperateUserName,OperateContent)" +
                " VALUES(?,?,?,?)";
        Object[] objects = new Object[]{operateLog.getOperateDate(),operateLog.getOperateIP(),operateLog.getOperateUserName(),operateLog.getOperateContent()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(int ID) {
        String sqlStr = "DELETE FROM sys_OperateLog  WHERE ID=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{ID});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
    * 按Id查询
    * */
    public OperateLog findById(String ID) {
        String sqlStr = "SELECT * FROM sys_OperateLog WHERE ID=?";
        return dbUtilsTemplate.findFirst(OperateLog.class, sqlStr, ID);
    }

    //获取总行数
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(ID) as RowCount FROM sys_OperateLog";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if(objects!=null){
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        }
        return 0;
    }

    /*
 * 查询
 * */
    public List<OperateLog> findAll() {
        String sqlStr = "SELECT * FROM sys_OperateLog ORDER BY OperateDate DESC";
        return dbUtilsTemplate.find(OperateLog.class, sqlStr);
    }

    public String findAll(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM sys_OperateLog ORDER BY OperateDate DESC";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        String sSql1 = "SELECT ID,OperateDate,OperateIP,OperateUserName,OperateContent" +
                " FROM sys_OperateLog"
                + " WHERE OperateDate LIKE ? OR OperateIP LIKE ? OR OperateUserName LIKE ? OR OperateContent LIKE ?"
                + " ORDER BY OperateDate DESC LIMIT ?,?";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%", start, number);
        String[] strings1 = new String[]{"ID", "OperateDate", "OperateIP", "OperateUserName", "OperateContent"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        String sSql1 = "SELECT COUNT(ID) as RowCount" +
                " FROM sys_OperateLog"
                + " WHERE OperateDate LIKE ? OR OperateIP LIKE ? OR OperateUserName LIKE ? OR OperateContent LIKE ?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%");
        if(objects!=null){
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        }
        return 0;
    }
}
