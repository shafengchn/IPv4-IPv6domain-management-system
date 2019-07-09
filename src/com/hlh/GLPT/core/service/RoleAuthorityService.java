package com.hlh.GLPT.core.service;

import com.hlh.GLPT.core.dao.RoleAuthorityDao;
import com.hlh.GLPT.core.domain.RoleAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-7-21
 * Time: 下午8:35
 */
@Service
public class RoleAuthorityService {
    @Autowired
    private RoleAuthorityDao roleAuthorityDao;

    /*
* 添加
* */
    public String add(RoleAuthority roleAuthority) {
        return roleAuthorityDao.add(roleAuthority);
    }

    /*
  * 彻底删除 按RoleCode删除角色权限记录
  * */
    public String ThoroughDel(String RoleCode) {
        return roleAuthorityDao.ThoroughDel(RoleCode);
    }

    /*
  * 查询 按RoleCode查询角色权限记录
  * */
    public List<RoleAuthority> FindRoleAuthority(String RoleCode) {
        return roleAuthorityDao.FindRoleAuthority(RoleCode);
    }

    /*
    * 查询 按RoleCode,AuthorityCode查询角色权限记录是否存在
    * */
    public boolean findRoleAuthorityIsCheck(String RoleCode, String AuthorityCode) {
        return roleAuthorityDao.findRoleAuthorityIsCheck(RoleCode, AuthorityCode);
    }


}
