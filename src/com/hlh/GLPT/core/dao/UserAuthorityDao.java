package com.hlh.GLPT.core.dao;

import com.hlh.GLPT.core.domain.UserAuthority;
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
public class UserAuthorityDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /*
    * 添加
    * */
    public String add(UserAuthority roleAuthority) {
        String sqlStr = "INSERT INTO CORE_UserAuthority (UserCode,AuthorityCode,CreateDate,CreateUserCode,CreateUserName)" +
                " VALUES(?,?,?,?,?)";
        Object[] objects = new Object[]{roleAuthority.getUserCode(), roleAuthority.getAuthorityCode(), roleAuthority.getCreateDate(), roleAuthority.getCreateUserCode(), roleAuthority.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
  * 彻底删除 按UserCode删除角色权限记录
  * */
    public String ThoroughDel(String UserCode) {
        String sqlStr = "DELETE FROM CORE_UserAuthority WHERE UserCode=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{UserCode});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
  * 查询 按UserCode查询用户权限记录
  * */
    public List<UserAuthority> FindUserAuthority(String UserCode) {
        String sqlStr = "SELECT * FROM CORE_UserAuthority WHERE UserCode=?";
        return dbUtilsTemplate.find(UserAuthority.class, sqlStr, UserCode);
    }

    /*
    * 查询 按UserCode,AuthorityCode查询用户权限记录是否存在
    * */
    public boolean findUserAuthorityIsCheck(String UserCode, String AuthorityCode) {
        String sSql1 = "SELECT COUNT(UserAuthorityId) as RowCount FROM CORE_UserAuthority WHERE UserCode=? AND AuthorityCode=?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, UserCode, AuthorityCode);
        if (objects != null) {
            if (Integer.parseInt(objects[0].toString()) > 0) {
                return true;
            }
        }
        return false;
    }


}
