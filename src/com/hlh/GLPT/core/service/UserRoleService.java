package com.hlh.GLPT.core.service;

import com.hlh.GLPT.core.dao.UserRoleDao;
import com.hlh.GLPT.core.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-4-7
 * Time: 下午9:33
 */
@Service
public class UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    /*
* 添加
* */
    public String add(UserRole userRole){
        return userRoleDao.add(userRole);
    }

    /*
 * 彻底删除 按UserCode删除用户角色关系记录
 * */
    public String ThoroughDel(String UserCode) {
        return userRoleDao.ThoroughDel(UserCode);
    }

    /*
 * 查询 按UserCode查询用户角色关系记录
 * */
    public List<UserRole> FindUserRole(String UserCode) {
        return userRoleDao.FindUserRole(UserCode);
    }

    /*
  * 查询 按UserCode,RoleCode查询用户角色关系记录是否存在
  * */
    public boolean findUserRoleIsCheck(String UserCode,String RoleCode){
        return userRoleDao.findUserRoleIsCheck(UserCode,RoleCode);
    }
}
