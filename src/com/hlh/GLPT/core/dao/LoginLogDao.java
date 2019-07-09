package com.hlh.GLPT.core.dao;

import com.hlh.GLPT.core.domain.LoginLog;
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
public class LoginLogDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /*
    * 增加
    * */
    public String add(LoginLog loginLog) {
        String sqlStr = "INSERT INTO sys_LoginLog (LoginDate,LoginIP,LoginUserName,UserType)" +
                " VALUES(?,?,?,?)";
        Object[] objects = new Object[]{loginLog.getLoginDate(),loginLog.getLoginIP(),loginLog.getLoginUserName(),loginLog.getUserType()};
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
        String sqlStr = "DELETE FROM sys_LoginLog  WHERE ID=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{ID});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
    * 按Id查询
    * */
    public LoginLog findById(String ID) {
        String sqlStr = "SELECT * FROM sys_LoginLog WHERE ID=?";
        return dbUtilsTemplate.findFirst(LoginLog.class, sqlStr, ID);
    }

    //获取总行数
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(ID) as RowCount FROM sys_LoginLog";
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
    public List<LoginLog> findAll() {
        String sqlStr = "SELECT * FROM sys_LoginLog ORDER BY LoginDate DESC";
        return dbUtilsTemplate.find(LoginLog.class, sqlStr);
    }

    public String findAll(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM sys_LoginLog ORDER BY LoginDate DESC";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        String sSql1 = "SELECT ID,LoginDate,LoginIP,LoginUserName,UserType" +
                " FROM sys_LoginLog"
                + " WHERE LoginDate LIKE ? OR LoginIP LIKE ? OR LoginUserName LIKE ?"
                + " ORDER BY LoginDate DESC LIMIT ?,?";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%", start, number);
        String[] strings1 = new String[]{"ID", "LoginDate", "LoginIP", "LoginUserName","UserType"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        String sSql1 = "SELECT COUNT(ID) as RowCount" +
                " FROM sys_LoginLog"
                + " WHERE LoginDate LIKE ? OR LoginIP LIKE ? OR LoginUserName LIKE ?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%");
        if(objects!=null){
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        }
        return 0;
    }
}
