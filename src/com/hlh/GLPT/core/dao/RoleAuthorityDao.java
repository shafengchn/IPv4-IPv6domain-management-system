package com.hlh.GLPT.core.dao;

import com.hlh.GLPT.core.domain.RoleAuthority;
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
public class RoleAuthorityDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /*
    * 添加
    * */
    public String add(RoleAuthority roleAuthority) {
        String sqlStr = "INSERT INTO CORE_RoleAuthority (RoleCode,AuthorityCode,CreateDate,CreateUserCode,CreateUserName)" +
                " VALUES(?,?,?,?,?)";
        Object[] objects = new Object[]{roleAuthority.getRoleCode(), roleAuthority.getAuthorityCode(), roleAuthority.getCreateDate(), roleAuthority.getCreateUserCode(), roleAuthority.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
  * 彻底删除 按RoleCode删除角色权限记录
  * */
    public String ThoroughDel(String RoleCode) {
        String sqlStr = "DELETE FROM CORE_RoleAuthority WHERE RoleCode=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{RoleCode});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
  * 查询 按RoleCode查询角色权限记录
  * */
    public List<RoleAuthority> FindRoleAuthority(String RoleCode) {
        String sqlStr = "SELECT * FROM CORE_RoleAuthority WHERE RoleCode=?";
        return dbUtilsTemplate.find(RoleAuthority.class, sqlStr, RoleCode);
    }

    /*
    * 查询 按RoleCode,AuthorityCode查询角色权限记录是否存在
    * */
    public boolean findRoleAuthorityIsCheck(String RoleCode, String AuthorityCode) {
        String sSql1 = "SELECT COUNT(RoleAuthorityId) as RowCount FROM CORE_RoleAuthority WHERE RoleCode=? AND AuthorityCode=?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, RoleCode, AuthorityCode);
        if (objects != null) {
            if (Integer.parseInt(objects[0].toString()) > 0) {
                return true;
            }
        }
        return false;
    }


}
