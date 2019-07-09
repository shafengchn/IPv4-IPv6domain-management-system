package com.hlh.GLPT.core.dao;

import com.hlh.GLPT.core.domain.UserRole;
import com.hlh.util.DbUtilsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-4-7
 * Time: 下午9:21
 */
@Repository
public class UserRoleDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /*
    * 添加
    * */
    public String add(UserRole userRole) {
        String sqlStr = "INSERT INTO CORE_UserRole (UserCode,RoleCode,CreateDate,CreateUserCode,CreateUserName)" +
                " VALUES(?,?,?,?,?)";
        Object[] objects = new Object[]{userRole.getUserCode(), userRole.getRoleCode(), userRole.getCreateDate(), userRole.getCreateUserCode(), userRole.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
  * 彻底删除 按UserCode删除用户角色关系记录
  * */
    public String ThoroughDel(String UserCode) {
        String sqlStr = "DELETE FROM CORE_UserRole WHERE UserCode=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{UserCode});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
  * 查询 按UserCode查询用户角色关系记录
  * */
    public List<UserRole> FindUserRole(String UserCode) {
        String sqlStr = "SELECT * FROM CORE_UserRole WHERE UserCode=?";
        return dbUtilsTemplate.find(UserRole.class, sqlStr, UserCode);
    }

    /*
    * 查询 按UserCode,RoleCode查询用户角色关系记录是否存在
    * */
    public boolean findUserRoleIsCheck(String UserCode, String RoleCode) {
        String sSql1 = "SELECT COUNT(UserRoleId) as RowCount FROM CORE_UserRole WHERE UserCode=? AND RoleCode=? ";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, UserCode, RoleCode);
        if (objects != null) {
            if (Integer.parseInt(objects[0].toString()) > 0) {
                return true;
            }
        }
        return false;
    }


}
